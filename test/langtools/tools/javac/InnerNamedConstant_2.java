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

public class InnerNamedConstant_2 {

    static class Inner1 {
        static int x = 1;                  // OK - class is top-level
        static final int y = x * 5;        // OK - class is top-level
        static final String z;             // OK - class is top-level
        static {
            z = "foobar";
        }
    }

    class Inner2 {
        static int x = 1;                  // ERROR - static not final
        static final String z;             // ERROR - static blank final
        {
            z = "foobar";                  // Error may be reported here. See 4278961.
        }
    }

    // This case must go in a separate class, as otherwise the detection
    // of the error is suppressed as a result of recovery from the other
    // errors.

    class Inner3 {
        static final int y = Inner1.x * 5; // ERROR - initializer not constant
    }

}
