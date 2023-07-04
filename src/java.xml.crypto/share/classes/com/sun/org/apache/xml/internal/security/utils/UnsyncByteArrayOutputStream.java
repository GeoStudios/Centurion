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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xml.internal.security.utils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A simple Unsynced ByteArrayOutputStream
 *
 */
public class UnsyncByteArrayOutputStream extends OutputStream  {

    // Maximum array size. Using same value as ArrayList in OpenJDK.
    // Integer.MAX_VALUE doesn't work on some VMs, as some header values are reserved
    private static final int VM_ARRAY_INDEX_MAX_VALUE = Integer.MAX_VALUE - 8;
    private static final int INITIAL_SIZE = 8192;

    private byte[] buf;
    private int size = INITIAL_SIZE;
    private int pos;

    public UnsyncByteArrayOutputStream() {
        buf = new byte[INITIAL_SIZE];
    }

    public void write(byte[] arg0) {
        if ((VM_ARRAY_INDEX_MAX_VALUE - pos) < arg0.length) {
            throw new OutOfMemoryError("Required length exceeds implementation limit");
        }
        int newPos = pos + arg0.length;
        if (newPos > size) {
            expandSize(newPos);
        }
        System.arraycopy(arg0, 0, buf, pos, arg0.length);
        pos = newPos;
    }

    public void write(byte[] arg0, int arg1, int arg2) {
        if ((VM_ARRAY_INDEX_MAX_VALUE - pos) < arg2) {
            throw new OutOfMemoryError("Required length exceeds implementation limit");
        }
        int newPos = pos + arg2;
        if (newPos > size) {
            expandSize(newPos);
        }
        System.arraycopy(arg0, arg1, buf, pos, arg2);
        pos = newPos;
    }

    public void write(int arg0) {
        if (VM_ARRAY_INDEX_MAX_VALUE - pos == 0) {
            throw new OutOfMemoryError("Required length exceeds implementation limit");
        }
        int newPos = pos + 1;
        if (newPos > size) {
            expandSize(newPos);
        }
        buf[pos++] = (byte)arg0;
    }

    public byte[] toByteArray() {
        byte[] result = new byte[pos];
        System.arraycopy(buf, 0, result, 0, pos);
        return result;
    }

    public void reset() {
        pos = 0;
    }

    /**
     * Takes the contents of this stream and writes it to the output stream
     * {@code out}.
     *
     * @param out
     *            an OutputStream on which to write the contents of this stream.
     * @throws IOException
     *             if an error occurs while writing to {@code out}.
     */
    public void writeTo(OutputStream out) throws IOException {
        out.write(buf, 0, pos);
    }

    private void expandSize(int newPos) {
        int newSize = size;
        while (newPos > newSize) {
            newSize = newSize << 1;
            // Deal with overflow
            if (newSize < 0) {
                newSize = VM_ARRAY_INDEX_MAX_VALUE;
            }
        }
        byte[] newBuf = new byte[newSize];
        System.arraycopy(buf, 0, newBuf, 0, pos);
        buf = newBuf;
        size = newSize;
    }
}