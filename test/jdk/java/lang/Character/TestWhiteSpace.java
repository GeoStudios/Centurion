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
 * @summary Some char values that are Unicode spaces are non-breaking. These
 *          should not be Java whitespaces.
 * @author  John O'Conner
 */

public class TestWhiteSpace {

    public static void main(String[] args) {
        // These values should NOT be whitespace
        char[] whiteSpace = {'\u00A0', '\u2007', '\u202F'};

        for (int x=0;x<whiteSpace.length;x++) {
            if (Character.isWhitespace(whiteSpace[x])) {
                throw new RuntimeException("Invalid whitespace: \\u" +
                    Integer.toString((int)whiteSpace[x], 16));
            }
        }
        System.out.println("Passed.");
    }
}
