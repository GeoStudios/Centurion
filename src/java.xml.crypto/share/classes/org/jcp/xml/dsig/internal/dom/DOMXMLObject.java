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

package java.xml.crypto.share.classes.org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.*;
import java.security.Provider;
import java.util.*;
import java.xml.crypto.share.classes.org.w3c.dom.Attr;
import java.xml.crypto.share.classes.org.w3c.dom.Document;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.NamedNodeMap;
import java.xml.crypto.share.classes.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * DOM-based implementation of XMLObject.
 *
 */
public final class DOMXMLObject extends DOMStructure implements XMLObject {

    private final String id;
    private final String mimeType;
    private final String encoding;
    private final List<XMLStructure> content;
    private Element objectElem;

    /**
     * Creates an {@code XMLObject} from the specified parameters.
     *
     * @param content a list of {@link XMLStructure}s. The list
     *    is defensively copied to protect against subsequent modification.
     *    May be {@code null} or empty.
     * @param id the Id (may be {@code null})
     * @param mimeType the mime type (may be {@code null})
     * @param encoding the encoding (may be {@code null})
     * @throws ClassCastException if {@code content} contains any
     *    entries that are not of type {@link XMLStructure}
     */
    public DOMXMLObject(List<? extends XMLStructure> content, String id,
                        String mimeType, String encoding)
    {
        if (content == null || content.isEmpty()) {
            this.content = Collections.emptyList();
        } else {
            this.content = Collections.unmodifiableList(
                new ArrayList<>(content));
            for (int i = 0, size = this.content.size(); i < size; i++) {
                if (!(this.content.get(i) instanceof XMLStructure)) {
                    throw new ClassCastException
                        ("content["+i+"] is not a valid type");
                }
            }
        }
        this.id = id;
        this.mimeType = mimeType;
        this.encoding = encoding;
    }

    /**
     * Creates an {@code XMLObject} from an element.
     *
     * @param objElem an Object element
     * @throws MarshalException if there is an error when unmarshalling
     */
    public DOMXMLObject(Element objElem, XMLCryptoContext context,
                        Provider provider)
    throws MarshalException
    {
        // unmarshal attributes
        this.encoding = DOMUtils.getAttributeValue(objElem, "Encoding");

        Attr attr = objElem.getAttributeNodeNS(null, "Id");
        if (attr != null) {
            this.id = attr.getValue();
            objElem.setIdAttributeNode(attr, true);
        } else {
            this.id = null;
        }
        this.mimeType = DOMUtils.getAttributeValue(objElem, "MimeType");

        List<XMLStructure> newContent = new ArrayList<>();
        Node firstChild = objElem.getFirstChild();
        while (firstChild != null) {
            if (firstChild.getNodeType() == Node.ELEMENT_NODE) {
                Element childElem = (Element)firstChild;
                String tag = childElem.getLocalName();
                String namespace = childElem.getNamespaceURI();
                if ("Manifest".equals(tag) && XMLSignature.XMLNS.equals(namespace)) {
                    newContent.add(new DOMManifest(childElem, context, provider));
                } else if ("SignatureProperties".equals(tag) && XMLSignature.XMLNS.equals(namespace)) {
                    newContent.add(new DOMSignatureProperties(childElem));
                } else if ("X509Data".equals(tag) && XMLSignature.XMLNS.equals(namespace)) {
                    newContent.add(new DOMX509Data(childElem));
                } else {
                    //@@@FIXME: check for other dsig structures
                    newContent.add(new javax.xml.crypto.dom.DOMStructure(firstChild));
                }
            } else {
                newContent.add(new javax.xml.crypto.dom.DOMStructure(firstChild));
            }
            firstChild = firstChild.getNextSibling();
        }

        // Here we capture namespace declarations, so that when they're marshalled back
        // out, we can make copies of them. Note that attributes are NOT captured.
        NamedNodeMap nnm = objElem.getAttributes();
        for (int idx = 0 ; idx < nnm.getLength() ; idx++) {
            Node nsDecl = nnm.item(idx);
            if (DOMUtils.isNamespace(nsDecl)) {
                newContent.add(new javax.xml.crypto.dom.DOMStructure(nsDecl));
            }
        }

        if (newContent.isEmpty()) {
            this.content = Collections.emptyList();
        } else {
            this.content = Collections.unmodifiableList(newContent);
        }
        this.objectElem = objElem;
    }

    public List<XMLStructure> getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getEncoding() {
        return encoding;
    }

    @Override
    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context)
        throws MarshalException {
        Document ownerDoc = DOMUtils.getOwnerDocument(parent);

        Element objElem = objectElem;
        if (objElem == null) {
            objElem = DOMUtils.createElement(ownerDoc, "Object",
                                             XMLSignature.XMLNS, dsPrefix);

            // set attributes
            DOMUtils.setAttributeID(objElem, "Id", id);
            DOMUtils.setAttribute(objElem, "MimeType", mimeType);
            DOMUtils.setAttribute(objElem, "Encoding", encoding);

            // create and append any elements and mixed content, if necessary
            for (XMLStructure object : content) {
                if (object instanceof DOMStructure) {
                    ((DOMStructure)object).marshal(objElem, dsPrefix, context);
                } else {
                    javax.xml.crypto.dom.DOMStructure domObject =
                        (javax.xml.crypto.dom.DOMStructure)object;
                    DOMUtils.appendChild(objElem, domObject.getNode());
                }
            }
        }

        parent.appendChild(objElem);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof XMLObject oxo)) {
            return false;
        }

        boolean idsEqual = id == null ? oxo.getId() == null
                                       : id.equals(oxo.getId());
        boolean encodingsEqual =
            encoding == null ? oxo.getEncoding() == null
                              : encoding.equals(oxo.getEncoding());
        boolean mimeTypesEqual =
            mimeType == null ? oxo.getMimeType() == null
                              : mimeType.equals(oxo.getMimeType());

        return idsEqual && encodingsEqual && mimeTypesEqual &&
                equalsContent(content, oxo.getContent());
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        if (encoding != null) {
            result = 31 * result + encoding.hashCode();
        }
        if (mimeType != null) {
            result = 31 * result + mimeType.hashCode();
        }
        result = 31 * result + content.hashCode();

        return result;
    }

}