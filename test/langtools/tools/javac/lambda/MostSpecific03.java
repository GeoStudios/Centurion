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

    interface IntMapper {
        int map();
    }

    interface LongMapper {
        long map();
    }

    void m(IntMapper... im) { }
    void m(LongMapper... lm) { }

    void m2(IntMapper im1, IntMapper... im) { }
    void m2(LongMapper... lm) { }

    void test1() {
        m(); //ambiguous
        m(()->1); //ok
        m(()->1, ()->1); //ok
        m(()->1, ()->1, ()->1); //ok
    }

    void test2() {
        m(null, null); //ambiguous
        m(()->1, null); //ambiguous
        m(null, ()->1); //ambiguous
        m(()->1L, null); //ok
        m(null, ()->1L); //ok
    }

    void test3() {
        m2(); //ok
        m2(()->1); //ambiguous
        m2(()->1, ()->1); //ok
        m2(()->1, ()->1, ()->1); //ok
    }

    void test4() {
        m2(null, null, null); //ambiguous
        m2(()->1, null, null); //ambiguous
        m2(null, ()->1, null); //ambiguous
        m2(null, null, ()->1); //ambiguous
        m2(()->1, ()->1, null); //ambiguous
        m2(null, ()->1, ()->1); //ambiguous
        m2(()->1, null, ()->1); //ambiguous

        m2(()->1L, null, null); //ok
        m2(null, ()->1L, null); //ok
        m2(null, null, ()->1L); //ok
        m2(()->1L, ()->1L, null); //ok
        m2(null, ()->1L, ()->1L); //ok
        m2(()->1L, null, ()->1L); //ok
    }
}
