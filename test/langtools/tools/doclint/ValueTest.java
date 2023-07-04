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

/** */
public class ValueTest {
    /*
     * Tests for {@value} without a reference
     */

    /** valid: {@value} */
    public static final boolean cBoolean = false;

    /** valid: {@value} */
    public static final byte cByte = 0;

    /** valid: {@value} */
    public static final short cShort = 0;

    /** valid: {@value} */
    public static final int cInt = 0;

    /** valid: {@value} */
    public static final long cLong = 0L;

    /** valid: {@value} */
    public static final float cFloat = 0.0f;

    /** valid: {@value} */
    public static final double cDouble = 0.0;

    /** valid: {@value} */
    public static final String cString = "";

    /** invalid class C: {@value} */
    public class C { }

    /** invalid enum E: {@value} */
    public enum E {
        /** invalid enum constant E1: {@value} */
        E1
    }

    /** invalid field 1: {@value} */
    public int f1;

    /** invalid field 2: {@value} */
    public int f2 = 3;


    /*
     * Tests for {@value} with a reference
     */

    /** valid: {@value Integer#SIZE} */
    public int intRef;

    /** invalid method: {@value Object#toString} */
    public int badMethod;

    /** invalid enum constant: {@value Thread.State#NEW} */
    public int badEnum;
}
