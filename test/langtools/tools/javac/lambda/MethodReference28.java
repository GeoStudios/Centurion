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

class MethodReference28 {

    interface SAM1 {
        void m(int i);
    }

    interface SAM2 {
        void m(MethodReference28 rec, int i);
    }

    static void static_m1(Integer i) { } //ok - boxing
    static void static_m2(Integer i1, Integer i2) { } //wrong arity
    static void static_m3(String s) { } //type mismatch
    static void static_m4(String... ss) { } //type mismatch - varargs

    void m1(Integer i) { } //ok - boxing
    void m2(Integer i1, Integer i2) { } //wrong arity
    void m3(String s) { } //type mismatch
    void m4(String... ss) { } //type mismatch - varargs

    static void testStatic() {
        SAM1 s1 = MethodReference28::static_m1;
        SAM1 s2 = MethodReference28::static_m2;
        SAM1 s3 = MethodReference28::static_m3;
        SAM1 s4 = MethodReference28::static_m4;
    }

    void testBadMember() {
        SAM1 s1 = MethodReference28::m1;
        SAM1 s2 = MethodReference28::m2;
        SAM1 s3 = MethodReference28::m3;
        SAM1 s4 = MethodReference28::m4;
    }

    void testMember() {
        SAM1 s1 = this::m1;
        SAM1 s2 = this::m2;
        SAM1 s3 = this::m3;
        SAM1 s4 = this::m4;
    }

    static void testUnbound() {
        SAM2 s1 = MethodReference28::m1;
        SAM2 s2 = MethodReference28::m2;
        SAM2 s3 = MethodReference28::m3;
        SAM2 s4 = MethodReference28::m4;
    }
}
