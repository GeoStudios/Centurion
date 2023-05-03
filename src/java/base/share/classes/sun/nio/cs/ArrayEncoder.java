/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.cs;

/**
 * FastPath char[]/byte[] -> byte[] encoder, REPLACE on malformed input or
 * unmappable input.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public interface ArrayEncoder {

    //  is only used by j.u.zip.ZipCoder for utf8
    int encode(char[] src, int off, int len, byte[] dst);

    default int encodeFromLatin1(byte[] src, int sp, int len, byte[] dst) {
        return -1;
    }

    default int encodeFromUTF16(byte[] src, int sp, int len, byte[] dst) {
        return -1;
    }

    default boolean isASCIICompatible() {
        return false;
    }
}
