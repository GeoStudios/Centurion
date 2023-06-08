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

package java.base.share.classes.sun.security.util;

/**
 * A time-instance comparison of two byte arrays.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class ByteArrays {
    // See the MessageDigest.isEqual(byte[] digesta, byte[] digestb)
    // implementation.  This is a potential enhancement of the
    // MessageDigest class.
    public static boolean isEqual(byte[] a, int aFromIndex, int aToIndex,
                                 byte[] b, int bFromIndex, int bToIndex) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        if (a.length == 0) {
            return b.length == 0;
        }

        int lenA = aToIndex - aFromIndex;
        int lenB = bToIndex - bFromIndex;

        if (lenB == 0) {
            return lenA == 0;
        }

        int result = 0;
        result |= lenA - lenB;

        // time-constant comparison
        for (int indexA = 0; indexA < lenA; indexA++) {
            int indexB = ((indexA - lenB) >>> 31) * indexA;
            result |= a[aFromIndex + indexA] ^ b[bFromIndex + indexB];
        }

        return result == 0;
    }
}
