/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.windows.classes.sun.nio.ch;

import java.io.IOException;
import static java.base.windows.classes.sun.nio.ch.WEPoll.*;

/**
 * Poller implementation based on wepoll.
 * 
 * @since Alpha cdk-1.0
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