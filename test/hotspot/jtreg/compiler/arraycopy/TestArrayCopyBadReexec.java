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
 * @bug 8073866
 * @summary Fix for 8064703 may also cause stores between the allocation and arraycopy to be rexecuted after a deoptimization
 *
 * @run main/othervm -XX:-BackgroundCompilation -XX:-UseOnStackReplacement
 *                   compiler.arraycopy.TestArrayCopyBadReexec
 */

public class TestArrayCopyBadReexec {

    static int val;

    static int[] m1(int[] src, int l) {
        if (src == null) {
            return null;
        }
        int[] dest = new int[10];
        val++;
        try {
            System.arraycopy(src, 0, dest, 0, l);
        } catch (IndexOutOfBoundsException npe) {
        }
        return dest;
    }

    static public void main(String[] args) {
        int[] src = new int[10];
        int[] res = null;
        boolean success = true;

        for (int i = 0; i < 20000; i++) {
            m1(src, 10);
        }

        int val_before = val;

        m1(src, -1);

        if (val - val_before != 1) {
            System.out.println("Bad increment: " + (val - val_before));
            throw new RuntimeException("Test failed");
        }
    }
}
