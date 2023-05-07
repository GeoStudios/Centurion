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

package java.base.aix.classes.sun.nio.ch;

import java.io.IOException;

/**
 * Poller implementation based on the AIX Pollset library.
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 16/4/2023
 */

class PollsetPoller extends Poller {

    PollsetPoller(boolean read) throws IOException {
        super(read);
    }

    @Override
    int fdVal() {
        // Stub
        throw new UnsupportedOperationException("Unimplemented on AIX");
    }

    @Override
    void implRegister(int fdVal) throws IOException {
        // Stub
        throw new UnsupportedOperationException("Unimplemented on AIX");
    }

    @Override
    void implDeregister(int fdVal) {
        // Stub
        throw new UnsupportedOperationException("Unimplemented on AIX");
    }

    @Override
    int poll(int timeout) throws IOException {
        // Stub
        throw new UnsupportedOperationException("Unimplemented on AIX");
    }
}