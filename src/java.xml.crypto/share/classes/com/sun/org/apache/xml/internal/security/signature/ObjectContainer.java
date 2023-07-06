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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature;


import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * Handles {@code &lt;ds:Object&gt;} elements
 * {@code Object} {@link Element} supply facility which can contain any kind data
 *
 * $todo$ if we remove childen, the boolean values are not updated
 */
public class ObjectContainer extends SignatureElementProxy {

    /**
     * Constructs {@link ObjectContainer}
     *
     * @param doc the {@link Document} in which {@code Object} element is placed
     */
    public ObjectContainer(Document doc) {
        super(doc);
    }

    /**
     * Constructs {@link ObjectContainer} from {@link Element}
     *
     * @param element is {@code Object} element
     * @param baseURI the URI of the resource where the XML instance was stored
     * @throws XMLSecurityException
     */
    public ObjectContainer(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Sets the {@code Id} attribute
     *
     * @param Id {@code Id} attribute
     */
    public void setId(String Id) {
        if (Id != null) {
            setLocalIdAttribute(Constants._ATT_ID, Id);
        }
    }

    /**
     * Returns the {@code Id} attribute
     *
     * @return the {@code Id} attribute
     */
    public String getId() {
        return getLocalAttribute(Constants._ATT_ID);
    }

    /**
     * Sets the {@code MimeType} attribute
     *
     * @param MimeType the {@code MimeType} attribute
     */
    public void setMimeType(String MimeType) {
        if (MimeType != null) {
            setLocalAttribute(Constants._ATT_MIMETYPE, MimeType);
        }
    }

    /**
     * Returns the {@code MimeType} attribute
     *
     * @return the {@code MimeType} attribute
     */
    public String getMimeType() {
        return getLocalAttribute(Constants._ATT_MIMETYPE);
    }

    /**
     * Sets the {@code Encoding} attribute
     *
     * @param Encoding the {@code Encoding} attribute
     */
    public void setEncoding(String Encoding) {
        if (Encoding != null) {
            setLocalAttribute(Constants._ATT_ENCODING, Encoding);
        }
    }

    /**
     * Returns the {@code Encoding} attribute
     *
     * @return the {@code Encoding} attribute
     */
    public String getEncoding() {
        return getLocalAttribute(Constants._ATT_ENCODING);
    }

    /**
     * Adds child Node
     *
     * @param node child Node
     * @return the new node in the tree.
     */
    public Node appendChild(Node node) {
        appendSelf(node);
        return node;
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_OBJECT;
    }
}
