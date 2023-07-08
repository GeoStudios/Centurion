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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.dom;

import java.xml.share.classes.com.sun.org.w3c.dom.DOMException;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMImplementation;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.w3c.dom.DocumentType;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * The DOMImplementation class is description of a particular
 * implementation of the Document Object Model. As such its data is
 * static, shared by all instances of this implementation.
 * <P>
 * The DOM API requires that it be a real object rather than static
 * methods. However, there's nothing that says it can't be a singleton,
 * so that's how I've implemented it.
 *
 * @xerces.internal
 *
 */
public class DOMImplementationImpl extends CoreDOMImplementationImpl
    implements DOMImplementation {

    //
    // Data
    //

    // static

    /** Dom implementation singleton. */
    static DOMImplementationImpl singleton = new DOMImplementationImpl();

    //
    // Public methods
    //

    /** NON-DOM: Obtain and return the single shared object */
    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    //
    // DOMImplementation methods
    //

    /**
     * Test if the DOM implementation supports a specific "feature" --
     * currently meaning language and level thereof.
     *
     * @param feature      The package name of the feature to test.
     * In Level 1, supported values are "HTML" and "XML" (case-insensitive).
     * At this writing, com.sun.org.apache.xerces.internal.dom supports only XML.
     *
     * @param version      The version number of the feature being tested.
     * This is interpreted as "Version of the DOM API supported for the
     * specified Feature", and in Level 1 should be "1.0"
     *
     * @return    true iff this implementation is compatable with the
     * specified feature and version.
     */
    public boolean hasFeature(String feature, String version) {
        if (feature == null || feature.length() == 0) {
            return false;
        }

        boolean result = super.hasFeature(feature, version);
        if (!result) {
            boolean anyVersion = version == null || version.length() == 0;
            if (feature.startsWith("+")) {
                feature = feature.substring(1);
            }
            return (
                (feature.equalsIgnoreCase("Events")
                    && (anyVersion || version.equals("2.0")))
                    || (feature.equalsIgnoreCase("MutationEvents")
                        && (anyVersion || version.equals("2.0")))
                    || (feature.equalsIgnoreCase("Traversal")
                        && (anyVersion || version.equals("2.0")))
                    || (feature.equalsIgnoreCase("Range")
                        && (anyVersion || version.equals("2.0")))
                    || (feature.equalsIgnoreCase("MutationEvents")
                        && (anyVersion || version.equals("2.0"))));
        }
        return result;
    } // hasFeature(String,String):boolean

    /**
     * Introduced in DOM Level 2. <p>
     *
     * Creates an XML Document object of the specified type with its document
     * element.
     *
     * @param namespaceURI     The namespace URI of the document
     *                         element to create, or null.
     * @param qualifiedName    The qualified name of the document
     *                         element to create.
     * @param doctype          The type of document to be created or null.<p>
     *
     *                         When doctype is not null, its
     *                         Node.ownerDocument attribute is set to
     *                         the document being created.
     * @return Document        A new Document object.
     * @throws DOMException    WRONG_DOCUMENT_ERR: Raised if doctype has
     *                         already been used with a different document.
     */
    public Document           createDocument(String namespaceURI,
                                             String qualifiedName,
                                             DocumentType doctype)
                                             throws DOMException
    {
        if(namespaceURI == null && qualifiedName == null && doctype == null){
        //if namespaceURI, qualifiedName and doctype are null, returned document is empty with
        //no document element
            return new DocumentImpl();
        }
        else if (doctype != null && doctype.getOwnerDocument() != null) {
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null);
            throw new DOMException(DOMException.WRONG_DOCUMENT_ERR, msg);
        }
        DocumentImpl doc = new DocumentImpl(doctype);
        Element e = doc.createElementNS( namespaceURI, qualifiedName);
        doc.appendChild(e);
        return doc;
    }

} // class DOMImplementationImpl
