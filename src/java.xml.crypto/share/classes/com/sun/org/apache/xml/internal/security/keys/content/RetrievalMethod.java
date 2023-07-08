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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content;


import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.Transforms;
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



public class RetrievalMethod extends SignatureElementProxy implements KeyInfoContent {

    /** DSA retrieval */
    public static final String TYPE_DSA = Constants.SignatureSpecNS + "DSAKeyValue";
    /** RSA retrieval */
    public static final String TYPE_RSA = Constants.SignatureSpecNS + "RSAKeyValue";
    /** PGP retrieval */
    public static final String TYPE_PGP = Constants.SignatureSpecNS + "PGPData";
    /** SPKI retrieval */
    public static final String TYPE_SPKI = Constants.SignatureSpecNS + "SPKIData";
    /** MGMT retrieval */
    public static final String TYPE_MGMT = Constants.SignatureSpecNS + "MgmtData";
    /** X509 retrieval */
    public static final String TYPE_X509 = Constants.SignatureSpecNS + "X509Data";
    /** RAWX509 retrieval */
    public static final String TYPE_RAWX509 = Constants.SignatureSpecNS + "rawX509Certificate";

    /**
     * Constructor RetrievalMethod
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public RetrievalMethod(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Constructor RetrievalMethod
     *
     * @param doc
     * @param URI
     * @param transforms
     * @param Type
     */
    public RetrievalMethod(Document doc, String URI, Transforms transforms, String Type) {
        super(doc);

        setLocalAttribute(Constants._ATT_URI, URI);

        if (Type != null) {
            setLocalAttribute(Constants._ATT_TYPE, Type);
        }

        if (transforms != null) {
            appendSelf(transforms);
            addReturnToSelf();
        }
    }

    /**
     * Method getURIAttr
     *
     * @return the URI attribute
     */
    public Attr getURIAttr() {
        return getElement().getAttributeNodeNS(null, Constants._ATT_URI);
    }

    /**
     * Method getURI
     *
     * @return URI string
     */
    public String getURI() {
        return getLocalAttribute(Constants._ATT_URI);
    }

    /** @return the type*/
    public String getType() {
        return getLocalAttribute(Constants._ATT_TYPE);
    }

    /**
     * Method getTransforms
     *
     * @throws XMLSecurityException
     * @return the transformations
     */
    public Transforms getTransforms() throws XMLSecurityException {
        try {
            Element transformsElem =
                XMLUtils.selectDsNode(
                    getFirstChild(), Constants._TAG_TRANSFORMS, 0);

            if (transformsElem != null) {
                return new Transforms(transformsElem, this.baseURI);
            }

            return null;
        } catch (XMLSignatureException ex) {
            throw new XMLSecurityException(ex);
        }
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_RETRIEVALMETHOD;
    }
}
