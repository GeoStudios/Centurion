/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
//package sun.nio.ch;
package java.base.macosx.classes.sun.nio.ch;

import java.io.IOException;

/**
 * Default PollerProvider for macOS.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
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