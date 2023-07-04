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

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UnsyncBufferedOutputStream extends FilterOutputStream {

    protected byte[] buffer;
    protected int count;

    public UnsyncBufferedOutputStream(OutputStream out) {
        super(out);
        buffer = new byte[8192];
    }

    public UnsyncBufferedOutputStream(OutputStream out, int size) {
        super(out);
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }
        buffer = new byte[size];
    }

    @Override
    public void flush() throws IOException {
        flushInternal();
        out.flush();
    }

    @Override
    public void write(byte[] bytes, int offset, int length) throws IOException {
        if (length >= buffer.length) {
            flushInternal();
            out.write(bytes, offset, length);
            return;
        }

        // flush the internal buffer first if we have not enough space left
        if (length >= (buffer.length - count)) {
            flushInternal();
        }

        // the length is always less than (internalBuffer.length - count) here so arraycopy is safe
        System.arraycopy(bytes, offset, buffer, count, length);
        count += length;
    }

    @Override
    public void write(int oneByte) throws IOException {
        if (count == buffer.length) {
            out.write(buffer, 0, count);
            count = 0;
        }
        buffer[count++] = (byte) oneByte;
    }

    private void flushInternal() throws IOException {
        if (count > 0) {
            out.write(buffer, 0, count);
            count = 0;
        }
    }
}
