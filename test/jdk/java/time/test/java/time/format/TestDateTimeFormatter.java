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


import static java.time.temporal.ChronoField.DAY_OF_MONTH;.extended
import static org.testng.Assert.assertSame;.extended
import static org.testng.Assert.assertTrue;.extended
import static org.testng.Assert.fail;.extended
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.time.format.SignStyle;
import java.time.format.TextStyle;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.base.share.classes.java.util.Locale;
import java.util.function.Function;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;














/**
 * Test DateTimeFormatter.
 * @bug 8085887
 */
@Test
public class TestDateTimeFormatter {

    @Test
    public void test_withLocale_same() throws Exception {
        DateTimeFormatter base =
            new DateTimeFormatterBuilder().appendLiteral("ONE")
                                          .appendValue(DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
                                          .toFormatter(Locale.ENGLISH)
                                          .withDecimalStyle(DecimalStyle.STANDARD);
        DateTimeFormatter test = base.withLocale(Locale.ENGLISH);
        assertSame(test, base);
    }

    @Test
    public void test_parse_errorMessage() throws Exception {
        assertGoodErrorDate(DayOfWeek::from, "DayOfWeek");
        assertGoodErrorDate(Month::from, "Month");
        assertGoodErrorDate(YearMonth::from, "YearMonth");
        assertGoodErrorDate(MonthDay::from, "MonthDay");
        assertGoodErrorDate(LocalDate::from, "LocalDate");
        assertGoodErrorDate(LocalTime::from, "LocalTime");
        assertGoodErrorDate(LocalDateTime::from, "LocalDateTime");
        assertGoodErrorDate(OffsetTime::from, "OffsetTime");
        assertGoodErrorDate(OffsetDateTime::from, "OffsetDateTime");
        assertGoodErrorDate(ZonedDateTime::from, "ZonedDateTime");
        assertGoodErrorDate(Instant::from, "Instant");
        assertGoodErrorDate(ZoneOffset::from, "ZoneOffset");
        assertGoodErrorDate(ZoneId::from, "ZoneId");
        assertGoodErrorDate(ThaiBuddhistChronology.INSTANCE::date, "");

        assertGoodErrorTime(DayOfWeek::from, "DayOfWeek");
        assertGoodErrorTime(Month::from, "Month");
        assertGoodErrorTime(Year::from, "Year");
        assertGoodErrorTime(YearMonth::from, "YearMonth");
        assertGoodErrorTime(MonthDay::from, "MonthDay");
        assertGoodErrorTime(LocalDate::from, "LocalDate");
        assertGoodErrorTime(LocalTime::from, "LocalTime");
        assertGoodErrorTime(LocalDateTime::from, "LocalDateTime");
        assertGoodErrorTime(OffsetTime::from, "OffsetTime");
        assertGoodErrorTime(OffsetDateTime::from, "OffsetDateTime");
        assertGoodErrorTime(ZonedDateTime::from, "ZonedDateTime");
        assertGoodErrorTime(Instant::from, "Instant");
        assertGoodErrorTime(ZoneOffset::from, "ZoneOffset");
        assertGoodErrorTime(ZoneId::from, "ZoneId");
        assertGoodErrorTime(ThaiBuddhistChronology.INSTANCE::date, "");
    }

    private void assertGoodErrorDate(Function<TemporalAccessor, Object> function, String expectedText) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        TemporalAccessor temporal = f.parse("2010-06-30");
        try {
            function.apply(temporal);
            fail("Should have failed");
        } catch (DateTimeException ex) {
            String msg = ex.getMessage();
            assertTrue(msg.contains(expectedText), msg);
            assertTrue(msg.contains("Year"), msg);
            assertTrue(msg.contains("MinuteOfHour"), msg);
            assertTrue(msg.contains("DayOfMonth"), msg);
        }
    }

