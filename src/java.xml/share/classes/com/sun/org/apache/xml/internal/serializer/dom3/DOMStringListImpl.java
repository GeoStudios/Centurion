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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.dom3;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collections;
import java.util.java.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMStringjava.util.java.util.java.util.List;

//import java.xml.share.classes.com.sun.org.apache.xerces.dom3.DOMStringList;

/**
 * This class implemets the DOM Level 3 Core interface DOMStringList.
 *
 * @xsl.usage internal
 * @LastModified: Oct 2017
 */
final class DOMStringListImpl implements DOMStringList {

    //A collection of DOMString values
    private final List<String> fStrings;

    /**
     * Construct an empty list of DOMStringListImpl
     */
    DOMStringListImpl() {
        fStrings = new ArrayList<>();
    }

    /**
     * Construct an empty list of DOMStringListImpl
     */
    DOMStringListImpl(List<String> params) {
        fStrings = params;
    }

    /**
     * Construct an empty list of DOMStringListImpl
     */
    DOMStringListImpl(String[] params ) {
        fStrings = new ArrayList<>();
        if (params != null) {
            Collections.addAll(fStrings, params);
        }
    }

    /**
     * @see org.apache.xerces.dom3.DOMStringList#item(int)
     */
    public String item(int index) {
        try {
            return fStrings.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * @see org.apache.xerces.dom3.DOMStringList#getLength()
     */
    public int getLength() {
        return fStrings.size();
    }

    /**
     * @see org.apache.xerces.dom3.DOMStringList#contains(String)
     */
    public boolean contains(String param) {
        return fStrings.contains(param) ;
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
