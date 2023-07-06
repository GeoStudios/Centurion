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

package pkg;
















/**
 * @deprecated class_test1 passes.
 */
@Deprecated(forRemoval=true)
public class TestClass {

    /**
     * @deprecated class_test2 passes. This is the second sentence of deprecated description for a field.
     */
    public int field;

    /**
     * @deprecated class_test3 passes. This is the second sentence of deprecated description for a constructor.
     */
    @Deprecated(forRemoval=true)
    public TestClass() {}

    /**
     * @deprecated class_test4 passes. Overloaded constructor.
     */
    @Deprecated(forRemoval=true)
    public TestClass(String s) {}

    /**
     * @deprecated class_test5 passes. This is the second sentence of deprecated description for a method.
     */
    public void method() {}

    /**
     * @deprecated class_test6 passes. Overloaded method 1.
     */
    public void overloadedMethod(String s) {}

    /**
     * @deprecated class_test7 passes. Overloaded method 2.
     */
    public void overloadedMethod(int i) {}
}
