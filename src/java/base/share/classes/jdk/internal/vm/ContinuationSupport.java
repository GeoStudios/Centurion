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
