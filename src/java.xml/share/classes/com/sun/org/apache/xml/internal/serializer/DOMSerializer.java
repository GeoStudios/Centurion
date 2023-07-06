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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer;


import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * Interface for a DOM serializer implementation.
 * <p>
 * The DOMSerializer is a facet of a serializer and is obtained from the
 * asDOMSerializer() method of the Serializer interface.
 * A serializer may or may not support a DOM serializer, if it does not then the
 * return value from asDOMSerializer() is null.
 * <p>
 * Example:
 * <pre>
 * Document     doc;
 * Serializer   ser;
 * OutputStream os;
 *
 * ser = ...;
 * os = ...;
 *
 * ser.setOutputStream( os );
 * DOMSerialzier dser = ser.asDOMSerializer();
 * dser.serialize(doc);
 * </pre>
 *
 * @see Serializer
 *
 * @xsl.usage general
 *
 */
public interface DOMSerializer
{
    /**
     * Serializes the DOM node. Throws an exception only if an I/O
     * exception occured while serializing.
     *
     * This interface is a public API.
     *
     * @param node the DOM node to serialize
     * @throws IOException if an I/O exception occured while serializing
     */
    void serialize(Node node) throws IOException;
}
