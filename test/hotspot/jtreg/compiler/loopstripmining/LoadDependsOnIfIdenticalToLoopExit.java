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
 * @bug 8229450
 * @summary shared an identical bool node with a strip-mined loop
 *
 * @run main/othervm -XX:-TieredCompilation -XX:-UseOnStackReplacement -XX:-BackgroundCompilation -XX:LoopMaxUnroll=0 -XX:CompileCommand=dontinline,LoadDependsOnIfIdenticalToLoopExit::not_inlined -XX:CompileCommand=compileonly,LoadDependsOnIfIdenticalToLoopExit::test1 LoadDependsOnIfIdenticalToLoopExit
 *
 */

public class LoadDependsOnIfIdenticalToLoopExit {
    public static void main(String[] args) {
        for (int i = 0; i < 20_000; i++) {
            test1(false, false);
            test1(true, true);
        }
    }

    private static int test1(boolean flag1, boolean flag2) {
        int res = 1;
        int[] array = new int[10];
        not_inlined(array);
        int i;
        for (i = 0; i < 2000; i++) {
            res *= i;
        }

        if (flag1) {
            if (flag2) {
                res++;
            }
        }

        if (i >= 2000) {
            res *= array[0];
        }
        return res;
    }

    private static void not_inlined(int[] array) {
    }
}
