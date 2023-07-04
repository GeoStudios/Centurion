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

public class CheckWellFormednessIntersectionTypesTest {
    class U1 {}
    class U3 {}

    class X1 extends U1 {}
    class X3 extends U3 {}

    interface SAM<P1 extends X1, P2 extends P1, P3 extends X3> {
        P3 m(P1 p1, P2 p2);
    }

    interface I<T> {}

    @SuppressWarnings("unchecked")
    class Tester {
        public X3 foo(X1 x1, Object x2) { return new X3(); }
        Object method(SAM<?, ?, ?> sam) {
            return sam.m(null, null);
        }

        Object foo() {
            return method((SAM<?, ?, ?> & I<?>) this::foo);
        }
    }
}
