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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xml.internal.serializer.dom3;

import java.io.IOException;

import com.sun.org.apache.xml.internal.serializer.DOM3Serializer;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSSerializerFilter;

/**
 * This class implements the DOM3Serializer interface.
 *
 * @xsl.usage internal
 */
public final class DOM3SerializerImpl implements DOM3Serializer {

    /**
     * Private class members
     */
    // The DOMErrorHandler
    private DOMErrorHandler fErrorHandler;

    // A LSSerializerFilter
    private LSSerializerFilter fSerializerFilter;

    // The end-of-line character sequence
    private String fNewLine;

    // A SerializationHandler ex. an instance of ToXMLStream
    private SerializationHandler fSerializationHandler;

    /**
     * Constructor
     *
     * @param handler An instance of the SerializationHandler interface.
     */
    public DOM3SerializerImpl(SerializationHandler handler) {
        fSerializationHandler = handler;
    }

    // Public memebers

    /**
     * Returns a DOMErrorHandler set on the DOM Level 3 Serializer.
     *
     * This interface is a public API.
     *
     * @return A Level 3 DOMErrorHandler
     */
    public DOMErrorHandler getErrorHandler() {
        return fErrorHandler;
    }

    /**
     * Returns a LSSerializerFilter set on the DOM Level 3 Serializer to filter nodes
     * during serialization.
     *
     * This interface is a public API.
     *
     * @return The Level 3 LSSerializerFilter
     */
    public LSSerializerFilter getNodeFilter() {
        return fSerializerFilter;
    }


    /**
     * Serializes the Level 3 DOM node by creating an instance of DOM3TreeWalker
     * which traverses the DOM tree and invokes handler events to serialize
     * the DOM NOde. Throws an exception only if an I/O exception occured
     * while serializing.
     * This interface is a public API.
     *
     * @param node the Level 3 DOM node to serialize
     * @throws IOException if an I/O exception occured while serializing
     */
    public void serializeDOM3(Node node) throws IOException {
        try {
            DOM3TreeWalker walker = new DOM3TreeWalker(fSerializationHandler,
                    fErrorHandler, fSerializerFilter, fNewLine);

            walker.traverse(node);
        } catch (org.xml.sax.SAXException se) {
            throw new WrappedRuntimeException(se);
        }
    }

    /**
     * Sets a DOMErrorHandler on the DOM Level 3 Serializer.
     *
     * This interface is a public API.
     *
     * @param handler the Level 3 DOMErrorHandler
     */
    public void setErrorHandler(DOMErrorHandler handler) {
        fErrorHandler = handler;
    }

    /**
     * Sets a LSSerializerFilter on the DOM Level 3 Serializer to filter nodes
     * during serialization.
     *
     * This interface is a public API.
     *
     * @param filter the Level 3 LSSerializerFilter
     */
    public void setNodeFilter(LSSerializerFilter filter) {
        fSerializerFilter = filter;
    }

    /**
     * Sets a SerializationHandler on the DOM Serializer.
     *
     * This interface is a public API.
     *
     * @param handler An instance of SerializationHandler
     */
    public void setSerializationHandler(SerializationHandler handler) {
        fSerializationHandler = handler;
    }

    /**
     * Sets the new line character to be used during serialization
     * @param newLine a String that is the end-of-line character sequence to be
     * used in serialization.
     */
    public void setNewLine(String newLine) {
        fNewLine = newLine;
    }
}
