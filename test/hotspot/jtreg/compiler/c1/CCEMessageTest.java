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

package compiler.c1;

/**
 * @test
 * @bug 8225644
 * @summary C1 dumps incorrect class name in CCE message
 * @run main/othervm compiler.c1.CCEMessageTest
 * @run main/othervm -Xcomp -XX:TieredStopAtLevel=1 compiler.c1.CCEMessageTest
 */

public class CCEMessageTest {
    public static void main(String... args) {
        String[] s = null;
        try {
            s = (String[])new Object[1];
        } catch (ClassCastException cce) {
            if (!cce.getMessage().contains("[Ljava.lang.String;"))
                throw new AssertionError("Incorrect CCE message", cce);
        }
        if (s != null)
            throw new AssertionError("Unexpected error");
    }
}