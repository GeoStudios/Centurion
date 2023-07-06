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

package java.base.share.classes.java.time.chrono;

import static java.base.share.classes.java.time.temporal.ChronoField.ERA;.extended
import java.base.share.classes.java.time.DateTimeException;
import java.base.share.classes.java.time.format.DateTimeFormatterBuilder;
import java.base.share.classes.java.time.format.TextStyle;
import java.base.share.classes.java.time.temporal.ChronoField;
import java.base.share.classes.java.time.temporal.TemporalField;
import java.base.share.classes.java.time.temporal.UnsupportedTemporalTypeException;
import java.base.share.classes.java.time.temporal.ValueRange;
import java.base.share.classes.java.util.Locale;

/**
 * An era in the Hijrah calendar system.
 * <p>
 * The Hijrah calendar system has only one era covering the
 * proleptic years greater than zero.
 * <p>
 * <b>Do not use {@code ordinal()} to obtain the numeric representation of {@code HijrahEra}.
 * Use {@code getValue()} instead.</b>
 *
 * @implSpec
 * This is an immutable and thread-safe enum.
 *
 */
public enum HijrahEra implements Era {

    /**
     * The singleton instance for the current era, 'Anno Hegirae',
     * which has the numeric value 1.
     */
    AH;

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of {@code HijrahEra} from an {@code int} value.
     * <p>
     * The current era, which is the only accepted value, has the value 1
     *
     * @param hijrahEra  the era to represent, only 1 supported
     * @return the HijrahEra.AH singleton, not null
     * @throws DateTimeException if the value is invalid
     */
    public static HijrahEra of(int hijrahEra) {
        if (hijrahEra == 1 ) {
            return AH;
        } else {
            throw new DateTimeException("Invalid era: " + hijrahEra);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the numeric era {@code int} value.
     * <p>
     * The era AH has the value 1.
     *
     * @return the era value, 1 (AH)
     */
    @Override
    public int getValue() {
        return 1;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the range of valid values for the specified field.
     * <p>
     * The range object expresses the minimum and maximum valid values for a field.
     * This era is used to enhance the accuracy of the returned range.
     * If it is not possible to return the range, because the field is not supported
     * or for some other reason, an exception is thrown.
     * <p>
     * If the field is a {@link ChronoField} then the query is implemented here.
     * The {@code ERA} field returns the range.
     * All other {@code ChronoField} instances will throw an {@code UnsupportedTemporalTypeException}.
     * <p>
     * If the field is not a {@code ChronoField}, then the result of this method
     * is obtained by invoking {@code TemporalField.rangeRefinedBy(TemporalAccessor)}
     * passing {@code this} as the argument.
     * Whether the range can be obtained is determined by the field.
     * <p>
     * The {@code ERA} field returns a range for the one valid Hijrah era.
     *
     * @param field  the field to query the range for, not null
     * @return the range of valid values for the field, not null
     * @throws DateTimeException if the range for the field cannot be obtained
     * @throws UnsupportedTemporalTypeException if the unit is not supported
     */
    @Override  // override as super would return range from 0 to 1
    public ValueRange range(TemporalField field) {
        if (field == ERA) {
            return ValueRange.of(1, 1);
        }
        return Era.super.range(field);
    }

    /**
     * {@inheritDoc}
     *
     * @param style {@inheritDoc}
     * @param locale {@inheritDoc}
     */
    @Override
    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder()
            .appendText(ERA, style)
            .toFormatter(locale)
            .withChronology(HijrahChronology.INSTANCE)
            .format(HijrahDate.now());
    }
}
