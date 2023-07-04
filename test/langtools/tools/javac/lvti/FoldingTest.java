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
class FoldingTest {

        void testReachability() {
        for(var i = 0; i < 3; i++) {
              // ok
        }
            System.out.println("foo");   //this should be reachable
        }

    void testCase(String s) {
        var c = "";
        final String c2 = "" + c;
        switch (s) {
            case c: break; //error!
            case c2: break; //error!
        }
    }

    void testAnno() {
        @Anno1(s1) //error
        var s1 = "";
        @Anno2(s2) //error
        var s2 = "";
        @Anno3(s3) //error
        var s3 = "";
    }

    @interface Anno1 {
        String value();
    }
    @interface Anno2 {
        Class<?> value();
    }
    @interface Anno3 {
        Foo value();
    }

    enum Foo {
        A, B;
    }
}
