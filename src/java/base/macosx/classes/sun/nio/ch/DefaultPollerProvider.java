/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
//package java.base.macosx.classes.sun.nio.ch;

package java.base.macosx.classes.sun.nio.ch;

import java.base.share.classes.java.io.IOException;

/**
 * Default PollerProvider for macOS.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
class DefaultPollerProvider extends PollerProvider {
    DefaultPollerProvider() { }

    @Override
    Poller readPoller() throws IOException {
        return new KQueuePoller(true);
    }

    @Override
    Poller writePoller() throws IOException {
        return new KQueuePoller(false);
    }
}