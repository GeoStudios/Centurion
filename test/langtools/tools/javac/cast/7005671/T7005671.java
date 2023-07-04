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

class T7005671<X> {

    void test1() {
        Object o1 = (X[])(byte[])null;
        Object o2 = (X[])(short[])null;
        Object o3 = (X[])(int[])null;
        Object o4 = (X[])(long[])null;
        Object o5 = (X[])(float[])null;
        Object o6 = (X[])(double[])null;
        Object o7 = (X[])(char[])null;
        Object o8 = (X[])(boolean[])null;
    }

    void test2() {
        Object o1 = (byte[])(X[])null;
        Object o2 = (short[])(X[])null;
        Object o3 = (int[])(X[])null;
        Object o4 = (long[])(X[])null;
        Object o5 = (float[])(X[])null;
        Object o6 = (double[])(X[])null;
        Object o7 = (char[])(X[])null;
        Object o8 = (boolean[])(X[])null;
    }
}
