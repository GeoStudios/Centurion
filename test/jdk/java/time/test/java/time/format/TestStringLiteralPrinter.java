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

package test.java.time.format;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * Test StringLiteralPrinterParser.
 */
@Test
public class TestStringLiteralPrinter extends AbstractTestPrinterParser {

    //-----------------------------------------------------------------------
    public void test_print_emptyCalendrical() throws Exception {
        buf.append("EXISTING");
        getFormatter("hello").formatTo(EMPTY_DTA, buf);
        assertEquals(buf.toString(), "EXISTINGhello");
    }

    public void test_print_dateTime() throws Exception {
        buf.append("EXISTING");
        getFormatter("hello").formatTo(dta, buf);
        assertEquals(buf.toString(), "EXISTINGhello");
    }




    public void test_print_emptyAppendable() throws Exception {
        getFormatter("hello").formatTo(dta, buf);
        assertEquals(buf.toString(), "hello");
    }

    //-----------------------------------------------------------------------
    public void test_toString() throws Exception {
        assertEquals(getFormatter("hello").toString(), "'hello'");
    }

    public void test_toString_apos() throws Exception {
        assertEquals(getFormatter("o'clock").toString(), "'o''clock'");
    }

}
