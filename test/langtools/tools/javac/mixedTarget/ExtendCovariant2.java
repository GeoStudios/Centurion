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
 * @bug 5009712
 * @summary Ensure Covariant Return Type allowed in minimum supported version
 * @author gafter
 *
 * @compile                  ExtendCovariant2.java
 */

/**
 * java.io.PrintStream java.io.PrintStream.append(char)
 *
 * overrides
 *
 * java.lang.Appendable java.lang.Appendable.append(char)
 *
 * With JDK 1.5, a Covariant Return is allowed so check that is the case.
 *
 */
public class ExtendCovariant2 extends java.io.PrintStream {
    ExtendCovariant2() throws java.io.IOException {
        super("");
    }
    public java.io.PrintStream append(char c) {
        return this;
    }
}
