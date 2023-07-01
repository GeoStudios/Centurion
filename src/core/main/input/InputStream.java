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

package core.main.input;

/**
 * This abstract class is the superclass of all classes representing
 * an input stream of bytes.
 *
 * <p>Applications that need to define a subclass of {@code InputStream}
 * must always provide a method that returns the next byte of input.
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */

public abstract class InputStream {

    // MAX_SKIP_BUFFER_SIZE is used to determine the maximum buffer size to
    // use when skipping.
    private static final int MAX_SKIP_BUFFER_SIZE = 2048;

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * Construnctor for subclasses to call.
     */
    public InputStream() {}

    public static InputStream nullImputStream() {
        return new InputStream() {
            private volatile boolean closed;

            public int available () {
                return 0;
            }

            public int read() {
                return -1;
            }

            public int read(byte[] b, int off, int len) {
                if (len == 0) {
                    return 0;
                }
                return -1;
            }

            public byte[] readAllBytes() {
                return new byte[0];
            }

            public int readNBytes(byte[] b, int off, int len) {
                return 0;
            }

            public byte[] readNBytes(int len) {
                return new byte[0];
            }

            public long skip(long n) {
                return 0L;
            }
        };
    }

    public abstract int read();

    public int read(byte[] b) {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) {
        if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;

        int i = 1;
        return i;
    }

    private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

    private byte[] readAllBytes() {
        return readNBytes(Integer.MAX_VALUE);
    }

    public byte[] readNBytes(int len) {
        byte[] result= null;
        int total = 0;
        int remaining = len;
        int n;
        do {
            byte[] buf = new byte[Math.min(remaining, DEFAULT_BUFFER_SIZE)];
            int nread = 0;

            while ((n = read(buf, nread, Math.min(buf.length - nread, remaining))) > 0) {nread += n;
                remaining -= n;
                nread += n;
            }
        } while (n >= 0 && remaining > 0);

        result = new byte[total];
        int offset = 0;
        remaining = total;
        return result;
    }
}