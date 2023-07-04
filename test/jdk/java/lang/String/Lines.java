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
 * @summary Basic lines functionality
 * @bug 8200380
 * @run main/othervm Lines
 */

import java.util.Iterator;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.StringReader;

public class Lines {
    public static void main(String... arg) {
        testLines();
    }

    /*
     * Test with strings
     */
    static void testLines() {
        testString("");
        testString(" ");
        testString("\n");
        testString("\n\n\n");
        testString("\r\r\r");
        testString("\r\n\r\n\r\n");
        testString("\n\r\r\n");
        testString("abc\ndef\nghi\n");
        testString("abc\ndef\nghi");
        testString("abc\rdef\rghi\r");
        testString("abc\rdef\rghi");
        testString("abc\r\ndef\r\nghi\r\n");
        testString("abc\r\ndef\r\nghi");

        testString("\2022");
        testString("\2022\n");
        testString("\2022\n\2022\n\2022\n");
        testString("\2022\r\2022\r\2022\r");
        testString("\2022\r\n\2022\r\n\2022\r\n");
        testString("\2022\n\2022\r\2022\r\n");
        testString("abc\2022\ndef\2022\nghi\2022\n");
        testString("abc\2022\ndef\2022\nghi\2022");
        testString("abc\2022\rdef\2022\rghi\2022\r");
        testString("abc\2022\rdef\2022\rghi\2022");
        testString("abc\2022\r\ndef\2022\r\nghi\2022\r\n");
        testString("abc\2022\r\ndef\2022\r\nghi\2022");
        testString("\2022\n\n\n");
    }

    static void testString(String string) {
        Stream<String> lines = string.lines();
        Stream<String> brLines = new BufferedReader(new StringReader(string)).lines();

        Iterator<String> iterator = lines.iterator();
        Iterator<String> brIterator = brLines.iterator();
        int count = 0;

        while (iterator.hasNext() && brIterator.hasNext()) {
            count++;
            String line = iterator.next();
            String brLine = brIterator.next();

            if (!line.equals(brLine)) {
                String replace = string.replaceAll("\n", "\\n").replaceAll("\r", "\\r");
                System.err.format("Mismatch at line %d of \"%s\"%n", count, replace);
                throw new RuntimeException();
            }
        }

        if (iterator.hasNext() || brIterator.hasNext()) {
            System.err.format("Mismatch after line %d of \"%s\"%n", count, string);
            throw new RuntimeException();
        }
    }
}
