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
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.identity.KeyRef;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.identity.UniqueOrKey;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class contains code that is used to traverse <keyref>s.
 *
 * @xerces.internal
 *
 */
class XSDKeyrefTraverser extends XSDAbstractIDConstraintTraverser {

    public XSDKeyrefTraverser (XSDHandler handler,
                                  XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    void traverse(Element krElem, XSElementDecl element,
            XSDocumentInfo schemaDoc, SchemaGrammar grammar) {

        // General Attribute Checking
        Object[] attrValues = fAttrChecker.checkAttributes(krElem, false, schemaDoc);

        // create identity constraint
        String krName = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        if(krName == null){
            reportSchemaError("s4s-att-must-appear", new Object [] {SchemaSymbols.ELT_KEYREF , SchemaSymbols.ATT_NAME }, krElem);
            //return this array back to pool
            fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        QName kName = (QName)attrValues[XSAttributeChecker.ATTIDX_REFER];
        if(kName == null){
            reportSchemaError("s4s-att-must-appear", new Object [] {SchemaSymbols.ELT_KEYREF , SchemaSymbols.ATT_REFER }, krElem);
            //return this array back to pool
            fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }

        UniqueOrKey key = null;
        IdentityConstraint ret = (IdentityConstraint)fSchemaHandler.getGlobalDecl(schemaDoc, XSDHandler.IDENTITYCONSTRAINT_TYPE, kName, krElem);
        // if ret == null, we've already reported an error in getGlobalDecl
        // we report an error only when ret != null, and the return type keyref
        if (ret != null) {
            if (ret.getCategory() == IdentityConstraint.IC_KEY ||
                ret.getCategory() == IdentityConstraint.IC_UNIQUE) {
                key = (UniqueOrKey)ret;
            } else {
                reportSchemaError("src-resolve", new Object[]{kName.rawname, "identity constraint key/unique"}, krElem);
            }
        }

        if(key == null) {
            fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }

        KeyRef keyRef = new KeyRef(schemaDoc.fTargetNamespace, krName, element.fName, key);

        // If errors occurred in traversing the identity constraint, then don't
        // add it to the schema, to avoid errors when processing the instance.
        if (traverseIdentityConstraint(keyRef, krElem, schemaDoc, attrValues)) {
            //Schema Component Constraint: Identity-constraint Definition Properties Correct
            //2 If the {identity-constraint category} is keyref, the cardinality of the {fields} must equal that of the {fields} of the {referenced key}.
            if(key.getFieldCount() != keyRef.getFieldCount()) {
                reportSchemaError("c-props-correct.2" , new Object [] {krName,key.getIdentityConstraintName()}, krElem);
            } else {
                // add key reference to element decl
                // and stuff this in the grammar
                if (grammar.getIDConstraintDecl(keyRef.getIdentityConstraintName()) == null) {
                    grammar.addIDConstraintDecl(element, keyRef);
                }

                // also add it to extended map
                final String loc = fSchemaHandler.schemaDocument2SystemId(schemaDoc);
                final IdentityConstraint idc = grammar.getIDConstraintDecl(keyRef.getIdentityConstraintName(), loc);
                if (idc  == null) {
                    grammar.addIDConstraintDecl(element, keyRef, loc);
                }

                // handle duplicates
                if (fSchemaHandler.fTolerateDuplicates) {
                    if (idc  != null) {
                        if (idc instanceof KeyRef) {
                            keyRef = (KeyRef) idc;
                        }
                    }
                    fSchemaHandler.addIDConstraintDecl(keyRef);
                }
            }
        }

        // and put back attributes
        fAttrChecker.returnAttrArray(attrValues, schemaDoc);
    } // traverse(Element,int,XSDocumentInfo, SchemaGrammar)
} // XSDKeyrefTraverser
