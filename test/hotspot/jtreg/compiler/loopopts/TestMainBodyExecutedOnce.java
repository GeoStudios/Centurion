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

/**
 * @test
 * @bug 8269752
 * @summary C2: assert(false) failed: Bad graph detected in build_loop_late
 *
 * @run main/othervm -Xcomp -XX:-TieredCompilation -XX:CompileOnly=TestMainBodyExecutedOnce TestMainBodyExecutedOnce
 *
 */


public class TestMainBodyExecutedOnce {
    static int N;
    static long vMeth_check_sum;

    public static void main(String[] strArr) {
        TestMainBodyExecutedOnce _instance = new TestMainBodyExecutedOnce();
        for (int i = 0; i < 10; i++) {
            _instance.test();
        }
    }

    void test() {
        vMeth(3);
    }

    void vMeth(int i2) {
        double d = 1.74287;
        int i3 = -36665, i4, iArr[] = new int[N];
        short s;
        long lArr[] = new long[N];
        while (++i3 < 132) {
            if (i2 != 0) {
                vMeth_check_sum += i3;
                return;
            }
            i4 = 1;
            while (++i4 < 12) {
                i2 += i4;
            }
        }
        vMeth_check_sum += i3;
    }
}
