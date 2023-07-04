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

class T8016177c {

    interface Function<X, Y> {
        Y m(X x);
    }

    interface ExtFunction<X, Y> extends Function<X, Y> { }

    <U, V> U m1(Function<U, V> f) { return null; }
    <U, V> U m1(ExtFunction<U, V> f) { return null; }

    void m2(Function<Integer, Integer> f) { }
    void m2(ExtFunction<Integer, Integer> f) { }

    void m3(Function<Integer, Integer> f) { }
    void m3(ExtFunction<Object, Integer> f) { }

    int g1(Object s) { return 1; }

    int g2(Number s) { return 1; }
    int g2(Object s) { return 1; }

    void test() {
        m1((Integer x)->x); //ok - explicit lambda - subtyping picks most specific
        m2((Integer x)->x); //ok - explicit lambda - subtyping picks most specific
        m3((Integer x)->x); //ok - explicit lambda (only one applicable)

        m1(x->1); //ok - stuck lambda but nominal most specific wins
        m2(x->1); //ok - stuck lambda but nominal most specific wins
        m3(x->1); //ambiguous - implicit lambda & different params

        m1(this::g1); //ok - unambiguous ref - subtyping picks most specific
        m2(this::g1); //ok - unambiguous ref - subtyping picks most specific
        m3(this::g1); //ambiguous - both applicable, neither most specific

        m1(this::g2); //ok - stuck mref but nominal most specific wins
        m2(this::g2); //ok - stuck mref but nominal most specific wins
        m3(this::g2); //ambiguous - different params
    }
}
