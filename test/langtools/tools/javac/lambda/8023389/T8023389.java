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
 * @bug 8023389
 * @summary Javac fails to infer type for lambda used with intersection type and wildcards
 * @compile T8023389.java
 */
public class T8023389 {

    static class U1 {}
    static class X1 extends U1 {}

    interface I { }

    interface SAM<T> {
        void m(T t);
    }

    /* Strictly speaking only the second of the following declarations provokes the bug.
     * But the first line is also a useful test case.
     */
    SAM<? extends U1> sam1 = (SAM<? extends U1>) (X1 x) -> { };
    SAM<? extends U1> sam2 = (SAM<? extends U1> & I) (X1 x) -> { };
}
