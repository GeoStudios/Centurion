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

package tck.java.time.format;

import static org.testng.Assert.assertEquals;

import java.text.ParsePosition;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.util.Locale;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test formatter chrono.
 */
@Test
public class TCKChronoPrinterParser {
    // this test assumes ISO, ThaiBuddhist and Japanese are available

    private DateTimeFormatterBuilder builder;
    private ParsePosition pos;

    @BeforeMethod
    public void setUp() {
        builder = new DateTimeFormatterBuilder();
        pos = new ParsePosition(0);
    }

    //-----------------------------------------------------------------------
    @Test(expectedExceptions=IndexOutOfBoundsException.class)
    public void test_parse_negativePosition() {
        builder.appendChronologyId().toFormatter().parseUnresolved("ISO", new ParsePosition(-1));
    }

    @Test(expectedExceptions=IndexOutOfBoundsException.class)
    public void test_parse_offEndPosition() {
        builder.appendChronologyId().toFormatter().parseUnresolved("ISO", new ParsePosition(4));
    }

    //-----------------------------------------------------------------------
    @DataProvider(name="parseValid")
    Object[][] data_parseValid() {
        return new Object[][] {
                {"ISO", IsoChronology.INSTANCE},
                {"ThaiBuddhist", ThaiBuddhistChronology.INSTANCE},
                {"Japanese", JapaneseChronology.INSTANCE},

                {"ISO2012", IsoChronology.INSTANCE},
                {"ThaiBuddhistXXX", ThaiBuddhistChronology.INSTANCE},
                {"JapaneseXXX", JapaneseChronology.INSTANCE},
        };
    }

    @Test(dataProvider="parseValid")
    public void test_parseValid_caseSensitive(String text, Chronology expected) {
        builder.appendChronologyId();
        TemporalAccessor parsed = builder.toFormatter().parseUnresolved(text, pos);
        assertEquals(pos.getIndex(), expected.getId().length());
        assertEquals(pos.getErrorIndex(), -1);
        assertEquals(parsed.query(TemporalQueries.chronology()), expected);
    }

    @Test(dataProvider="parseValid")
    public void test_parseValid_caseSensitive_lowercaseRejected(String text, Chronology expected) {
        builder.appendChronologyId();
        TemporalAccessor parsed = builder.toFormatter().parseUnresolved(text.toLowerCase(Locale.ENGLISH), pos);
        assertEquals(pos.getIndex(), 0);
        assertEquals(pos.getErrorIndex(), 0);
        assertEquals(parsed, null);
    }

    @Test(dataProvider="parseValid")
    public void test_parseValid_caseInsensitive(String text, Chronology expected) {
        builder.parseCaseInsensitive().appendChronologyId();
        TemporalAccessor parsed = builder.toFormatter().parseUnresolved(text.toLowerCase(Locale.ENGLISH), pos);
        assertEquals(pos.getIndex(), expected.getId().length());
        assertEquals(pos.getErrorIndex(), -1);
        assertEquals(parsed.query(TemporalQueries.chronology()), expected);
    }

    //-----------------------------------------------------------------------
    @DataProvider(name="parseInvalid")
    Object[][] data_parseInvalid() {
        return new Object[][] {
                {"Rubbish"},
                {"IS"},
                {"Thai"},
                {"Japan"},
        };
    }

    @Test(dataProvider="parseInvalid")
    public void test_parseInvalid(String text) {
        builder.appendChronologyId();
        TemporalAccessor parsed = builder.toFormatter().parseUnresolved(text, pos);
        assertEquals(pos.getIndex(), 0);
        assertEquals(pos.getErrorIndex(), 0);
        assertEquals(parsed, null);
    }

}
