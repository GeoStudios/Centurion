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

import java.security.cert.X509Certificate;
import java.base.share.classes.java.util.Arrays;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
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
 * Handles SubjectKeyIdentifier (SKI) for X.509v3.
 *
 * @see <A HREF="http://docs.oracle.com/javase/8/docs/api/java/security/cert/X509Extension.html">
 * Interface X509Extension</A>
 */
public class XMLX509SKI extends SignatureElementProxy implements XMLX509DataContent {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(XMLX509SKI.class);

    /**
     * {@code SubjectKeyIdentifier (id-ce-subjectKeyIdentifier) (2.5.29.14)}:
     * This extension identifies the public key being certified. It enables
     * distinct keys used by the same subject to be differentiated
     * (e.g., as key updating occurs).
     * <p></p>
     * A key identifier shall be unique with respect to all key identifiers
     * for the subject with which it is used. This extension is always non-critical.
     */
    public static final String SKI_OID = "2.5.29.14"; //NOPMD

    /**
     * Constructor X509SKI
     *
     * @param doc
     * @param skiBytes
     */
    public XMLX509SKI(Document doc, byte[] skiBytes) {
        super(doc);
        this.addBase64Text(skiBytes);
    }

    /**
     * Constructor XMLX509SKI
     *
     * @param doc
     * @param x509certificate
     * @throws XMLSecurityException
     */
    public XMLX509SKI(Document doc, X509Certificate x509certificate)
        throws XMLSecurityException {
        super(doc);
        this.addBase64Text(XMLX509SKI.getSKIBytesFromCert(x509certificate));
    }

    /**
     * Constructor XMLX509SKI
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public XMLX509SKI(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Method getSKIBytes
     *
     * @return the skibytes
     * @throws XMLSecurityException
     */
    public byte[] getSKIBytes() throws XMLSecurityException {
        return this.getBytesFromTextChild();
    }

    /**
     * Method getSKIBytesFromCert
     *
     * @param cert
     * @return ski bytes from the given certificate
     *
     * @throws XMLSecurityException
     * @see java.security.cert.X509Extension#getExtensionValue(java.lang.String)
     */
    public static byte[] getSKIBytesFromCert(X509Certificate cert)
        throws XMLSecurityException {

        if (cert.getVersion() < 3) {
            Object[] exArgs = { cert.getVersion() };
            throw new XMLSecurityException("certificate.noSki.lowVersion", exArgs);
        }

        /*
         * Gets the DER-encoded OCTET string for the extension value
         * (extnValue) identified by the passed-in oid String. The oid
         * string is represented by a set of positive whole numbers
         * separated by periods.
         */
        byte[] extensionValue = cert.getExtensionValue(XMLX509SKI.SKI_OID);
        if (extensionValue == null) {
            throw new XMLSecurityException("certificate.noSki.null");
        }

        /**
         * Strip away first four bytes from the extensionValue
         * The first two bytes are the tag and length of the extensionValue
         * OCTET STRING, and the next two bytes are the tag and length of
         * the ski OCTET STRING.
         */
        byte[] skidValue = new byte[extensionValue.length - 4];

        System.arraycopy(extensionValue, 4, skidValue, 0, skidValue.length);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Base64 of SKI is " + XMLUtils.encodeToString(skidValue));
        }

        return skidValue;
    }

    /** {@inheritDoc} */
    public boolean equals(Object obj) {
        if (!(obj instanceof XMLX509SKI other)) {
            return false;
        }

        try {
            return Arrays.equals(other.getSKIBytes(), this.getSKIBytes());
        } catch (XMLSecurityException ex) {
            return false;
        }
    }

    public int hashCode() {
        int result = 17;
        try {
            byte[] bytes = getSKIBytes();
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
        return Constants._TAG_X509SKI;
    }
}
