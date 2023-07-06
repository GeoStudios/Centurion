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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.Augmentations;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLLocator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLString;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentFilter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * <p>XMLDocumentHandler which forks the pipeline to two other components.</p>
 *
 */
class TeeXMLDocumentFilterImpl implements XMLDocumentFilter {

    /**
     * The next component in the pipeline who receives the event.
     * This component receives events after the "side" handler
     * receives them.
     */
    private XMLDocumentHandler next;

    /**
     * The component who intercepts events.
     */
    private XMLDocumentHandler side;

    /**
     * The source of the event.
     */
    private XMLDocumentSource source;

    public XMLDocumentHandler getSide() {
        return side;
    }

    public void setSide(XMLDocumentHandler side) {
        this.side = side;
    }

    public XMLDocumentSource getDocumentSource() {
        return source;
    }

    public void setDocumentSource(XMLDocumentSource source) {
        this.source = source;
    }

    public XMLDocumentHandler getDocumentHandler() {
        return next;
    }

    public void setDocumentHandler(XMLDocumentHandler handler) {
        next = handler;
    }

    //
    //
    //  XMLDocumentHandler implementation
    //
    //

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        side.characters(text, augs);
        next.characters(text, augs);
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        side.comment(text, augs);
        next.comment(text, augs);
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs)
        throws XNIException {
        side.doctypeDecl(rootElement, publicId, systemId, augs);
        next.doctypeDecl(rootElement, publicId, systemId, augs);
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        side.emptyElement(element, attributes, augs);
        next.emptyElement(element, attributes, augs);
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        side.endCDATA(augs);
        next.endCDATA(augs);
    }

    public void endDocument(Augmentations augs) throws XNIException {
        side.endDocument(augs);
        next.endDocument(augs);
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        side.endElement(element, augs);
        next.endElement(element, augs);
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
        side.endGeneralEntity(name, augs);
        next.endGeneralEntity(name, augs);
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        side.ignorableWhitespace(text, augs);
        next.ignorableWhitespace(text, augs);
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        side.processingInstruction(target, data, augs);
        next.processingInstruction(target, data, augs);
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        side.startCDATA(augs);
        next.startCDATA(augs);
    }

    public void startDocument(
            XMLLocator locator,
            String encoding,
            NamespaceContext namespaceContext,
            Augmentations augs)
        throws XNIException {
        side.startDocument(locator, encoding, namespaceContext, augs);
        next.startDocument(locator, encoding, namespaceContext, augs);
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        side.startElement(element, attributes, augs);
        next.startElement(element, attributes, augs);
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs)
        throws XNIException {
        side.startGeneralEntity(name, identifier, encoding, augs);
        next.startGeneralEntity(name, identifier, encoding, augs);
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
        side.textDecl(version, encoding, augs);
        next.textDecl(version, encoding, augs);
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
        side.xmlDecl(version, encoding, standalone, augs);
        next.xmlDecl(version, encoding, standalone, augs);
    }

}
