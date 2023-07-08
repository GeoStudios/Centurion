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

package compiler.arraycopy;
















/*
 * @test
 * @bug 8055153
 * @summary missing control on LoadRange and LoadKlass when array copy macro node is expanded
 *
 * @run main/othervm -XX:-BackgroundCompilation -XX:-UseOnStackReplacement -XX:-TieredCompilation
 *                   compiler.arraycopy.TestMissingControl
 *
 */


public class TestMissingControl {

    static int[] m1(int[] a2) {
        int[] a1 = new int[10];
        System.arraycopy(a1, 0, a2, 0, 10);
        return a1;
    }

    static class A {
    }

    static Object m2(Object[] a2) {
        A[] a1 = new A[10];
        System.arraycopy(a1, 0, a2, 0, 10);
        return a1;
    }

    static void test1() {
        int[] a2 = new int[10];
        int[] a3 = new int[5];

        // compile m1 with arraycopy intrinsified
        for (int i = 0; i < 20000; i++) {
            m1(a2);
        }

        // make m1 trap
        for (int i = 0; i < 10; i++) {
            try {
                m1(a3);
            } catch(IndexOutOfBoundsException ioobe) {
            }
        }

        // recompile m1
        for (int i = 0; i < 20000; i++) {
            m1(a2);
        }

        try {
            m1(null);
        } catch(NullPointerException npe) {}
    }

    static void test2() {
        A[] a2 = new A[10];
        A[] a3 = new A[5];

        // compile m2 with arraycopy intrinsified
        for (int i = 0; i < 20000; i++) {
            m2(a2);
        }

        // make m2 trap
        for (int i = 0; i < 10; i++) {
            try {
                m2(a3);
            } catch(IndexOutOfBoundsException ioobe) {
            }
        }

        // recompile m2
        for (int i = 0; i < 20000; i++) {
            m2(a2);
        }

        try {
            m2(null);
        } catch(NullPointerException npe) {}
    }

    static public void main(String[] args) {
        test1();
        test2();
    }
}
