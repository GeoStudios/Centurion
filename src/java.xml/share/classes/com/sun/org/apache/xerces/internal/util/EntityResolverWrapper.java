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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;


import java.io.InputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.Reader;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import javax.xml.catalog.CatalogException;
import java.xml.share.classes.com.sun.org.xml.sax.EntityResolver;
import java.xml.share.classes.com.sun.org.xml.sax.InputSource;
import java.xml.share.classes.com.sun.org.xml.sax.SAXException;















/**
 * This class wraps a SAX entity resolver in an XNI entity resolver.
 *
 * @see EntityResolver
 *
 *
 */
public class EntityResolverWrapper
    implements XMLEntityResolver {

    //
    // Data
    //

    /** The SAX entity resolver. */
    protected EntityResolver fEntityResolver;

    //
    // Constructors
    //

    /** Default constructor. */
    public EntityResolverWrapper() {}

    /** Wraps the specified SAX entity resolver. */
    public EntityResolverWrapper(EntityResolver entityResolver) {
        setEntityResolver(entityResolver);
    } // <init>(EntityResolver)

    //
    // Public methods
    //

    /** Sets the SAX entity resolver. */
    public void setEntityResolver(EntityResolver entityResolver) {
        fEntityResolver = entityResolver;
    } // setEntityResolver(EntityResolver)

    /** Returns the SAX entity resolver. */
    public EntityResolver getEntityResolver() {
        return fEntityResolver;
    } // getEntityResolver():EntityResolver

    //
    // XMLEntityResolver methods
    //

    /**
     * Resolves an external parsed entity. If the entity cannot be
     * resolved, this method should return null.
     *
     * @param resourceIdentifier        contains the physical co-ordinates of the resource to be resolved
     *
     * @throws XNIException Thrown on general error.
     * @throws IOException  Thrown if resolved entity stream cannot be
     *                      opened or some other i/o error occurs.
     */
    public XMLInputSource resolveEntity(XMLResourceIdentifier resourceIdentifier)
        throws XNIException, IOException {

        // When both pubId and sysId are null, the user's entity resolver
        // can do nothing about it. We'd better not bother calling it.
        // This happens when the resourceIdentifier is a GrammarDescription,
        // which describes a schema grammar of some namespace, but without
        // any schema location hint. -Sg
        String pubId = resourceIdentifier.getPublicId();
        String sysId = resourceIdentifier.getExpandedSystemId();
        if (pubId == null && sysId == null)
            return null;

        // resolve entity using SAX entity resolver
        if (fEntityResolver != null && resourceIdentifier != null) {
            try {
                InputSource inputSource = fEntityResolver.resolveEntity(pubId, sysId);
                if (inputSource != null) {
                    String publicId = inputSource.getPublicId();
                    String systemId = inputSource.getSystemId();
                    String baseSystemId = resourceIdentifier.getBaseSystemId();
                    InputStream byteStream = inputSource.getByteStream();
                    Reader charStream = inputSource.getCharacterStream();
                    String encoding = inputSource.getEncoding();
                    XMLInputSource xmlInputSource =
                        new XMLInputSource(publicId, systemId, baseSystemId, true);
                    xmlInputSource.setByteStream(byteStream);
                    xmlInputSource.setCharacterStream(charStream);
                    xmlInputSource.setEncoding(encoding);
                    return xmlInputSource;
                }
            }

            // error resolving entity
            catch (SAXException e) {
                Exception ex = e.getException();
                if (ex == null) {
                    ex = e;
                }
                throw new XNIException(ex);
            }

            catch (CatalogException e) {
                throw new XNIException(e);
            }
        }

        // unable to resolve entity
        return null;

    } // resolveEntity(String,String,String):XMLInputSource
}
