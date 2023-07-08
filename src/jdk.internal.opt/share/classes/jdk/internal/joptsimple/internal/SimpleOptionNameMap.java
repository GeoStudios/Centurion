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

package jdk.internal.opt.share.classes.jdk.internal.joptsimple.internal;


import java.util.HashMap;
import java.util.Map;















/**
 * <p>An {@code OptionNameMap} which wraps and behaves like {@code HashMap}.</p>
 */
public class SimpleOptionNameMap<V> implements OptionNameMap<V> {
    private final Map<String, V> map = new HashMap<>();

    @Override
    public boolean contains( String key ) {
        return map.containsKey( key );
    }

    @Override
    public V get( String key ) {
        return map.get( key );
    }

    @Override
    public void put( String key, V newValue ) {
        map.put( key, newValue );
    }

    @Override
    public void putAll( Iterable<String> keys, V newValue ) {
        for ( String each : keys )
            map.put( each, newValue );
    }

    @Override
    public void remove( String key ) {
        map.remove( key );
    }

    @Override
    public Map<String, V> toJavaUtilMap() {
        return new HashMap<>( map );
    }
}
