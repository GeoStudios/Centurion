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
import java.util.List;

class T8016177a {

    interface ToIntFunction<X> {
        int m(X x);
    }

    interface Function<X, Y> {
        Y m(X x);
    }

    <T,R> void m1(List<T> s, Function<T,R> f) { }
    <T,R> void m1(List<T> s, ToIntFunction<T> f) { }

    <T,R> List<R> m2(List<T> s, Function<T,R> f) { return null; }
    <T,R> List<R> m2(List<T> s, ToIntFunction<T> f) { return null; }

    <T,R> List<T> m3(List<T> s, Function<T,R> f) { return null; }
    <T,R> List<R> m3(List<T> s, ToIntFunction<T> f) { return null; }

    <T,R> List<T> m4(List<T> s, Function<T,R> f) { return null; }
    <T,R> List<T> m4(List<T> s, ToIntFunction<T> f) { return null; }

    <T,R> List<R> m5(List<T> s, Function<T,R> f) { return null; }
    <T,R> List<T> m5(List<T> s, ToIntFunction<T> f) { return null; }

    <T extends R,R> List<R> m6(List<T> s, Function<T,R> f) { return null; }
    <T extends R,R> List<T> m6(List<T> s, ToIntFunction<T> f) { return null; }

    void test(List<String> ss) {
         m1(ss, s->s.length()); //ambiguous
         m2(ss, s->s.length()); //ambiguous
         m3(ss, s->s.length()); //ambiguous
         m4(ss, s->s.length()); //ambiguous
         m5(ss, s->s.length()); //ambiguous
         m6(ss, s->s.length()); //ambiguous
    }
}
