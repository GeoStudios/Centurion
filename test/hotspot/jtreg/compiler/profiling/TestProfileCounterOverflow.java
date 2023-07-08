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

package compiler.profiling;

/**
 * @test
 * @bug 8224162
 * @summary Profile counter for a call site may overflow.
 * @run main/othervm -Xbatch -XX:-UseOnStackReplacement -XX:+IgnoreUnrecognizedVMOptions -XX:MaxTrivialSize=0 -XX:C1MaxTrivialSize=0 compiler.profiling.TestProfileCounterOverflow
 */

public class TestProfileCounterOverflow {
    public static void test(long iterations) {
        for (long j = 0; j < iterations; j++) {
            call();
        }
    }

    public static void call() {}

    public static void main(String[] args) {
        // trigger profiling on tier3
        for (int i = 0; i < 500; i++) {
            test(1);
        }

        test(Integer.MAX_VALUE + 10000L); // overflow call counter

        // trigger c2 compilation
        for (int i = 0; i < 10_000; i++) {
            test(1);
        }
        System.out.println("TEST PASSED");
    }
}
