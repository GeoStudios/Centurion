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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.util;

/**
 */
public final class IntegerArray {
    private static final int InitialSize = 32;

    private int[] _array;
    private int   _size;
    private int   _free = 0;

    public IntegerArray() {
        this(InitialSize);
    }

    public IntegerArray(int size) {
        _array = new int[_size = size];
    }

    public IntegerArray(int[] array) {
        this(array.length);
        System.arraycopy(array, 0, _array, 0, _free = _size);
    }

    public void clear() {
        _free = 0;
    }

    public Object clone() {
        final IntegerArray clone = new IntegerArray(_free > 0 ? _free : 1);
        System.arraycopy(_array, 0, clone._array, 0, _free);
        clone._free = _free;
        return clone;
    }

    public int[] toIntArray() {
        final int[] result = new int[cardinality()];
        System.arraycopy(_array, 0, result, 0, cardinality());
        return result;
    }

    public int at(int index) {
        return _array[index];
    }

    public void set(int index, int value) {
        _array[index] = value;
    }

    public int indexOf(int n) {
        for (int i = 0; i < _free; i++) {
            if (n == _array[i]) return i;
        }
        return -1;
    }

    public void add(int value) {
        if (_free == _size) {
            growArray(_size * 2);
        }
        _array[_free++] = value;
    }

    /**
     * Adds new int at the end if not already present.
     */
    public void addNew(int value) {
        for (int i = 0; i < _free; i++) {
            if (_array[i] == value) return;  // already in array
        }
        add(value);
    }

    public void reverse() {
        int left = 0;
        int right = _free - 1;

        while (left < right) {
            int temp = _array[left];
            _array[left++] = _array[right];
            _array[right--] = temp;
        }
    }

    /**
     * Merge two sorted arrays and eliminate duplicates.
     * Elements of the other IntegerArray must not be changed.
     */
    public void merge(final IntegerArray other) {
        final int newSize = _free + other._free;
// System.out.println("IntegerArray.merge() begin newSize = " + newSize);
        int[] newArray = new int[newSize];

        // Merge the two arrays
        int i = 0, j = 0, k;
        for (k = 0; i < _free && j < other._free; k++) {
            int x = _array[i];
            int y = other._array[j];

            if (x < y) {
                newArray[k] = x;
                i++;
            }
            else if (x > y) {
                newArray[k] = y;
                j++;
            }
            else {
                newArray[k] = x;
                i++; j++;
            }
        }

        // Copy the rest if of different lengths
        if (i >= _free) {
            while (j < other._free) {
                newArray[k++] = other._array[j++];
            }
        }
        else {
            while (i < _free) {
                newArray[k++] = _array[i++];
            }
        }

        // Update reference to this array
        _array = newArray;
        _free = _size = newSize;
// System.out.println("IntegerArray.merge() end");
    }

    public void sort() {
        quicksort(_array, 0, _free - 1);
    }

    private static void quicksort(int[] array, int p, int r) {
        if (p < r) {
            final int q = partition(array, p, r);
            quicksort(array, p, q);
            quicksort(array, q + 1, r);
        }
    }

    private static int partition(int[] array, int p, int r) {
        final int x = array[(p + r) >>> 1];
        int i = p - 1; int j = r + 1;

        while (true) {
            while (x < array[--j]);
            while (x > array[++i]);
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            else {
                return j;
            }
        }
    }

    private void growArray(int size) {
        final int[] newArray = new int[_size = size];
        System.arraycopy(_array, 0, newArray, 0, _free);
        _array = newArray;
    }

    public int popLast() {
        return _array[--_free];
    }

    public int last() {
        return _array[_free - 1];
    }

    public void setLast(int n) {
        _array[_free - 1] = n;
    }

    public void pop() {
        _free--;
    }

    public void pop(int n) {
        _free -= n;
    }

    public int cardinality() {
        return _free;
    }

    public void print(java.io.PrintStream out) {
        if (_free > 0) {
            for (int i = 0; i < _free - 1; i++) {
                out.print(_array[i]);
                out.print(' ');
            }
            out.println(_array[_free - 1]);
        }
        else {
            out.println("IntegerArray: empty");
        }
    }
}
