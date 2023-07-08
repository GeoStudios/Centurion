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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.xs;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.XMLChar;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.datatypes.XSQName;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Represent the schema type "QName" and "NOTATION"
 *
 * @xerces.internal
 *
 *
 */
public class QNameDV extends TypeValidator {

    private static final String EMPTY_STRING = "";

    public short getAllowedFacets() {
        return (XSSimpleTypeDecl.FACET_LENGTH | XSSimpleTypeDecl.FACET_MINLENGTH | XSSimpleTypeDecl.FACET_MAXLENGTH | XSSimpleTypeDecl.FACET_PATTERN | XSSimpleTypeDecl.FACET_ENUMERATION | XSSimpleTypeDecl.FACET_WHITESPACE);
    }

    public Object getActualValue(String content, ValidationContext context)
        throws InvalidDatatypeValueException {

        // "prefix:localpart" or "localpart"
        // get prefix and local part out of content
        String prefix, localpart;
        int colonptr = content.indexOf(":");
        if (colonptr > 0) {
            prefix = context.getSymbol(content.substring(0,colonptr));
            localpart = content.substring(colonptr+1);
        } else {
            prefix = EMPTY_STRING;
            localpart = content;
        }

        // both prefix (if any) a nd localpart must be valid NCName
        if (prefix.length() > 0 && !XMLChar.isValidNCName(prefix))
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "QName"});

        if(!XMLChar.isValidNCName(localpart))
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "QName"});

        // resove prefix to a uri, report an error if failed
        String uri = context.getURI(prefix);
        if (prefix.length() > 0 && uri == null)
            throw new InvalidDatatypeValueException("UndeclaredPrefix", new Object[]{content, prefix});

        return new XQName(prefix, context.getSymbol(localpart), context.getSymbol(content), uri);

    }

    // REVISIT: qname and notation shouldn't support length facets.
    //          now we just return the length of the rawname
    public int getDataLength(Object value) {
        return ((XQName)value).rawname.length();
    }

    /**
     * represent QName data
     */
    private static final class XQName extends QName implements XSQName {
        /** Constructs a QName with the specified values. */
        public XQName(String prefix, String localpart, String rawname, String uri) {
            setValues(prefix, localpart, rawname, uri);
        } // <init>(String,String,String,String)

        /** Returns true if the two objects are equal. */
        public boolean equals(Object object) {
            if (object instanceof QName qname) {
                return uri == qname.uri && localpart == qname.localpart;
            }
            return false;
        } // equals(Object):boolean

        public synchronized String toString() {
            return rawname;
        }
        public javax.xml.namespace.QName getJAXPQName() {
            return new javax.xml.namespace.QName(uri, localpart, prefix);
        }
        public QName getXNIQName() {
            return this;
        }
    }
} // class QNameDVDV
