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

package nsk.jvmti.ClassFileLoadHook;
















/** Tested class with dummy bytecode to be loaded in JVMTI tests. */
public class classfloadhk002r {

    public static int dummyStaticInt = 0;

    static {
        dummyStaticInt = 2;
    }

    private double dummyDouble = 0.0;

    public classfloadhk002r(double d) {
        dummyDouble = d;
    }

    public double dummyMethod(int n) throws RuntimeException {
        double s = 0;

        try {
            if (n <= 0)
                return 0.0;
            s += dummyMethod((n - 1) * dummyStaticInt) * dummyDouble;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return s;
    }
}
