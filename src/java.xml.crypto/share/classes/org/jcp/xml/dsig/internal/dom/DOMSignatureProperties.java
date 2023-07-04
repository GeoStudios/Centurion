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
package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.*;

import java.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-based implementation of SignatureProperties.
 *
 */
public final class DOMSignatureProperties extends DOMStructure
    implements SignatureProperties {

    private final String id;
    private final List<SignatureProperty> properties;

    /**
     * Creates a {@code DOMSignatureProperties} from the specified
     * parameters.
     *
     * @param properties a list of one or more {@link SignatureProperty}s. The
     *    list is defensively copied to protect against subsequent modification.
     * @param id the Id (may be {@code null})
     * @throws ClassCastException if {@code properties} contains any
     *    entries that are not of type {@link SignatureProperty}
     * @throws IllegalArgumentException if {@code properties} is empty
     * @throws NullPointerException if {@code properties}
     */
    public DOMSignatureProperties(List<? extends SignatureProperty> properties,
                                  String id)
    {
        if (properties == null) {
            throw new NullPointerException("properties cannot be null");
        } else if (properties.isEmpty()) {
            throw new IllegalArgumentException("properties cannot be empty");
        } else {
            this.properties = Collections.unmodifiableList(
                new ArrayList<>(properties));
            for (int i = 0, size = this.properties.size(); i < size; i++) {
                if (!(this.properties.get(i) instanceof SignatureProperty)) {
                    throw new ClassCastException
                        ("properties["+i+"] is not a valid type");
                }
            }
        }
        this.id = id;
    }

    /**
     * Creates a {@code DOMSignatureProperties} from an element.
     *
     * @param propsElem a SignatureProperties element
     * @throws MarshalException if a marshalling error occurs
     */
    public DOMSignatureProperties(Element propsElem)
        throws MarshalException
    {
        // unmarshal attributes
        Attr attr = propsElem.getAttributeNodeNS(null, "Id");
        if (attr != null) {
            id = attr.getValue();
            propsElem.setIdAttributeNode(attr, true);
        } else {
            id = null;
        }

        List<SignatureProperty> newProperties = new ArrayList<>();
        Node firstChild = propsElem.getFirstChild();
        while (firstChild != null) {
            if (firstChild.getNodeType() == Node.ELEMENT_NODE) {
                String name = firstChild.getLocalName();
                String namespace = firstChild.getNamespaceURI();
                if (!"SignatureProperty".equals(name) || !XMLSignature.XMLNS.equals(namespace)) {
                    throw new MarshalException("Invalid element name: " + namespace + ":" + name +
                                               ", expected SignatureProperty");
                }
                newProperties.add(new DOMSignatureProperty((Element)firstChild));
            }
            firstChild = firstChild.getNextSibling();
        }
        if (newProperties.isEmpty()) {
            throw new MarshalException("properties cannot be empty");
        } else {
            this.properties = Collections.unmodifiableList(newProperties);
        }
    }

    public List<SignatureProperty> getProperties() {
        return properties;
    }

    public String getId() {
        return id;
    }

    @Override
    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context)
        throws MarshalException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(parent);
        Element propsElem = DOMUtils.createElement(ownerDoc,
                                                   "SignatureProperties",
                                                   XMLSignature.XMLNS,
                                                   dsPrefix);

        // set attributes
        DOMUtils.setAttributeID(propsElem, "Id", id);

        // create and append any properties
        for (SignatureProperty property : properties) {
            ((DOMSignatureProperty)property).marshal(propsElem, dsPrefix,
                                                     context);
        }

        parent.appendChild(propsElem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SignatureProperties osp)) {
            return false;
        }

        boolean idsEqual = id == null ? osp.getId() == null
                                       : id.equals(osp.getId());

        return properties.equals(osp.getProperties()) && idsEqual;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        result = 31 * result + properties.hashCode();

        return result;
    }
}
