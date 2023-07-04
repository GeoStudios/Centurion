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

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

import jdk.internal.joptsimple.internal.Reflection;
import jdk.internal.joptsimple.internal.ReflectionException;

import static jdk.internal.joptsimple.internal.Strings.*;

/**
 * @param <V> represents the type of the arguments this option accepts
 */
public abstract class AbstractOptionSpec<V> implements OptionSpec<V>, OptionDescriptor {
    private final List<String> options = new ArrayList<>();
    private final String description;
    private boolean forHelp;

    AbstractOptionSpec( String option ) {
        this( singletonList( option ), EMPTY );
    }

    AbstractOptionSpec( List<String> options, String description ) {
        arrangeOptions( options );

        this.description = description;
    }

    public final List<String> options() {
        return unmodifiableList( options );
    }

    public final List<V> values( OptionSet detectedOptions ) {
        return detectedOptions.valuesOf( this );
    }

    public final V value( OptionSet detectedOptions ) {
        return detectedOptions.valueOf( this );
    }

    public String description() {
        return description;
    }

    public final AbstractOptionSpec<V> forHelp() {
        forHelp = true;
        return this;
    }

    public final boolean isForHelp() {
        return forHelp;
    }

    public boolean representsNonOptions() {
        return false;
    }

    protected abstract V convert( String argument );

    protected V convertWith( ValueConverter<V> converter, String argument ) {
        try {
            return Reflection.convertWith( converter, argument );
        } catch ( ReflectionException | ValueConversionException ex ) {
            throw new OptionArgumentConversionException( this, argument, ex );
        }
    }

    protected String argumentTypeIndicatorFrom( ValueConverter<V> converter ) {
        if ( converter == null )
            return null;

        String pattern = converter.valuePattern();
        return pattern == null ? converter.valueType().getName() : pattern;
    }

    abstract void handleOption( OptionParser parser, ArgumentList arguments, OptionSet detectedOptions,
        String detectedArgument );

    private void arrangeOptions( List<String> unarranged ) {
        if ( unarranged.size() == 1 ) {
            options.addAll( unarranged );
            return;
        }

        List<String> shortOptions = new ArrayList<>();
        List<String> longOptions = new ArrayList<>();

        for ( String each : unarranged ) {
            if ( each.length() == 1 )
                shortOptions.add( each );
            else
                longOptions.add( each );
        }

        sort( shortOptions );
        sort( longOptions );

        options.addAll( shortOptions );
        options.addAll( longOptions );
    }

    @Override
    public boolean equals( Object that ) {
        if ( !(that instanceof AbstractOptionSpec<?> other) )
            return false;

        return options.equals( other.options );
    }

    @Override
    public int hashCode() {
        return options.hashCode();
    }

    @Override
    public String toString() {
        return options.toString();
    }
}
