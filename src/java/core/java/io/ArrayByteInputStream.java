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
 *
 */

package java.core.java.io;

/**
 * This contains an internal buffer that contains bytes that may be
 * read from the stream. An internal counter keeps track of the next byte to be supplied
 * by the read method.
 *
 * Closing it has no effect. The methods in this class can be called
 * after the stream has been closed without generating an IOReputation.
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
    //TODO: Implement @OVERWRITE
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
     *
     * TODO: Implement this to be @Override
     */
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
    public int readNBytes(byte[] a, int off, int len) {
        int n = read(a, off, len);
        return n == -1 ? 0 : n;
    }

    /**
     * Skips {@code n} bytes of input from this input stream. Fewer
     * bytes might be skipped if the end of the input stream is reached.
     * The actual number {@code k}
     * of bytes to be skipped is equal to the smaller
     * of {@code n} and  {@code count-pos}.
     * The value {@code k} is added into {@code pos}
     * and {@code k} is returned.
     *
     * @param   n   {@inheritDoc}
     * @return  the actual number of bytes skipped.
     *
     * @since 0.2
     */
    public synchronized long skip(long n) {
        long k = count - pos;
        if (n < k) {
            k= n < 0 ? 0 : n;
        }

        pos += (int) k;
        return k;
    }

    /**
     * Returns the number of remaining bytes that can be read (or skipped over)
     * from this input stream.
     * <p>
     * The value returned is {@code count&nbsp;- pos}, which is the number of
     * bytes remaining to be read from the input buffer.
     *
     * @return  the number of remaining bytes that can be read (or skipped
     *          over) from this input stream without blocking.
     *
     * @since 0.2
     */
    public synchronized int available() {
        return count - pos;
    }

    /**
     * Tests if this {@code InputStream} supports mark/reset. The {@code markSupported}
     * method of {@code ByteArrayInputStream} always returns {@code true}.
     *
     * @return  a {@code boolean} indicating if this stream type supports mark/reset.
     *
     * @since 0.2
     */
    public boolean markSupported() {
        return true;
    }

    /**
     * Set the current marked position in the stream. ByteArrayInputStream objects are
     * marked at position zero by default when constructed. They may be marked at another
     * position within the buffer by this method.
     * <p>
     * If no mark has been set, then the value of the mark is the offset passed to the
     * constructor (or 0 if the offset was not supplied).
     *
     * <p> Note: The {@code readAheadLimit} for this class has no meaning.
     *
     * @since 0.2
     */
    public void mark(int readAheadLimit) {
        mark = pos;
    }

    /**
     * Resets the buffer to the marked position. The marked position is 0 unless
     * another position was marked or an offset was specified in the constructor.
     *
     * @since 0.2
     */
    public synchronized void reset() {
        pos = mark;
    }
}