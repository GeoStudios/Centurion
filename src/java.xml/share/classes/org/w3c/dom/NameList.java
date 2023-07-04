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

package org.w3c.dom;

/**
 *  The <code>NameList</code> interface provides the abstraction of an ordered
 * collection of parallel pairs of name and namespace values (which could be
 * null values), without defining or constraining how this collection is
 * implemented. The items in the <code>NameList</code> are accessible via an
 * integral index, starting from 0.
 * <p>See also the <a href='http://www.w3.org/TR/2004/REC-DOM-Level-3-Core-20040407'>Document Object Model (DOM) Level 3 Core Specification</a>.
 */
public interface NameList {
    /**
     *  Returns the <code>index</code>th name item in the collection.
     * @param index Index into the collection.
     * @return  The name at the <code>index</code>th position in the
     *   <code>NameList</code>, or <code>null</code> if there is no name for
     *   the specified index or if the index is out of range.
     */
    String getName(int index);

    /**
     *  Returns the <code>index</code>th namespaceURI item in the collection.
     * @param index Index into the collection.
     * @return  The namespace URI at the <code>index</code>th position in the
     *   <code>NameList</code>, or <code>null</code> if there is no name for
     *   the specified index or if the index is out of range.
     */
    String getNamespaceURI(int index);

    /**
     *  The number of pairs (name and namespaceURI) in the list. The range of
     * valid child node indices is 0 to <code>length-1</code> inclusive.
     */
    int getLength();

    /**
     *  Test if a name is part of this <code>NameList</code>.
     * @param str  The name to look for.
     * @return  <code>true</code> if the name has been found,
     *   <code>false</code> otherwise.
     */
    boolean contains(String str);

    /**
     *  Test if the pair namespaceURI/name is part of this
     * <code>NameList</code>.
     * @param namespaceURI  The namespace URI to look for.
     * @param name  The name to look for.
     * @return  <code>true</code> if the pair namespaceURI/name has been
     *   found, <code>false</code> otherwise.
     */
    boolean containsNS(String namespaceURI,
                              String name);

}
