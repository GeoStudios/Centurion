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

import java.io.IOException;

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

    /**
     * Returns a new {@code InputStream} that reads no bytes. The returned
     * stream is initially open. The stream is closed by calling the
     * {@code close()} method.
     *
     * <p> While the stream is open, the {@code available()}, {@code read()},
     * {@code read(byte[])}, {@code read(byte[], int, int)},
     * {@code readAllBytes()}, {@code readNBytes(byte[], int, int)},
     * {@code readNBytes(int)}, {@code skip(long)}, {@code skipNBytes(long)},
     * and {@code transferTo()} methods all behave as if end of stream has been
     * reached.
     *
     * <p> The {@code markSupported()} method returns {@code false}.  The
     * {@code mark()} method does nothing, and the {@code reset()} method
     * throws {@code IOException}.
     *
     * @return an {@code InputStream} which contains no bytes
     */
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

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an {@code int} in the range {@code 0} to {@code 255}. If no
     * byte is available because the end of the stream has been reached, the
     * value {@code -1} is returned. This method blocks until input data is
     * available, the end of the stream is detected, or an exception is thrown.
     *
     * @return     the next byte of data, or {@code -1} if the end of the
     *             stream is reached.
     */
    public abstract int read();

    /**
     * Reads some number of bytes from the input stream and stores them into
     * the buffer array {@code b}. The number of bytes actually read is
     * returned as an integer.
     *
     * <p> If the length of {@code b} is zero, then no bytes are read and
     * {@code 0} is returned; otherwise, there is an attempt to read at
     * least one byte. If no byte is available because the stream is at the
     * end of the file, the value {@code -1} is returned; otherwise, at
     * least one byte is read and stored into {@code b}.
     *
     * <p> The first byte read is stored into element {@code b[0]}, the
     * next one into {@code b[1]}, and so on. The number of bytes read is,
     * at most, equal to the length of {@code b}. Let <i>k</i> be the
     * number of bytes actually read; these bytes will be stored in elements
     * {@code b[0]} through {@code b[}<i>k</i>{@code -1]},
     * leaving elements {@code b[}<i>k</i>{@code ]} through
     * {@code b[b.length-1]} unaffected.
     *
     * @implSpec
     * The {@code read(b)} method for class {@code InputStream}
     * has the same effect as: <pre>{@code  read(b, 0, b.length) }</pre>
     *
     * @param      b   the buffer into which the data is read.
     * @return     the total number of bytes read into the buffer, or
     *             {@code -1} if there is no more data because the end of
     *             the stream has been reached.
     * @see        InputStream#read(byte[], int, int)
     */
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

    public long skip(long n) {
        long remaining = n;
        int nr;

        if (n <= 0) {
            return 0;
        }

        int size = (int)Math.min(MAX_SKIP_BUFFER_SIZE, remaining);
        byte[] skipBuffer = new byte[size];
        while (remaining > 0) {
            nr = read(skipBuffer, 0, (int)Math.min(size, remaining));
            if (nr < 0) {
                break;
            }
            remaining -= nr;
        }
        return n - remaining;
    }

    public void skipNBytes(long n) {
        while (n > 0) {
            long ns = skip(n);
            if (ns > 0 && ns <= n) {
                // adjust number to skip
                n -= ns;
            } else if (ns == 0) {
                n--;
            }
        }
    }

    public int available() {
        return 0;
    }

    public void close() {}

    public boolean markSupported() {
        return false;
    }
}