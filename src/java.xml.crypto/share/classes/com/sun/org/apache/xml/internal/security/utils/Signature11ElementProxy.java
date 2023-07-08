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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils;


import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Class SignatureElementProxy
 *
 */
public abstract class Signature11ElementProxy extends ElementProxy {

    protected Signature11ElementProxy() {
    }

    /**
     * Constructor Signature11ElementProxy
     *
     * @param doc
     */
    public Signature11ElementProxy(Document doc) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        setDocument(doc);
        setElement(XMLUtils.createElementInSignature11Space(doc, this.getBaseLocalName()));

        String prefix = ElementProxy.getDefaultPrefix(getBaseNamespace());
        if (prefix == null || prefix.length() == 0) {
            getElement().setAttributeNS(Constants.NamespaceSpecNS, "xmlns", getBaseNamespace());
        } else {
            getElement().setAttributeNS(Constants.NamespaceSpecNS, "xmlns:" + prefix, getBaseNamespace());
        }
    }

    /**
     * Constructor Signature11ElementProxy
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public Signature11ElementProxy(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);

    }

    /** {@inheritDoc} */
    public String getBaseNamespace() {
        return Constants.SignatureSpec11NS;
    }
}
