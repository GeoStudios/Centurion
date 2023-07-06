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


import java.io.ByteArrayInputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.base.share.classes.java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.base.share.classes.java.util.Arrays;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




public class XMLX509Certificate extends SignatureElementProxy implements XMLX509DataContent {

    /** Field JCA_CERT_ID */
    public static final String JCA_CERT_ID = "X.509";

    /**
     * Constructor X509Certificate
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public XMLX509Certificate(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Constructor X509Certificate
     *
     * @param doc
     * @param certificateBytes
     */
    public XMLX509Certificate(Document doc, byte[] certificateBytes) {
        super(doc);

        this.addBase64Text(certificateBytes);
    }

    /**
     * Constructor XMLX509Certificate
     *
     * @param doc
     * @param x509certificate
     * @throws XMLSecurityException
     */
    public XMLX509Certificate(Document doc, X509Certificate x509certificate)
        throws XMLSecurityException {
        super(doc);

        try {
            this.addBase64Text(x509certificate.getEncoded());
        } catch (java.security.cert.CertificateEncodingException ex) {
            throw new XMLSecurityException(ex);
        }
    }

    /**
     * Method getCertificateBytes
     *
     * @return the certificate bytes
     * @throws XMLSecurityException
     */
    public byte[] getCertificateBytes() throws XMLSecurityException {
        return this.getBytesFromTextChild();
    }

    /**
     * Method getX509Certificate
     *
     * @return the x509 certificate
     * @throws XMLSecurityException
     */
    public X509Certificate getX509Certificate() throws XMLSecurityException {
        byte[] certbytes = this.getCertificateBytes();
        try (InputStream is = new ByteArrayInputStream(certbytes)) {
            CertificateFactory certFact =
                CertificateFactory.getInstance(XMLX509Certificate.JCA_CERT_ID);
            return (X509Certificate) certFact.generateCertificate(is);
        } catch (CertificateException | IOException ex) {
            throw new XMLSecurityException(ex);
        }
    }

    /**
     * Method getPublicKey
     *
     * @return the publickey
     * @throws XMLSecurityException
     */
    public PublicKey getPublicKey() throws XMLSecurityException, IOException {
        X509Certificate cert = this.getX509Certificate();

        if (cert != null) {
            return cert.getPublicKey();
        }

        return null;
    }

    /** {@inheritDoc} */
    public boolean equals(Object obj) {
        if (!(obj instanceof XMLX509Certificate other)) {
            return false;
        }
        try {
            return Arrays.equals(other.getCertificateBytes(), this.getCertificateBytes());
        } catch (XMLSecurityException ex) {
            return false;
        }
    }

    public int hashCode() {
        int result = 17;
        try {
            byte[] bytes = getCertificateBytes();
            for (int i = 0; i < bytes.length; i++) {
                result = 31 * result + bytes[i];
            }
        } catch (XMLSecurityException e) {
            LOG.debug(e.getMessage(), e);
        }
        return result;
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_X509CERTIFICATE;
    }
}
