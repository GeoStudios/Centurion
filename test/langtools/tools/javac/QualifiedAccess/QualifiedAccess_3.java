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

class CMain {

    class Foo {
        class Bar {}
    }

    static class Baz {
        private static class Quux {
            static class Quem {}
        }
    }

    // These are all OK.
    CMain z = new CMain();
    Foo x = z.new Foo();
    Foo.Bar y = x.new Bar();

    void test() {
        P1 p1 = new P1();

        // These are NOT errors, and should NOT be detected, as observed.
        /*------------------------------------*
        Baz.Quux z = null;
        Baz.Quux.Quem y = null;
        *------------------------------------*/

        P1.Foo.Bar x = null;            // ERROR - 'P1.Foo' not accessible
        int i = p1.a.length;            // ERROR - Type of 'a' not accessible
        // The type of the expression from which a component
        // is selected must be accessible.
        p1.p2.privatei = 3;             // ERROR - Type of 'p1.p2' not accessible.
        System.out.println (p1.p2.privatei);    // ERROR - Type of 'p1.p2' not accessible.
    }

}
