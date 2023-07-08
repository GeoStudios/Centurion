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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation;

import javax.xml.transform.dom.DOMResult;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.AttrImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.ElementImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.PSVIDocumentImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.PSVIElementNSImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.Augmentations;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLLocator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLString;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import java.xml.share.classes.com.sun.org.w3c.dom.CDATASection;
import java.xml.share.classes.com.sun.org.w3c.dom.Comment;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.w3c.dom.DocumentType;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.ProcessingInstruction;
import java.xml.share.classes.com.sun.org.w3c.dom.Text;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>DOM result augmentor.</p>
 *
 */
final class DOMResultAugmentor implements DOMDocumentHandler {

    //
    // Data
    //

    private final DOMValidatorHelper fDOMValidatorHelper;

    private Document fDocument;
    private CoreDocumentImpl fDocumentImpl;
    private boolean fStorePSVI;

    private boolean fIgnoreChars;

    private final QName fAttributeQName = new QName();

    public DOMResultAugmentor(DOMValidatorHelper helper) {
        fDOMValidatorHelper = helper;
    }

    public void setDOMResult(DOMResult result) {
        fIgnoreChars = false;
        if (result != null) {
            final Node target = result.getNode();
            fDocument = (target.getNodeType() == Node.DOCUMENT_NODE) ? (Document) target : target.getOwnerDocument();
            fDocumentImpl = (fDocument instanceof CoreDocumentImpl) ? (CoreDocumentImpl) fDocument : null;
            fStorePSVI = (fDocument instanceof PSVIDocumentImpl);
            return;
        }
        fDocument = null;
        fDocumentImpl = null;
        fStorePSVI = false;
    }

    public void doctypeDecl(DocumentType node) throws XNIException {}

    public void characters(Text node) throws XNIException {}

    public void cdata(CDATASection node) throws XNIException {}

    public void comment(Comment node) throws XNIException {}

    public void processingInstruction(ProcessingInstruction node)
            throws XNIException {}

    public void setIgnoringCharacters(boolean ignore) {
        fIgnoreChars = ignore;
    }

    public void startDocument(XMLLocator locator, String encoding,
            NamespaceContext namespaceContext, Augmentations augs)
            throws XNIException {}

    public void xmlDecl(String version, String encoding, String standalone,
            Augmentations augs) throws XNIException {}

    public void doctypeDecl(String rootElement, String publicId,
            String systemId, Augmentations augs) throws XNIException {}

    public void comment(XMLString text, Augmentations augs) throws XNIException {}

    public void processingInstruction(String target, XMLString data,
            Augmentations augs) throws XNIException {}

    public void startElement(QName element, XMLAttributes attributes,
            Augmentations augs) throws XNIException {
        final Element currentElement = (Element) fDOMValidatorHelper.getCurrentElement();
        final NamedNodeMap attrMap = currentElement.getAttributes();

        final int oldLength = attrMap.getLength();
        // If it's a Xerces DOM store type information for attributes, set idness, etc..
        if (fDocumentImpl != null) {
            AttrImpl attr;
            for (int i = 0; i < oldLength; ++i) {
                attr = (AttrImpl) attrMap.item(i);

                // write type information to this attribute
                AttributePSVI attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem (Constants.ATTRIBUTE_PSVI);
                if (attrPSVI != null) {
                    if (processAttributePSVI(attr, attrPSVI)) {
                        ((ElementImpl) currentElement).setIdAttributeNode (attr, true);
                    }
                }
            }
        }

        final int newLength = attributes.getLength();
        // Add default/fixed attributes
        if (newLength > oldLength) {
            if (fDocumentImpl == null) {
                for (int i = oldLength; i < newLength; ++i) {
                    attributes.getName(i, fAttributeQName);
                    currentElement.setAttributeNS(fAttributeQName.uri, fAttributeQName.rawname, attributes.getValue(i));
                }
            }
            // If it's a Xerces DOM store type information for attributes, set idness, etc..
            else {
                for (int i = oldLength; i < newLength; ++i) {
                    attributes.getName(i, fAttributeQName);
                    AttrImpl attr = (AttrImpl) fDocumentImpl.createAttributeNS(fAttributeQName.uri,
                            fAttributeQName.rawname, fAttributeQName.localpart);
                    attr.setValue(attributes.getValue(i));

                    // write type information to this attribute
                    AttributePSVI attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem (Constants.ATTRIBUTE_PSVI);
                    if (attrPSVI != null) {
                        if (processAttributePSVI(attr, attrPSVI)) {
                            ((ElementImpl) currentElement).setIdAttributeNode (attr, true);
                        }
                    }
                    attr.setSpecified(false);
                    currentElement.setAttributeNode(attr);
                }
            }
        }
    }

    public void emptyElement(QName element, XMLAttributes attributes,
            Augmentations augs) throws XNIException {
        startElement(element, attributes, augs);
        endElement(element, augs);
    }

    public void startGeneralEntity(String name,
            XMLResourceIdentifier identifier, String encoding,
            Augmentations augs) throws XNIException {}

    public void textDecl(String version, String encoding, Augmentations augs)
            throws XNIException {}

    public void endGeneralEntity(String name, Augmentations augs)
            throws XNIException {}

    public void characters(XMLString text, Augmentations augs)
            throws XNIException {
        if (!fIgnoreChars) {
            final Element currentElement = (Element) fDOMValidatorHelper.getCurrentElement();
            currentElement.appendChild(fDocument.createTextNode(text.toString()));
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs)
            throws XNIException {
        characters(text, augs);
    }

    public void endElement(QName element, Augmentations augs)
            throws XNIException {
        final Node currentElement = fDOMValidatorHelper.getCurrentElement();
        // Write type information to this element
        if (augs != null && fDocumentImpl != null) {
            ElementPSVI elementPSVI = (ElementPSVI)augs.getItem(Constants.ELEMENT_PSVI);
            if (elementPSVI != null) {
                if (fStorePSVI) {
                    ((PSVIElementNSImpl) currentElement).setPSVI(elementPSVI);
                }
                XSTypeDefinition type = elementPSVI.getMemberTypeDefinition();
                if (type == null) {
                    type = elementPSVI.getTypeDefinition();
                }
                ((ElementNSImpl) currentElement).setType(type);
            }
        }
    }

    public void startCDATA(Augmentations augs) throws XNIException {}

    public void endCDATA(Augmentations augs) throws XNIException {}

    public void endDocument(Augmentations augs) throws XNIException {}

    public void setDocumentSource(XMLDocumentSource source) {}

    public XMLDocumentSource getDocumentSource() {
        return null;
    }

    /** Returns whether the given attribute is an ID type. **/
    private boolean processAttributePSVI(AttrImpl attr, AttributePSVI attrPSVI) {
        if (fStorePSVI) {
            ((PSVIAttrNSImpl) attr).setPSVI (attrPSVI);
        }
        Object type = attrPSVI.getMemberTypeDefinition ();
        if (type == null) {
            type = attrPSVI.getTypeDefinition ();
            if (type != null) {
                attr.setType(type);
                return ((XSSimpleType) type).isIDType();
            }
        }
        else {
            attr.setType(type);
            return ((XSSimpleType) type).isIDType();
        }
        return false;
    }

} // DOMResultAugmentor
