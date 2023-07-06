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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.opti;

import java.xml.share.classes.com.sun.org.w3c.dom.DOMException;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMImplementation;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.w3c.dom.DocumentType;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * @xerces.internal
 *
 */
final class SchemaDOMImplementation implements DOMImplementation {

    private static final SchemaDOMImplementation singleton = new SchemaDOMImplementation();

    /** NON-DOM: Obtain and return the single shared object */
    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    private SchemaDOMImplementation() {}

    public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype)
            throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId)
            throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    public Object getFeature(String feature, String version) {
        if (singleton.hasFeature(feature, version)) {
            return singleton;
        }
        return null;
    }

    public boolean hasFeature(String feature, String version) {
        final boolean anyVersion = version == null || version.length() == 0;
        return (feature.equalsIgnoreCase("Core") || feature.equalsIgnoreCase("XML")) &&
            (anyVersion || version.equals("1.0") || version.equals("2.0") || version.equals("3.0"));
    }

}
