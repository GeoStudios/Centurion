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

class MethodReference37 {

    interface SAM1<R> {
        R invoke();
    }

    interface SAM2<R, A> {
        R invoke(A a);
    }

    static class Outer {
        class Inner { }

        void test1() {
            SAM2<Inner, Outer> sam = Inner::new;
        }

        void test2() {
            SAM1<Inner> sam0 = Inner::new;
            SAM2<Inner, Outer> sam1 = Inner::new;
        }
    }

    static void test1() {
        SAM2<Outer.Inner, Outer> sam = Outer.Inner::new;
    }

    void test2() {
        SAM2<Outer.Inner, Outer> sam1 = Outer.Inner::new;
    }
}
