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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectjava.util.ListImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.Shortjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSAttributeUse;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSConstants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSObjectjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSValue;















/**
 * The XML representation for an attribute use
 * schema component is a local <attribute> element information item
 *
 * @xerces.internal
 *
 */
public class XSAttributeUseImpl implements XSAttributeUse {

    // the referred attribute decl
    public XSAttributeDecl fAttrDecl = null;
    // use information: SchemaSymbols.USE_OPTIONAL, REQUIRED, PROHIBITED
    public short fUse = SchemaSymbols.USE_OPTIONAL;
    // value constraint type: default, fixed or !specified
    public short fConstraintType = XSConstants.VC_NONE;
    // value constraint value
    public ValidatedInfo fDefault = null;
    // optional annotation
    public XSObjectList fAnnotations = null;

    public void reset(){
        fDefault = null;
        fAttrDecl = null;
        fUse = SchemaSymbols.USE_OPTIONAL;
        fConstraintType = XSConstants.VC_NONE;
        fAnnotations = null;
    }

    /**
     * Get the type of the object, i.e ELEMENT_DECLARATION.
     */
    public short getType() {
        return XSConstants.ATTRIBUTE_USE;
    }

    /**
     * The <code>name</code> of this <code>XSObject</code> depending on the
     * <code>XSObject</code> type.
     */
    public String getName() {
        return null;
    }

    /**
     * The namespace URI of this node, or <code>null</code> if it is
     * unspecified.  defines how a namespace URI is attached to schema
     * components.
     */
    public String getNamespace() {
        return null;
    }

    /**
     * {required} determines whether this use of an attribute declaration
     * requires an appropriate attribute information item to be present, or
     * merely allows it.
     */
    public boolean getRequired() {
        return fUse == SchemaSymbols.USE_REQUIRED;
    }

    /**
     * {attribute declaration} provides the attribute declaration itself,
     * which will in turn determine the simple type definition used.
     */
    public XSAttributeDeclaration getAttrDeclaration() {
        return fAttrDecl;
    }

    /**
     * Value Constraint: one of default, fixed.
     */
    public short getConstraintType() {
        return fConstraintType;
    }

    /**
     * Value Constraint: The actual value (with respect to the {type
     * definition}).
     */
    @Deprecated
    public String getConstraintValue() {
        // REVISIT: SCAPI: what's the proper representation
        return getConstraintType() == XSConstants.VC_NONE ?
               null :
               fDefault.stringValue();
    }

    /**
     * @see org.apache.xerces.xs.XSObject#getNamespaceItem()
     */
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }

    @Deprecated
    public Object getActualVC() {
        return getConstraintType() == XSConstants.VC_NONE ?
               null :
               fDefault.actualValue;
    }

    @Deprecated
    public short getActualVCType() {
        return getConstraintType() == XSConstants.VC_NONE ?
               XSConstants.UNAVAILABLE_DT :
               fDefault.actualValueType;
    }

    @Deprecated
    public ShortList getItemValueTypes() {
        return getConstraintType() == XSConstants.VC_NONE ?
               null :
               fDefault.itemValueTypes;
    }

    public XSValue getValueConstraintValue() {
        return fDefault;
    }

    /**
     * Optional. Annotations.
     */
    public XSObjectList getAnnotations() {
        return (fAnnotations != null) ? fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

} // class XSAttributeUseImpl
