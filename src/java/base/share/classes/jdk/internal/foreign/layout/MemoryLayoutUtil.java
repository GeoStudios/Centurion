/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.layout;

public final class MemoryLayoutUtil {

    private MemoryLayoutUtil() {
    }

    public static void checkSize(long size) {
        checkSize(size, false);
    }

    public static void checkSize(long size, boolean includeZero) {
        if (size < 0 || (!includeZero && size == 0)) {
            throw new IllegalArgumentException("Invalid size for layout: " + size);
        }
    }

}