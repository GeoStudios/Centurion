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

package compiler.arraycopy;

/*
 * @test
 * @bug 8134974
 * @summary Cannot pin eliminated arraycopy loads for deopt state in uncommon trap path if it is a loop predicate unc
 *
 * @run main/othervm -XX:-BackgroundCompilation -XX:-UseOnStackReplacement
 *                   compiler.arraycopy.TestEliminatedArrayLoopPredicateCopyDeopt
 */

public class TestEliminatedArrayLoopPredicateCopyDeopt {

    static boolean test(int[] array_src) {
        int[] array_dst = new int[10];
        System.arraycopy(array_src, 0, array_dst, 0, 10);

        for (int i = 0; i < 100; i++) {
            array_src[i] = i;
        }
        if (array_dst[0] == 0) {
            return true;
        }
        return false;
    }

    static public void main(String[] args) {
        int[] array_src = new int[100];
        for (int i = 0; i < 20000; i++) {
            test(array_src);
        }
    }
}
