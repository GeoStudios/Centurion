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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content.keyvalues;

import java.math.BigInteger;
import java.base.share.classes.java.security.Key;
import java.base.share.classes.java.security.KeyFactory;
import java.base.share.classes.java.security.NoSuchAlgorithmException;
import java.base.share.classes.java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.base.share.classes.java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.I18n;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

public class RSAKeyValue extends SignatureElementProxy implements KeyValueContent {

    /**
     * Constructor RSAKeyValue
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public RSAKeyValue(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Constructor RSAKeyValue
     *
     * @param doc
     * @param modulus
     * @param exponent
     */
    public RSAKeyValue(Document doc, BigInteger modulus, BigInteger exponent) {
        super(doc);

        addReturnToSelf();
        this.addBigIntegerElement(modulus, Constants._TAG_MODULUS);
        this.addBigIntegerElement(exponent, Constants._TAG_EXPONENT);
    }

    /**
     * Constructor RSAKeyValue
     *
     * @param doc
     * @param key
     * @throws IllegalArgumentException
     */
    public RSAKeyValue(Document doc, Key key) throws IllegalArgumentException {
        super(doc);

        addReturnToSelf();

        if (key instanceof RSAPublicKey ) {
            this.addBigIntegerElement(
                ((RSAPublicKey) key).getModulus(), Constants._TAG_MODULUS
            );
            this.addBigIntegerElement(
                ((RSAPublicKey) key).getPublicExponent(), Constants._TAG_EXPONENT
            );
        } else {
            Object[] exArgs = { Constants._TAG_RSAKEYVALUE, key.getClass().getName() };

            throw new IllegalArgumentException(I18n.translate("KeyValue.IllegalArgument", exArgs));
        }
    }

    /** {@inheritDoc} */
    public PublicKey getPublicKey() throws XMLSecurityException {
        try {
            KeyFactory rsaFactory = KeyFactory.getInstance("RSA");

            RSAPublicKeySpec rsaKeyspec =
                new RSAPublicKeySpec(
                    this.getBigIntegerFromChildElement(
                        Constants._TAG_MODULUS, Constants.SignatureSpecNS
                    ),
                    this.getBigIntegerFromChildElement(
                        Constants._TAG_EXPONENT, Constants.SignatureSpecNS
                    )
                );
            PublicKey pk = rsaFactory.generatePublic(rsaKeyspec);

            return pk;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new XMLSecurityException(ex);
        }
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_RSAKEYVALUE;
    }
}
