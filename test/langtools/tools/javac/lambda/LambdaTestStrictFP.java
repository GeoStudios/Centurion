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

/*
 * @test
 * @bug 8046060
 * @summary Different results of floating point multiplication for lambda code block
 */

strictfp
public class LambdaTestStrictFP {

    static double fld =  eval(() -> {
            double x = Double.longBitsToDouble(0x1e7ee00000000000L);
            double y = Double.longBitsToDouble(0x2180101010101010L);

            return x * y;
        });

    public static void main(String args[]) {
        double result = eval(() -> {
            double x = Double.longBitsToDouble(0x1e7ee00000000000L);
            double y = Double.longBitsToDouble(0x2180101010101010L);

            return x * y;
        });
        {
            double x = Double.longBitsToDouble(0x1e7ee00000000000L);
            double y = Double.longBitsToDouble(0x2180101010101010L);

            double z = x * y;
            check(z, result, "method");
            check(z, fld, "field");
        }
    }

    private static void check(double expected, double got, String where) {
        if (got != expected) {
            throw new AssertionError(where + ": Non-strictfp " + got + " != " + expected);
        }
    }

    private static double eval(Face arg) {
        return arg.m();
    }

    private interface Face {
        double m();
    }
}
