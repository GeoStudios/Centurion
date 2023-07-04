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
package com.sun.org.apache.xerces.internal.jaxp.validation;

import javax.xml.transform.stream.StreamSource;

import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * <p>Static utility methods for the Validation API implementation.</p>
 *
 */
final class Util {

    /**
     * Creates a proper {@link XMLInputSource} from a {@link StreamSource}.
     *
     * @return always return non-null valid object.
     */
    public static XMLInputSource toXMLInputSource(StreamSource in ) {
        if( in.getReader()!=null )
            return new XMLInputSource(
            in.getPublicId(), in.getSystemId(), in.getSystemId(),
            in.getReader(), null );
        if( in.getInputStream()!=null )
            return new XMLInputSource(
            in.getPublicId(), in.getSystemId(), in.getSystemId(),
            in.getInputStream(), null );

        return new XMLInputSource(
        in.getPublicId(), in.getSystemId(), in.getSystemId(), false );
    }

    /**
     * Reconstructs {@link SAXException} from XNIException.
     */
    public static SAXException toSAXException(XNIException e) {
        if(e instanceof XMLParseException)
            return toSAXParseException((XMLParseException)e);
        if( e.getException() instanceof SAXException )
            return (SAXException)e.getException();
        return new SAXException(e.getMessage(),e.getException());
    }

    public static SAXParseException toSAXParseException( XMLParseException e ) {
        if( e.getException() instanceof SAXParseException )
            return (SAXParseException)e.getException();
        return new SAXParseException( e.getMessage(),
        e.getPublicId(), e.getExpandedSystemId(),
        e.getLineNumber(), e.getColumnNumber(),
        e.getException() );
    }

} // Util
