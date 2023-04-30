/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.sun.nio.ch;

import java.base.share.classes.java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.base.share.classes.java.nio.channels.SelectionKey;
import java.base.share.classes.java.nio.channels.Selector;
import java.base.share.classes.java.nio.channels.spi.SelectorProvider;
import java.base.share.classes.java.util.ArrayDeque;
import java.base.share.classes.java.util.Deque;
import java.base.share.classes.java.util.HashMap;
import java.base.share.classes.java.util.Map;
import java.base.share.classes.java.util.concurrent.TimeUnit;
import java.base.share.classes.java.util.function.Consumer;
import java.base.share.classes.jdk.internal.misc.Blocker;

import static java.base.macosx.classes.sun.nio.ch.KQueue.EVFILT_READ;
import static java.base.macosx.classes.sun.nio.ch.KQueue.EVFILT_WRITE;
import static java.base.macosx.classes.sun.nio.ch.KQueue.EV_ADD;
import static java.base.macosx.classes.sun.nio.ch.KQueue.EV_DELETE;

/**
 * KQueue based Selector implementation for macOS
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class KQueueSelectorImpl extends SelectorImpl {

    // maximum number of events to poll in one call to kqueue
    private static final int MAX_KEVENTS = 256;

    // kqueue file descriptor
    private final int kqfd;

    // address of poll array (event list) when polling for pending events
    private final long pollArrayAddress;

    // file descriptors used for interrupt
    private final int fd0;
    private final int fd1;

    // maps file descriptor to selection key, synchronize on selector
    private final Map<Integer, SelectionKeyImpl> fdToKey = new HashMap<>();

    // pending new registrations/updates, queued by setEventOps
    private final Object updateLock = new Object();
    private final Deque<SelectionKeyImpl> updateKeys = new ArrayDeque<>();

    // interrupt triggering and clearing
    private final Object interruptLock = new Object();
    private boolean interruptTriggered;

    // used by updateSelectedKeys to handle cases where the same file
    // descriptor is polled by more than one filter
    private int pollCount;

    KQueueSelectorImpl(SelectorProvider sp) throws IOException {
        super(sp);

        this.kqfd = KQueue.create();
        this.pollArrayAddress = KQueue.allocatePollArray(MAX_KEVENTS);

        try {
            long fds = IOUtil.makePipe(false);
            this.fd0 = (int) (fds >>> 32);
            this.fd1 = (int) fds;
        } catch (IOException ioe) {
            KQueue.freePollArray(pollArrayAddress);
            FileDispatcherImpl.closeIntFD(kqfd);
            throw ioe;
        }

        // register one end of the socket pair for wakeups
        KQueue.register(kqfd, fd0, EVFILT_READ, EV_ADD);
    }

    private void ensureOpen() {
        if (!isOpen())
            throw new ClosedSelectorException();
    }

    @Override
    protected int doSelect(Consumer<SelectionKey> action, long timeout)
        throws IOException
    {
        assert Thread.holdsLock(this);

        long to = Math.min(timeout, Integer.MAX_VALUE);  // max kqueue timeout
        boolean blocking = (to != 0);
        boolean timedPoll = (to > 0);

        int numEntries;
        processUpdateQueue();
        processDeregisterQueue();
        try {
            begin(blocking);

            do {
                long startTime = timedPoll ? System.nanoTime() : 0;
                long comp = Blocker.begin(blocking);
                try {
                    numEntries = KQueue.poll(kqfd, pollArrayAddress, MAX_KEVENTS, to);
                } finally {
                    Blocker.end(comp);
                }
                if (numEntries == IOStatus.INTERRUPTED && timedPoll) {
                    // timed poll interrupted so need to adjust timeout
                    long adjust = System.nanoTime() - startTime;
                    to -= TimeUnit.NANOSECONDS.toMillis(adjust);
                    if (to <= 0) {
                        // timeout expired so no retry
                        numEntries = 0;
                    }
                }
            } while (numEntries == IOStatus.INTERRUPTED);
            assert IOStatus.check(numEntries);

        } finally {
            end(blocking);
        }
        processDeregisterQueue();
        return processEvents(numEntries, action);
    }

    /**
     * Process changes to the interest ops.
     */
    private void processUpdateQueue() {
        assert Thread.holdsLock(this);

        synchronized (updateLock) {
            SelectionKeyImpl ski;
            while ((ski = updateKeys.pollFirst()) != null) {
                if (ski.isValid()) {
                    int fd = ski.getFDVal();
                    // add to fdToKey if needed
                    SelectionKeyImpl previous = fdToKey.putIfAbsent(fd, ski);
                    assert (previous == null) || (previous == ski);

                    int newEvents = ski.translateInterestOps();
                    int registeredEvents = ski.registeredEvents();

                    // DatagramChannelImpl::disconnect has reset socket
                    if (ski.getAndClearReset() && registeredEvents != 0) {
                        KQueue.register(kqfd, fd, EVFILT_READ, EV_DELETE);
                        registeredEvents = 0;
                    }

                    if (newEvents != registeredEvents) {

                        // add or delete interest in read events
                        if ((registeredEvents & Net.POLLIN) != 0) {
                            if ((newEvents & Net.POLLIN) == 0) {
                                KQueue.register(kqfd, fd, EVFILT_READ, EV_DELETE);
                            }
                        } else if ((newEvents & Net.POLLIN) != 0) {
                            KQueue.register(kqfd, fd, EVFILT_READ, EV_ADD);
                        }

                        // add or delete interest in write events
                        if ((registeredEvents & Net.POLLOUT) != 0) {
                            if ((newEvents & Net.POLLOUT) == 0) {
                                KQueue.register(kqfd, fd, EVFILT_WRITE, EV_DELETE);
                            }
                        } else if ((newEvents & Net.POLLOUT) != 0) {
                            KQueue.register(kqfd, fd, EVFILT_WRITE, EV_ADD);
                        }

                        ski.registeredEvents(newEvents);
                    }
                }
            }
        }
    }

    /**
     * Process the polled events.
     * If the interrupt fd has been selected, drain it and clear the interrupt.
     */
    private int processEvents(int numEntries, Consumer<SelectionKey> action)
        throws IOException
    {
        assert Thread.holdsLock(this);

        int numKeysUpdated = 0;
        boolean interrupted = false;

        // A file descriptor may be registered with kqueue with more than one
        // filter and so there may be more than one event for a fd. The poll
        // count is incremented here and compared against the SelectionKey's
        // "lastPolled" field. This ensures that the ready ops is updated rather
        // than replaced when a file descriptor is polled by both the read and
        // write filter.
        pollCount++;

        for (int i = 0; i < numEntries; i++) {
            long kevent = KQueue.getEvent(pollArrayAddress, i);
            int fd = KQueue.getDescriptor(kevent);
            if (fd == fd0) {
                interrupted = true;
            } else {
                SelectionKeyImpl ski = fdToKey.get(fd);
                if (ski != null) {
                    int rOps = 0;
                    short filter = KQueue.getFilter(kevent);
                    if (filter == EVFILT_READ) {
                        rOps |= Net.POLLIN;
                    } else if (filter == EVFILT_WRITE) {
                        rOps |= Net.POLLOUT;
                    }
                    int updated = processReadyEvents(rOps, ski, action);
                    if (updated > 0 && ski.lastPolled != pollCount) {
                        numKeysUpdated++;
                        ski.lastPolled = pollCount;
                    }
                }
            }
        }

        if (interrupted) {
            clearInterrupt();
        }
        return numKeysUpdated;
    }

    @Override
    protected void implClose() throws IOException {
        assert !isOpen();
        assert Thread.holdsLock(this);

        // prevent further wakeup
        synchronized (interruptLock) {
            interruptTriggered = true;
        }

        FileDispatcherImpl.closeIntFD(kqfd);
        KQueue.freePollArray(pollArrayAddress);

        FileDispatcherImpl.closeIntFD(fd0);
        FileDispatcherImpl.closeIntFD(fd1);
    }

    @Override
    protected void implDereg(SelectionKeyImpl ski) throws IOException {
        assert !ski.isValid();
        assert Thread.holdsLock(this);

        int fd = ski.getFDVal();
        int registeredEvents = ski.registeredEvents();
        if (fdToKey.remove(fd) != null) {
            if (registeredEvents != 0) {
                if ((registeredEvents & Net.POLLIN) != 0)
                    KQueue.register(kqfd, fd, EVFILT_READ, EV_DELETE);
                if ((registeredEvents & Net.POLLOUT) != 0)
                    KQueue.register(kqfd, fd, EVFILT_WRITE, EV_DELETE);
                ski.registeredEvents(0);
            }
        } else {
            assert registeredEvents == 0;
        }
    }

    @Override
    public void setEventOps(SelectionKeyImpl ski) {
        ensureOpen();
        synchronized (updateLock) {
            updateKeys.addLast(ski);
        }
    }

    @Override
    public Selector wakeup() {
        synchronized (interruptLock) {
            if (!interruptTriggered) {
                try {
                    IOUtil.write1(fd1, (byte)0);
                } catch (IOException ioe) {
                    throw new InternalError(ioe);
                }
                interruptTriggered = true;
            }
        }
        return this;
    }

    private void clearInterrupt() throws IOException {
        synchronized (interruptLock) {
            IOUtil.drain(fd0);
            interruptTriggered = false;
        }
    }
}
