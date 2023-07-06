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

import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

public class XMLX509CRL extends SignatureElementProxy implements XMLX509DataContent {

    /**
     * Constructor XMLX509CRL
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public XMLX509CRL(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Constructor X509CRL
     *
     * @param doc
     * @param crlBytes
     */
    public XMLX509CRL(Document doc, byte[] crlBytes) {
        super(doc);

        this.addBase64Text(crlBytes);
    }

    /**
     * Method getCRLBytes
     *
     * @return the CRL bytes
     * @throws XMLSecurityException
     */
    public byte[] getCRLBytes() throws XMLSecurityException {
        return this.getBytesFromTextChild();
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_X509CRL;
    }
}
