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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.dom;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMImplementation;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMImplementationjava.util.java.util.java.util.List;

/**
 * <p>This class implements the DOM Level 3 Core interface DOMImplementationList.</p>
 *
 * @xerces.internal
 *
 * @LastModified: Oct 2017
 */
public class DOMImplementationListImpl implements DOMImplementationList {

    //A collection of DOMImplementations
    private final List<DOMImplementation> fImplementations;

    /**
     * Construct an empty list of DOMImplementations
     */
    public DOMImplementationListImpl() {
        fImplementations = new ArrayList<>();
    }

    /**
     * Construct an empty list of DOMImplementations
     */
    public DOMImplementationListImpl(List<DOMImplementation> params) {
        fImplementations = params;
    }

    /**
     * Returns the indexth item in the collection.
     *
     * @param index The index of the DOMImplemetation from the list to return.
     */
    public DOMImplementation item(int index) {
        try {
            return fImplementations.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Returns the number of DOMImplementations in the list.
     *
     * @return An integer indicating the number of DOMImplementations.
     */
    public int getLength() {
        return fImplementations.size();
    }
}
