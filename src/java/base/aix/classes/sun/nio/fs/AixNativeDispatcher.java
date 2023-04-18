/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.aix.classes.sun.nio.fs;

/**
 * AIX specific system calls.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 17/4/2023
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
        jdk.internal.loader.BootLoader.loadLibrary("nio");
        init();
    }
}
