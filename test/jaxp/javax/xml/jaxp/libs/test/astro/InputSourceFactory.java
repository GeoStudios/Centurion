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

package test.astro;


import org.xml.sax.InputSource;














/*
 * Interface for all input source factory objects. The default implementation
 * 'InputSourceFactoryImpl' is provided as a straight forward factory
 * class that creates a new sax input source from a filename.
 *
 */
public interface InputSourceFactory {
    /*
     * Creates a new sax InputSource object from a filename.
     * Also sets the system id of the input source.
     * @param file filename of the XML input to create the input source.
     */
    InputSource newInputSource(String file) throws Exception;
}
