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


import java.xml.share.classes.com.sun.org.w3c.dom.DOMConfiguration;
import java.xml.share.classes.com.sun.org.w3c.dom.Attr;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.Text;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.share.classes.com.sun.org.w3c.dom.Comment;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.w3c.dom.Nodejava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.w3c.dom.DocumentType;
import java.xml.share.classes.com.sun.org.w3c.dom.CDATASection;
import java.xml.share.classes.com.sun.org.w3c.dom.EntityReference;
import java.xml.share.classes.com.sun.org.w3c.dom.DocumentFragment;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMImplementation;
import java.xml.share.classes.com.sun.org.w3c.dom.ProcessingInstruction;
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
public class DefaultDocument extends NodeImpl
                             implements Document {

    private String fDocumentURI = null;

    // default constructor
    public DefaultDocument() {
    }

    //
    // org.w3c.dom.Document methods
    //

    public DocumentType getDoctype() {
        return null;
    }


    public DOMImplementation getImplementation() {
        return null;
    }


    public Element getDocumentElement() {
        return null;
    }


    public NodeList getElementsByTagName(String tagname) {
        return null;
    }


    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return null;
    }


    public Element getElementById(String elementId) {
        return null;
    }


    public Node importNode(Node importedNode, boolean deep) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Element createElement(String tagName) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public DocumentFragment createDocumentFragment() {
        return null;
    }


    public Text createTextNode(String data) {
        return null;
    }

    public Comment createComment(String data) {
        return null;
    }


    public CDATASection createCDATASection(String data) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Attr createAttribute(String name) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public EntityReference createEntityReference(String name) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }


    public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    // DOM Level 3 methods.

    public String getInputEncoding(){
        return null;
    }

    /**
    public void setInputEncoding(String actualEncoding){
       throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }
        */

    public String getXmlEncoding(){
        return null;
    }


    /**
     * An attribute specifying, as part of the XML declaration, the encoding
     * of this document. This is <code>null</code> when unspecified.
    public void setXmlEncoding(String encoding){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }
     */

    /**
     * An attribute specifying, as part of the XML declaration, whether this
     * document is standalone.
     * <br> This attribute represents the property [standalone] defined in .
     */
    public boolean getXmlStandalone(){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }
    /**
     * An attribute specifying, as part of the XML declaration, whether this
     * document is standalone.
     * <br> This attribute represents the property [standalone] defined in .
     */
    public void setXmlStandalone(boolean standalone){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    /**
     * An attribute specifying, as part of the XML declaration, the version
     * number of this document. This is <code>null</code> when unspecified.
     * <br> This attribute represents the property [version] defined in .
     * @exception DOMException
     *   NOT_SUPPORTED_ERR: Raised if the version is set to a value that is
     *   not supported by this <code>Document</code>.
     */
    public String getXmlVersion(){
        return null;
    }
    /**
     * An attribute specifying, as part of the XML declaration, the version
     * number of this document. This is <code>null</code> when unspecified.
     * <br> This attribute represents the property [version] defined in .
     * @exception DOMException
     *   NOT_SUPPORTED_ERR: Raised if the version is set to a value that is
     *   not supported by this <code>Document</code>.
     */
    public void setXmlVersion(String version) throws DOMException{
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    /**
     * An attribute specifying whether errors checking is enforced or not.
     * When set to <code>false</code>, the implementation is free to not
     * test every possible error case normally defined on DOM operations,
     * and not raise any <code>DOMException</code>. In case of error, the
     * behavior is undefined. This attribute is <code>true</code> by
     * defaults.
     */
    public boolean getStrictErrorChecking(){
        return false;
    }
    /**
     * An attribute specifying whether errors checking is enforced or not.
     * When set to <code>false</code>, the implementation is free to not
     * test every possible error case normally defined on DOM operations,
     * and not raise any <code>DOMException</code>. In case of error, the
     * behavior is undefined. This attribute is <code>true</code> by
     * defaults.
     */
    public void setStrictErrorChecking(boolean strictErrorChecking){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    /**
     * The location of the document or <code>null</code> if undefined.
     * <br>Beware that when the <code>Document</code> supports the feature
     * "HTML" , the href attribute of the HTML BASE element takes precedence
     * over this attribute.
     */
    public String getDocumentURI() {
        return fDocumentURI;
    }

    /**
     * The location of the document or <code>null</code> if undefined.
     * <br>Beware that when the <code>Document</code> supports the feature
     * "HTML" , the href attribute of the HTML BASE element takes precedence
     * over this attribute.
     */
    public void setDocumentURI(String documentURI) {
        fDocumentURI = documentURI;
    }

    /** DOM Level 3*/
    public Node adoptNode(Node source) throws DOMException{
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    /** DOM Level 3*/
    public void normalizeDocument(){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    /**
     *  The configuration used when <code>Document.normalizeDocument</code> is
     * invoked.
     */
    public DOMConfiguration getDomConfig(){
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }

    /** DOM Level 3*/
    public Node renameNode(Node n,String namespaceURI, String name) throws DOMException{
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
    }








}
