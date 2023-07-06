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

package jdk.jdi.share.classes.com.sun.jdi;

/**
 * Provides access to a primitive <code>short</code> value in
 * the target VM.
 *
 */
public interface ShortValue extends PrimitiveValue, Comparable<ShortValue> {

    /**
     * Returns this ShortValue as a short.
     *
     * @return the <code>short</code> mirrored by this object.
     */
    short value();

    /**
     * Compares the specified Object with this ShortValue for equality.
     *
     * @return true if the Object is a ShortValue and if applying "=="
     * to the two mirrored primitives would evaluate to true; false
     * otherwise.
     */
    boolean equals(Object obj);

    /**
     * Returns the hash code value for this ShortValue.
     *
     * @return the integer hash code
     */
    int hashCode();
}
