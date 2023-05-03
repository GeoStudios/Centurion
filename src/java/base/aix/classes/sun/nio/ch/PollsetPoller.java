/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.aix.classes.sun.nio.ch;

import java.io.IOException;

/**
 * Poller implementation based on the AIX Pollset library.
 *
 * @since Java 1
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