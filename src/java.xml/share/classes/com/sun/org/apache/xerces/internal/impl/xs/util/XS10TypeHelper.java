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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Class defining utility/helper methods to support XML Schema 1.0 implementation.
 *
 * @xerces.internal
 *
 */
public class XS10TypeHelper {

    /*
     * Class constructor.
     */
    private XS10TypeHelper() {
       // a private constructor, to prohibit instantiating this class from an outside class/application.
       // this is a good practice, since all methods of this class are "static".
    }

    /*
     * Get name of an XSD type definition as a string value (which will typically be the value of "name" attribute of a
     * type definition, or an internal name determined by the validator for anonymous types).
     */
    public static String getSchemaTypeName(XSTypeDefinition typeDefn) {

        String typeNameStr;
        if (typeDefn instanceof XSSimpleTypeDefinition) {
            typeNameStr = ((XSSimpleTypeDecl) typeDefn).getTypeName();
        }
        else {
            typeNameStr = ((XSComplexTypeDecl) typeDefn).getTypeName();
        }

        return typeNameStr;

    } // getSchemaTypeName

} // class XS10TypeHelper
