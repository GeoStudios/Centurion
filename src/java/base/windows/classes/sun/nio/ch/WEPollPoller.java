/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.nio.ch;

import java.io.IOException;
import static sun.nio.ch.WEPoll.*;

/**
 * Poller implementation based on wepoll.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 19/4/2023 
 */
class WEPollPoller extends Poller {
    private static final int MAX_EVENTS_TO_POLL = 256;
    private static final int ENOENT = 2;

    private final long handle;
    private final int event;
    private final long address;

    WEPollPoller(boolean read) throws IOException {
        super(read);
        this.handle = WEPoll.create();
        this.event = (read) ? EPOLLIN : EPOLLOUT;
        this.address = WEPoll.allocatePollArray(MAX_EVENTS_TO_POLL);
    }

    @Override
    void implRegister(int fdVal) throws IOException {
        // re-arm
        int err = WEPoll.ctl(handle, EPOLL_CTL_MOD, fdVal, (event | EPOLLONESHOT));
        if (err == ENOENT)
            err = WEPoll.ctl(handle, EPOLL_CTL_ADD, fdVal, (event | EPOLLONESHOT));
        if (err != 0)
            throw new IOException("epoll_ctl failed: " + err);
    }

    @Override
    void implDeregister(int fdVal) {
        WEPoll.ctl(handle, EPOLL_CTL_DEL, fdVal, 0);
    }

    @Override
    int poll(int timeout) throws IOException {
        int n = WEPoll.wait(handle, address, MAX_EVENTS_TO_POLL, timeout);
        int i = 0;
        while (i < n) {
            long event = WEPoll.getEvent(address, i);
            int fdVal = WEPoll.getDescriptor(event);
            polled(fdVal);
            i++;
        }
        return n;
    }
}