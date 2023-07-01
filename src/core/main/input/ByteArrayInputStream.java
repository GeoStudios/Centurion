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

public class ByteArrayInputStream extends InputStream {

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
    public ByteArrayInputStream(byte[] buffer) {
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
    public ByteArrayInputStream(byte[] buffer, int offset, int length) {
        this.buffer = buffer;
        this.pos = offset;
        this.count = Math.min(offset + length, buffer.length);
        this.mark = offset;
    }

    /**
     * Reads the next byte of data from this input stream. The value byte is
     * returned as an int.. in the range 0 to 255. If no byte is available
     * because the end of the stream has been reached, the value -1 is
     * returned. This method blocks until input data is available, the end of
     * the stream is detected, or an exception is thrown.
     * <p>
     *     This read method cannot block.
     * </p>
     * @return the next byte of data, or -1 if the end of the stream is reached.
     *
     * @since Alpha CDK 0.2
     */
    public synchronized int read() {
        return (pos < count) ? (buffer[pos++] & 0xff) : -1;
    }
}