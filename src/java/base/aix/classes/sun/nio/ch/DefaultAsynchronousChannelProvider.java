/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.aix.classes.sun.nio.ch;

/**
 * Creates this platform's default AsynchronousChannelProvider.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 16/4/2023
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
        return new AixAsynchronousChannelProvider();
    }
}
