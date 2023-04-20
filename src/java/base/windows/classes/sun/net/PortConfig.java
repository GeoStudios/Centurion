/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.net;

/**
 * Determines the ephemeral port range in use on this system.
 * On Windows we always use the default range.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 19/4/2023
 */

public final class PortConfig {

    private static final int defaultLower = 49152;
    private static final int defaultUpper = 65535;

    public static int getLower() {
        return defaultLower;
    }

    public static int getUpper() {
        return defaultUpper;
    }
}