/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.windows.classes.sun.net;

/**
 * Determines the ephemeral port range in use on this system.
 * On Windows we always use the default range.
 *
 * @since Alpha cdk-1.0
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