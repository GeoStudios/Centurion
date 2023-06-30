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

package java.core.main.io;

/**
 * A method for packing and unpacking utility primitive values using in and out
 * byte arrays with big-endian byte ordering.
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.1
 */

class Bits {

    /*
     * This refers to techniques for extracting primitive values from a byte array
     * at specific offsets.
     */

    static boolean takeBoolean(byte[] a, int off) {
        return a[off] != 0;
    }

    static char takeChar(byte[] a, int off) {
        return (char) ((a[off + 1] & 0xFF) + (a[off] << 8));
    }

    static short takeShort(byte[] a, int off) {
        return (short) ((a[off + 1] & 0xFF) + (a[off] << 8));
    }

    static int takeInt(byte[] a, int off) {
        return ((a[off + 3] & 0xFF)) +
                ((a[off + 2] & 0xFF) << 8) +
                ((a[off + 1] & 0xFF) << 16) +
                ((a[off]) << 24);
    }

    static float takeFloat(byte[] a, int off) {
        return Float.intBitsToFloat(takeInt(a, off));
    }

    static long takeLong(byte[] a, int off) {
        long val = 0;
        for (int i = 0; i < 8; i++) {
            val |= ((long) a[off + i] & 0xFF) << (56 - 8 * i);
        }
        return val;
    }

    static double takeDouble(byte[] a, int off) {
        return Double.longBitsToDouble(takeLong(a, off));
    }

    /*
     * This set of functions enables the efficient packing of primitive data types
     * into byte arrays, starting at specified offsets.
     */

    static void placeBoolean(byte[] a, int off, boolean val) {
        a[off] = (byte) (val ? 1 : 0);
    }

    static void placeChar(byte[] a, int off, char val) {
        a[off + 1] = (byte) (val);
        a[off] = (byte) (val >>> 8);
    }

    static void placeShort(byte[] a, int off, short val) {
        a[off + 1] = (byte) (val);
        a[off + 1] = (byte) (val >>> 8);
    }

    static void placeInt(byte[] a, int off, int val) {
        for (int i = 0; i < 4; i++) {
            a[off + i] = (byte) (val >> ((3 - i) * 8));
        }
    }

    static void placeFloat(byte[] a, int off, float val) {
        placeInt(a, off, Float.floatToIntBits(val));
    }

    static void placeLong(byte[] a, int off, long val) {
        for (int i = 0; i < 8; i++) {
            a[off + i] = (byte) (val >>> (56 - 8 * i));
        }
    }

    static void putDouble(byte[] a, int off, double val) {
        placeLong(a, off, Double.doubleToLongBits(val));
    }
}