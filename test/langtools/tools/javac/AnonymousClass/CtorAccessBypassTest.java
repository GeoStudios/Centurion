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
 * @bug 8075799
 *
 * @summary Extraneous access checks implemented by javac
 * @compile CtorAccessBypassTest.java
 * @run main CtorAccessBypassTest
 *
 */

public class CtorAccessBypassTest {
    public static void main(String[] args) {
        new CtorAccessBypassTest_01<Object>(null) {};
        new CtorAccessBypassTest_01<>(null) {};
    }
}

class CtorAccessBypassTest_01<T> {
    private class Private {}
      CtorAccessBypassTest_01(Private p) {
    }
}
