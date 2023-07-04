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

package test.java.time.temporal;

import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.WEEKS;

import java.time.DateTimeException;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;

/**
 * Mock TemporalField that returns null.
 */
public enum MockFieldNoValue implements TemporalField {

    INSTANCE;

    @Override
    public TemporalUnit getBaseUnit() {
        return WEEKS;
    }

    @Override
    public TemporalUnit getRangeUnit() {
        return MONTHS;
    }

    @Override
    public ValueRange range() {
        return ValueRange.of(1, 20);
    }

    @Override
    public boolean isDateBased() {
        return false;
    }

    @Override
    public boolean isTimeBased() {
        return false;
    }

    //-----------------------------------------------------------------------
    @Override
    public boolean isSupportedBy(TemporalAccessor temporal) {
        return true;
    }

    @Override
    public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
        return ValueRange.of(1, 20);
    }

    @Override
    public long getFrom(TemporalAccessor temporal) {
        throw new DateTimeException("Mock");
    }

    @Override
    public <R extends Temporal> R adjustInto(R temporal, long newValue) {
        throw new DateTimeException("Mock");
    }

    @Override
    public String toString() {
        return null;
    }

}
