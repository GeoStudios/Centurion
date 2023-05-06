/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

public class IOUtils {

    /**
     * Read exactly {@code length} of bytes from {@code in}.
     *
     * <p> Note that this method is safe to be called with unknown large
     * {@code length} argument. The memory used is proportional to the
     * actual bytes available. An exception is thrown if there are not
     * enough bytes in the stream.
     *
     * @param is input stream, must not be null
     * @param length number of bytes to read
     * @return bytes read
     * @throws EOFException if there are not enough bytes in the stream
     * @throws IOException if an I/O error occurs or {@code length} is negative
     * @throws OutOfMemoryError if an array of the required size cannot be
     *         allocated.
     *         
     * 
     */
    public static byte[] readExactlyNBytes(InputStream is, int length)
            throws IOException {
        if (length < 0) {
            throw new IOException("length cannot be negative: " + length);
        }
        byte[] data = is.readNBytes(length);
        if (data.length < length) {
            throw new EOFException();
        }
        return data;
    }
}
