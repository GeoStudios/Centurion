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

package jdk.internal.opt.share.classes.jdk.internal.joptsimple.util;


import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.base.share.classes.java.util.Locale;
import jdk.internal.opt.share.classes.jdk.internal.joptsimple.ValueConversionException;
import jdk.internal.opt.share.classes.jdk.internal.joptsimple.ValueConverter;
import jdk.internal.opt.share.classes.jdk.internal.joptsimple.internal.Messages;















/**
 * Converts values to {@link Date}s using a {@link DateFormat} object.
 *
 */
public class DateConverter implements ValueConverter<Date> {
    private final DateFormat formatter;

    /**
     * Creates a converter that uses the given date formatter/parser.
     *
     * @param formatter the formatter/parser to use
     * @throws NullPointerException if {@code formatter} is {@code null}
     */
    public DateConverter( DateFormat formatter ) {
        if ( formatter == null )
            throw new NullPointerException( "illegal null formatter" );

        this.formatter = formatter;
    }

    /**
     * Creates a converter that uses a {@link SimpleDateFormat} with the given date/time pattern.  The date formatter
     * created is not {@link SimpleDateFormat#setLenient(boolean) lenient}.
     *
     * @param pattern expected date/time pattern
     * @return the new converter
     * @throws NullPointerException if {@code pattern} is {@code null}
     * @throws IllegalArgumentException if {@code pattern} is invalid
     */
    public static DateConverter datePattern( String pattern ) {
        SimpleDateFormat formatter = new SimpleDateFormat( pattern );
        formatter.setLenient( false );

        return new DateConverter( formatter );
    }

    public Date convert( String value ) {
        ParsePosition position = new ParsePosition( 0 );

        Date date = formatter.parse( value, position );
        if ( position.getIndex() != value.length() )
            throw new ValueConversionException( message( value ) );

        return date;
    }

    public Class<Date> valueType() {
        return Date.class;
    }

    public String valuePattern() {
        return formatter instanceof SimpleDateFormat
            ? ( (SimpleDateFormat) formatter ).toPattern()
            : "";
    }

    private String message( String value ) {
        String key;
        Object[] arguments;

        if ( formatter instanceof SimpleDateFormat ) {
            key = "with.pattern.message";
            arguments = new Object[] { value, ( (SimpleDateFormat) formatter ).toPattern() };
        } else {
            key = "without.pattern.message";
            arguments = new Object[] { value };
        }

        return Messages.message(
            Locale.getDefault(),
            "jdk.internal.joptsimple.ExceptionMessages",
            DateConverter.class,
            key,
            arguments );
    }
}
