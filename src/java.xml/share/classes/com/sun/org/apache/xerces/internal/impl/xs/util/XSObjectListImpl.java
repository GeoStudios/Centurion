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

package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.xs.XSObject;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Containts a list of XSObject's.
 *
 * @xerces.internal
 *
 *
 * @LastModified: Oct 2017
 */
@SuppressWarnings("unchecked") // method <T>toArray(T[])
public class XSObjectListImpl extends AbstractList<XSObject> implements XSObjectList {

    /**
     * An immutable empty list.
     */
    public static final XSObjectListImpl EMPTY_LIST = new XSObjectListImpl(new XSObject[0], 0);
    private static final ListIterator<XSObject> EMPTY_ITERATOR = new EmptyIterator();
    static class EmptyIterator implements ListIterator<XSObject> {
        public boolean hasNext() {
            return false;
        }
        public XSObject next() {
            throw new NoSuchElementException();
        }
        public boolean hasPrevious() {
            return false;
        }
        public XSObject previous() {
            throw new NoSuchElementException();
        }
        public int nextIndex() {
            return 0;
        }
        public int previousIndex() {
            return -1;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public void set(XSObject object) {
            throw new UnsupportedOperationException();
        }
        public void add(XSObject object) {
            throw new UnsupportedOperationException();
        }
    }
    private static final int DEFAULT_SIZE = 4;

    // The array to hold all data
    private XSObject[] fArray = null;
    // Number of elements in this list
    private int fLength = 0;

    public XSObjectListImpl() {
        fArray = new XSObject[DEFAULT_SIZE];
        fLength = 0;
    }

    /**
     * Construct an XSObjectList implementation
     *
     * @param array     the data array
     * @param length    the number of elements
     */
    public XSObjectListImpl(XSObject[] array, int length) {
        fArray = array;
        fLength = length;
    }

    /**
     * The number of <code>XSObjects</code> in the list. The range of valid
     * child node indices is 0 to <code>length-1</code> inclusive.
     */
    public int getLength() {
        return fLength;
    }

    /**
     * Returns the <code>index</code>th item in the collection. The index
     * starts at 0. If <code>index</code> is greater than or equal to the
     * number of nodes in the list, this returns <code>null</code>.
     * @param index index into the collection.
     * @return The XSObject at the <code>index</code>th position in the
     *   <code>XSObjectList</code>, or <code>null</code> if that is not a
     *   valid index.
     */
    public XSObject item(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        return fArray[index];
    }

    // clear this object
    public void clearXSObjectList() {
        for (int i=0; i<fLength; i++) {
            fArray[i] = null;
        }
        fArray = null;
        fLength = 0;
    }

    public void addXSObject(XSObject object) {
       if (fLength == fArray.length) {
           XSObject[] temp = new XSObject[fLength + 4];
           System.arraycopy(fArray, 0, temp, 0, fLength);
           fArray = temp;
       }
       fArray[fLength++] = object;
    }

    public void addXSObject(int index, XSObject object) {
        fArray[index] = object;
    }

    /*
     * List methods
     */

    public boolean contains(Object value) {
        return (value == null) ? containsNull() : containsObject(value);
    }

    public XSObject get(int index) {
        if (index >= 0 && index < fLength) {
            return fArray[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

    public Iterator<XSObject> iterator() {
        return listIterator0(0);
    }

    public ListIterator<XSObject> listIterator() {
        return listIterator0(0);
    }

    public ListIterator<XSObject> listIterator(int index) {
        if (index >= 0 && index < fLength) {
            return listIterator0(index);
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    private ListIterator<XSObject> listIterator0(int index) {
        return fLength == 0 ? EMPTY_ITERATOR : new XSObjectListIterator(index);
    }

    private boolean containsObject(Object value) {
        for (int i = fLength - 1; i >= 0; --i) {
            if (value.equals(fArray[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNull() {
        for (int i = fLength - 1; i >= 0; --i) {
            if (fArray[i] == null) {
                return true;
            }
        }
        return false;
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

    private final class XSObjectListIterator implements ListIterator<XSObject> {
        private int index;
        public XSObjectListIterator(int index) {
            this.index = index;
        }
        public boolean hasNext() {
            return (index < fLength);
        }
        public XSObject next() {
            if (index < fLength) {
                return fArray[index++];
            }
            throw new NoSuchElementException();
        }
        public boolean hasPrevious() {
            return (index > 0);
        }
        public XSObject previous() {
            if (index > 0) {
                return fArray[--index];
            }
            throw new NoSuchElementException();
        }
        public int nextIndex() {
            return index;
        }
        public int previousIndex() {
            return index - 1;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public void set(XSObject o) {
            throw new UnsupportedOperationException();
        }
        public void add(XSObject o) {
            throw new UnsupportedOperationException();
        }
    }

} // class XSObjectListImpl
