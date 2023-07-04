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


public class Private03 {
    interface I {
        private void foo(int x) {}
        private void goo(int x) {}
    }

    interface J extends I {
        // Verify that we are able to declare a public abstract method with the same signature as a private method in super type.
        void foo(int x);
        // Verify that we are able to declare a public default method with the same signature as a private method in super type.
        default void goo(int x) {}
    }

    interface K extends J {
        private void foo(int x) {} // Error, cannot reduce visibility
        private void goo(int x) {} // Ditto.
    }
}
