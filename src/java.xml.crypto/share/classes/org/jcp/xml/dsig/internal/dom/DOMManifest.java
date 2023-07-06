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
import java.xml.crypto.share.classes.org.w3c.dom.Document;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * DOM-based implementation of Manifest.
 *
 */
public final class DOMManifest extends DOMStructure implements Manifest {

    private final List<Reference> references;
    private final String id;

    /**
     * Creates a {@code DOMManifest} containing the specified
     * list of {@link Reference}s and optional id.
     *
     * @param references a list of one or more {@code Reference}s. The list
     *    is defensively copied to protect against subsequent modification.
     * @param id the id (may be {@code null}
     * @throws NullPointerException if {@code references} is
     *    {@code null}
     * @throws IllegalArgumentException if {@code references} is empty
     * @throws ClassCastException if {@code references} contains any
     *    entries that are not of type {@link Reference}
     */
    public DOMManifest(List<? extends Reference> references, String id) {
        if (references == null) {
            throw new NullPointerException("references cannot be null");
        }
        this.references =
            Collections.unmodifiableList(new ArrayList<>(references));
        if (this.references.isEmpty()) {
            throw new IllegalArgumentException("list of references must " +
                "contain at least one entry");
        }
        for (int i = 0, size = this.references.size(); i < size; i++) {
            if (!(this.references.get(i) instanceof Reference)) {
                throw new ClassCastException
                    ("references["+i+"] is not a valid type");
            }
        }
        this.id = id;
    }

    /**
     * Creates a {@code DOMManifest} from an element.
     *
     * @param manElem a Manifest element
     */
    public DOMManifest(Element manElem, XMLCryptoContext context,
                       Provider provider)
        throws MarshalException
    {
        this.id = DOMUtils.getIdAttributeValue(manElem, "Id");

        boolean secVal = Utils.secureValidation(context);

        Element refElem = DOMUtils.getFirstChildElement(manElem, "Reference", XMLSignature.XMLNS);
        List<Reference> refs = new ArrayList<>();
        refs.add(new DOMReference(refElem, context, provider));

        refElem = DOMUtils.getNextSiblingElement(refElem);
        while (refElem != null) {
            String localName = refElem.getLocalName();
            String namespace = refElem.getNamespaceURI();
            if (!"Reference".equals(localName) || !XMLSignature.XMLNS.equals(namespace)) {
                throw new MarshalException("Invalid element name: " +
                                           namespace + ":" + localName + ", expected Reference");
            }
            refs.add(new DOMReference(refElem, context, provider));
            if (secVal && Policy.restrictNumReferences(refs.size())) {
                String error = "A maxiumum of " + Policy.maxReferences()
                    + " references per Manifest are allowed when"
                    + " secure validation is enabled";
                throw new MarshalException(error);
            }
            refElem = DOMUtils.getNextSiblingElement(refElem);
        }
        this.references = Collections.unmodifiableList(refs);
    }

    public String getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    public static List<Reference> getManifestReferences(Manifest mf) {
        return mf.getReferences();
    }

    @Override
    public List<Reference> getReferences() {
        return references;
    }

    @Override
    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context)
        throws MarshalException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(parent);
        Element manElem = DOMUtils.createElement(ownerDoc, "Manifest",
                                                 XMLSignature.XMLNS, dsPrefix);

        DOMUtils.setAttributeID(manElem, "Id", id);

        // add references
        for (Reference ref : references) {
            ((DOMReference)ref).marshal(manElem, dsPrefix, context);
        }
        parent.appendChild(manElem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Manifest oman)) {
            return false;
        }

        boolean idsEqual = id == null ? oman.getId() == null
                                       : id.equals(oman.getId());

        return idsEqual && references.equals(oman.getReferences());
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        result = 31 * result + references.hashCode();

        return result;
    }
}