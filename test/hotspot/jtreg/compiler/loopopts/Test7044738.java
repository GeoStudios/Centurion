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

package compiler.loopopts;
















/**
 * @test
 * @bug 7044738
 * @summary Loop unroll optimization causes incorrect result
 *
 * @run main/othervm -Xbatch compiler.loopopts.Test7044738
 */


public class Test7044738 {

    private static final int INITSIZE = 10000;
    public int d[] = {1, 2, 3, 4};
    public int i, size;

    private static int iter = 5;

    boolean done() {
        return (--iter > 0);
    }

    public static void main(String args[]) {
        Test7044738 t = new Test7044738();
        t.test();
    }

    int test() {

        while (done()) {
            size = INITSIZE;

            for (i = 0; i < size; i++) {
                d[0] = d[1]; // 2
                d[1] = d[2]; // 3
                d[2] = d[3]; // 4
                d[3] = d[0]; // 2

                d[0] = d[1]; // 3
                d[1] = d[2]; // 4
                d[2] = d[3]; // 2
                d[3] = d[0]; // 3

                d[0] = d[1]; // 4
                d[1] = d[2]; // 2
                d[2] = d[3]; // 3
                d[3] = d[0]; // 4

                d[0] = d[1]; // 2
                d[1] = d[2]; // 3
                d[2] = d[3]; // 4
                d[3] = d[0]; // 2

                d[0] = d[1]; // 3
                d[1] = d[2]; // 4
                d[2] = d[3]; // 2
                d[3] = d[0]; // 3

                d[0] = d[1]; // 4
                d[1] = d[2]; // 2
                d[2] = d[3]; // 3
                d[3] = d[0]; // 4

                d[0] = d[1]; // 2
                d[1] = d[2]; // 3
                d[2] = d[3]; // 4
                d[3] = d[0]; // 2

                d[0] = d[1]; // 3
                d[1] = d[2]; // 4
                d[2] = d[3]; // 2
                d[3] = d[0]; // 3
            }

            // try to defeat dead code elimination
            if (d[0] == d[1]) {
                System.out.println("test failed: iter=" + iter + "  i=" + i + " d[] = { " + d[0] + ", " + d[1] + ", " + d[2] + ", " + d[3] + " } ");
                System.exit(97);
            }
        }
        return d[3];
    }
}
