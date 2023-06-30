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

package java.core.main.io;

/**
 * This class provides an output stream implementation where the data is
 * written into a byte array. The buffer automatically expands as data
 * is written to it.
 * The data can be retrieved using the {@code toByteArray()} and
 * {@code toString()} methods.
 * <p>
 * Closing an {@code ArrayByteOutputStream} has no effect. The methods in
 * this class can still be called even after the stream has been closed without
 * causing any IO-related exceptions.
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */

public class ArrayByteOutputStream extends Reader {
    /** The buffer where data is stored. */
    protected byte[] buf;

    /** The number of valid bytes in the buffer. */
    protected int count;

    /**
     * Creates a new byte array output stream. The buffer capacity is
     * initially 32 bytes, though its size increases if necessary.
     */
    public ArrayByteOutputStream() {
        this(32);
    }

    /**
     * Creates a new byte array output stream, with a buffer capacity of
     * the specified size, in bytes.
     *
     * @param   size   the initial size.
     * @exception IllegalArgumentException if size is negative.
     */

    public ArrayByteOutputStream(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative initial size: " + size);
        }
        buf = new byte[size];
    }

    /**
     * Ensures that the capacity of the buffer is at least equal to the specified minimum
     * capacity. If the current capacity is less than the minimum capacity, the buffer's
     * capacity will be increased accordingly.
     *
     * @param minCapacity the desired minimum capacity.
     * @throws OutOfMemoryError if {@code minCapacity < 0} and
     * {@code minCapacity - buf.length > 0}. This condition is interpreted
     * as a request for an excessively large capacity beyond the limit of
     * {@code (long) Integer.MAX_VALUE + (minCapacity - Integer.MAX_VALUE)}.
     */

    private void ensureCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = buf.length;
        int minGrowth = minCapacity - oldCapacity;
        if (minGrowth > 0) {
        }
    }

    /**
     * Writes the specified byte to this byte array output stream.
     *
     * @param   a   the byte to be written.
     */
    public synchronized void write(int a) {
        ensureCapacity(count + 1);
        buf[count] = (byte) a;
        count += 1;
    }
}