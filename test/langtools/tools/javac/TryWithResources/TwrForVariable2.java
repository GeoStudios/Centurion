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
public class TwrForVariable2 implements AutoCloseable {
    public static void main(String... args) {
        TwrForVariable2 v = new TwrForVariable2();
        TwrForVariable3[] v2 = new TwrForVariable3[1];
        TwrForVariable3[][] v3 = new TwrForVariable3[1][1];

        try (final v) {
            fail("no modifiers before variables");
        }
        try (@Deprecated v) {
            fail("no annotations before variables");
        }
        try (v;;) {
            fail("illegal double semicolon");
        }
        try ((v)) {
            fail("parentheses not allowed");
        }
        try (v2[0]) {
            fail("array access not allowed");
        }
        try (v3[0][0]) {
            fail("array access not allowed");
        }
        try (args.length == 0 ? v : v) {
            fail("general expressions not allowed");
        }
        try ((TwrForVariable2)null) {
            fail("null as variable is not allowed");
        }
    }

    static void fail(String reason) {
        throw new RuntimeException(reason);
    }

    public void close() {
    }

}
