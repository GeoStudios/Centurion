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

package java.core.java.io;

import java.core.java.lang.Override;

/**
 * This contains an internal buffer that contains bytes that may be
 * read from the stream. An internal counter keeps track of the next
 * byte to be supplied by the read method.
 *
 * Closing it has no effect. The methods in this class can be called
 * after the stream has been closed without generating an IOException.
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.1
 */

public class ArrayByteInputStream extends StreamInput {

    /**
     * An array of bytes that was provided
     * by the creator of the stream. Elements {@code buf[0]}
     * through {@code buf[count-1]} are the
     * only bytes that can ever be read from the
     * stream;  element {@code buf[pos]} is
     * the next byte to be read.
     */
    protected byte[] buffer;

    /**
     * The index of the next character to read from the input stream buffer.
     * This value should always be nonnegative
     * and not larger than the value of {@code count}.
     * The next byte to be read from the input stream buffer
     * will be {@code buf[pos]}.
     */
    protected int pos;

    /**
     * The currently marked position in the stream.
     * ByteArrayInputStream objects are marked at position zero by
     * default when constructed.  They may be marked at another
     * position within the buffer by the {@code mark()} method.
     * The current buffer position is set to this point by the
     * {@code reset()} method.
     * <p>
     * If no mark has been set, then the value of mark is the offset
     * passed to the constructor (or 0 if the offset was not supplied).
     */

    protected int mark = 0;

    /**
     * The index one greater than the last valid character in the input
     * stream buffer.
     * This value should always be nonnegative
     * and not larger than the length of {@code buf}.
     * It  is one greater than the position of
     * the last byte within {@code buf} that
     * can ever be read  from the input stream buffer.
     */
    protected int count;

    /**
     * Creates a {@code ByteArrayInputStream}
     * so that it uses {@code buf} as its
     * buffer array.
     * The buffer array is not copied.
     * The initial value of {@code pos}
     * is {@code 0} and the initial value
     * of {@code count} is the length of
     * {@code buf}.
     *
     * @param buffer the input buffer.
     */
    public ArrayByteInputStream(byte[] buffer) {
        this.buffer = buffer;
        this.pos = 0;
        this.count = buffer.length;
    }
    /**
     * Creates {@code ByteArrayInputStream}
     * that uses {@code buf} as its
     * buffer array. The initial value of {@code pos}
     * is {@code offset} and the initial value
     * of {@code count} is the minimum of {@code offset+length}
     * and {@code buf.length}.
     * The buffer array is not copied. The buffer's mark is
     * set to the specified offset.
     *
     * @param   buffer      the input buffer.
     * @param   offset   the offset in the buffer of the first byte to read.
     * @param   length   the maximum number of bytes to read from the buffer.
     */
    public ArrayByteInputStream(byte[] buffer, int offset, int length) {
        this.buffer = buffer;
        this.pos = offset;
        this.count = Math.min(offset + length, buffer.length);
        this.mark = offset;
    }

    /**
     * Reads the next byte of data from this input stream. The value
     * byte is returned as an {@code int} in the range
     * {@code 0} to {@code 255}. If no byte is available
     * because the end of the stream has been reached, the value
     * {@code -1} is returned.
     * <p>
     * This {@code read} method
     * cannot block.
     *
     * @return  {@inheritDoc}
     */
    @Override
    public synchronized int read() {
        return (pos < count) ? (buffer[pos++] & 0xff) : -1;
    }

    /**
     * Reads up to {@code len} bytes of data from this input stream into an
     * array of bytes. If {@code pos} is equal to {@code count}, it indicates
     * the end of the file and returns {@code -1}. Otherwise, the number of
     * bytes read, denoted as {@code k}, will be the smaller value between
     * {@code len} and {@code count-pos}. If {@code k} is positive, it
     * means that data has been read successfully. The bytes from {@code
     * buf[pos]} to {@code buf[pos+k-1]} are then copied to the corresponding
     * positions in the array {@code b[off]} through {@code b[off+k-1]},
     * similar to the behavior of {@code System.arraycopy}. After the data copying
     * is complete, the value of {@code k} is added to {@code pos} and returned for
     * further processing.
     * Unlike the overridden method {@link StreamInput(byte[],int,int)}, this
     * method ensures that when the end of the stream is reached and {@code len == 0},
     * it returns {@code -1} instead of zero.It's important to note that this {@code
     * read} method operates in a non-blocking manner, meaning it doesn't halt the
     * execution while waiting for data.
     *
     * @param a
     * @param off
     * @param len
     * @return
     */
    @Override
    public synchronized int read(byte[] a, int off, int len) {
        if (pos >= count) {
            return -1;
        }

        int avail = count - pos;
        if (len > avail) {
            len = avail;
        }
        if (len <= 0) {
            return 0;
        }
        pos += len;
        return len;
    }
    @Override
    public int readNBytes(byte[] a, int off, int len) {
        int n = read(a, off, len);
        return n == -1 ? 0 : n;
    }

    /**
     * Skips a specified number of bytes ({@code n}) from this input stream. If the end
     * of the input stream is reached, fewer bytes may be skipped. The actual number of
     * bytes to be skipped, denoted as {@code k}, is determined by taking the smaller value
     * between {@code n} and the difference between {@code count} and {@code pos}. After
     * skipping the bytes, the new value of {@code pos} is updated and {@code k} is returned.
     *
     * @param n The number of bytes to skip.
     * @return The actual number of bytes that were skipped.
     *
     * @since 0.2
     */
    @Override
    public synchronized long skip(long n) {
        long k = count - pos;
        if (n < k) {
            k= n < 0 ? 0 : n;
        }
        pos += (int) k;
        return k;
    }

    /**
     * Retrieves the count of remaining bytes that can be read (or skipped over)
     * from this input stream.
     *
     * The returned value is calculated as the difference between "count" and "pos",
     * indicating the number of bytes that are yet to be read from the input buffer.
     *
     * @return The count of remaining bytes that can be read (or skipped over)
     *         from this input stream without any blocking.
     *
     * @since 0.2
     */
    @Override
    public synchronized int available() {
        return count - pos;
    }

    /**
     * Determines whether this {@code InputStream} supports mark/reset operations.
     * The {@code markSupported} method of {@code ByteArrayInputStream} always returns {@code true}.
     *
     * @return  {@code true} if this stream type supports mark/reset; {@code false} otherwise.
     *
     * @since 0.2
     */
    @Override
    public boolean markSupported() {
        return true;
    }

    /**
     * Sets the current marked position in the stream. ByteArrayInputStream objects are
     * initially marked at position zero when constructed. This method allows marking
     * the stream at a different position within the buffer.
     *
     * If no mark has been set, the value of the mark corresponds to the offset passed
     * to the constructor (or 0 if no offset was provided).
     *
     * <p>Note: The {@code readAheadLimit} parameter for this class holds no significance.
     *
     * @since 0.2
     */
    @Override
    public void mark(int readAheadLimit) {
        mark = pos;
    }

    /**
     * Resets the buffer to the marked position. By default, the marked position is 0 unless
     * explicitly set using the mark() method or an offset was specified in the constructor.
     *
     * @since 0.2
     */
    @Override
    public synchronized void reset() {
        pos = mark;
    }
}