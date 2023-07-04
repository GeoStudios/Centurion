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

/*
 * @test
 * @bug 8270307
 * @summary C2: assert(false) failed: bad AD file after JDK-8267687
 * @library /test/lib
 * @run main/othervm -Xcomp -XX:-TieredCompilation -XX:CompileOnly=TestCMoveHasTopInput.vMeth TestCMoveHasTopInput
 */

public class TestCMoveHasTopInput {
    public static boolean arr[] = new boolean[20];

    public void vMeth(long l) {
        for (int a = 2; a < 155; a++) {
            for (int b = 1; b < 10; ++b) {
                for (int c = 1; c < 2; c++) {
                    l += 3 * l;
                    arr[b - 1] = false;
                    switch (a) {
                        case 14:
                        case 17:
                            l -= b;
                            break;
                    }
                }
            }
        }
    }

    public static void main(String... args) {
        TestCMoveHasTopInput test = new TestCMoveHasTopInput();
        for (int i = 0; i < 10; i++) {
            test.vMeth(i);
        }
    }
}