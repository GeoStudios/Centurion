/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.security.jgss.share.classes.sun.security.krb5.internal.util;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.java.io.java.io.java.io.IOException;















/**
 * This class implements a buffered input stream. It provides methods to read a chunck
 * of data from underlying data stream.
 *
 *
 */
public class KrbDataInputStream extends BufferedInputStream{
    private boolean bigEndian = true;

    public void setNativeByteOrder() {
        bigEndian = java.nio.ByteOrder.nativeOrder().
                equals(java.nio.ByteOrder.BIG_ENDIAN);
    }
    public KrbDataInputStream(InputStream is){
        super(is);
    }

    /**
     * Reads a length value which is represented in 4 bytes from
     * this input stream. The value must be positive.
     * @return the length value represented by this byte array.
     * @throws IOException if there are not enough bytes or it represents
     * a negative value
     */
    public final int readLength4() throws IOException {
        int len = read(4);
        if (len < 0) {
            throw new IOException("Invalid encoding");
        }
        return len;
    }

    /**
     * Reads up to the specific number of bytes from this input stream.
     * @param num the number of bytes to be read.
     * @return the int value of this byte array.
     * @throws IOException if there are not enough bytes
     */
    public int read(int num) throws IOException {
        byte[] bytes = new byte[num];
        if (read(bytes, 0, num) != num) {
            throw new IOException("Premature end of stream reached");
        }
        int result = 0;
        for (int i = 0; i < num; i++) {
            if (bigEndian) {
                result |= (bytes[i] & 0xff) << (num - i - 1) * 8;
            } else {
                result |= (bytes[i] & 0xff) << i * 8;
            }
        }
        return result;
    }

    public int readVersion() throws IOException {
        // always read in big-endian mode
        int result = (read() & 0xff) << 8;
        return result | (read() & 0xff);
    }
}
