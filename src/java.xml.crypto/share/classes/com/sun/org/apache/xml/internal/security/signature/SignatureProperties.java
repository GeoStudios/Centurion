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
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Attr;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Handles {@code &lt;ds:SignatureProperties&gt;} elements
 * This Element holds {@link SignatureProperty} properties that contain additional information items
 * concerning the generation of the signature.
 * for example, data-time stamp, serial number of cryptographic hardware.
 *
 */
public class SignatureProperties extends SignatureElementProxy {

    /**
     * Constructor SignatureProperties
     *
     * @param doc
     */
    public SignatureProperties(Document doc) {
        super(doc);

        addReturnToSelf();
    }

    /**
     * Constructs {@link SignatureProperties} from {@link Element}
     * @param element {@code SignatureProperties} element
     * @param baseURI the URI of the resource where the XML instance was stored
     * @throws XMLSecurityException
     */
    public SignatureProperties(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);

        Attr attr = element.getAttributeNodeNS(null, "Id");
        if (attr != null) {
            element.setIdAttributeNode(attr, true);
        }

        Element[] propertyElems =
                XMLUtils.selectDsNodes(getFirstChild(), Constants._TAG_SIGNATUREPROPERTY);
        for (Element propertyElem : propertyElems) {
            Attr propertyAttr = propertyElem.getAttributeNodeNS(null, "Id");
            if (propertyAttr != null) {
                propertyElem.setIdAttributeNode(propertyAttr, true);
            }
        }
    }

    /**
     * Return the nonnegative number of added SignatureProperty elements.
     *
     * @return the number of SignatureProperty elements
     */
    public int getLength() {
        Element[] propertyElems =
            XMLUtils.selectDsNodes(getFirstChild(), Constants._TAG_SIGNATUREPROPERTY);

        return propertyElems.length;
    }

    /**
     * Return the <i>i</i><sup>th</sup> SignatureProperty. Valid {@code i}
     * values are 0 to {@code {link@ getSize}-1}.
     *
     * @param i Index of the requested {@link SignatureProperty}
     * @return the <i>i</i><sup>th</sup> SignatureProperty
     * @throws XMLSignatureException
     */
    public SignatureProperty item(int i) throws XMLSignatureException {
        try {
            Element propertyElem =
                XMLUtils.selectDsNode(getFirstChild(), Constants._TAG_SIGNATUREPROPERTY, i);

            if (propertyElem == null) {
                return null;
            }
            return new SignatureProperty(propertyElem, this.baseURI);
        } catch (XMLSecurityException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Sets the {@code Id} attribute
     *
     * @param Id the {@code Id} attribute
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
     * Method addSignatureProperty
     *
     * @param sp
     */
    public void addSignatureProperty(SignatureProperty sp) {
        appendSelf(sp);
        addReturnToSelf();
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_SIGNATUREPROPERTIES;
    }
}
