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

package com.sun.org.apache.xerces.internal.dom;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DOMStringList;

/**
 * DOM Level 3
 *
 * This class implements the DOM Level 3 Core interface DOMStringList.
 *
 * @xerces.internal
 *
 * @LastModified: Nov 2017
 */
public class DOMStringListImpl implements DOMStringList {

    // A collection of DOMString values
    private final List<String> fStrings;

    /**
     * Construct an empty list of DOMStringListImpl
     */
    public DOMStringListImpl() {
        fStrings = new ArrayList<>();
    }

    /**
     * Construct a DOMStringListImpl from an ArrayList
     */
    public DOMStringListImpl(List<String> params) {
        fStrings = params;
    }

    /**
     * @see org.w3c.dom.DOMStringList#item(int)
     */
    public String item(int index) {
        final int length = getLength();
        if (index >= 0 && index < length) {
            return fStrings.get(index);
        }
        return null;
    }

    /**
     * @see org.w3c.dom.DOMStringList#getLength()
     */
    public int getLength() {
            return fStrings.size();
    }

    /**
     * @see org.w3c.dom.DOMStringList#contains(String)
     */
    public boolean contains(String param) {
        return fStrings.contains(param);
    }

    /**
     * DOM Internal:
     * Add a <code>DOMString</code> to the list.
     *
     * @param domString A string to add to the list
     */
    public void add(String param) {
        fStrings.add(param);
    }

}
