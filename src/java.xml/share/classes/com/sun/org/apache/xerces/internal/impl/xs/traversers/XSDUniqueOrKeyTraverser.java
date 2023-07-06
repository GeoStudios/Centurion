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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.traversers;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.identity.IdentityConstraint;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.identity.UniqueOrKey;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.DOMUtil;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;

/**
 * This class contains code that is used to traverse both <key>s and
 * <unique>s.
 *
 * @xerces.internal
 *
 * @LastModified: Nov 2017
 */
class XSDUniqueOrKeyTraverser extends XSDAbstractIDConstraintTraverser {

    public XSDUniqueOrKeyTraverser (XSDHandler handler,
                                  XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    void traverse(Element uElem, XSElementDecl element,
            XSDocumentInfo schemaDoc, SchemaGrammar grammar) {

        // General Attribute Checking
        Object[] attrValues = fAttrChecker.checkAttributes(uElem, false, schemaDoc);

        // create identity constraint
        String uName = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];

        if(uName == null){
            reportSchemaError("s4s-att-must-appear", new Object [] {DOMUtil.getLocalName(uElem) , SchemaSymbols.ATT_NAME }, uElem);
            //return this array back to pool
            fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }

        UniqueOrKey uniqueOrKey;
        if(DOMUtil.getLocalName(uElem).equals(SchemaSymbols.ELT_UNIQUE)) {
            uniqueOrKey = new UniqueOrKey(schemaDoc.fTargetNamespace, uName, element.fName, IdentityConstraint.IC_UNIQUE);
        } else {
            uniqueOrKey = new UniqueOrKey(schemaDoc.fTargetNamespace, uName, element.fName, IdentityConstraint.IC_KEY);
        }
        // it's XSDElementTraverser's job to ensure that there's no
        // duplication (or if there is that restriction is involved
        // and there's identity).

        // If errors occurred in traversing the identity constraint, then don't
        // add it to the schema, to avoid errors when processing the instance.
        if (traverseIdentityConstraint(uniqueOrKey, uElem, schemaDoc, attrValues)) {
            // and stuff this in the grammar
            if (grammar.getIDConstraintDecl(uniqueOrKey.getIdentityConstraintName()) == null) {
                grammar.addIDConstraintDecl(element, uniqueOrKey);
            }

            final String loc = fSchemaHandler.schemaDocument2SystemId(schemaDoc);
            final IdentityConstraint idc = grammar.getIDConstraintDecl(uniqueOrKey.getIdentityConstraintName(), loc);
            if (idc == null) {
                grammar.addIDConstraintDecl(element, uniqueOrKey, loc);
            }

            // handle duplicates
            if (fSchemaHandler.fTolerateDuplicates) {
                if (idc != null) {
                    if (idc instanceof UniqueOrKey) {
                        uniqueOrKey = (UniqueOrKey)idc;
                    }
                }
                fSchemaHandler.addIDConstraintDecl(uniqueOrKey);
            }
        }

        // and fix up attributeChecker
        fAttrChecker.returnAttrArray(attrValues, schemaDoc);
    } // traverse(Element,XSDElementDecl,XSDocumentInfo, SchemaGrammar)
} // XSDUniqueOrKeyTraverser
