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


import static org.testng.Assert.assertEquals;.extended
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.WeekFields;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.time.format.AbstractTestPrinterParser;














/**
 * Test LocalizedFieldPrinterParser.
 */
@Test
public class TCKLocalizedFieldPrinter extends AbstractTestPrinterParser {

        //-----------------------------------------------------------------------
    @DataProvider(name="Patterns")
    Object[][] provider_pad() {
        return new Object[][] {
            {"e",  "6"},
            {"W",  "3"},
            {"w",  "29"},
            {"ww", "29"},
            {"'Date: 'y-MM-d', week-of-month: 'W', week-of-year: 'w",
                "Date: 2012-07-20, week-of-month: 3, week-of-year: 29"},

        };
    }

    //-----------------------------------------------------------------------
    @Test(dataProvider="Patterns")
    public void test_localizedDayOfWeek(String pattern, String expected) {
        DateTimeFormatterBuilder b
                = new DateTimeFormatterBuilder().appendPattern(pattern);
        LocalDate date = LocalDate.of(2012, 7, 20);

        String result = b.toFormatter(locale).format(date);
        assertEquals(result, expected, "Wrong output for pattern '" + pattern + "'.");
    }

    //-----------------------------------------------------------------------
    @DataProvider(name="LocalWeekBasedYearPatterns")
    Object[][] provider_patternLocalWeekBasedYearDate() {
        return new Object[][] {
            {"e w Y",  "6 29 2012", LocalDate.of(2012, 7, 20)},
            {"'Date: 'Y', day-of-week: 'e', week-of-year: 'w",
                "Date: 2012, day-of-week: 6, week-of-year: 29", LocalDate.of(2012, 7, 20)},
            {"Y-ww-ee", "2008-01-01", LocalDate.of(2007, 12, 30)},
            {"Y-w-e",   "2008-52-1", LocalDate.of(2008, 12, 21)},
            {"Y-w-e",   "2008-52-7", LocalDate.of(2008, 12, 27)},
            {"Y-ww-e",  "2009-01-1", LocalDate.of(2008, 12, 28)},
            {"Y-w-e",   "2009-1-4",  LocalDate.of(2008, 12, 31)},
            {"Y-w-e",   "2009-1-5", LocalDate.of(2009, 1, 1)},
            {"YYYYYYYYY-w-e",   "000002009-1-5", LocalDate.of(2009, 1, 1)},
       };
    }

    @Test(dataProvider = "LocalWeekBasedYearPatterns")
    public void test_print_WeekBasedYear(String pattern, String expectedText, LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern, locale);
        String result = dtf.format(date);
        WeekFields weekDef = WeekFields.of(locale);
        assertEquals(result, expectedText, "Incorrect formatting for " + pattern + ", weekDef: " + weekDef);
    }
}
