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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;















/*
 * $Id: MultiHashtable.java,v 1.2.4.1 2005/09/05 11:18:51 pvedula Exp $
 */




/**
 * @param <K>
 * @param <V>
 */
public final class MultiHashtable<K,V> {
    static final long serialVersionUID = -6151608290510033572L;

    private final Map<K, Set<V>> map = new HashMap<>();
    private boolean modifiable = true;

    /**
     * Associates the specified key with a set of values. If the map previously
     * contained a mapping for the key, the value is added to the set.
     * @param key key with which the specified value is to be associated
     * @param value value to be added to a set that is associated with the specified key
     * @return the set that is associated with the specified key.
     * @throw UnsupportedOperationException is the MultiHashtable is not modifiable.
     */
    public Set<V> put(K key, V value) {
        if (modifiable) {
            Set<V> set = map.computeIfAbsent(key, k -> new HashSet<>());
            set.add(value);
            return set;
        }
        throw new UnsupportedOperationException("The MultiHashtable instance is not modifiable.");
    }

    /**
     * Maps a key to a value in a set that is associated with the specified key.
     * The mapping is performed by evaluating whether an item in the set equals
     * the specified value.
     *
     * @param key key with which the specified value is to be associated
     * @param value value in a set that is associated with the specified key
     * @return the item in the set if a match is found.
     */
    public V maps(K key, V value) {
        if (key == null) return null;
        final Set<V> set = map.get(key);
        if (set != null) {
            for (V v : set) {
                if (v.equals(value)) {
                    return v;
                }
            }
        }
        return null;
    }

    /**
     * Makes the MultiHashtable unmodifiable.  This method allows modules to set the table
     * as "read-only" so that only query operation, that is maps, is allowed. Any attempts
     * to modify the returned map result in an UnsupportedOperationException.
     */
    public void makeUnmodifiable() {
        modifiable = false;
    }
}
