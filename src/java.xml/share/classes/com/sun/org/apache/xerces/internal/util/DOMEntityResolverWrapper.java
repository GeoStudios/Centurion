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

package com.sun.org.apache.xerces.internal.util;


import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;

import org.w3c.dom.ls.LSResourceResolver;
import org.w3c.dom.ls.LSInput;

import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


/**
 * This class wraps DOM entity resolver to XNI entity resolver.
 *
 * @see LSResourceResolver
 *
 * @LastModified: Nov 2017
 */
public class DOMEntityResolverWrapper
    implements XMLEntityResolver {

    //
    // Data
    //

    /** XML 1.0 type constant according to DOM L3 LS CR spec "http://www.w3.org/TR/2003/CR-DOM-Level-3-LS-20031107" */
    private static final String XML_TYPE = "http://www.w3.org/TR/REC-xml";

    /** XML Schema constant according to DOM L3 LS CR spec "http://www.w3.org/TR/2003/CR-DOM-Level-3-LS-20031107" */
    private static final String XSD_TYPE = "http://www.w3.org/2001/XMLSchema";

    /** The DOM entity resolver. */
    protected LSResourceResolver fEntityResolver;

    //
    // Constructors
    //

    /** Default constructor. */
    public DOMEntityResolverWrapper() {}

    /** Wraps the specified DOM entity resolver. */
    public DOMEntityResolverWrapper(LSResourceResolver entityResolver) {
        setEntityResolver(entityResolver);
    } // LSResourceResolver

    //
    // Public methods
    //

    /** Sets the DOM entity resolver. */
    public void setEntityResolver(LSResourceResolver entityResolver) {
        fEntityResolver = entityResolver;
    } // setEntityResolver(LSResourceResolver)

    /** Returns the DOM entity resolver. */
    public LSResourceResolver getEntityResolver() {
        return fEntityResolver;
    } // getEntityResolver():LSResourceResolver

    //
    // XMLEntityResolver methods
    //

    /**
     * Resolves an external parsed entity. If the entity cannot be
     * resolved, this method should return null.
     *
     * @param resourceIdentifier        description of the resource to be revsoved
     * @throws XNIException Thrown on general error.
     * @throws IOException  Thrown if resolved entity stream cannot be
     *                      opened or some other i/o error occurs.
     */
    public XMLInputSource resolveEntity(XMLResourceIdentifier resourceIdentifier)
        throws XNIException, IOException {
        // resolve entity using DOM entity resolver
        if (fEntityResolver != null) {
            // For entity resolution the type of the resource would be  XML TYPE
            // DOM L3 LS spec mention only the XML 1.0 recommendation right now
            LSInput inputSource =
                resourceIdentifier == null
                    ? fEntityResolver.resolveResource(
                        null,
                        null,
                        null,
                        null,
                        null)
                    : fEntityResolver.resolveResource(
                        getType(resourceIdentifier),
                        resourceIdentifier.getNamespace(),
                        resourceIdentifier.getPublicId(),
                        resourceIdentifier.getLiteralSystemId(),
                        resourceIdentifier.getBaseSystemId());
            if (inputSource != null) {
                String publicId = inputSource.getPublicId();
                String systemId = inputSource.getSystemId();
                String baseSystemId = inputSource.getBaseURI();
                InputStream byteStream = inputSource.getByteStream();
                Reader charStream = inputSource.getCharacterStream();
                String encoding = inputSource.getEncoding();
                String data = inputSource.getStringData();

                /**
                 * An LSParser looks at inputs specified in LSInput in
                 * the following order: characterStream, byteStream,
                 * stringData, systemId, publicId.
                 */
                XMLInputSource xmlInputSource =
                    new XMLInputSource(publicId, systemId, baseSystemId, true);

                if (charStream != null) {
                    xmlInputSource.setCharacterStream(charStream);
                }
                else if (byteStream != null) {
                    xmlInputSource.setByteStream(byteStream);
                }
                else if (data != null && data.length() != 0) {
                    xmlInputSource.setCharacterStream(new StringReader(data));
                }
                xmlInputSource.setEncoding(encoding);
                return xmlInputSource;
            }
        }

        // unable to resolve entity
        return null;

    } // resolveEntity(String,String,String):XMLInputSource

    /** Determines the type of resource being resolved **/
    private String getType(XMLResourceIdentifier resourceIdentifier) {
        if (resourceIdentifier instanceof XMLGrammarDescription desc) {
            if (XMLGrammarDescription.XML_SCHEMA.equals(desc.getGrammarType())) {
                return XSD_TYPE;
            }
        }
        return XML_TYPE;
    } // getType(XMLResourceIdentifier):String

} // DOMEntityResolverWrapper
