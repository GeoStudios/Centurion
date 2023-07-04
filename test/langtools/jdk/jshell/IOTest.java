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
 * @summary Test input/output
 * @build KullaTesting TestingInputStream
 * @run testng IOTest
 */

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class IOTest extends KullaTesting {

    String LINE_SEPARATOR = System.getProperty("line.separator");

    public void testOutput() {
        assertEval("System.out.println(\"Test\");");
        assertEquals(getOutput(), "Test" + LINE_SEPARATOR);
    }

    public void testErrorOutput() {
        assertEval("System.err.println(\"Oops\");");
        assertEquals(getErrorOutput(), "Oops" + LINE_SEPARATOR);
    }

    public void testInput() {
        setInput("x");
        assertEval("(char)System.in.read();", "'x'");
    }
}
