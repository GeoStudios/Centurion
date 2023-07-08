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

package compiler.c2;
















/**
 * @test
 * @bug 7020614
 * @summary "-server" mode optimizer makes code hang
 *
 * @run main/othervm/timeout=30 -Xbatch compiler.c2.Test7020614
 */


public class Test7020614 {

    private static final int ITERATIONS = 1000;
    private static int doNotOptimizeOut = 0;

    public static long bitCountShort() {
        long t0 = System.currentTimeMillis();
        int sum = 0;
        for (int it = 0; it < ITERATIONS; ++it) {
            short value = 0;
            do {
                sum += Integer.bitCount(value);
            } while (++value != 0);
        }
        doNotOptimizeOut += sum;
        return System.currentTimeMillis() - t0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; ++i) {
            System.out.println((i + 1) + ": " + bitCountShort());
        }
        System.out.println("doNotOptimizeOut value: " + doNotOptimizeOut);
    }
}

