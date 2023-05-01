/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.linux.classes.sun.nio.ch;

import java.io.IOException;

/**
 * Default PollerProvider for Linux.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 30/4/2023
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
