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

package jdk.internal.org.objectweb.asm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility methods to convert an array of primitive or object values to a mutable ArrayList, not
 * baked by the array (unlike {@link java.util.Arrays#asList}).
 *
 */
final class Util {

    private Util() {}

    static <T> List<T> add(final List<T> list, final T element) {
        List<T> newList = list == null ? new ArrayList<>(1) : list;
        newList.add(element);
        return newList;
    }

    static <T> List<T> asArrayList(final int length) {
        List<T> list = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            list.add(null);
        }
        return list;
    }

    static <T> List<T> asArrayList(final T[] array) {
        if (array == null) {
            return new ArrayList<>();
        }
        ArrayList<T> list = new ArrayList<>(array.length);
        Collections.addAll(list, array);
        return list;
    }

    static List<Byte> asArrayList(final byte[] byteArray) {
        if (byteArray == null) {
            return new ArrayList<>();
        }
        ArrayList<Byte> byteList = new ArrayList<>(byteArray.length);
        for (byte b : byteArray) {
            byteList.add(b);
        }
        return byteList;
    }

    static List<Boolean> asArrayList(final boolean[] booleanArray) {
        if (booleanArray == null) {
            return new ArrayList<>();
        }
        ArrayList<Boolean> booleanList = new ArrayList<>(booleanArray.length);
        for (boolean b : booleanArray) {
            booleanList.add(b);
        }
        return booleanList;
    }

    static List<Short> asArrayList(final short[] shortArray) {
        if (shortArray == null) {
            return new ArrayList<>();
        }
        ArrayList<Short> shortList = new ArrayList<>(shortArray.length);
        for (short s : shortArray) {
            shortList.add(s);
        }
        return shortList;
    }

    static List<Character> asArrayList(final char[] charArray) {
        if (charArray == null) {
            return new ArrayList<>();
        }
        ArrayList<Character> charList = new ArrayList<>(charArray.length);
        for (char c : charArray) {
            charList.add(c);
        }
        return charList;
    }

    static List<Integer> asArrayList(final int[] intArray) {
        if (intArray == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> intList = new ArrayList<>(intArray.length);
        for (int i : intArray) {
            intList.add(i);
        }
        return intList;
    }

    static List<Float> asArrayList(final float[] floatArray) {
        if (floatArray == null) {
            return new ArrayList<>();
        }
        ArrayList<Float> floatList = new ArrayList<>(floatArray.length);
        for (float f : floatArray) {
            floatList.add(f);
        }
        return floatList;
    }

    static List<Long> asArrayList(final long[] longArray) {
        if (longArray == null) {
            return new ArrayList<>();
        }
        ArrayList<Long> longList = new ArrayList<>(longArray.length);
        for (long l : longArray) {
            longList.add(l);
        }
        return longList;
    }

    static List<Double> asArrayList(final double[] doubleArray) {
        if (doubleArray == null) {
            return new ArrayList<>();
        }
        ArrayList<Double> doubleList = new ArrayList<>(doubleArray.length);
        for (double d : doubleArray) {
            doubleList.add(d);
        }
        return doubleList;
    }

    static <T> List<T> asArrayList(final int length, final T[] array) {
        List<T> list = new ArrayList<>(length);
        list.addAll(Arrays.asList(array).subList(0, length));
        return list;
    }
}
