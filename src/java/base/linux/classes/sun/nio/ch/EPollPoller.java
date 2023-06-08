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

package java.base.linux.classes.sun.nio.ch;

import java.io.IOException;
import static java.base.linux.classes.sun.nio.ch.EPoll.*;

/**
 * Poller implementation based on the epoll facility.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class EPollPoller extends Poller {
    private static final int MAX_EVENTS_TO_POLL = 512;
    private static final int ENOENT = 2;

    private final int epfd;
    private final int event;
    private final long address;

    EPollPoller(boolean read) throws IOException {
        super(read);
        this.epfd = EPoll.create();
        this.event = (read) ? EPOLLIN : EPOLLOUT;
        this.address = EPoll.allocatePollArray(MAX_EVENTS_TO_POLL);
    }

    @Override
    int fdVal() {
        return epfd;
    }

    @Override
    void implRegister(int fdVal) throws IOException {
        // re-arm
        int err = EPoll.ctl(epfd, EPOLL_CTL_MOD, fdVal, (event | EPOLLONESHOT));
        if (err == ENOENT)
            err = EPoll.ctl(epfd, EPOLL_CTL_ADD, fdVal, (event | EPOLLONESHOT));
        if (err != 0)
            throw new IOException("epoll_ctl failed: " + err);
    }

    @Override
    void implDeregister(int fdVal) {
        EPoll.ctl(epfd, EPOLL_CTL_DEL, fdVal, 0);
    }

    @Override
    int poll(int timeout) throws IOException {
        int n = EPoll.wait(epfd, address, MAX_EVENTS_TO_POLL, timeout);
        int i = 0;
        while (i < n) {
            long eventAddress = EPoll.getEvent(address, i);
            int fdVal = EPoll.getDescriptor(eventAddress);
            polled(fdVal);
            i++;
        }
        return n;
    }
}