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

package jdk.internal.joptsimple.internal;

import java.util.HashMap;
import java.util.Map;

/**
 */
public final class Classes {
    private static final Map<Class<?>, Class<?>> WRAPPERS = new HashMap<>( 13 );

    static {
        WRAPPERS.put( boolean.class, Boolean.class );
        WRAPPERS.put( byte.class, Byte.class );
        WRAPPERS.put( char.class, Character.class );
        WRAPPERS.put( double.class, Double.class );
        WRAPPERS.put( float.class, Float.class );
        WRAPPERS.put( int.class, Integer.class );
        WRAPPERS.put( long.class, Long.class );
        WRAPPERS.put( short.class, Short.class );
        WRAPPERS.put( void.class, Void.class );
    }

    private Classes() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gives the "short version" of the given class name.  Somewhat naive to inner classes.
     *
     * @param className class name to chew on
     * @return the short name of the class
     */
    public static String shortNameOf( String className ) {
        return className.substring( className.lastIndexOf( '.' ) + 1 );
    }

    /**
     * Gives the primitive wrapper class for the given class. If the given class is not
     * {@linkplain Class#isPrimitive() primitive}, returns the class itself.
     *
     * @param <T> generic class type
     * @param clazz the class to check
     * @return primitive wrapper type if {@code clazz} is primitive, otherwise {@code clazz}
     */
    @SuppressWarnings( "unchecked" )
    public static <T> Class<T> wrapperOf( Class<T> clazz ) {
        return clazz.isPrimitive() ? (Class<T>) WRAPPERS.get( clazz ) : clazz;
    }
}
