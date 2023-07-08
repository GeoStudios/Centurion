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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.opti;


import java.xml.share.classes.com.sun.org.w3c.dom.TypeInfo;
import java.xml.share.classes.com.sun.org.w3c.dom.Attr;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.share.classes.com.sun.org.w3c.dom.Nodejava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * @xerces.internal
 *
 *
 */
public class DefaultElement extends NodeImpl
                            implements Element {

    // default constructor
    public DefaultElement() {
    }


    public DefaultElement(String prefix, String localpart, String rawname, String uri, short nodeType) {
        super(prefix, localpart, rawname, uri, nodeType);
    }


    //
    // org.w3c.dom.Element methods
    //

    // getter methods
    public String getTagName() {
        return null;
    }


    public String getAttribute(String name) {
        return null;
    }


    public Attr getAttributeNode(String name) {
        return null;
    }


    public NodeList getElementsByTagName(String name) {
        return null;
    }


    public String getAttributeNS(String namespaceURI, String localName) {
        return null;
    }


    public Attr getAttributeNodeNS(String namespaceURI, String localName) {
        return null;
    }


    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return null;
    }


    public boolean hasAttribute(String name) {
        return false;
    }


    public boolean hasAttributeNS(String namespaceURI, String localName) {
        return false;
    }

    public TypeInfo getSchemaTypeInfo(){
      return null;
    }


    // setter methods
    public void setAttribute(String name, String value) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public void removeAttribute(String name) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Attr setAttributeNode(Attr newAttr) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    public void setIdAttributeNode(Attr at, boolean makeId) throws DOMException{
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }
    public void setIdAttribute(String name, boolean makeId) throws DOMException{
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    public void setIdAttributeNS(String namespaceURI, String localName,
                                    boolean makeId) throws DOMException{
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

}
