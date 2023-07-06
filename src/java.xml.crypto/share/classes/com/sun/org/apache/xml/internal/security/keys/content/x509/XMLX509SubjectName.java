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
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.RFC2253Parser;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 */
public class XMLX509SubjectName extends SignatureElementProxy implements XMLX509DataContent {

    /**
     * Constructor X509SubjectName
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public XMLX509SubjectName(Element element, String baseURI)
        throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Constructor X509SubjectName
     *
     * @param doc
     * @param X509SubjectNameString
     */
    public XMLX509SubjectName(Document doc, String X509SubjectNameString) {
        super(doc);

        this.addText(X509SubjectNameString);
    }

    /**
     * Constructor XMLX509SubjectName
     *
     * @param doc
     * @param x509certificate
     */
    public XMLX509SubjectName(Document doc, X509Certificate x509certificate) {
        this(doc, x509certificate.getSubjectX500Principal().getName());
    }

    /**
     * Method getSubjectName
     *
     *
     * @return the subject name
     */
    public String getSubjectName() {
        return RFC2253Parser.normalize(this.getTextFromTextChild());
    }

    /** {@inheritDoc} */
    public boolean equals(Object obj) {
        if (!(obj instanceof XMLX509SubjectName other)) {
            return false;
        }

        String otherSubject = other.getSubjectName();
        String thisSubject = this.getSubjectName();

        return thisSubject.equals(otherSubject);
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getSubjectName().hashCode();
        return result;
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_X509SUBJECTNAME;
    }
}
