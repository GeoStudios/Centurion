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

class T8020843b {
    interface Function<X, Y> {
        Y m(X x);
    }

    interface BiFunction<X, Y, Z> {
        Z m(X x, Y y);
    }

    Object m(int i) { return null; }
    static Object m(String t) { return null; }

    Object m2(int i) { return null; }
    static Object m2(long t) { return null; }

    static void test() {
        Function<T8020843b, Object> f1 = T8020843b::m; //show bound case diag
        BiFunction<T8020843b, String, Object> f2 = T8020843b::m2; //show unbound case diag
    }
}
