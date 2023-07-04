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

class LambdaConv09 {

    // Not a SAM type; not enough abstract methods
    interface Foo1 {}

    // SAM type; Foo has no abstract methods
    interface Foo2 { boolean equals(Object object); }


    // Not a SAM type; Foo still has no abstract methods
    interface Foo3 extends Foo2 { public abstract String toString(); }

    // SAM type; Bar has one abstract non-Object method
    interface Foo4<T> extends Foo2 { int compare(T o1, T o2); }

    // Not a SAM type; still no valid abstract methods
    interface Foo5 {
        boolean equals(Object object);
        String toString();
    }

    // SAM type; Foo6 has one abstract non-Object method
    interface Foo6<T> {
        boolean equals(Object obj);
        int compare(T o1, T o2);
    }

    // SAM type; Foo6 has one abstract non-Object method
    interface Foo7<T> extends Foo2, Foo6<T> { }

    void test() {
        Foo1 f1 = ()-> { };
        Foo2 f2 = ()-> { };
        Foo3 f3 = x -> true;
        Foo4 f4 = (x, y) -> 1;
        Foo5 f5 = x -> true;
        Foo6 f6 = (x, y) -> 1;
        Foo7 f7 = (x, y) -> 1;
    }
}
