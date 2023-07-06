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

package nsk.jdi.ClassType.newInstance;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 *  <code>newinstance005a</code> is deugee's part of the newinstance005.
 */
public class newinstance005a extends AbstractJDIDebuggee {

    private static byte     byteValue;
    private static char     charValue;
    private static double   doubleValue;
    private static float    floatValue;
    private static int      intValue;
    private static long     longValue;
    private static short    shortValue;

    public static void main (String args[]) {
        new newinstance005a().doTest(args);
    }

    newinstance005a() {

    }

    newinstance005a(byte value) {
        byteValue = value;
    }

    newinstance005a(char value) {
        charValue = value;
    }

    newinstance005a(double value) {
        doubleValue = value;
    }

    newinstance005a(float value) {
        floatValue = value;
    }

    newinstance005a(int value) {
        intValue = value;
    }

    newinstance005a(long value) {
        longValue = value;
    }

    newinstance005a(short value) {
        shortValue = value;
    }

}