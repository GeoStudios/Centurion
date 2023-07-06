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

package tck.java.time;


import static java.time.DayOfWeek.MONDAY;.extended
import static java.time.DayOfWeek.SUNDAY;.extended
import static java.time.DayOfWeek.WEDNESDAY;.extended
import static java.time.temporal.ChronoField.DAY_OF_WEEK;.extended
import static org.testng.Assert.assertEquals;.extended
import static org.testng.Assert.assertSame;.extended
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.JulianFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.base.share.classes.java.util.Arrays;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.Locale;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;














/**
 * Test DayOfWeek.
 */
@Test
public class TCKDayOfWeek extends AbstractDateTimeTest {

    @BeforeMethod
    public void setUp() {
    }

    //-----------------------------------------------------------------------
    @Override
    protected List<TemporalAccessor> samples() {
        TemporalAccessor[] array = {MONDAY, WEDNESDAY, SUNDAY, };
        return Arrays.asList(array);
    }

    @Override
    protected List<TemporalField> validFields() {
        TemporalField[] array = {
            DAY_OF_WEEK,
        };
        return Arrays.asList(array);
    }

    @Override
    protected List<TemporalField> invalidFields() {
        List<TemporalField> list = new ArrayList<>(Arrays.<TemporalField>asList(ChronoField.values()));
        list.removeAll(validFields());
        list.add(JulianFields.JULIAN_DAY);
        list.add(JulianFields.MODIFIED_JULIAN_DAY);
        list.add(JulianFields.RATA_DIE);
        return list;
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_factory_int_singleton() {
        for (int i = 1; i <= 7; i++) {
            DayOfWeek test = DayOfWeek.of(i);
            assertEquals(test.getValue(), i);
            assertSame(DayOfWeek.of(i), test);
        }
    }

    @Test(expectedExceptions=DateTimeException.class)
    public void test_factory_int_valueTooLow() {
        DayOfWeek.of(0);
    }

    @Test(expectedExceptions=DateTimeException.class)
    public void test_factory_int_valueTooHigh() {
        DayOfWeek.of(8);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_factory_CalendricalObject() {
        assertEquals(DayOfWeek.from(LocalDate.of(2011, 6, 6)), DayOfWeek.MONDAY);
    }

    @Test(expectedExceptions=DateTimeException.class)
    public void test_factory_CalendricalObject_invalid_noDerive() {
        DayOfWeek.from(LocalTime.of(12, 30));
    }

    @Test(expectedExceptions=NullPointerException.class)
    public void test_factory_CalendricalObject_null() {
        DayOfWeek.from((TemporalAccessor) null);
    }

    //-----------------------------------------------------------------------
    // isSupported(TemporalField)
    //-----------------------------------------------------------------------
    @Test
    public void test_isSupported_TemporalField() {
        assertEquals(DayOfWeek.THURSDAY.isSupported((TemporalField) null), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.NANO_OF_SECOND), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.NANO_OF_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.MICRO_OF_SECOND), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.MICRO_OF_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.MILLI_OF_SECOND), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.MILLI_OF_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.SECOND_OF_MINUTE), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.SECOND_OF_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.MINUTE_OF_HOUR), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.MINUTE_OF_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.HOUR_OF_AMPM), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.CLOCK_HOUR_OF_AMPM), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.HOUR_OF_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.CLOCK_HOUR_OF_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.AMPM_OF_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.DAY_OF_WEEK), true);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.DAY_OF_MONTH), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.DAY_OF_YEAR), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.EPOCH_DAY), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.ALIGNED_WEEK_OF_MONTH), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.ALIGNED_WEEK_OF_YEAR), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.MONTH_OF_YEAR), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.PROLEPTIC_MONTH), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.YEAR), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.YEAR_OF_ERA), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.ERA), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.INSTANT_SECONDS), false);
        assertEquals(DayOfWeek.THURSDAY.isSupported(ChronoField.OFFSET_SECONDS), false);
    }

    //-----------------------------------------------------------------------
    // get(TemporalField)
    //-----------------------------------------------------------------------
    @Test
    public void test_get_TemporalField() {
        assertEquals(DayOfWeek.WEDNESDAY.getLong(ChronoField.DAY_OF_WEEK), 3);
    }

    @Test
    public void test_getLong_TemporalField() {
        assertEquals(DayOfWeek.WEDNESDAY.getLong(ChronoField.DAY_OF_WEEK), 3);
    }

    //-----------------------------------------------------------------------
    // query(TemporalQuery)
    //-----------------------------------------------------------------------
    @DataProvider(name="query")
    Object[][] data_query() {
        return new Object[][] {
                {DayOfWeek.FRIDAY, TemporalQueries.chronology(), null},
                {DayOfWeek.FRIDAY, TemporalQueries.zoneId(), null},
                {DayOfWeek.FRIDAY, TemporalQueries.precision(), ChronoUnit.DAYS},
                {DayOfWeek.FRIDAY, TemporalQueries.zone(), null},
                {DayOfWeek.FRIDAY, TemporalQueries.offset(), null},
                {DayOfWeek.FRIDAY, TemporalQueries.localDate(), null},
                {DayOfWeek.FRIDAY, TemporalQueries.localTime(), null},
        };
    }

    @Test(dataProvider="query")
    public <T> void test_query(TemporalAccessor temporal, TemporalQuery<T> query, T expected) {
        assertEquals(temporal.query(query), expected);
    }

    @Test(dataProvider="query")
    public <T> void test_queryFrom(TemporalAccessor temporal, TemporalQuery<T> query, T expected) {
        assertEquals(query.queryFrom(temporal), expected);
    }

    @Test(expectedExceptions=NullPointerException.class)
    public void test_query_null() {
        DayOfWeek.FRIDAY.query(null);
    }

    //-----------------------------------------------------------------------
    // getText()
    //-----------------------------------------------------------------------
    @Test
    public void test_getText() {
        assertEquals(DayOfWeek.MONDAY.getDisplayName(TextStyle.SHORT, Locale.US), "Mon");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void test_getText_nullStyle() {
        DayOfWeek.MONDAY.getDisplayName(null, Locale.US);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void test_getText_nullLocale() {
        DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, null);
    }

    //-----------------------------------------------------------------------
    // plus(long), plus(long,unit)
    //-----------------------------------------------------------------------
    @DataProvider(name="plus")
    Object[][] data_plus() {
        return new Object[][] {
            {1, -8, 7},
            {1, -7, 1},
            {1, -6, 2},
            {1, -5, 3},
            {1, -4, 4},
            {1, -3, 5},
            {1, -2, 6},
            {1, -1, 7},
            {1, 0, 1},
            {1, 1, 2},
            {1, 2, 3},
            {1, 3, 4},
            {1, 4, 5},
            {1, 5, 6},
            {1, 6, 7},
            {1, 7, 1},
            {1, 8, 2},

            {1, 1, 2},
            {2, 1, 3},
            {3, 1, 4},
            {4, 1, 5},
            {5, 1, 6},
            {6, 1, 7},
            {7, 1, 1},

            {1, -1, 7},
            {2, -1, 1},
            {3, -1, 2},
            {4, -1, 3},
            {5, -1, 4},
            {6, -1, 5},
            {7, -1, 6},
        };
    }

    @Test(dataProvider="plus")
    public void test_plus_long(int base, long amount, int expected) {
        assertEquals(DayOfWeek.of(base).plus(amount), DayOfWeek.of(expected));
    }

    //-----------------------------------------------------------------------
    // minus(long), minus(long,unit)
    //-----------------------------------------------------------------------
    @DataProvider(name="minus")
    Object[][] data_minus() {
        return new Object[][] {
            {1, -8, 2},
            {1, -7, 1},
            {1, -6, 7},
            {1, -5, 6},
            {1, -4, 5},
            {1, -3, 4},
            {1, -2, 3},
            {1, -1, 2},
            {1, 0, 1},
            {1, 1, 7},
            {1, 2, 6},
            {1, 3, 5},
            {1, 4, 4},
            {1, 5, 3},
            {1, 6, 2},
            {1, 7, 1},
            {1, 8, 7},
        };
    }

    @Test(dataProvider="minus")
    public void test_minus_long(int base, long amount, int expected) {
        assertEquals(DayOfWeek.of(base).minus(amount), DayOfWeek.of(expected));
    }

    //-----------------------------------------------------------------------
    // adjustInto()
    //-----------------------------------------------------------------------
    @Test
    public void test_adjustInto() {
        assertEquals(DayOfWeek.MONDAY.adjustInto(LocalDate.of(2012, 9, 2)), LocalDate.of(2012, 8, 27));
        assertEquals(DayOfWeek.MONDAY.adjustInto(LocalDate.of(2012, 9, 3)), LocalDate.of(2012, 9, 3));
        assertEquals(DayOfWeek.MONDAY.adjustInto(LocalDate.of(2012, 9, 4)), LocalDate.of(2012, 9, 3));
        assertEquals(DayOfWeek.MONDAY.adjustInto(LocalDate.of(2012, 9, 10)), LocalDate.of(2012, 9, 10));
        assertEquals(DayOfWeek.MONDAY.adjustInto(LocalDate.of(2012, 9, 11)), LocalDate.of(2012, 9, 10));
    }

    @Test(expectedExceptions=NullPointerException.class)
    public void test_adjustInto_null() {
        DayOfWeek.MONDAY.adjustInto((Temporal) null);
    }

    //-----------------------------------------------------------------------
    // toString()
    //-----------------------------------------------------------------------
    @Test
    public void test_toString() {
        assertEquals(DayOfWeek.MONDAY.toString(), "MONDAY");
        assertEquals(DayOfWeek.TUESDAY.toString(), "TUESDAY");
        assertEquals(DayOfWeek.WEDNESDAY.toString(), "WEDNESDAY");
        assertEquals(DayOfWeek.THURSDAY.toString(), "THURSDAY");
        assertEquals(DayOfWeek.FRIDAY.toString(), "FRIDAY");
        assertEquals(DayOfWeek.SATURDAY.toString(), "SATURDAY");
        assertEquals(DayOfWeek.SUNDAY.toString(), "SUNDAY");
    }

    //-----------------------------------------------------------------------
    // generated methods
    //-----------------------------------------------------------------------
    @Test
    public void test_enum() {
        assertEquals(DayOfWeek.valueOf("MONDAY"), DayOfWeek.MONDAY);
        assertEquals(DayOfWeek.values()[0], DayOfWeek.MONDAY);
    }

}
