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

class T7086601 {
    static <S> void m1(Iterable<? super S> s1, Iterable<? super S> s2) { }
    static void m1(Object o) {}

    static <S> void m2(Iterable<? super S> s1, Iterable<? super S> s2, Iterable<? super S> s3) { }
    static void m2(Object o) {}

    @SafeVarargs
    static <S> void m3(Iterable<? super S>... ss) { }
    static void m3(Object o) {}

    static void test1(Iterable<String> is, Iterable<Integer> ii) {
        m1(is, ii);
    }

    static void test2(Iterable<String> is, Iterable<Integer> ii, Iterable<Double> id) {
        m2(is, ii, id);
    }

    static void test3(Iterable<String> is, Iterable<Integer> ii) {
        m3(is, ii);
    }

    static void test4(Iterable<String> is, Iterable<Integer> ii, Iterable<Double> id) {
        m3(is, ii, id);
    }
}
