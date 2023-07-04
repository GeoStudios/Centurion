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
class Neg14 {

    static interface A<T> {
        void foo();
    }

    static void foo(A<String> as) {}

    public static void main(String[] args) {

        // Method invocation context - good <>(){}
        foo(new A<>() {
            public void foo() {}
        });

        // Assignment context - good <>(){}
        A<?> aq = new A<>() {
            public void foo() {}
        };

        // When the anonymous type subtypes an interface but is missing definitions for
        // abstract methods, expect no overload resolution error, but an attribution error
        // while attributing anonymous class body.


        // Method invocation context - bad <>(){}
        foo(new A<>() {
        });

        // Assignment invocation context - bad <>(){}
        aq = new A<>() {
        };

        // Method invocation context - bad <>()
        foo(new A<>());

        // Assignment invocation context - bad <>()
        aq = new A<>();
    }
}
