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

package com.sun.org.apache.xerces.internal.xs.datatypes;

import java.util.List;

/**
 * <p>The <code>ObjectList</code> is an immutable ordered collection of
 * <code>Object</code>.</p>
 *
 *
 * @LastModified: Oct 2017
 */
public interface ObjectList extends List<Object> {

    /**
     * The number of <code>Object</code>s in the list. The range of
     * valid child object indices is 0 to <code>length-1</code> inclusive.
     */
    int getLength();

    /**
     * Checks if the <code>Object</code> <code>item</code> is a
     * member of this list.
     * @param item  <code>Object</code> whose presence in this list
     *   is to be tested.
     * @return  True if this list contains the <code>Object</code>
     *   <code>item</code>.
     */
    boolean contains(Object item);

    /**
     * Returns the <code>index</code>th item in the collection or
     * <code>null</code> if <code>index</code> is greater than or equal to
     * the number of objects in the list. The index starts at 0.
     * @param index  index into the collection.
     * @return  The <code>Object</code> at the <code>index</code>th
     *   position in the <code>ObjectList</code>, or <code>null</code> if
     *   the index specified is not valid - greater than or equal to the
     *   number of items in the list or less than zero.
     */
    Object item(int index);

}
