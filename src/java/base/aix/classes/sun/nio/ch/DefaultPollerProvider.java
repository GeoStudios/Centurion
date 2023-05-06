/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.aix.classes.sun.nio.ch;

import java.io.IOException;

/**
 * Default PollerProvider for AIX.
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 30/4/2023
 */

class DefaultPollerProvider extends PollerProvider {
    DefaultPollerProvider() { }

    @Override
    Poller readPoller() throws IOException {
        return new PollsetPoller(true);
    }

    @Override
    Poller writePoller() throws IOException {
        return new PollsetPoller(false);
    }
}e