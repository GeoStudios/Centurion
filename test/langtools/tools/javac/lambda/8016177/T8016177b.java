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
class T8016177b {
    interface ToIntFunction<X> {
        int m(X x);
    }

    interface Function<X, Y> {
        Y m(X x);
    }

    <U, V> Function<U, V> id(Function<U, V> arg) { return null; }

    <U, V> Function<U, V> id2(Function<U, V> arg) { return null; }
    <U> ToIntFunction<U> id2(ToIntFunction<U> arg) { return null; }


    <X,Y,Z> X f(Y arg, Function<Y, Z> f) { return null; }

    <X,Y,Z> X f2(Y arg, Function<Y, Z> f) { return null; }
    <X,Y> X f2(Y arg, ToIntFunction<Y> f) { return null; }

    <T> T g(T arg) { return null; }

    void test() {
        g(f("hi", id(x->1))); //ok
        g(f("hi", id2(x->1))); //ambiguous
        g(f2("hi", id(x->1))); //ok
    }
}
