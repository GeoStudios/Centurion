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
 * @bug     4427146
 * @summary Undefined char values should have DIRECTIONALITY_UNDEFINED.
 * @author  John O'Conner
 */

public class TestUndefinedDirectionality {

    public static void main(String[] args) {
        int failures = 0;
        for (int ch=0x0000;ch <= 0xFFFF; ch++) {
            if (!Character.isDefined((char)ch)) {
                byte direction = Character.getDirectionality((char)ch);
                if (direction != Character.DIRECTIONALITY_UNDEFINED) {
                    System.err.println("Fail: \\u" + Integer.toString(ch, 16));
                    failures++;
                }
            }
        }
        if (failures != 0) {
            throw new RuntimeException("TestUndefinedDirectionality: failed.");
        } else {
            System.out.println("Passed.");
        }


    }
}
