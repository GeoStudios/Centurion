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

import java.util.function.Function;

class SelfRefTest {

        int q() { return 42; }
    int m(int t) { return t; }

    void test(boolean cond) {
       var x = cond ? x : x; //error - self reference
       var y = (Function<Integer, Integer>)(Integer y) -> y; //error - bad shadowing
       var z = (Runnable)() -> { int z2 = m(z); }; //error - self reference
       var w = new Object() { int w = 42; void test() { int w2 = w; } }; //ok
       int u = u; //ok
       int q = q(); //ok
    }
}
