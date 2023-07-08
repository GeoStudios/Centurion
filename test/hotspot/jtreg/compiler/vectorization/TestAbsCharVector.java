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

package compiler.vectorization;
















/**
 * @test
 * @bug 8261022
 * @summary Test vectorization of Math.abs() with unsigned type
 * @run main/othervm compiler.vectorization.TestAbsCharVector
 */


public class TestAbsCharVector {

    private static int SIZE = 60000;

    public static void main(String args[]) {
        char[] a = new char[SIZE];
        char[] b = new char[SIZE];

        for (int i = 0; i < SIZE; i++) {
            a[i] = b[i] = (char) i;
        }

        for (int i = 0; i < 20000; i++) {
            arrayAbs(a);
        }

        for (int i = 0; i < SIZE; i++) {
            if (a[i] != b[i]) {
                throw new RuntimeException("Broken!");
            }
        }
    }

    private static void arrayAbs(char[] arr) {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (char) Math.abs(arr[i]);
        }
    }
}

