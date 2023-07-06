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

import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.AttrImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.DocumentTypeImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.ElementImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.EntityImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.dom.NotationImpl;
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
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import javax.xml.transform.dom.DOMResult;
import java.xml.share.classes.com.sun.org.w3c.dom.CDATASection;
import java.xml.share.classes.com.sun.org.w3c.dom.Comment;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.w3c.dom.DocumentType;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.share.classes.com.sun.org.w3c.dom.Entity;
import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.Notation;
import java.xml.share.classes.com.sun.org.w3c.dom.ProcessingInstruction;
import java.xml.share.classes.com.sun.org.w3c.dom.Text;

/**
 * <p>DOM result builder.</p>
 *
 * @LastModified: Oct 2017
 */
final class DOMResultBuilder implements DOMDocumentHandler {

    /** Table for quick check of child insertion. */
    private final static int[] kidOK;

    static {
        kidOK = new int[13];
        kidOK[Node.DOCUMENT_NODE] =
            1 << Node.ELEMENT_NODE | 1 << Node.PROCESSING_INSTRUCTION_NODE |
            1 << Node.COMMENT_NODE | 1 << Node.DOCUMENT_TYPE_NODE;
        kidOK[Node.DOCUMENT_FRAGMENT_NODE] =
        kidOK[Node.ENTITY_NODE] =
        kidOK[Node.ENTITY_REFERENCE_NODE] =
        kidOK[Node.ELEMENT_NODE] =
            1 << Node.ELEMENT_NODE | 1 << Node.PROCESSING_INSTRUCTION_NODE |
            1 << Node.COMMENT_NODE | 1 << Node.TEXT_NODE |
            1 << Node.CDATA_SECTION_NODE | 1 << Node.ENTITY_REFERENCE_NODE ;
        kidOK[Node.ATTRIBUTE_NODE] = 1 << Node.TEXT_NODE | 1 << Node.ENTITY_REFERENCE_NODE;
        kidOK[Node.DOCUMENT_TYPE_NODE] = 0;
        kidOK[Node.PROCESSING_INSTRUCTION_NODE] = 0;
        kidOK[Node.COMMENT_NODE] = 0;
        kidOK[Node.TEXT_NODE] = 0;
        kidOK[Node.CDATA_SECTION_NODE] = 0;
        kidOK[Node.NOTATION_NODE] = 0;
    } // static

    //
    // Data
    //

    private Document fDocument;
    private CoreDocumentImpl fDocumentImpl;
    private boolean fStorePSVI;

    private Node fTarget;
    private Node fNextSibling;

    private Node fCurrentNode;
    private Node fFragmentRoot;

    private final List<Node> fTargetChildren = new ArrayList<>();

    private boolean fIgnoreChars;

    private final QName fAttributeQName = new QName();

    public DOMResultBuilder() {}

    /*
     * DOMDocumentHandler methods
     */

    public void setDOMResult(DOMResult result) {
        fCurrentNode = null;
        fFragmentRoot = null;
        fIgnoreChars = false;
        fTargetChildren.clear();
        if (result != null) {
            fTarget = result.getNode();
            fNextSibling = result.getNextSibling();
            fDocument = (fTarget.getNodeType() == Node.DOCUMENT_NODE) ? (Document) fTarget : fTarget.getOwnerDocument();
            fDocumentImpl = (fDocument instanceof CoreDocumentImpl) ? (CoreDocumentImpl) fDocument : null;
            fStorePSVI = (fDocument instanceof PSVIDocumentImpl);
            return;
        }
        fTarget = null;
        fNextSibling = null;
        fDocument = null;
        fDocumentImpl = null;
        fStorePSVI = false;
    }

    public void doctypeDecl(DocumentType node) throws XNIException {
        /** Create new DocumentType node for the target. */
        if (fDocumentImpl != null) {
            DocumentType docType = fDocumentImpl.createDocumentType(node.getName(), node.getPublicId(), node.getSystemId());
            final String internalSubset = node.getInternalSubset();
            /** Copy internal subset. */
            if (internalSubset != null) {
                ((DocumentTypeImpl) docType).setInternalSubset(internalSubset);
            }
            /** Copy entities. */
            NamedNodeMap oldMap = node.getEntities();
            NamedNodeMap newMap = docType.getEntities();
            int length = oldMap.getLength();
            for (int i = 0; i < length; ++i) {
                Entity oldEntity = (Entity) oldMap.item(i);
                EntityImpl newEntity = (EntityImpl) fDocumentImpl.createEntity(oldEntity.getNodeName());
                newEntity.setPublicId(oldEntity.getPublicId());
                newEntity.setSystemId(oldEntity.getSystemId());
                newEntity.setNotationName(oldEntity.getNotationName());
                newMap.setNamedItem(newEntity);
            }
            /** Copy notations. */
            oldMap = node.getNotations();
            newMap = docType.getNotations();
            length = oldMap.getLength();
            for (int i = 0; i < length; ++i) {
                Notation oldNotation = (Notation) oldMap.item(i);
                NotationImpl newNotation = (NotationImpl) fDocumentImpl.createNotation(oldNotation.getNodeName());
                newNotation.setPublicId(oldNotation.getPublicId());
                newNotation.setSystemId(oldNotation.getSystemId());
                newMap.setNamedItem(newNotation);
            }
            append(docType);
        }
    }

