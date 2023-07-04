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

/**
 * @test
 * @bug 4407610
 * @summary java.net.URLDecode.decode(st,"UTF-16") works incorrectly on '+' sign
 */

import java.net.*;

public class Decoder {

    public static void main(String args[]) throws Exception {

        boolean passed = true;
        String enc = "UTF-16";
        String strings[] = {
            "\u0100\u0101",
                "\u0100 \u0101",
                "\u0100 \u0101\u0102",
                "\u0100 \u0101 \u0102",
                "\u0100C\u0101 \u0102",
                "\u0100\u0101\u0102",
                "?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&",
                "foobar",
                "foo?bar"
        };

        for (int i = 0; i < strings.length; i++) {
            String encoded = URLEncoder.encode(strings[i], enc);
            System.out.println("ecnoded: " + encoded);
            String decoded = URLDecoder.decode(encoded, enc);
            System.out.print("init:    ");
            printString(strings[i]);
            System.out.print("decoded: ");
            printString(decoded);
            if (strings[i].equals(decoded)) {
                System.out.println(" - correct - \n");
            } else {
                System.out.println(" - incorrect - \n");
                throw new RuntimeException ("Unexpected decoded output on string " + i);
            }
        }
    }

    static void printString(String s) {
        for (int i = 0; i < s.length(); i++) {
            System.out.print((int)s.charAt(i) + " ");
        }
        System.out.println();
    }
}
