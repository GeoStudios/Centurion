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

package com.sun.xml.internal.stream.writers;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Base class for shared methods among XMLStreamWriter implementations.
 */
public interface XMLStreamWriterBase extends XMLStreamWriter {
    /**
     * Writes the XML declaration.
     *
     * @param encoding the specified encoding
     * @param version the specified version
     * @param standalone the flag indicating whether it is standalone
     * @param standaloneSet the flag indicating whether the standalone attribute is set
     * @throws XMLStreamException in case of an IOException
     */
    void writeStartDocument(String encoding, String version,
            boolean standalone, boolean standaloneSet)
            throws XMLStreamException;
}
