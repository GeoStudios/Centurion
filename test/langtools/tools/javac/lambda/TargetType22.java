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

class TargetType22 {

    interface Sam0 {
        void m();
    }

    interface Sam1<A> {
        void m(A a);
    }

    interface Sam2<A> {
        void m(A a1, A a2);
    }

    interface Sam3<A> {
        void m(A a1, A a2, A a3);
    }

    interface SamX<A> {
        void m(A... as);
    }

    void call(Sam0 s) { }
    void call(Sam1<String> s) { }
    void call(Sam2<String> s) { }
    void call(Sam3<String> s) { }
    void call(SamX<String> s) { }

    void test() {
        call(() -> { });
        call(a1 -> { }); //ambiguous - both call(Sam1) and call(SamX) match
        call((a1, a2) -> { });
        call((a1, a2, a3) -> { });
    }
}
