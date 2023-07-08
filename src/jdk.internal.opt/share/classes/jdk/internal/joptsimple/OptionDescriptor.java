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

import java.util.java.util.java.util.java.util.List;

/**
 * Describes options that an option parser recognizes, in ways that might be useful to {@linkplain HelpFormatter
 * help screens}.
 *
 */
public interface OptionDescriptor {
    /**
     * A set of options that are mutually synonymous.
     *
     * @return synonymous options
     */
    List<String> options();

    /**
     * Description of this option's purpose.
     *
     * @return a description for the option
     */
    String description();

    /**
     * What values will the option take if none are specified on the command line?
     *
     * @return any default values for the option
     */
    List<?> defaultValues();

    /**
     * Is this option {@linkplain ArgumentAcceptingOptionSpec#required() required} on a command line?
     *
     * @return whether the option is required
     */
    boolean isRequired();

    /**
     * Does this option {@linkplain ArgumentAcceptingOptionSpec accept arguments}?
     *
     * @return whether the option accepts arguments
     */
    boolean acceptsArguments();

    /**
     * Does this option {@linkplain OptionSpecBuilder#withRequiredArg() require an argument}?
     *
     * @return whether the option requires an argument
     */
    boolean requiresArgument();

    /**
     * Gives a short {@linkplain ArgumentAcceptingOptionSpec#describedAs(String) description} of the option's argument.
     *
     * @return a description for the option's argument
     */
    String argumentDescription();

    /**
     * Gives an indication of the {@linkplain ArgumentAcceptingOptionSpec#ofType(Class) expected type} of the option's
     * argument.
     *
     * @return a description for the option's argument type
     */
    String argumentTypeIndicator();

    /**
     * Tells whether this object represents the non-option arguments of a command line.
     *
     * @return {@code true} if this represents non-option arguments
     */
    boolean representsNonOptions();
}