    public void characters(Text node) throws XNIException {
        /** Create new Text node for the target. */
        append(fDocument.createTextNode(node.getNodeValue()));
    }

    public void cdata(CDATASection node) throws XNIException {
        /** Create new CDATASection node for the target. */
        append(fDocument.createCDATASection(node.getNodeValue()));
    }

    public void comment(Comment node) throws XNIException {
        /** Create new Comment node for the target. */
        append(fDocument.createComment(node.getNodeValue()));
    }

    public void processingInstruction(ProcessingInstruction node)
            throws XNIException {
        /** Create new ProcessingInstruction node for the target. */
        append(fDocument.createProcessingInstruction(node.getTarget(), node.getData()));
    }

    public void setIgnoringCharacters(boolean ignore) {
        fIgnoreChars = ignore;
    }

    /*
     * XMLDocumentHandler methods
     */

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
        Element elem;
        int attrCount = attributes.getLength();
        if (fDocumentImpl == null) {
            elem = fDocument.createElementNS(element.uri, element.rawname);
            for (int i = 0; i < attrCount; ++i) {
                attributes.getName(i, fAttributeQName);
                elem.setAttributeNS(fAttributeQName.uri, fAttributeQName.rawname, attributes.getValue(i));
            }
        }
        // If it's a Xerces DOM store type information for attributes, set idness, etc..
        else {
            elem = fDocumentImpl.createElementNS(element.uri, element.rawname, element.localpart);
            for (int i = 0; i < attrCount; ++i) {
                attributes.getName(i, fAttributeQName);
                AttrImpl attr = (AttrImpl) fDocumentImpl.createAttributeNS(fAttributeQName.uri,
                        fAttributeQName.rawname, fAttributeQName.localpart);
                attr.setValue(attributes.getValue(i));

                // write type information to this attribute
                AttributePSVI attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem (Constants.ATTRIBUTE_PSVI);
                if (attrPSVI != null) {
                    if (fStorePSVI) {
                        ((PSVIAttrNSImpl) attr).setPSVI(attrPSVI);
                    }
                    Object type = attrPSVI.getMemberTypeDefinition();
                    if (type == null) {
                        type = attrPSVI.getTypeDefinition();
                        if (type != null) {
                            attr.setType (type);
                            if (((XSSimpleType) type).isIDType()) {
                                ((ElementImpl) elem).setIdAttributeNode (attr, true);
                            }
                        }
                    }
                    else {
                        attr.setType (type);
                        if (((XSSimpleType) type).isIDType()) {
                            ((ElementImpl) elem).setIdAttributeNode (attr, true);
                        }
                    }
                }
                attr.setSpecified(attributes.isSpecified(i));
                elem.setAttributeNode(attr);
            }
        }
        append(elem);
        fCurrentNode = elem;
        if (fFragmentRoot == null) {
            fFragmentRoot = elem;
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
            append(fDocument.createTextNode(text.toString()));
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs)
            throws XNIException {
        characters(text, augs);
    }

    public void endElement(QName element, Augmentations augs)
            throws XNIException {
        // write type information to this element
        if (augs != null && fDocumentImpl != null) {
            ElementPSVI elementPSVI = (ElementPSVI)augs.getItem(Constants.ELEMENT_PSVI);
            if (elementPSVI != null) {
                if (fStorePSVI) {
                    ((PSVIElementNSImpl)fCurrentNode).setPSVI(elementPSVI);
                }
                XSTypeDefinition type = elementPSVI.getMemberTypeDefinition();
                if (type == null) {
                    type = elementPSVI.getTypeDefinition();
                }
                ((ElementNSImpl)fCurrentNode).setType(type);
            }
        }

        // adjust current node reference
        if (fCurrentNode == fFragmentRoot) {
            fCurrentNode = null;
            fFragmentRoot = null;
            return;
        }
        fCurrentNode = fCurrentNode.getParentNode();
    }

    public void startCDATA(Augmentations augs) throws XNIException {}

    public void endCDATA(Augmentations augs) throws XNIException {}

    public void endDocument(Augmentations augs) throws XNIException {
        if (fNextSibling == null) {
            for (Node node : fTargetChildren) {
                fTarget.appendChild(node);
            }
        }
        else {
            for (Node node : fTargetChildren) {
                fTarget.insertBefore(node, fNextSibling);
            }
        }
    }

    public void setDocumentSource(XMLDocumentSource source) {}

    public XMLDocumentSource getDocumentSource() {
        return null;
    }

    /*
     * Other methods
     */

    private void append(Node node) throws XNIException {
        if (fCurrentNode != null) {
            fCurrentNode.appendChild(node);
        }
        else {
            /** Check if this node can be attached to the target. */
            if ((kidOK[fTarget.getNodeType()] & (1 << node.getNodeType())) == 0) {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null);
                throw new XNIException(msg);
            }
            fTargetChildren.add(node);
        }
    }

} // DOMResultBuilder