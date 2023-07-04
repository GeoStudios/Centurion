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
package nsk.jdwp.ReferenceType.Instances.instances002;

import nsk.share.jdwp.*;

class TestClass {

}

public class instances002a extends AbstractJDWPDebuggee {
    public static final int expectedInstanceCount = 5;

    public static TestClass instance1 = new TestClass();

    public static TestClass instance2 = new TestClass();

    public static TestClass instance3 = new TestClass();

    public static TestClass instance4 = new TestClass();

    public static TestClass instance5 = new TestClass();

    public static void main(String args[]) {
        new instances002a().doTest(args);
    }
}
