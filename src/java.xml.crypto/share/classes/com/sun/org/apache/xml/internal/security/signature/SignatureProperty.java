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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature;


import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Handles {@code &lt;ds:SignatureProperty&gt;} elements
 * Additional information item concerning the generation of the signature(s) can
 * be placed in this Element
 *
 */
public class SignatureProperty extends SignatureElementProxy {

    /**
     * Constructs{@link SignatureProperty} using specified {@code target} attribute
     *
     * @param doc the {@link Document} in which {@code XMLsignature} is placed
     * @param target the {@code target} attribute references the {@code Signature}
     * element to which the property applies SignatureProperty
     */
    public SignatureProperty(Document doc, String target) {
        this(doc, target, null);
    }

    /**
     * Constructs {@link SignatureProperty} using sepcified {@code target} attribute and
     * {@code id} attribute
     *
     * @param doc the {@link Document} in which {@code XMLsignature} is placed
     * @param target the {@code target} attribute references the {@code Signature}
     *  element to which the property applies
     * @param id the {@code id} will be specified by {@link Reference#getURI} in validation
     */
    public SignatureProperty(Document doc, String target, String id) {
        super(doc);

        this.setTarget(target);
        this.setId(id);
    }

    /**
     * Constructs a {@link SignatureProperty} from an {@link Element}
     * @param element {@code SignatureProperty} element
     * @param baseURI the URI of the resource where the XML instance was stored
     * @throws XMLSecurityException
     */
    public SignatureProperty(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     *   Sets the {@code id} attribute
     *
     *   @param id the {@code id} attribute
     */
    public void setId(String id) {
        if (id != null) {
            setLocalIdAttribute(Constants._ATT_ID, id);
        }
    }

    /**
     * Returns the {@code id} attribute
     *
     * @return the {@code id} attribute
     */
    public String getId() {
        return getLocalAttribute(Constants._ATT_ID);
    }

    /**
     * Sets the {@code target} attribute
     *
     * @param target the {@code target} attribute
     */
    public void setTarget(String target) {
        if (target != null) {
            setLocalAttribute(Constants._ATT_TARGET, target);
        }
    }

    /**
     * Returns the {@code target} attribute
     *
     * @return the {@code target} attribute
     */
    public String getTarget() {
        return getLocalAttribute(Constants._ATT_TARGET);
    }

    /**
     * Method appendChild
     *
     * @param node
     * @return the node in this element.
     */
    public Node appendChild(Node node) {
        appendSelf(node);
        return node;
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_SIGNATUREPROPERTY;
    }
}
