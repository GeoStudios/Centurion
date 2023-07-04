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

import static java.time.temporal.ChronoField.YEAR;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.text.ParsePosition;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test CharLiteralPrinterParser.
 */
@Test
public class TestCharLiteralParser extends AbstractTestPrinterParser {

    @DataProvider(name="success")
    Object[][] data_success() {
        return new Object[][] {
            // match
            {'a', true, "a", 0, 1},
            {'a', true, "aOTHER", 0, 1},
            {'a', true, "OTHERaOTHER", 5, 6},
            {'a', true, "OTHERa", 5, 6},

            // no match
            {'a', true, "", 0, 0},
            {'a', true, "a", 1, 1},
            {'a', true, "A", 0, 0},
            {'a', true, "b", 0, 0},
            {'a', true, "OTHERbOTHER", 5, 5},
            {'a', true, "OTHERb", 5, 5},

            // case insensitive
            {'a', false, "a", 0, 1},
            {'a', false, "A", 0, 1},
        };
    }

    @Test(dataProvider="success")
    public void test_parse_success(char c, boolean caseSensitive,
                                   String text, int pos, int expectedPos) {
        setCaseSensitive(caseSensitive);
        ParsePosition ppos = new ParsePosition(pos);
        TemporalAccessor parsed = getFormatter(c).parseUnresolved(text, ppos);
        if (ppos.getErrorIndex() != -1) {
            assertEquals(ppos.getIndex(), expectedPos);
        } else {
            assertEquals(ppos.getIndex(), expectedPos);
            assertEquals(parsed.isSupported(YEAR), false);
            assertEquals(parsed.query(TemporalQueries.chronology()), null);
            assertEquals(parsed.query(TemporalQueries.zoneId()), null);
        }
    }

    //-----------------------------------------------------------------------
    @DataProvider(name="error")
    Object[][] data_error() {
        return new Object[][] {
            {'a', "a", -1, IndexOutOfBoundsException.class},
            {'a', "a", 2, IndexOutOfBoundsException.class},
        };
    }

    @Test(dataProvider="error")
    public void test_parse_error(char c, String text, int pos, Class<?> expected) {
        try {
            ParsePosition ppos = new ParsePosition(pos);
            getFormatter(c).parseUnresolved(text, ppos);
            fail();
        } catch (RuntimeException ex) {
            assertTrue(expected.isInstance(ex));
        }
    }
}
