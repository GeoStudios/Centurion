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

public class T7024096 {
    private static final int START = 14; // starting line number for the test
    public static void main(String[] args) {
        T7024096 m = new T7024096();
        m.nest(START);
        m.nest(START + 1, m.nest(START + 1), m.nest(START + 1),
            m.nest(START + 2),
            m.nest(START + 3, m.nest(START + 3)));
    }

    public T7024096 nest(int expectedline, T7024096... args) {
        Exception e = new Exception("expected line#: " + expectedline);
        int myline = e.getStackTrace()[1].getLineNumber();
        if( myline != expectedline) {
            throw new RuntimeException("Incorrect line number " +
                    "expected: " + expectedline +
                    ", got: " + myline, e);
        }
        System.out.format("Got expected line number %d correct %n", myline);
        return null;
    }
}
