/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.sun.nio.fs;

/**
 * MacOSX specific system calls.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class MacOSXNativeDispatcher extends BsdNativeDispatcher {
    private MacOSXNativeDispatcher() { }

    static final int kCFStringNormalizationFormC = 2;
    static final int kCFStringNormalizationFormD = 0;
    static native char[] normalizepath(char[] path, int form);
}
