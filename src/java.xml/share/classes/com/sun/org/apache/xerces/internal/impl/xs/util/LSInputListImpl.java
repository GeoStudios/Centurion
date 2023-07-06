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

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.LSInputjava.util.java.util.java.util.List;
import java.lang.reflect.Array;
import java.util.Abstractjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.w3c.dom.ls.LSInput;

/**
 * Contains a list of LSInputs.
 *
 * @xerces.internal
 *
 *
 * @LastModified: Oct 2017
 */
@SuppressWarnings("unchecked") // method <T>toArray(T[])
public final class LSInputListImpl extends AbstractList<LSInput> implements LSInputList {

    /**
     * An immutable empty list.
     */
    public static final LSInputListImpl EMPTY_LIST = new LSInputListImpl(new LSInput[0], 0);

    // The array to hold all data
    private final LSInput[] fArray;
    // Number of elements in this list
    private final int fLength;

    /**
     * Construct an LSInputList implementation
     *
     * @param array     the data array
     * @param length    the number of elements
     */
    public LSInputListImpl(LSInput[] array, int length) {
        fArray = array;
        fLength = length;
    }

    /**
     * The number of <code>LSInput</code>s in the list. The range of valid
     * child object indices is 0 to <code>length-1</code> inclusive.
     */
    public int getLength() {
        return fLength;
    }

    /**
     * Returns the <code>index</code>th item in the collection or
     * <code>null</code> if <code>index</code> is greater than or equal to
     * the number of objects in the list. The index starts at 0.
     * @param index  index into the collection.
     * @return  The <code>LSInput</code> at the <code>index</code>th
     *   position in the <code>LSInputList</code>, or <code>null</code> if
     *   the index specified is not valid.
     */
    public LSInput item(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        return fArray[index];
    }

    /*
     * List methods
     */

    public LSInput get(int index) {
        if (index >= 0 && index < fLength) {
            return fArray[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

    public Object[] toArray() {
        Object[] a = new Object[fLength];
        toArray0(a);
        return a;
    }

    public Object[] toArray(Object[] a) {
        if (a.length < fLength) {
            Class<?> arrayClass = a.getClass();
            Class<?> componentType = arrayClass.getComponentType();
            a = (Object[]) Array.newInstance(componentType, fLength);
        }
        toArray0(a);
        if (a.length > fLength) {
            a[fLength] = null;
        }
        return a;
    }

    private void toArray0(Object[] a) {
        if (fLength > 0) {
            System.arraycopy(fArray, 0, a, 0, fLength);
        }
    }

} // LSInputListImpl
