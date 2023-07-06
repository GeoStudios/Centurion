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

package java.xml.crypto.share.classes.org.jcp.xml.dsig.internal.dom;


import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;
import java.math.BigInteger;
import javax.security.auth.x500.X500Principal;
import java.xml.crypto.share.classes.org.w3c.dom.Document;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * DOM-based implementation of X509IssuerSerial.
 *
 */
public final class DOMX509IssuerSerial extends DOMStructure
    implements X509IssuerSerial {

    private final String issuerName;
    private final BigInteger serialNumber;

    /**
     * Creates a {@code DOMX509IssuerSerial} containing the specified
     * issuer distinguished name/serial number pair.
     *
     * @param issuerName the X.509 issuer distinguished name in RFC 2253
     *    String format
     * @param serialNumber the serial number
     * @throws IllegalArgumentException if the format of {@code issuerName}
     *    is not RFC 2253 compliant
     * @throws NullPointerException if {@code issuerName} or
     *    {@code serialNumber} is {@code null}
     */
    public DOMX509IssuerSerial(String issuerName, BigInteger serialNumber) {
        if (issuerName == null) {
            throw new NullPointerException("issuerName cannot be null");
        }
        if (serialNumber == null) {
            throw new NullPointerException("serialNumber cannot be null");
        }
        // check that issuer distinguished name conforms to RFC 2253
        new X500Principal(issuerName);
        this.issuerName = issuerName;
        this.serialNumber = serialNumber;
    }

    /**
     * Creates a {@code DOMX509IssuerSerial} from an element.
     *
     * @param isElem an X509IssuerSerial element
     */
    public DOMX509IssuerSerial(Element isElem) throws MarshalException {
        Element iNElem = DOMUtils.getFirstChildElement(isElem,
                                                       "X509IssuerName",
                                                       XMLSignature.XMLNS);
        Element sNElem = DOMUtils.getNextSiblingElement(iNElem,
                                                        "X509SerialNumber",
                                                        XMLSignature.XMLNS);
        issuerName = iNElem.getFirstChild().getNodeValue();
        serialNumber = new BigInteger(sNElem.getFirstChild().getNodeValue());
    }

    public String getIssuerName() {
        return issuerName;
    }

    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    @Override
    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context)
        throws MarshalException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(parent);

        Element isElem = DOMUtils.createElement(ownerDoc, "X509IssuerSerial",
                                                XMLSignature.XMLNS, dsPrefix);
        Element inElem = DOMUtils.createElement(ownerDoc, "X509IssuerName",
                                                XMLSignature.XMLNS, dsPrefix);
        Element snElem = DOMUtils.createElement(ownerDoc, "X509SerialNumber",
                                                XMLSignature.XMLNS, dsPrefix);
        inElem.appendChild(ownerDoc.createTextNode(issuerName));
        snElem.appendChild(ownerDoc.createTextNode(serialNumber.toString()));
        isElem.appendChild(inElem);
        isElem.appendChild(snElem);
        parent.appendChild(isElem);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X509IssuerSerial ois)) {
            return false;
        }
        return issuerName.equals(ois.getIssuerName()) &&
                serialNumber.equals(ois.getSerialNumber());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + issuerName.hashCode();
        result = 31 * result + serialNumber.hashCode();

        return result;
    }
}
