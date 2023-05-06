/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.linux.classes.sun.nio.ch;

import java.nio.channels.spi.AsynchronousChannelProvider;

/**
 * Creates this platform's default AsynchronousChannelProvider
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public class DefaultAsynchronousChannelProvider {

    /**
     * Prevent instantiation.
     */
    private DefaultAsynchronousChannelProvider() { }

    /**
     * Returns the default AsynchronousChannelProvider.
     */
    public static AsynchronousChannelProvider create() {
        return new LinuxAsynchronousChannelProvider();
    }
}
