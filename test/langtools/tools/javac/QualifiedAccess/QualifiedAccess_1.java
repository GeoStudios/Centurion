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

public class QualifiedAccess_1 {

    // Inaccessible types in member declarations.
    // These exercise 'Env.resolve'.
    // Errors are localized poorly.
    //
    // Fields 'P3' and 'P5' are inaccessible.

    P1 foo;
    P1.P3 bar;                                  // ERROR
    P1.P3.P4 baz;                               // ERROR
    P1.P3.P4.P5 quux;                           // ERROR

    P1 m11() {return null;}
    P1.P3 m12() {return null;}                  // ERROR
    P1.P3.P4 m13() {return null;}               // ERROR
    P1.P3.P4.P5 m14() {return null;}            // ERROR

    void m21(P1 x) {}
    void m22(P1.P3 x) {}                        // ERROR
    void m23(P1.P3.P4 x) {}                     // ERROR
    void m24(P1.P3.P4.P5 x) {}                  // ERROR

    void test1() {

        // Inaccessible types in local variable declarations.
        // These exercise 'FieldExpression.checkCommon'.
        //
        // Fields 'P3' and 'P5' are inaccessible.

        P1 foo = null;
        P1.P3 bar = null;                       // ERROR
        P1.P3.P4 baz = null;                    // ERROR
        P1.P3.P4.P5 quux = null;                // ERROR
    }

    void test2() {

        // Inaccessible types in casts.
        // These exercise 'FieldExpression.checkCommon'.
        //
        // Fields 'P3' and 'P5' are inaccessible.

        Object foo = (P1)null;
        Object bar = (P1.P3)null;               // ERROR
        Object baz = (P1.P3.P4)null;            // ERROR
        Object quux = (P1.P3.P4.P5)null;        // ERROR
    }

    void test3() {

        // Inaccessible types in 'instanceof' expressions.
        // These exercise 'FieldExpression.checkCommon'.
        //
        // Fields 'P3' and 'P5' are inaccessible.

        boolean foo = null instanceof P1;
        boolean bar = null instanceof P1.P3;            // ERROR
        boolean baz = null instanceof P1.P3.P4;         // ERROR
        boolean quux = null instanceof P1.P3.P4.P5;     // ERROR
    }

}
