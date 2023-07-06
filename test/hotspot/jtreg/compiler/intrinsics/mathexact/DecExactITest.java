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
 * @bug 8026844
 * @summary Test decrementExact
 * @library /test/lib /
 * @modules java.base/jdk.internal.misc
 *          java.management
 *
 * @run main compiler.intrinsics.mathexact.DecExactITest
 */


public class DecExactITest {
    public static int[] values = {1, 1, 1, 1};
    public static int[] minvalues = {Integer.MIN_VALUE, Integer.MIN_VALUE};

    public static void main(String[] args) {
        runTest(new Verify.DecExactI());
    }

    public static void runTest(Verify.UnaryMethod method) {
        for (int i = 0; i < 20000; ++i) {
            Verify.verifyUnary(Integer.MIN_VALUE, method);
            Verify.verifyUnary(minvalues[0], method);
            Verify.verifyUnary(Integer.MIN_VALUE - values[2], method);
            Verify.verifyUnary(0, method);
            Verify.verifyUnary(values[2], method);
            Verify.verifyUnary(Integer.MAX_VALUE, method);
            Verify.verifyUnary(Integer.MIN_VALUE - values[0] + values[3], method);
            Verify.verifyUnary(Integer.MIN_VALUE + 1 - values[0], method);
        }
    }
}
