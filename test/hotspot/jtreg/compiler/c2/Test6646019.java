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

package compiler.c2;
















/*
 * @test
 * @bug 6646019
 * @summary array subscript expressions become top() with -d64
 *
 * @run main/othervm -Xcomp
 *      -XX:CompileCommand=compileonly,compiler.c2.Test6646019::test
 *      compiler.c2.Test6646019
*/


public class Test6646019 {
    final static int i = 2076285318;
    long l = 2;
    short s;

    public static void main(String[] args) {
        Test6646019 t = new Test6646019();
        try {
            t.test();
        } catch (Throwable e) {
            if (t.l != 5) {
                System.out.println("Fails: " + t.l + " != 5");
            }
        }
    }

    private void test() {
        l = 5;
        l = (new short[(byte) -2])[(byte) (l = i)];
    }
}
