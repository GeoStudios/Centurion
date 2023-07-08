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

package compiler.loopopts;

/**
 * @test
 * @key stress randomness
 * @bug 8069191
 * @summary predicate moved out of loops and CastPP removal causes dependency to be lost
 *
 * @run main/othervm -Xcomp -XX:+IgnoreUnrecognizedVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+StressGCM
 *                   -XX:CompileCommand=compileonly,compiler.loopopts.TestPredicateLostDependency::m1
 *                   compiler.loopopts.TestPredicateLostDependency
 *
 */

public class TestPredicateLostDependency {
    static class A {
        int i;
    }

    static class B extends A {
    }

    static boolean crash = false;

    static boolean m2() {
        return crash;
    }

    static int m3(float[] arr) {
        return 0;
    }

    static float m1(A aa) {
        float res = 0;
        float[] arr = new float[10];
        for (int i = 0; i < 10; i++) {
            if (m2()) {
                arr = null;
            }
            m3(arr);
            int j = arr.length;
            int k = 0;
            for (k = 9; k < j; k++) {
            }
            if (k == 10) {
                if (aa instanceof B) {
                }
            }
            res += arr[0];
            res += arr[1];
        }
        return res;
    }

    static public void main(String args[]) {
        A a = new A();
        B b = new B();
        for (int i = 0; i < 20000; i++) {
            m1(a);
        }
        crash = true;
        try {
            m1(a);
        } catch (NullPointerException npe) {}
    }
}
