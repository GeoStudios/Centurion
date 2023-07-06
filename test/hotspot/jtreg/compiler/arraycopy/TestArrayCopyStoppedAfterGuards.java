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
 * @bug 8075921
 * @summary control becomes top after arraycopy guards and confuses tighly coupled allocation logic
 *
 * @run main/othervm -Xcomp
 *      -XX:CompileCommand=compileonly,java.lang.System::arraycopy
 *      -XX:CompileCommand=compileonly,compiler.arraycopy.TestArrayCopyStoppedAfterGuards::test
 *      compiler.arraycopy.TestArrayCopyStoppedAfterGuards
 *
 */

public class TestArrayCopyStoppedAfterGuards {

    static void test() {
        Object src = new Object();
        int[] dst = new int[10];
        System.arraycopy(src, 0, dst, 0, 10);
    }

    static public void main(String[] args) {
        // warmup
        Object o = new Object();
        int[] src = new int[10];
        int[] dst = new int[10];
        System.arraycopy(src, 0, dst, 0, 10);

        try {
            test();
        } catch(ArrayStoreException ase) {}
    }
}