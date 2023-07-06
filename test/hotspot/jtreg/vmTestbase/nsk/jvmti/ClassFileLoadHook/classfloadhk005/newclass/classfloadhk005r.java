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
















/** Instrumented tested class with new fields and methods. */
public class classfloadhk005r {
    static long staticField = 0;

    static {
        staticField = 200;
    }

    int intField;
    long longField;

    public classfloadhk005r(int n, long m) {
        intField = n;
        longField = m;
    }

    public int intMethod(int i) {
        return (i + intField);
    }

    public long longMethod(int i) {
        return intMethod(i) * longField;
    }

    // expected to return 2200
    public static long testedStaticMethod() {
        classfloadhk005r obj = new classfloadhk005r(10, 20);
        return obj.longMethod(90) + staticField;
    }
}
