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

package java.core.java.util;

import java.core.java.io.Serializable;

public class IdentityHashMap extends AbstractMap implements Serializable {
    /**
     * The initial capacity used by the no-args constructor.
     * MUST be a power of two.  The value 32 corresponds to the
     * (specified) expected maximum size of 21, given a load factor
     * of 2/3.
     */
    private static final int DEFAULT_CAPACITY = 32;

    /**
     * The minimum capacity, used if a lower value is implicitly specified
     * by either of the constructors with arguments.  The value 4 corresponds
     * to an expected maximum size of 2, given a load factor of 2/3.
     * MUST be a power of two.
     */
    private static final int MINIMUM_CAPACITY = 4;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<29.
     *
     * In fact, the map can hold no more than MAXIMUM_CAPACITY-1 items
     * because it has to have at least one slot with the key == null
     * in order to avoid infinite loops in get(), put(), remove()
     */
    private static final int MAXIMUM_CAPACITY = 1 << 29;

    /**
     * The table, resized as necessary. Length MUST always be a power of two.
     */
    transient Object[] table; // non-private to simplify nested class access

    /**
     * The number of key-value mappings contained in this identity hash map.
     *
     * @serial
     */
    int size;

    /**
     * The number of modifications, to support fast-fail iterators
     */
    transient int modCount;

    /**
     * Value representing null keys inside tables.
     */
    static final Object NULL_KEY = new Object();

    /**
     * Use NULL_KEY for key if it is null.
     */
    private static Object maskNull(Object key) {
        return (key == null ? NULL_KEY : key);
    }

    /**
     * Returns internal representation of null key back to caller as null.
     */
    static final Object unmaskNull(Object key) {
        return (key == NULL_KEY ? null : key);
    }

    /**
     * Constructs a new, empty identity hash map with a default expected
     * maximum size (21).
     */
    public IdentityHashMap() {
        init(DEFAULT_CAPACITY);
    }
}