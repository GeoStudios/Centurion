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

import java.util.Map;

/**
 * <p>Represents objects charged with taking a set of option descriptions and producing some help text from them.</p>
 *
 */
public interface HelpFormatter {
    /**
     * Produces help text, given a set of option descriptors.
     *
     * @param options descriptors for the configured options of a parser
     * @return text to be used as help
     * @see OptionParser#printHelpOn(java.io.Writer)
     * @see OptionParser#formatHelpWith(HelpFormatter)
     */
    String format( Map<String, ? extends OptionDescriptor> options );
}
