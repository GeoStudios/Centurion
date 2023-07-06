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

package compiler.codegen;

/*
 * @test
 * @bug 8031743
 * @summary loadI2L_immI broken for negative memory values
 *
 * @run main/othervm -Xbatch
 *      -XX:CompileCommand=compileonly,compiler.codegen.LoadWithMask2::foo*
 *      compiler.codegen.LoadWithMask2
 */

public class LoadWithMask2 {
    static int x;

    static long foo1() {
        return x & 0xfffffffe;
    }

    static long foo2() {
        return x & 0xff000000;
    }

    static long foo3() {
        return x & 0x8abcdef1;
    }

    public static void main(String[] args) {
        x = -1;
        long l = 0;
        for (int i = 0; i < 100000; ++i) {
            l = foo1() & foo2() & foo3();
        }
        if (l > 0) {
            System.out.println("FAILED");
            System.exit(97);
        }
        System.out.println("PASSED");
    }
}
