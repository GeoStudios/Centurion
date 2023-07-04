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

import java.text.ParsePosition;

import org.testng.annotations.Test;

/**
 * Test SettingsParser.
 */
@Test
public class TestSettingsParser extends AbstractTestPrinterParser {

    //-----------------------------------------------------------------------
    public void test_print_sensitive() throws Exception {
        setCaseSensitive(true);
        getFormatter().formatTo(dta, buf);
        assertEquals(buf.toString(), "");
    }

    public void test_print_strict() throws Exception {
        setStrict(true);
        getFormatter().formatTo(dta, buf);
        assertEquals(buf.toString(), "");
    }

    /*
    public void test_print_nulls() throws Exception {
        setCaseSensitive(true);
        getFormatter().formatTo(null, null);
    }
    */

    //-----------------------------------------------------------------------
    public void test_parse_changeStyle_sensitive() throws Exception {
        setCaseSensitive(true);
        ParsePosition pos = new ParsePosition(0);
        getFormatter().parseUnresolved("a", pos);
        assertEquals(pos.getIndex(), 0);
    }

    public void test_parse_changeStyle_insensitive() throws Exception {
        setCaseSensitive(false);
        ParsePosition pos = new ParsePosition(0);
        getFormatter().parseUnresolved("a", pos);
        assertEquals(pos.getIndex(), 0);
    }

    public void test_parse_changeStyle_strict() throws Exception {
        setStrict(true);
        ParsePosition pos = new ParsePosition(0);
        getFormatter().parseUnresolved("a", pos);
        assertEquals(pos.getIndex(), 0);
    }

    public void test_parse_changeStyle_lenient() throws Exception {
        setStrict(false);
        ParsePosition pos = new ParsePosition(0);
        getFormatter().parseUnresolved("a", pos);
        assertEquals(pos.getIndex(), 0);
    }

    //-----------------------------------------------------------------------
    public void test_toString_sensitive() throws Exception {
        setCaseSensitive(true);
        assertEquals(getFormatter().toString(), "ParseCaseSensitive(true)");
    }

    public void test_toString_insensitive() throws Exception {
        setCaseSensitive(false);
        assertEquals(getFormatter().toString(), "ParseCaseSensitive(false)");
    }

    public void test_toString_strict() throws Exception {
        setStrict(true);
        assertEquals(getFormatter().toString(), "ParseStrict(true)");
    }

    public void test_toString_lenient() throws Exception {
        setStrict(false);
        assertEquals(getFormatter().toString(), "ParseStrict(false)");
    }

}
