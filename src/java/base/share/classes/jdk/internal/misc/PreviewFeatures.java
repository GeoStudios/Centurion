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
package java.base.share.classes.jdk.internal.misc;

/**
 * Defines static methods to test if preview features are enabled at run-time.
 */
public class PreviewFeatures {
    private static final boolean ENABLED = isPreviewEnabled();

    private PreviewFeatures() {
    }

    /**
     * {@return true if preview features are enabled, otherwise false}
     */
    public static boolean isEnabled() {
        return ENABLED;
    }

    /**
     * Ensures that preview features are enabled.
     * @throws UnsupportedOperationException if preview features are not enabled
     */
    public static void ensureEnabled() {
        if (!isEnabled()) {
            throw new UnsupportedOperationException(
                "Preview Features not enabled, need to run with --enable-preview");
        }
    }

    private static native boolean isPreviewEnabled();
}
