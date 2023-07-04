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

class MethodReference22 {

    void m1(String x) { }
    void m1(MethodReference22 rec, String x) { }

    static void m2(String x) { }
    static void m2(MethodReference22 rec, String x) { }

    static void m3(String x) { }
    void m3(MethodReference22 rec, String x) { }

    void m4(String x) { }
    static void m4(MethodReference22 rec, String x) { }

    interface SAM1 {
        void m(String x);
    }

    interface SAM2 {
        void m(MethodReference22 rec, String x);
    }

    static void call1(SAM1 s) {   }

    static void call2(SAM2 s) {   }

    static void call3(SAM1 s) {   }
    static void call3(SAM2 s) {   }

    static void test1() {
        SAM1 s1 = MethodReference22::m1; //fail
        call1(MethodReference22::m1); //fail
        SAM1 s2 = MethodReference22::m2; //ok
        call1(MethodReference22::m2); //ok
        SAM1 s3 = MethodReference22::m3; //ok
        call1(MethodReference22::m3); //ok
        SAM1 s4 = MethodReference22::m4; //fail
        call1(MethodReference22::m4); //fail
    }

    static void test2() {
        SAM2 s1 = MethodReference22::m1; //ok
        call2(MethodReference22::m1); //ok
        SAM2 s2 = MethodReference22::m2; //ok
        call2(MethodReference22::m2); //ok
        SAM2 s3 = MethodReference22::m3; //fail
        call2(MethodReference22::m3); //fail
        SAM2 s4 = MethodReference22::m4; //fail
        call2(MethodReference22::m4); //fail
    }

    static void test3() {
        call3(MethodReference22::m1); //ok
        call3(MethodReference22::m2); //ambiguous
        call3(MethodReference22::m3); //ok
        call3(MethodReference22::m4); //fail
    }
}
