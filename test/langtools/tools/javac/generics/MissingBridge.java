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
 * @bug 5021445
 * @summary Calling inherited generics method via interface causes AbstractMethodError
 * @author gafter
 *
 * @compile  MissingBridge.java
 * @run main MissingBridge
 */

class GenObject<T>
{
    public void foo(T obj)
    {
        System.out.println("obj = "+obj);
    }
}

interface TestInterface
{
    void foo(String blah);
}

public class MissingBridge extends GenObject<String> implements TestInterface
{
    public static void main(String[] args)
    {
        MissingBridge test = new MissingBridge();
        TestInterface test2 = test;

        // works
        test.foo("blah");

        // fails with AbstractMethodError
        test2.foo("blah");
    }
}
