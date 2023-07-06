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

package pkg1;


import pkg2.*;














/**
 * A test class.
 */
public class C1 implements I1 {

    /**
     * Test field for class.
     */
    public C2 field;

    /**
     * Constant value.
     */
    public static final String CONSTANT1 = "C1";

    /**
     * A test constructor.
     */
    C1() {
    }

    /**
     * Method thats does some processing.
     *
     * @param param some parameter that is passed.
     * @return a sample object.
     */
    public C2 method(C2 param) {
        return param;
    }

    /**
     * Method that is implemented.
     *
     * @param a some random value.
     * @param b some random value.
     */
    public void method1(int a, int b) {
    }

    /**
     * Another inherited method.
     * @param c some value.
     */
    public void method2(int c) {
    }

    /**
     * @deprecated don't use this anymore.
     */
    public void deprecatedMethod() {}
}
