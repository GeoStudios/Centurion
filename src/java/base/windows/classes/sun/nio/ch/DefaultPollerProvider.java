/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.nio.ch;

import java.io.IOException;

/**
 * Default PollerProvider for Windows based on wepoll.
 * 
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023 
 */
class DefaultPollerProvider extends PollerProvider {
    DefaultPollerProvider() { }

    @Override
    Poller readPoller() throws IOException {
        return new WEPollPoller(true);
    }

    @Override
    Poller writePoller() throws IOException {
        return new WEPollPoller(false);
    }
}