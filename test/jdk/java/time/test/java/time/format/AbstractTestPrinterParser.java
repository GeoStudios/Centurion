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

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DecimalStyle;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Locale;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Abstract PrinterParser test.
 */
@Test
public class AbstractTestPrinterParser {

    protected StringBuilder buf;
    protected DateTimeFormatterBuilder builder;
    protected TemporalAccessor dta;
    protected Locale locale;
    protected DecimalStyle decimalStyle;


    @BeforeMethod
    public void setUp() {
        buf = new StringBuilder();
        builder = new DateTimeFormatterBuilder();
        dta = ZonedDateTime.of(LocalDateTime.of(2011, 6, 30, 12, 30, 40, 0), ZoneId.of("Europe/Paris"));
        locale = Locale.ENGLISH;
        decimalStyle = DecimalStyle.STANDARD;
    }

    protected void setCaseSensitive(boolean caseSensitive) {
        if (caseSensitive) {
            builder.parseCaseSensitive();
        } else {
            builder.parseCaseInsensitive();
        }
    }

    protected void setStrict(boolean strict) {
        if (strict) {
            builder.parseStrict();
        } else {
            builder.parseLenient();
        }
    }

    protected DateTimeFormatter getFormatter() {
        return builder.toFormatter(locale).withDecimalStyle(decimalStyle);
    }

    protected DateTimeFormatter getFormatter(char c) {
        return builder.appendLiteral(c).toFormatter(locale).withDecimalStyle(decimalStyle);
    }

    protected DateTimeFormatter getFormatter(String s) {
        return builder.appendLiteral(s).toFormatter(locale).withDecimalStyle(decimalStyle);
    }

    protected DateTimeFormatter getFormatter(TemporalField field) {
        return builder.appendText(field).toFormatter(locale).withDecimalStyle(decimalStyle);
    }

    protected DateTimeFormatter getFormatter(TemporalField field, TextStyle style) {
        return builder.appendText(field, style).toFormatter(locale).withDecimalStyle(decimalStyle);
    }

    protected DateTimeFormatter getFormatter(TemporalField field, int minWidth, int maxWidth, SignStyle signStyle) {
        return builder.appendValue(field, minWidth, maxWidth, signStyle).toFormatter(locale).withDecimalStyle(decimalStyle);
    }

    protected DateTimeFormatter getFormatter(String pattern, String noOffsetText) {
        return builder.appendOffset(pattern, noOffsetText).toFormatter(locale).withDecimalStyle(decimalStyle);
    }

    protected DateTimeFormatter getPatternFormatter(String pattern) {
        return builder.appendPattern(pattern).toFormatter(locale).withDecimalStyle(decimalStyle);
    }

    protected static final TemporalAccessor EMPTY_DTA = new TemporalAccessor() {
        public boolean isSupported(TemporalField field) {
            return true;
        }
        @Override
        public long getLong(TemporalField field) {
            throw new DateTimeException("Mock");
        }
    };
}
