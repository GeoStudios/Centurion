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

public class MethodReference73 {

    interface SAM {
        void m(MethodReference73 rec, String x);
    }

    void m1(MethodReference73 rec, String x) {}
    static void m1(MethodReference73 rec, Object x) {}
    void m1(String x) {}

    static void m2(MethodReference73 rec, String x) {}
    void m2(Object x) {}
    static void m2(String x) {}

    static void m3(MethodReference73 rec, String x) {}
    void m3(String x) {}

    void m4(MethodReference73 rec, String x) {}
    static void m4(MethodReference73 rec, Object x) {}
    static void m4(String x) {}
    void m4(Object x) {}

    static void m5(MethodReference73 rec, String x) {}
    static void m5(String x) {}

    static void m6(MethodReference73 rec, String x) {}
    void m6(String x, int i) {}

    void m7(MethodReference73 rec, String x) {}
    void m7(String x) {}

    static void m8(MethodReference73 rec, String x, int i) {}
    void m8(String x) {}

    void m9(MethodReference73 rec, String x) {}
    static void m9(MethodReference73 rec, Object x) {}
    static void m9(String x) {}

    void m10(MethodReference73 rec, String x) {}
    static void m10(MethodReference73 rec, Object x) {}
    void m10(String x, int i) {}

    void m11(MethodReference73 rec, String x) {}
    void m11(Object x) {}
    static void m11(String x) {}

    static void m12(MethodReference73 rec, String x, int i) {}
    void m12(Object x) {}
    static void m12(String x) {}

    void m13(MethodReference73 rec, String x) {}
    void m13(String x, int i) {}

    static void m14(MethodReference73 rec, String x, int i) {}
    static void m14(String x) {}

    void m15(MethodReference73 rec, String x) {}
    static void m15(String x) {}

    static void m16(MethodReference73 rec, String x, int i) {}
    void m16(String x, int i) {}

    /** For method references with a type selector two searches are performed.
     *  Each of them may yield one of the following results:
     *      I)   a good match
     *      II)  a bad match more specific than a good match
     *      III) a bad match with no good matches
     *      IV)  no applicable method found
     *
     *  Whether a match is considered to be good or not depends on the staticness
     *  of the matched method. The expected result of the first search is a static
     *  method. The expected result of the second search is an instance method.
     *
     *  If the most specific method has the wrong staticness but there is an
     *  applicable method with the right staticness then we have the (II) case.
     *  The (III) case is reserved for those cases when the most specific method
     *  has the wrong staticness but there is no applicable method with the right
     *  staticness.
     */

    static void test() {
        SAM s1 = MethodReference73::m1;           //(II, I)       ambiguous
        SAM s2 = MethodReference73::m2;           //(I, II)       ambiguous
        SAM s3 = MethodReference73::m3;           //(I, I)        ambiguous
        SAM s4 = MethodReference73::m4;           //(II, II)      ambiguous

        SAM s5 = MethodReference73::m5;           //(I, III)      first search's result gets selected
        SAM s6 = MethodReference73::m6;           //(I, IV)       first search's result gets selected

        SAM s7 = MethodReference73::m7;           //(III, I)      second search's result gets selected
        SAM s8 = MethodReference73::m8;           //(IV, I)       second search's result gets selected

        SAM s9 = MethodReference73::m9;           //(II, III)     method matched by first search has the wrong staticness
        SAM s10 = MethodReference73::m10;         //(II, IV)      method matched by first search has the wrong staticness
        SAM s11 = MethodReference73::m11;         //(III, II)     method matched by second search has the wrong staticness
        SAM s12 = MethodReference73::m12;         //(IV, II)      method matched by second search has the wrong staticness
        SAM s13 = MethodReference73::m13;         //(III, IV)     method matched by first search has the wrong staticness
        SAM s14 = MethodReference73::m14;         //(IV, III)     method matched by second search has the wrong staticness
        SAM s15 = MethodReference73::m15;         //(III, III)    method matched by first search has the wrong staticness

        SAM s16 = MethodReference73::m16;         //(IV, IV)      incompatible types, invalid method reference
    }
}
