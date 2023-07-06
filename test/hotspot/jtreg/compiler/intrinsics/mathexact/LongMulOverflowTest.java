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

package compiler.intrinsics.mathexact;

/*
 * @test
 * @bug 8191915
 * @summary Regression test for multiplyExact intrinsic
 * @library /test/lib /
 * @modules java.base/jdk.internal.misc
 *          java.management
 *
 * @run main/othervm -Xcomp -XX:-TieredCompilation compiler.intrinsics.mathexact.LongMulOverflowTest
 */

public class LongMulOverflowTest {
    public static void main(String[] args) {
        LongMulOverflowTest test = new LongMulOverflowTest();
        for (int i = 0; i < 10; ++i) {
            try {
                test.runTest();
                throw new RuntimeException("Error, runTest() did not overflow!");
            } catch (ArithmeticException e) {
                // success
            }

            try {
                test.runTestOverflow();
                throw new RuntimeException("Error, runTestOverflow() did not overflow!");
            } catch (ArithmeticException e) {
                // success
            }
        }
    }

    public void runTest() {
        java.lang.Math.multiplyExact(Long.MIN_VALUE, 7);
    }

    public void runTestOverflow() {
      java.lang.Math.multiplyExact((Long.MAX_VALUE / 2) + 1, 2);
    }
}