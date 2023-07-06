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


import java.xml.share.classes.com.sun.org.w3c.dom.UserDataHandler;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.w3c.dom.Nodejava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;
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
public class DefaultNode implements Node {

    // default constructor
    public DefaultNode() {
    }

    //
    // org.w3c.dom.Node methods
    //

    // getter methods
    public String getNodeName() {
        return null;
    }


    public String getNodeValue() throws DOMException {
        return null;
    }


    public short getNodeType() {
        return -1;
    }


    public Node getParentNode() {
        return null;
    }


    public NodeList getChildNodes() {
        return null;
    }


    public Node getFirstChild() {
        return null;
    }


    public Node getLastChild() {
        return null;
    }


    public Node getPreviousSibling() {
        return null;
    }


    public Node getNextSibling() {
        return null;
    }


    public NamedNodeMap getAttributes() {
        return null;
    }


    public Document getOwnerDocument() {
        return null;
    }


    public boolean hasChildNodes() {
        return false;
    }


    public Node cloneNode(boolean deep) {
        return null;
    }


    public void normalize() {
    }


    public boolean isSupported(String feature, String version) {
        return false;
    }


    public String getNamespaceURI() {
        return null;
    }


    public String getPrefix() {
        return null;
    }


    public String getLocalName() {
        return null;
    }
    /** DOM Level 3*/
    public String getBaseURI(){
        return null;
    }



    public boolean hasAttributes() {
        return false;
    }

    // setter methods
    public void setNodeValue(String nodeValue) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Node removeChild(Node oldChild) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Node appendChild(Node newChild) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public void setPrefix(String prefix) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    public short compareDocumentPosition(Node other){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    public String getTextContent() throws DOMException{
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }
    public void setTextContent(String textContent)throws DOMException{
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }
    public boolean isSameNode(Node other){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");

    }
    public String lookupPrefix(String namespaceURI){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
                                        }
    public boolean isDefaultNamespace(String namespaceURI){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    public String lookupNamespaceURI(String prefix){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    public boolean isEqualNode(Node arg){
       throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");

    }

    public Object getFeature(String feature, String version){
        return null;
    }
    public Object setUserData(String key,  Object data, UserDataHandler handler){
       throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }
    public Object getUserData(String key){
        return null;
    }


}
