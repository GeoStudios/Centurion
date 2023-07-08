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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content.x509;

import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Signature11ElementProxy;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Attr;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Provides content model support for the {@code dsig11:X509Digest} element.
 *
 */
public class XMLX509Digest extends Signature11ElementProxy implements XMLX509DataContent {

    /**
     * Constructor XMLX509Digest
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public XMLX509Digest(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Constructor XMLX509Digest
     *
     * @param doc
     * @param digestBytes
     * @param algorithmURI
     */
    public XMLX509Digest(Document doc, byte[] digestBytes, String algorithmURI) {
        super(doc);
        this.addBase64Text(digestBytes);
        setLocalAttribute(Constants._ATT_ALGORITHM, algorithmURI);
    }

    /**
     * Constructor XMLX509Digest
     *
     * @param doc
     * @param x509certificate
     * @param algorithmURI
     * @throws XMLSecurityException
     */
    public XMLX509Digest(Document doc, X509Certificate x509certificate, String algorithmURI) throws XMLSecurityException {
        super(doc);
        this.addBase64Text(getDigestBytesFromCert(x509certificate, algorithmURI));
        setLocalAttribute(Constants._ATT_ALGORITHM, algorithmURI);
    }

    /**
     * Method getAlgorithmAttr
     *
     * @return the Algorithm attribute
     */
    public Attr getAlgorithmAttr() {
        return getElement().getAttributeNodeNS(null, Constants._ATT_ALGORITHM);
    }

    /**
     * Method getAlgorithm
     *
     * @return Algorithm string
     */
    public String getAlgorithm() {
        return this.getAlgorithmAttr().getNodeValue();
    }

    /**
     * Method getDigestBytes
     *
     * @return the digestbytes
     * @throws XMLSecurityException
     */
    public byte[] getDigestBytes() throws XMLSecurityException {
        return this.getBytesFromTextChild();
    }

    /**
     * Method getDigestBytesFromCert
     *
     * @param cert
     * @param algorithmURI
     * @return digest bytes from the given certificate
     *
     * @throws XMLSecurityException
     */
    public static byte[] getDigestBytesFromCert(X509Certificate cert, String algorithmURI) throws XMLSecurityException {
        String jcaDigestAlgorithm = JCEMapper.translateURItoJCEID(algorithmURI);
        if (jcaDigestAlgorithm == null) {
            Object[] exArgs = {algorithmURI};
            throw new XMLSecurityException("XMLX509Digest.UnknownDigestAlgorithm", exArgs);
        }

        try {
            MessageDigest md = MessageDigest.getInstance(jcaDigestAlgorithm);
            return md.digest(cert.getEncoded());
        } catch (Exception e) {
            Object[] exArgs = {jcaDigestAlgorithm};
            throw new XMLSecurityException("XMLX509Digest.FailedDigest", exArgs);
        }

    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_X509DIGEST;
    }
}
