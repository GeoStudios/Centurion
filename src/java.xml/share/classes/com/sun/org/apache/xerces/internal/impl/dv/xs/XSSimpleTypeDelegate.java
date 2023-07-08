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


import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeFacetException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.XSFacets;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.Stringjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSObject;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSObjectjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Base class for XSSimpleType wrapper implementations.
 *
 * @xerces.internal
 *
 */
public class XSSimpleTypeDelegate
    implements XSSimpleType {

    protected final XSSimpleType type;

    public XSSimpleTypeDelegate(XSSimpleType type) {
        if (type == null) {
            throw new NullPointerException();
        }
        this.type = type;
    }

    public XSSimpleType getWrappedXSSimpleType() {
        return type;
    }

    public XSObjectList getAnnotations() {
        return type.getAnnotations();
    }

    public boolean getBounded() {
        return type.getBounded();
    }

    public short getBuiltInKind() {
        return type.getBuiltInKind();
    }

    public short getDefinedFacets() {
        return type.getDefinedFacets();
    }

    public XSObjectList getFacets() {
        return type.getFacets();
    }

    public XSObject getFacet(int facetType) {
        return type.getFacet(facetType);
    }

    public boolean getFinite() {
        return type.getFinite();
    }

    public short getFixedFacets() {
        return type.getFixedFacets();
    }

    public XSSimpleTypeDefinition getItemType() {
        return type.getItemType();
    }

    public StringList getLexicalEnumeration() {
        return type.getLexicalEnumeration();
    }

    public String getLexicalFacetValue(short facetName) {
        return type.getLexicalFacetValue(facetName);
    }

    public StringList getLexicalPattern() {
        return type.getLexicalPattern();
    }

    public XSObjectList getMemberTypes() {
        return type.getMemberTypes();
    }

    public XSObjectList getMultiValueFacets() {
        return type.getMultiValueFacets();
    }

    public boolean getNumeric() {
        return type.getNumeric();
    }

    public short getOrdered() {
        return type.getOrdered();
    }

    public XSSimpleTypeDefinition getPrimitiveType() {
        return type.getPrimitiveType();
    }

    public short getVariety() {
        return type.getVariety();
    }

    public boolean isDefinedFacet(short facetName) {
        return type.isDefinedFacet(facetName);
    }

    public boolean isFixedFacet(short facetName) {
        return type.isFixedFacet(facetName);
    }

    public boolean derivedFrom(String namespace, String name, short derivationMethod) {
        return type.derivedFrom(namespace, name, derivationMethod);
    }

    public boolean derivedFromType(XSTypeDefinition ancestorType, short derivationMethod) {
        return type.derivedFromType(ancestorType, derivationMethod);
    }

    public boolean getAnonymous() {
        return type.getAnonymous();
    }

    public XSTypeDefinition getBaseType() {
        return type.getBaseType();
    }

    public short getFinal() {
        return type.getFinal();
    }

    public short getTypeCategory() {
        return type.getTypeCategory();
    }

    public boolean isFinal(short restriction) {
        return type.isFinal(restriction);
    }

    public String getName() {
        return type.getName();
    }

    public String getNamespace() {
        return type.getNamespace();
    }

    public XSNamespaceItem getNamespaceItem() {
        return type.getNamespaceItem();
    }

    public short getType() {
        return type.getType();
    }

    public void applyFacets(XSFacets facets, short presentFacet, short fixedFacet, ValidationContext context)
        throws InvalidDatatypeFacetException {
        type.applyFacets(facets, presentFacet, fixedFacet, context);
    }

    public short getPrimitiveKind() {
        return type.getPrimitiveKind();
    }

    public short getWhitespace() throws DatatypeException {
        return type.getWhitespace();
    }

    public boolean isEqual(Object value1, Object value2) {
        return type.isEqual(value1, value2);
    }

    public boolean isIDType() {
        return type.isIDType();
    }

    public void validate(ValidationContext context, ValidatedInfo validatedInfo)
        throws InvalidDatatypeValueException {
        type.validate(context, validatedInfo);
    }

    public Object validate(String content, ValidationContext context, ValidatedInfo validatedInfo)
        throws InvalidDatatypeValueException {
        return type.validate(content, context, validatedInfo);
    }

    public Object validate(Object content, ValidationContext context, ValidatedInfo validatedInfo)
        throws InvalidDatatypeValueException {
        return type.validate(content, context, validatedInfo);
    }

    public String toString() {
        return type.toString();
    }
}
