/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.vm;

/**
 * Defines a static method to test if the VM has continuations support.
 */
public class ContinuationSupport {
    private static final boolean SUPPORTED = isSupported0();

    private ContinuationSupport() {
    }

    /**
     * Return true if the VM has continuations support.
     */
    public static boolean isSupported() {
        return SUPPORTED;
    }

    /**
     * Ensures that VM has continuations support.
     * @throws UnsupportedOperationException if not supported
     */
    public static void ensureSupported() {
        if (!isSupported()) {
            throw new UnsupportedOperationException("VM does not support continuations");
        }
    }

    private static native boolean isSupported0();
}
