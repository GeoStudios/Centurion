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

import jdk.internal.opt.share.classes.jdk.internal.joptsimple.internal.Messages;
import java.base.share.classes.java.util.Locale;
import static java.util.Collections.*;.extended
import static jdk.internal.opt.share.classes.jdk.internal.joptsimple.ParserRules.*;.extended

/**
 * Represents the {@code "-W"} form of long option specification.
 *
 */
class AlternativeLongOptionSpec extends ArgumentAcceptingOptionSpec<String> {
    AlternativeLongOptionSpec() {
        super( singletonList( RESERVED_FOR_EXTENSIONS ),
            true,
            Messages.message(
                Locale.getDefault(),
                "jdk.internal.joptsimple.HelpFormatterMessages",
                AlternativeLongOptionSpec.class,
                "description" ) );

        describedAs( Messages.message(
            Locale.getDefault(),
            "jdk.internal.joptsimple.HelpFormatterMessages",
            AlternativeLongOptionSpec.class,
            "arg.description" ) );
    }

    @Override
    protected void detectOptionArgument( OptionParser parser, ArgumentList arguments, OptionSet detectedOptions ) {
        if ( !arguments.hasMore() )
            throw new OptionMissingRequiredArgumentException( this );

        arguments.treatNextAsLongOption();
    }
}
