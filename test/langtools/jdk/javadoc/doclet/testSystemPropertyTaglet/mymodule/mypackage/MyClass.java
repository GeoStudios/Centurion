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

package mypackage;
















/**
 * (class) the {@systemProperty test.property} system property.
 */
public class MyClass {

    /**
     * (class/field) the {@systemProperty test.property} system property.
     */
    public int intField;

    /**
     * (class/static-field) the {@systemProperty test.property} system property.
     */
    public final static int INT_CONSTANT = 42;

    /**
     * (class/static-method) the {@systemProperty test.property} system property.
     *
     * @return an integer, 42
     */
    public static Object value() { return INT_CONSTANT; }

    /**
     * (class/constructor) the {@systemProperty test.property} system property.
     */
    public MyClass() { }

    /**
     * (class/method) the {@systemProperty test.property} system property.
     */
    public void run() { }
}