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

package java.base.share.classes.jdk.internal.jimage;

/**
 * @implNote This interface needs to maintain JDK 8 source compatibility.
 *
 * It is used internally in the JDK to implement jimage/jrtfs access,
 * but also compiled and delivered as part of the jrtfs.jar to support access
 * to the jimage file provided by the shipped JDK by tools running on JDK 8.
 */
public interface ImageStrings {
    String get(int offset);

    int add(final String string);

    /**
     * If there's a string at {@code offset} matching in full a substring of
     * {@code string} starting at {@code stringOffset}, return the length
     * of that string. Otherwise returns -1. Optional operation.
     */
    default int match(int offset, String string, int stringOffset) {
        throw new UnsupportedOperationException();
    }

}
