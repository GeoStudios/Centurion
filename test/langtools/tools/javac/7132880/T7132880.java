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
class Outer {
    void m1(String s) { }
    void m2(int i1, int i2) { }

    class Inner {
        void test() {
           //ok - no method named 'm' in Inner - hence, class to search is Outer
           m1("");
        }
    }

    class Inner1 {
        void m1(Integer i) { }

        void test() {
           //error - Inner1 defines an incompatible method - hence, class to search is Inner1
           m1("");
        }
    }

    class Inner2 {
        private void m1(Integer i) { }
        private void m1(Double d) { }

        void test() {
           //error - Inner2 defines multiple incompatible methods - hence, class to search is Inner2
           m1("");
        }
    }

    class Inner3 {
        private void m2(Object o, int i) { }
        private void m2(int i, Object o) { }

        void test() {
           //error - Inner3 defines multiple ambiguous methods - hence, class to search is Inner3
           m2(1, 1);
        }
    }

    class Inner4 extends Inner2 {
        void test() {
           //ok - Inner2 defines multiple incompatible inaccessible methods - hence, class to search is Outer
           m1("");
        }
    }

    class Inner5 extends Inner3 {
        void test() {
           //ok - Inner3 defines multiple inaccessible ambiguous methods - hence, class to search is Outer
           m2(1, 1);
        }
    }
}
