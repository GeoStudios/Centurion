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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Represent the schema type "entity"
 *
 * @xerces.internal
 *
 *
 */
public class EntityDV extends TypeValidator {

    public short getAllowedFacets(){
        return (XSSimpleTypeDecl.FACET_LENGTH | XSSimpleTypeDecl.FACET_MINLENGTH | XSSimpleTypeDecl.FACET_MAXLENGTH | XSSimpleTypeDecl.FACET_PATTERN | XSSimpleTypeDecl.FACET_ENUMERATION | XSSimpleTypeDecl.FACET_WHITESPACE );
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (!XMLChar.isValidNCName(content)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "NCName"});
        }

        return content;
    }

    public void checkExtraRules(Object value, ValidationContext context) throws InvalidDatatypeValueException {
        if (!context.isEntityUnparsed((String)value)) {
            throw new InvalidDatatypeValueException("UndeclaredEntity", new Object[]{value});
        }
    }

} // class EntityDV
