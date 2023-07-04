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
 * @bug 8253756
 * @summary dead outer strip mined not optimized out after expansion
 * @requires vm.compiler2.enabled
 *
 * @run main/othervm -XX:-BackgroundCompilation -XX:LoopMaxUnroll=2 TestOuterStripMinedDeadAfterExpansion
 *
 */

public class TestOuterStripMinedDeadAfterExpansion {
    private static int field;

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 20_000; i++) {
            test(array, array, array.length);
            test_helper(array, 100);
        }
    }

    private static int test(int[] src, int[] dst, int length) {
        field = 4 << 17;
        System.arraycopy(src, 0, dst, 0, length);
        int stop = field >>> 17;
        return test_helper(dst, stop);
    }

    private static int test_helper(int[] dst, int stop) {
        int res = 0;
        for (int i = 0; i < stop; i++) {
            res += dst[i];
        }
        return res;
    }
}
