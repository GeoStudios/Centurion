/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.nio.ch;

import java.nio.channels.spi.AsynchronousChannelProvider;

/**
 * Creates this platform's default asynchronous channel provider
 * 
 * @since Java 1
 * @author Logan Abernathy
 * @edited 19/4/2023 
 */

public class DefaultAsynchronousChannelProvider {
    private DefaultAsynchronousChannelProvider() { }

    /**
     * Returns the default AsynchronousChannelProvider.
     */
    public static AsynchronousChannelProvider create() {
        return new WindowsAsynchronousChannelProvider();
    }
}