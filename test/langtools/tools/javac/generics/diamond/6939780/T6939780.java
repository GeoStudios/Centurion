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

class T6939780 {

    static class Foo<X extends Number> {
        Foo() {}
        Foo(X x) {}
    }

    void testAssign() {
        Foo<Number> f1 = new Foo<Number>(1);
        Foo<?> f2 = new Foo<Number>();
        Foo<?> f3 = new Foo<Integer>();
        Foo<Number> f4 = new Foo<Number>(1) {};
        Foo<?> f5 = new Foo<Number>() {};
        Foo<?> f6 = new Foo<Integer>() {};
    }

    void testMethod() {
        gn(new Foo<Number>(1));
        gw(new Foo<Number>());
        gw(new Foo<Integer>());
        gn(new Foo<Number>(1) {});
        gw(new Foo<Number>() {});
        gw(new Foo<Integer>() {});
    }

    void gw(Foo<?> fw) { }
    void gn(Foo<Number> fn) { }

    static class Foo2<X> {
        X copy(X t) {
            return t;
        }
    }

    void testReciever() {
        Number s = new Foo2<Number>().copy(0);
    }

}
