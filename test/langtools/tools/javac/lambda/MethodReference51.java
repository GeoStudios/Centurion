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
class MethodReference51 {

    private static class Foo {
        static int j(int i) { return i; }
    }

    static Foo foo = new Foo();

    static void m(String s) { }
    static void m(Integer i) { }

    static int f(String s) { return 1; }

    static int g(Integer i, Number n) { return 1; }
    static int g(Number n, Integer i) { return 1; }

    int h(int i) { return i; }
}

class TestMethodReference51 {

    interface IntSam {
        int m(int i);
    }

    interface IntegerIntegerSam {
        int m(Integer i1, Integer i2);
    }


    static void test() {
        IntSam s1 = MethodReference51::unknown; //fail
        IntSam s2 = MethodReference51::f; //fail
        IntSam s3 = MethodReference51::g; //fail
        IntegerIntegerSam s4 = MethodReference51::g; //fail
        IntSam s5 = MethodReference51::h; //fail
        IntSam s6 = MethodReference51.foo::j; //fail
    }
}
