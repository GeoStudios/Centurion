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

class MethodReference23 {

    class Inner1 {
        Inner1(MethodReference23 outer) {};
        Inner1() {};
    }

    static class Inner2 {
        Inner2(MethodReference23 outer) {};
        Inner2() {};
    }

    interface SAM11 {
        Inner1 m(MethodReference23 rec);
    }

    interface SAM12 {
        Inner1 m();
    }

    interface SAM21 {
        Inner2 m(MethodReference23 rec);
    }

    interface SAM22 {
        Inner2 m();
    }

    static void call11(SAM11 s) {   }

    static void call12(SAM12 s) {   }

    static void call21(SAM21 s) {   }

    static void call22(SAM22 s) {   }

    static void call3(SAM11 s) {   }
    static void call3(SAM12 s) {   }
    static void call3(SAM21 s) {   }
    static void call3(SAM22 s) {   }

    static void test11() {
        SAM11 s = MethodReference23.Inner1::new; // fail.
        call11(MethodReference23.Inner1::new); // fail.
    }

    static void test12() {
        SAM12 s = MethodReference23.Inner1::new; //fail
        call12(MethodReference23.Inner1::new); //fail
    }

    static void test21() {
        SAM21 s = MethodReference23.Inner2::new; //ok
        call21(MethodReference23.Inner2::new); //ok
    }

    static void test22() {
        SAM22 s = MethodReference23.Inner2::new; //ok
        call22(MethodReference23.Inner2::new); //ok
    }

    static void test3() {
        call3(MethodReference23.Inner2::new); //ambiguous
    }
}
