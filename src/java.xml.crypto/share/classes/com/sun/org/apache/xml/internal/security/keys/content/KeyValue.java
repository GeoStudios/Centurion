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

import java.base.share.classes.java.security.PublicKey;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content.keyvalues.DSAKeyValue;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content.keyvalues.ECKeyValue;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content.keyvalues.RSAKeyValue;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * The KeyValue element contains a single public key that may be useful in
 * validating the signature. Structured formats for defining DSA (REQUIRED)
 * and RSA (RECOMMENDED) public keys are defined in Signature Algorithms
 * (section 6.4). The KeyValue element may include externally defined public
 * keys values represented as PCDATA or element types from an external
 * namespace.
 *
 */
public class KeyValue extends SignatureElementProxy implements KeyInfoContent {

    /**
     * Constructor KeyValue
     *
     * @param doc
     * @param dsaKeyValue
     */
    public KeyValue(Document doc, DSAKeyValue dsaKeyValue) {
        super(doc);

        addReturnToSelf();
        appendSelf(dsaKeyValue);
        addReturnToSelf();
    }

    /**
     * Constructor KeyValue
     *
     * @param doc
     * @param rsaKeyValue
     */
    public KeyValue(Document doc, RSAKeyValue rsaKeyValue) {
        super(doc);

        addReturnToSelf();
        appendSelf(rsaKeyValue);
        addReturnToSelf();
    }

    /**
     * Constructor KeyValue
     *
     * @param doc
     * @param unknownKeyValue
     */
    public KeyValue(Document doc, Element unknownKeyValue) {
        super(doc);

        addReturnToSelf();
        appendSelf(unknownKeyValue);
        addReturnToSelf();
    }

    /**
     * Constructor KeyValue
     *
     * @param doc
     * @param pk
     */
    public KeyValue(Document doc, PublicKey pk) {
        super(doc);

        addReturnToSelf();

        if (pk instanceof java.security.interfaces.DSAPublicKey) {
            DSAKeyValue dsa = new DSAKeyValue(getDocument(), pk);

            appendSelf(dsa);
            addReturnToSelf();
        } else if (pk instanceof java.security.interfaces.RSAPublicKey) {
            RSAKeyValue rsa = new RSAKeyValue(getDocument(), pk);

            appendSelf(rsa);
            addReturnToSelf();
        } else if (pk instanceof java.security.interfaces.ECPublicKey) {
            ECKeyValue ec = new ECKeyValue(getDocument(), pk);

            appendSelf(ec);
            addReturnToSelf();
        } else {
            String error = "The given PublicKey type " + pk + " is not supported. Only DSAPublicKey and "
                + "RSAPublicKey and ECPublicKey types are currently supported";
            throw new IllegalArgumentException(error);
        }
    }

    /**
     * Constructor KeyValue
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public KeyValue(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Method getPublicKey
     *
     * @return the public key
     * @throws XMLSecurityException
     */
    public PublicKey getPublicKey() throws XMLSecurityException {
        Element rsa =
            XMLUtils.selectDsNode(
                getFirstChild(), Constants._TAG_RSAKEYVALUE, 0);

        if (rsa != null) {
            RSAKeyValue kv = new RSAKeyValue(rsa, this.baseURI);
            return kv.getPublicKey();
        }

        Element dsa =
            XMLUtils.selectDsNode(
                getFirstChild(), Constants._TAG_DSAKEYVALUE, 0);

        if (dsa != null) {
            DSAKeyValue kv = new DSAKeyValue(dsa, this.baseURI);
            return kv.getPublicKey();
        }

        return null;
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_KEYVALUE;
    }
}
