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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.Shortjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSException;
import java.util.Abstractjava.util.java.util.java.util.List;















/**
 * Containts a list of Object's.
 *
 * @xerces.internal
 *
 *
 * @LastModified: Oct 2017
 */
public final class ShortListImpl extends AbstractList<Short> implements ShortList {

    /**
     * An immutable empty list.
     */
    public static final ShortListImpl EMPTY_LIST = new ShortListImpl(new short[0], 0);

    // The array to hold all data
    private final short[] fArray;
    // Number of elements in this list
    private final int fLength;

    /**
     * Construct an XSObjectList implementation
     *
     * @param array     the data array
     * @param length    the number of elements
     */
    public ShortListImpl(short[] array, int length) {
        fArray = array;
        fLength = length;
    }

    /**
     * The number of <code>Objects</code> in the list. The range of valid
     * child node indices is 0 to <code>length-1</code> inclusive.
     */
    public int getLength() {
        return fLength;
    }

    /**
     *  Checks if the <code>unsigned short</code> <code>item</code> is a
     * member of this list.
     * @param item  <code>unsigned short</code> whose presence in this list
     *   is to be tested.
     * @return  True if this list contains the <code>unsigned short</code>
     *   <code>item</code>.
     */
    public boolean contains(short item) {
        for (int i = 0; i < fLength; i++) {
            if (fArray[i] == item) {
                return true;
            }
        }
        return false;
    }

    public short item(int index) throws XSException {
        if (index < 0 || index >= fLength) {
            throw new XSException(XSException.INDEX_SIZE_ERR, null);
        }
        return fArray[index];
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ShortList rhs)) {
            return false;
        }

        if (fLength != rhs.getLength()) {
            return false;
        }
        for (int i = 0;i < fLength; ++i) {
            if (fArray[i] != rhs.item(i)) {
                return false;
            }
        }
        return true;
    }

    /*
     * List methods
     */

    public Short get(int index) {
        if (index >= 0 && index < fLength) {
            return fArray[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

} // class ShortListImpl
