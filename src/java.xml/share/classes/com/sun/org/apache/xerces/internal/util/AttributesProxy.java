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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import java.xml.share.classes.com.sun.org.xml.sax.Attributejava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.xml.sax.ext.Attributes2;

/**
 * Wraps {@link XMLAttributes} and makes it look like
 * {@link AttributeList} and {@link Attributes}.
 *
 *
 */
@SuppressWarnings("deprecation")
public final class AttributesProxy
    implements AttributeList, Attributes2 {

    //
    // Data
    //

    /** XML attributes. */
    private XMLAttributes fAttributes;

    //
    // Constructors
    //

    public AttributesProxy(XMLAttributes attributes) {
        fAttributes = attributes;
    }

    //
    // Public methods
    //

    /** Sets the XML attributes to be wrapped. */
    public void setAttributes(XMLAttributes attributes) {
        fAttributes = attributes;
    } // setAttributes(XMLAttributes)

    public XMLAttributes getAttributes() {
        return fAttributes;
    }

    /*
     * Attributes methods
     */

    public int getLength() {
        return fAttributes.getLength();
    }

    public String getQName(int index) {
        return fAttributes.getQName(index);
    }

    public String getURI(int index) {
        // This hides the fact that internally we use null instead of empty string
        // SAX requires the URI to be a string or an empty string
        String uri = fAttributes.getURI(index);
        return uri != null ? uri : XMLSymbols.EMPTY_STRING;
    }

    public String getLocalName(int index) {
        return fAttributes.getLocalName(index);
    }

    public String getType(int i) {
        return fAttributes.getType(i);
    }

    public String getType(String name) {
        return fAttributes.getType(name);
    }

    public String getType(String uri, String localName) {
        return uri.equals(XMLSymbols.EMPTY_STRING) ?
                fAttributes.getType(null, localName) :
                    fAttributes.getType(uri, localName);
    }

    public String getValue(int i) {
        return fAttributes.getValue(i);
    }

    public String getValue(String name) {
        return fAttributes.getValue(name);
    }

    public String getValue(String uri, String localName) {
        return uri.equals(XMLSymbols.EMPTY_STRING) ?
                fAttributes.getValue(null, localName) :
                    fAttributes.getValue(uri, localName);
    }

    public int getIndex(String qName) {
        return fAttributes.getIndex(qName);
    }

    public int getIndex(String uri, String localPart) {
        return uri.equals(XMLSymbols.EMPTY_STRING) ?
                fAttributes.getIndex(null, localPart) :
                    fAttributes.getIndex(uri, localPart);
    }

    /*
     * Attributes2 methods
     */

    public boolean isDeclared(int index) {
        if (index < 0 || index >= fAttributes.getLength()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return Boolean.TRUE.equals(
            fAttributes.getAugmentations(index).getItem(
            Constants.ATTRIBUTE_DECLARED));
    }

    public boolean isDeclared(String qName) {
        int index = getIndex(qName);
        if (index == -1) {
            throw new IllegalArgumentException(qName);
        }
        return Boolean.TRUE.equals(
            fAttributes.getAugmentations(index).getItem(
            Constants.ATTRIBUTE_DECLARED));
    }

    public boolean isDeclared(String uri, String localName) {
        int index = getIndex(uri, localName);
        if (index == -1) {
            throw new IllegalArgumentException(localName);
        }
        return Boolean.TRUE.equals(
            fAttributes.getAugmentations(index).getItem(
            Constants.ATTRIBUTE_DECLARED));
    }

    public boolean isSpecified(int index) {
        if (index < 0 || index >= fAttributes.getLength()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return fAttributes.isSpecified(index);
    }

    public boolean isSpecified(String qName) {
        int index = getIndex(qName);
        if (index == -1) {
            throw new IllegalArgumentException(qName);
        }
        return fAttributes.isSpecified(index);
    }

    public boolean isSpecified(String uri, String localName) {
        int index = getIndex(uri, localName);
        if (index == -1) {
            throw new IllegalArgumentException(localName);
        }
        return fAttributes.isSpecified(index);
    }

    /*
     * AttributeList methods
     */

    public String getName(int i) {
        return fAttributes.getQName(i);
    }

}
