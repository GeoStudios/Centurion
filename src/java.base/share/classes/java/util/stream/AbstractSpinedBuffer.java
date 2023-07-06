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

package java.base.share.classes.java.util.stream;

/**
 * Base class for a data structure for gathering elements into a buffer and then
 * iterating them. Maintains an array of increasingly sized arrays, so there is
 * no copying cost associated with growing the data structure.
 */
abstract class AbstractSpinedBuffer {
    /**
     * Minimum power-of-two for the first chunk.
     */
    public static final int MIN_CHUNK_POWER = 4;

    /**
     * Minimum size for the first chunk.
     */
    public static final int MIN_CHUNK_SIZE = 1 << MIN_CHUNK_POWER;

    /**
     * Max power-of-two for chunks.
     */
    public static final int MAX_CHUNK_POWER = 30;

    /**
     * Minimum array size for array-of-chunks.
     */
    public static final int MIN_SPINE_SIZE = 8;

    /**
     * log2 of the size of the first chunk.
     */
    protected final int initialChunkPower;

    /**
     * Index of the *next* element to write; may point into, or just outside of,
     * the current chunk.
     */
    protected int elementIndex;

    /**
     * Index of the *current* chunk in the spine array, if the spine array is
     * non-null.
     */
    protected int spineIndex;

    /**
     * Count of elements in all prior chunks.
     */
    protected long[] priorElementCount;

    /**
     * Construct with an initial capacity of 16.
     */
    protected AbstractSpinedBuffer() {
        this.initialChunkPower = MIN_CHUNK_POWER;
    }

    /**
     * Construct with a specified initial capacity.
     *
     * @param initialCapacity The minimum expected number of elements
     */
    protected AbstractSpinedBuffer(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);

        this.initialChunkPower = Math.max(MIN_CHUNK_POWER,
                                          Integer.SIZE - Integer.numberOfLeadingZeros(initialCapacity - 1));
    }

    /**
     * Is the buffer currently empty?
     */
    public boolean isEmpty() {
        return (spineIndex == 0) && (elementIndex == 0);
    }

    /**
     * How many elements are currently in the buffer?
     */
    public long count() {
        return (spineIndex == 0)
               ? elementIndex
               : priorElementCount[spineIndex] + elementIndex;
    }

    /**
     * How big should the nth chunk be?
     */
    protected int chunkSize(int n) {
        int power = (n == 0 || n == 1)
                    ? initialChunkPower
                    : Math.min(initialChunkPower + n - 1, AbstractSpinedBuffer.MAX_CHUNK_POWER);
        return 1 << power;
    }

    /**
     * Remove all data from the buffer
     */
    public abstract void clear();
}