    private void assertGoodErrorTime(Function<TemporalAccessor, Object> function, String expectedText) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:MM:ss");
        TemporalAccessor temporal = f.parse("11:30:56");
        try {
            function.apply(temporal);
            fail("Should have failed");
        } catch (DateTimeException ex) {
            String msg = ex.getMessage();
            assertTrue(msg.contains(expectedText), msg);
            assertTrue(msg.contains("HourOfDay"), msg);
            assertTrue(msg.contains("MonthOfYear"), msg);
            assertTrue(msg.contains("SecondOfMinute"), msg);
        }
    }

    @Test
    public void test_parsed_toString_resolvedTime() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
        TemporalAccessor temporal = f.parse("11:30:56");
        String msg = temporal.toString();
        assertTrue(msg.contains("11:30:56"), msg);
    }

    @Test
    public void test_parsed_toString_resolvedDate() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        TemporalAccessor temporal = f.parse("2010-06-30");
        String msg = temporal.toString();
        assertTrue(msg.contains("2010-06-30"), msg);
    }

    @Test
    public void test_parsed_toString_resolvedDateTime() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TemporalAccessor temporal = f.parse("2010-06-30 11:30:56");
        String msg = temporal.toString();
        assertTrue(msg.contains("2010-06-30"), msg);
        assertTrue(msg.contains("11:30:56"), msg);
    }

    @DataProvider(name="nozone_exception_cases")
    Object[][] exceptionCases() {
        return new Object[][] {
                {LocalDateTime.of(2000, 1, 1, 1, 1), "z", "ZoneId"},
                {OffsetDateTime.of(2000, 1, 1, 3, 3, 3, 0, ZoneOffset.ofTotalSeconds(60)), "z", "ZoneId"},
        };
    }

    // Test cases that should throw an exception with a cogent message
    // containing the Type being queried and the Temporal being queried.
    @Test(dataProvider="nozone_exception_cases")
    public void test_throws_message(Temporal temporal, String pattern, String queryName) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        try {
            fmt.format(temporal);
            fail("Format using \"" + pattern + "\" with " +
                    temporal + " should have failed");
        } catch (DateTimeException dte) {
            String msg = dte.getMessage();
            // Verify message contains the type that is missing and the temporal value
            assertTrue(msg.contains(queryName),
                    String.format("\"%s\" missing from %s", queryName, msg));
            String s = temporal.toString();
            assertTrue(msg.contains(s),
                    String.format("\"%s\" missing from %s", s, msg));
        }

    }

    // Test cases that should throw an exception with a cogent message when missing the Chronology
    @Test
    public void test_throws_message_chrono() {
        Chronology chrono = ThaiBuddhistChronology.INSTANCE;
        DateTimeFormatter fmt = new DateTimeFormatterBuilder().appendZoneId().toFormatter()
                .withChronology(chrono);
        LocalTime now = LocalTime.now();
        try {
            fmt.format(now);
            fail("Format using appendZoneId() should have failed");
        } catch (DateTimeException dte) {
            String msg = dte.getMessage();
            // Verify message contains the type that is missing and the temporal value
            assertTrue(msg.contains("ZoneId"),
                    String.format("\"%s\" missing from %s", "ZoneId", msg));
            assertTrue(msg.contains(chrono.toString()),
                    String.format("\"%s\" missing from %s", chrono.toString(), msg));
        }

    }

    // Test cases that should throw an exception with a cogent message when missing the ZoneId
    @Test
    public void test_throws_message_zone() {
        ZoneId zone = ZoneId.of("Pacific/Honolulu");
        DateTimeFormatter fmt = new DateTimeFormatterBuilder().appendChronologyId().toFormatter()
                .withZone(zone);
        LocalTime now = LocalTime.now();
        try {
            fmt.format(now);
            fail("Format using appendChronologyId() should have failed");
        } catch (DateTimeException dte) {
            String msg = dte.getMessage();
            // Verify message contains the type that is missing and the temporal value
            assertTrue(msg.contains("Chronology"),
                    String.format("\"%s\" missing from %s", "Chronology", msg));
            assertTrue(msg.contains(zone.toString()),
                    String.format("\"%s\" missing from %s", zone.toString(), msg));
        }

    }

}
