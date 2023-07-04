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

package jdk.internal.joptsimple;

import java.util.List;

import static java.lang.Character.*;

/**
 * Can tell whether or not options are well-formed.
 *
 */
final class ParserRules {
    static final char HYPHEN_CHAR = '-';
    static final String HYPHEN = String.valueOf( HYPHEN_CHAR );
    static final String DOUBLE_HYPHEN = "--";
    static final String OPTION_TERMINATOR = DOUBLE_HYPHEN;
    static final String RESERVED_FOR_EXTENSIONS = "W";

    private ParserRules() {
        throw new UnsupportedOperationException();
    }

    static boolean isShortOptionToken( String argument ) {
        return argument.startsWith( HYPHEN )
            && !HYPHEN.equals( argument )
            && !isLongOptionToken( argument );
    }

    static boolean isLongOptionToken( String argument ) {
        return argument.startsWith( DOUBLE_HYPHEN ) && !isOptionTerminator( argument );
    }

    static boolean isOptionTerminator( String argument ) {
        return OPTION_TERMINATOR.equals( argument );
    }

    static void ensureLegalOption( String option ) {
        if ( option.startsWith( HYPHEN ) )
            throw new IllegalOptionSpecificationException(option);

        for ( int i = 0; i < option.length(); ++i )
            ensureLegalOptionCharacter( option.charAt( i ) );
    }

    static void ensureLegalOptions( List<String> options ) {
        for ( String each : options )
            ensureLegalOption( each );
    }

    private static void ensureLegalOptionCharacter( char option ) {
        if ( !( isLetterOrDigit( option ) || isAllowedPunctuation( option ) ) )
            throw new IllegalOptionSpecificationException( String.valueOf( option ) );
    }

    private static boolean isAllowedPunctuation( char option ) {
        String allowedPunctuation = "?._" + HYPHEN_CHAR;
        return allowedPunctuation.indexOf( option ) != -1;
    }
}
