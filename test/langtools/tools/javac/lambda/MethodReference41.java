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

public class MethodReference41 {

    interface SAM1 {
       void m(String s);
    }

    interface SAM2 {
       void m(Integer s);
    }

    interface SAM3 {
       void m(Object o);
    }

    static class Foo<X extends Number> {
        Foo(X x) { }
    }

    static void m1(SAM1 s) { }

    static void m2(SAM2 s) { }

    static void m3(SAM3 s) { }

    static void m4(SAM1 s) { }
    static void m4(SAM2 s) { }
    static void m4(SAM3 s) { }

    public static void main(String[] args) {
        m1(Foo::new);
        m2(Foo::new);
        m3(Foo::new);
        m4(Foo::new);
    }
}
