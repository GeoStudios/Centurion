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

class T8012003b {

    interface Consumer_V<X> {
        void m(X x);
    }

    interface Consumer_NV<X> {
        Integer m(X x);
    }

    void m1(Runnable r) { }
    void m1(Runnable r, String s) { }

    void m2(Consumer_V<Integer> ci) { }

    void m3(Consumer_NV<String> ci) { }

    void g(String arg) { }
    String g2(String arg) { return arg; }

    void test() {
        m1(this::g);
        m1(()->1);
        m1(()->false ? "" : "");
        m2(this::g);
        m3(this::g2);
        m3(this::k);
    }
}
