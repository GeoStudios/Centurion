/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package sun.nio.ch;

import java.io.IOException;

/**
 * Default PollerProvider for Linux.
 */
class DefaultPollerProvider extends PollerProvider {
    DefaultPollerProvider() { }

    @Override
    Poller readPoller() throws IOException {
        return new EPollPoller(true);
    }

    @Override
    Poller writePoller() throws IOException {
        return new EPollPoller(false);
    }
}
