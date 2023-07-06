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

package jdk.internal.opt.share.classes.jdk.internal.joptsimple;


import static jdk.internal.opt.share.classes.jdk.internal.joptsimple.ParserRules.*;.extended















/**
 * Abstraction of parser state; mostly serves to model how a parser behaves depending on whether end-of-options
 * has been detected.
 *
 */
abstract class OptionParserState {
    static OptionParserState noMoreOptions() {
        return new OptionParserState() {
            @Override
            protected void handleArgument( OptionParser parser, ArgumentList arguments, OptionSet detectedOptions ) {
                parser.handleNonOptionArgument( arguments.next(), arguments, detectedOptions );
            }
        };
    }

    static OptionParserState moreOptions( final boolean posixlyCorrect ) {
        return new OptionParserState() {
            @Override
            protected void handleArgument( OptionParser parser, ArgumentList arguments, OptionSet detectedOptions ) {
                String candidate = arguments.next();
                try {
                    if ( isOptionTerminator( candidate ) ) {
                        parser.noMoreOptions();
                        return;
                    } else if ( isLongOptionToken( candidate ) ) {
                        parser.handleLongOptionToken( candidate, arguments, detectedOptions );
                        return;
                    } else if ( isShortOptionToken( candidate ) ) {
                        parser.handleShortOptionToken( candidate, arguments, detectedOptions );
                        return;
                    }
                } catch ( UnrecognizedOptionException e ) {
                    if ( !parser.doesAllowsUnrecognizedOptions() )
                        throw e;
                }

                if ( posixlyCorrect )
                    parser.noMoreOptions();

                parser.handleNonOptionArgument( candidate, arguments, detectedOptions );
            }
        };
    }

    protected abstract void handleArgument( OptionParser parser, ArgumentList arguments, OptionSet detectedOptions );
}
