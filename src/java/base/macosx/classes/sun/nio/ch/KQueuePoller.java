/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.sun.nio.ch;

import java.base.share.classes.java.io.IOException;
import static java.base.macosx.classes.sun.nio.ch.KQueue.*;

/**
 * Poller implementation based on the kqueue facility.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

class KQueuePoller extends Poller {
    private static final int MAX_EVENTS_TO_POLL = 512;

    private final int kqfd;
    private final int filter;
    private final long address;

    KQueuePoller(boolean read) throws IOException {
        super(read);
        this.kqfd = KQueue.create();
        this.filter = (read) ? EVFILT_READ : EVFILT_WRITE;
        this.address = KQueue.allocatePollArray(MAX_EVENTS_TO_POLL);
    }

    @Override
    int fdVal() {
        return kqfd;
    }

    @Override
    void implRegister(int fdVal) throws IOException {
        int err = KQueue.register(kqfd, fdVal, filter, (EV_ADD|EV_ONESHOT));
        if (err != 0)
            throw new IOException("kevent failed: " + err);
    }

    @Override
    void implDeregister(int fdVal) {
        KQueue.register(kqfd, fdVal, filter, EV_DELETE);
    }

    @Override
    int poll(int timeout) throws IOException {
        int n = KQueue.poll(kqfd, address, MAX_EVENTS_TO_POLL, timeout);
        int i = 0;
        while (i < n) {
            long keventAddress = KQueue.getEvent(address, i);
            int fdVal = KQueue.getDescriptor(keventAddress);
            polled(fdVal);
            i++;
        }
        return n;
    }
}
