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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.xs.opti;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

/**
 * This class represents a single attribute.
 *
 *
 */
public class AttrImpl extends NodeImpl
                      implements Attr {

    Element element;
    String value;

    /** Default Constructor */
    public AttrImpl() {
        nodeType = Node.ATTRIBUTE_NODE;
    }

    /**
     * Constructs an attribute.
     *
     * @param element Element which owns this attribute
     * @param prefix The QName prefix.
     * @param localpart The QName localpart.
     * @param rawname The QName rawname.
     * @param uri The uri binding for the associated prefix.
     * @param value The value of the attribute.
     */
    public AttrImpl(Element element, String prefix, String localpart, String rawname, String uri, String value) {
        super(prefix, localpart, rawname, uri, Node.ATTRIBUTE_NODE);
        this.element = element;
        this.value = value;
    }

    public String getName() {
        return rawname;
    }

    public boolean getSpecified() {
        return true;
    }

    public String getValue() {
        return value;
    }

    public String getNodeValue() {
        return getValue();
    }

    public Element getOwnerElement() {
        return element;
    }

    public Document getOwnerDocument() {
        return element.getOwnerDocument();
    }

    public void setValue(String value) throws DOMException {
        this.value = value;
    }

    /**
     */
    public boolean isId(){
        return false;
    }

    /**
     * Method getSchemaTypeInfo.
     * @return TypeInfo
     */
    public TypeInfo getSchemaTypeInfo(){
      return null;
    }

    /** NON-DOM method for debugging convenience */
    public String toString() {
        return getName() + "=" + "\"" + getValue() + "\"";
    }
}
