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

// dummy package
package nsk.jdi.ReferenceType.dummyPackage;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This is an auxiliary class outside a main debuggee package. It's
 * used to provoke IllegalArgumentException in the debugger:
 * non-public fields cannot be accessed outside the package.
 */
public class getvalues003a {
// dummy private fields
    private static boolean boolPrFld = false;
    private static byte bytePrFld = 127;
    private static char charPrFld = 'b';
    private static double doublePrFld = 6.2D;
    private static float floatPrFld = 5.1F;
    private static int intPrFld = 2147483647;
    private static long longPrFld = 9223372036854775807L;
    private static short shortPrFld = -32768;

// dummy fields with default access
    static boolean boolFld = false;
    static byte byteFld = 127;
    static char charFld = 'a';
    static double doubleFld = 6.2D;
    static float floatFld = 5.1F;
    static int intFld = 2147483647;
    static long longFld = 9223372036854775807L;
    static short shortFld = -32768;

// dummy protected fields
    protected static boolean boolProtFld = true;
    protected static byte byteProtFld = Byte.MIN_VALUE;
    protected static char charProtFld = 'c';
    protected static double doubleProtFld = Double.MAX_VALUE;
    protected static float floatProtFld = Float.MAX_VALUE;
    protected static int intProtFld = Integer.MIN_VALUE;
    protected static long longProtFld = Long.MIN_VALUE;
    protected static short shortProtFld = Short.MAX_VALUE;
}
