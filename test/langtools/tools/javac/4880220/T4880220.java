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

public class T4880220 {
    void m1() {
        int good_1 = C.m();
        int good_2 = C.f;
        int good_3 = C.x;

        C c = new C();
        int bad_inst_1 = c.m();
        int bad_inst_2 = c.f;
        int bad_inst_3 = c.x;

        int bad_expr_1 = c().m();
        int bad_expr_2 = c().f;
        int bad_expr_3 = c().x;
    }

    void m2() {
        Class<?> good_1 = C.class;
        Class<?> good_2 = C[].class;
    }

    C c() {
        return new C();
    }

    static class C {
        static int m() { return 0; }
        static int f;
        static final int x = 3;
    }
}
