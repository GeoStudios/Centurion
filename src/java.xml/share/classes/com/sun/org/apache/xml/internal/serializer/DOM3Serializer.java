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
import java.xml.share.classes.com.sun.org.w3c.dom.DOMErrorHandler;
import java.xml.share.classes.com.sun.org.w3c.dom.ls.LSSerializerFilter;

/*
 * $Id:  $
 */

/**
 * Interface for a DOM serializer capable of serializing DOMs as specified in
 * the DOM Level 3 Save Recommendation.
 * <p>
 * The DOM3Serializer is a facet of a serializer and is obtained from the
 * asDOM3Serializer() method of the org.apache.xml.serializer.Serializer interface.
 * A serializer may or may not support a level 3 DOM serializer, if it does not then the
 * return value from asDOM3Serializer() is null.
 * <p>
 * Example:
 * <pre>
 * Document     doc;
 * Serializer   ser;
 * OutputStream os;
 * DOMErrorHandler handler;
 *
 * ser = ...;
 * os = ...;
 * handler = ...;
 *
 * ser.setOutputStream( os );
 * DOM3Serialzier dser = (DOM3Serialzier)ser.asDOM3Serializer();
 * dser.setErrorHandler(handler);
 * dser.serialize(doc);
 * </pre>
 *
 * @see org.apache.xml.serializer.Serializer
 *
 * @xsl.usage general
 *
 */
public interface DOM3Serializer {
    /**
     * Serializes the Level 3 DOM node. Throws an exception only if an I/O
     * exception occured while serializing.
     *
     * This interface is a public API.
     *
     * @param node the Level 3 DOM node to serialize
     * @throws IOException if an I/O exception occured while serializing
     */
    void serializeDOM3(Node node) throws IOException;

    /**
     * Sets a DOMErrorHandler on the DOM Level 3 Serializer.
     *
     * This interface is a public API.
     *
     * @param handler the Level 3 DOMErrorHandler
     */
    void setErrorHandler(DOMErrorHandler handler);

    /**
     * Returns a DOMErrorHandler set on the DOM Level 3 Serializer.
     *
     * This interface is a public API.
     *
     * @return A Level 3 DOMErrorHandler
     */
    DOMErrorHandler getErrorHandler();

    /**
     * Sets a LSSerializerFilter on the DOM Level 3 Serializer to filter nodes
     * during serialization.
     *
     * This interface is a public API.
     *
     * @param filter the Level 3 LSSerializerFilter
     */
    void setNodeFilter(LSSerializerFilter filter);

    /**
     * Returns a LSSerializerFilter set on the DOM Level 3 Serializer to filter nodes
     * during serialization.
     *
     * This interface is a public API.
     *
     * @return The Level 3 LSSerializerFilter
     */
    LSSerializerFilter getNodeFilter();

    /**
     * Sets the new line character to be used during serialization
     * @param newLine a String that is the end-of-line character sequence to be
     * used in serialization.
     */
    void setNewLine(String newLine);
}
