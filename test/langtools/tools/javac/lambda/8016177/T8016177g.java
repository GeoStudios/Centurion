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


class Test {

    interface Function<X, Y> {
        Y m(X x);
    }

    interface Box<T> {
        T get();
        <R> R map(Function<T,R> f);
    }

    static class Person {
        Person(String name) { }
    }

    void print(Object arg) { }
    void print(String arg) { }

    int abs(int a) { return 0; }
    long abs(long a) { return 0; }
    float abs(float a) { return 0; }
    double abs(double a) { return 0; }

    void test() {
        Box<String> b = null;
        print(b.map(s -> new Person(s)));
        int i = abs(b.map(s -> Double.valueOf(s)));
    }
}
