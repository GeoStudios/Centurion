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
package java.base.share.classes.jdk.internal.platform;

/*
 * @author bobv
 * @since 11
 */

public class Container {

    private Container() { }

    /**
     * Returns the platform specific Container Metrics class or
     * null if not supported on this platform.
     *
     * @return Metrics instance or null if not supported
     */
    public static Metrics metrics() {
        return Metrics.systemMetrics();
    }
}
