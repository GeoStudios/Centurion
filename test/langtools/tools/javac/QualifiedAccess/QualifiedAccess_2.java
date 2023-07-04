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

import pack1.P1;

class A {
    private static class B {
        static class Inner {}
    }
}

class X extends pack1.P1 {
    X() { super("bar"); }
    void foo() {
        /*-----------------*
        // BOGUS: Reports matching constructor not found.
        // OK if 'Q' is made a public constructor.
        Object y = new Q("foo");// ERROR - protected constructor Q inaccessible
        *------------------*/
        // Reports 'P1.R.S' not found at all. (private)
        Object z = new R.S.T();         // ERROR - S is inaccessible
    }
}

class Y {

    class Foo {
        class Bar {}
    }

    class C extends A.B {}              // ERROR - B is inaccessible
    class D extends A.B.Inner {}        // ERROR - B is inaccessible

    static class Quux {
        private static class Quem {
            P1.Foo.Bar x;               // ERROR - Foo is inaccessible
            static class MyError extends Error {}
        }
    }
}

class Z {
    void foo() throws Y.Quux.Quem.MyError {
                                // ERROR - type of Quux not accesible (private)
        throw new Y.Quux.Quem.MyError();
                                // ERROR - type of Quux not accesible (private)
    }
}
