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

package java.base.aix.classes.sun.nio.fs;

/**
 * AIX specific system calls.
 * 
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 30/4/2023
 */

class AixNativeDispatcher extends UnixNativeDispatcher {
    private AixNativeDispatcher() { }

    /**
     * Special implementation of 'getextmntent' (see SolarisNativeDispatcher)
     * that returns all entries at once.
     */
    static native UnixMountEntry[] getmntctl() throws UnixException;

    // initialize
    private static native void init();

    static {
        java.base.share.classes.jdk.internal.loader.BootLoader.loadLibrary("nio");
        init();
    }
}
