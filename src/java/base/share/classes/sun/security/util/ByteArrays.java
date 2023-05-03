/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

/**
 * A time-instance comparison of two byte arrays.
 * 
 * @since Java 2
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
