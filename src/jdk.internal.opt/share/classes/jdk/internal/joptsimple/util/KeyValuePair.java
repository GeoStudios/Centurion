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

package jdk.internal.joptsimple.util;

import static jdk.internal.joptsimple.internal.Strings.*;

/**
 * <p>A simple string key/string value pair.</p>
 *
 * <p>This is useful as an argument type for options whose values take on the form {@code key=value}, such as JVM
 * command line system properties.</p>
 *
 */
public final class KeyValuePair {
    public final String key;
    public final String value;

    private KeyValuePair( String key, String value ) {
        this.key = key;
        this.value = value;
    }

    /**
     * Parses a string assumed to be of the form {@code key=value} into its parts.
     *
     * @param asString key-value string
     * @return a key-value pair
     * @throws NullPointerException if {@code stringRepresentation} is {@code null}
     */
    public static KeyValuePair valueOf( String asString ) {
        int equalsIndex = asString.indexOf( '=' );
        if ( equalsIndex == -1 )
            return new KeyValuePair( asString, null );

        String aKey = asString.substring( 0, equalsIndex );
        String aValue = equalsIndex == asString.length() - 1 ? EMPTY : asString.substring( equalsIndex + 1 );

        return new KeyValuePair( aKey, aValue );
    }

    @Override
    public boolean equals( Object that ) {
        if ( !(that instanceof KeyValuePair other) )
            return false;

        return key.equals( other.key ) && value.equals( other.value );
    }

    @Override
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }

    @Override
    public String toString() {
        return key + '=' + value;
    }
}
