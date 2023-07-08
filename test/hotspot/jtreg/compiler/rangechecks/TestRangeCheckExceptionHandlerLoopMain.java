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

package compiler.rangechecks;

/*
 * @test
 * @bug 8134883
 * @summary C1's range check elimination breaks with a non-natural loop that an exception handler as one entry
 *
 * @compile TestRangeCheckExceptionHandlerLoop.jasm
 * @run main/othervm -XX:-BackgroundCompilation -XX:-UseOnStackReplacement
 *                  compiler.rangechecks.TestRangeCheckExceptionHandlerLoopMain
 */

public class TestRangeCheckExceptionHandlerLoopMain {
    public static void main(String[] args) throws Exception {
        Exception exception = new Exception();
        int[] array = new int[10];
        for (int i = 0; i < 20000; i++) {
            TestRangeCheckExceptionHandlerLoop.test(false, array, exception);
        }
    }
}
