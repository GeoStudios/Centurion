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

package compiler.c1;

/**
 * @test
 * @bug 6769124
 * @summary arraycopy may crash the VM with c1 on 64 bit
 *
 * @run main compiler.c1.TestArrayCopy
 */

public class TestArrayCopy {

    public static void main(String[] args) {

        int k = 1 << 31;

        for(int j = 0; j <1000000; j++) {
            int i = -1;
            while(i < 10) {
                i++;
            }

            int m = k * i;

            int[] O1 = new int[20];
            int[] O2 = new int[20];

            System.arraycopy(O1, i, O2, i, 1); //will crash on amd64
            System.arraycopy(O1, m, O2, m, 1); //will crash on sparcv9
        }
    }
}
