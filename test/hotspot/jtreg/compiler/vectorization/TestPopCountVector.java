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
 * @bug 8199421
 * @summary Test vectorization of popcount
 * @run main/othervm -XX:+IgnoreUnrecognizedVMOptions -XX:+UsePopCountInstruction
 *      compiler.vectorization.TestPopCountVector
 * @run main/othervm -XX:+IgnoreUnrecognizedVMOptions -XX:+UsePopCountInstruction
 *      -XX:MaxVectorSize=8 compiler.vectorization.TestPopCountVector
 */


public class TestPopCountVector {
    private int[] input;
    private int[] output;
    private static final int LEN = 1024;

    public static void main(String args[]) {
        TestPopCountVector test = new TestPopCountVector();

        for (int i = 0; i < 10_000; ++i) {
          test.vectorizeBitCount();
        }
        System.out.println("Checking popcount result");
        test.checkResult();

        for (int i = 0; i < 10_000; ++i) {
          test.vectorizeBitCount();
        }
        System.out.println("Checking popcount result");
        test.checkResult();
    }

    public TestPopCountVector() {
        input = new int[LEN];
        output = new int[LEN];
        for (int i = 0; i < LEN; ++i) {
            input[i] = i % 2 == 0 ? i : -1 * i;
        }
    }

    public void vectorizeBitCount() {
        for (int i = 0; i < LEN; ++i) {
            output[i] = Integer.bitCount(input[i]);
        }
    }

    public void checkResult() {
        for (int i = 0; i < LEN; ++i) {
            int expected = Integer.bitCount(input[i]);
            if (output[i] != expected) {
                throw new RuntimeException("Invalid result: output[" + i + "] = " + output[i] + " != " + expected);
            }
        }
    }
}

