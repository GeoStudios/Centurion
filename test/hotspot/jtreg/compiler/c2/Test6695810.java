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
 * @bug 6695810
 * @summary null oop passed to encode_heap_oop_not_null
 *
 * @run main/othervm -Xbatch compiler.c2.Test6695810
 */

public class Test6695810 {
    Test6695810 _t;

    static void test(Test6695810 t1, Test6695810 t2) {
        if (t2 != null)
            t1._t = t2;

        if (t2 != null)
            t1._t = t2;
    }

    public static void main(String[] args) {
        Test6695810 t = new Test6695810();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 100; j++) {
                test(t, t);
            }
            test(t, null);
        }
        for (int i = 0; i < 10000; i++) {
            test(t, t);
        }
        test(t, null);
    }
}