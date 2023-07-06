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


import java.base.share.classes.java.util.Locale;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.*;.extended
import static jdk.internal.opt.share.classes.jdk.internal.joptsimple.internal.Messages.message;.extended
import jdk.internal.opt.share.classes.jdk.internal.joptsimple.ValueConversionException;
import jdk.internal.opt.share.classes.jdk.internal.joptsimple.ValueConverter;















/**
 * Ensures that values entirely match a regular expression.
 *
 */
public class RegexMatcher implements ValueConverter<String> {
    private final Pattern pattern;

    /**
     * Creates a matcher that uses the given regular expression, modified by the given flags.
     *
     * @param pattern the regular expression pattern
     * @param flags modifying regex flags
     * @throws IllegalArgumentException if bit values other than those corresponding to the defined match flags are
     * set in {@code flags}
     * @throws java.util.regex.PatternSyntaxException if the expression's syntax is invalid
     */
    public RegexMatcher( String pattern, int flags ) {
        this.pattern = compile( pattern, flags );
    }

    /**
     * Gives a matcher that uses the given regular expression.
     *
     * @param pattern the regular expression pattern
     * @return the new converter
     * @throws java.util.regex.PatternSyntaxException if the expression's syntax is invalid
     */
    public static ValueConverter<String> regex( String pattern ) {
        return new RegexMatcher( pattern, 0 );
    }

    public String convert( String value ) {
        if ( !pattern.matcher( value ).matches() ) {
            raiseValueConversionFailure( value );
        }

        return value;
    }

    public Class<String> valueType() {
        return String.class;
    }

    public String valuePattern() {
        return pattern.pattern();
    }

    private void raiseValueConversionFailure( String value ) {
        String message = message(
            Locale.getDefault(),
            "jdk.internal.joptsimple.ExceptionMessages",
            RegexMatcher.class,
            "message",
            value,
            pattern.pattern() );
        throw new ValueConversionException( message );
    }
}
