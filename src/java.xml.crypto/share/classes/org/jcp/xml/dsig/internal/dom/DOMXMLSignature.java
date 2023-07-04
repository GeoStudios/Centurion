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
import javax.xml.crypto.dom.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.Provider;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.org.apache.xml.internal.security.utils.XMLUtils;

/**
 * DOM-based implementation of XMLSignature.
 *
 */
public final class DOMXMLSignature extends DOMStructure
    implements XMLSignature {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(DOMXMLSignature.class);
    private final String id;
    private final SignatureValue sv;
    private KeyInfo ki;
    private final List<XMLObject> objects;
    private final SignedInfo si;
    private Document ownerDoc = null;
    private Element localSigElem = null;
    private Element sigElem = null;
    private boolean validationStatus;
    private boolean validated = false;
    private KeySelectorResult ksr;
    private Map<String, XMLStructure> signatureIdMap;

    static {
        com.sun.org.apache.xml.internal.security.Init.init();
    }

    /**
     * Creates a {@code DOMXMLSignature} from the specified components.
     *
     * @param si the {@code SignedInfo}
     * @param ki the {@code KeyInfo}, or {@code null} if not specified
     * @param objs a list of {@code XMLObject}s or {@code null}
     *  if not specified. The list is copied to protect against subsequent
     *  modification.
     * @param id an optional id (specify {@code null} to omit)
     * @param signatureValueId an optional id (specify {@code null} to
     *  omit)
     * @throws NullPointerException if {@code si} is {@code null}
     */
    public DOMXMLSignature(SignedInfo si, KeyInfo ki,
                           List<? extends XMLObject> objs,
                           String id, String signatureValueId)
    {
        if (si == null) {
            throw new NullPointerException("signedInfo cannot be null");
        }
        this.si = si;
        this.id = id;
        this.sv = new DOMSignatureValue(signatureValueId);
        if (objs == null) {
            this.objects = Collections.emptyList();
        } else {
            this.objects =
                Collections.unmodifiableList(new ArrayList<>(objs));
            for (int i = 0, size = this.objects.size(); i < size; i++) {
                if (!(this.objects.get(i) instanceof XMLObject)) {
                    throw new ClassCastException
                        ("objs["+i+"] is not an XMLObject");
                }
            }
        }
        this.ki = ki;
    }

    /**
     * Creates a {@code DOMXMLSignature} from XML.
     *
     * @param sigElem Signature element
     * @throws MarshalException if XMLSignature cannot be unmarshalled
     */
    public DOMXMLSignature(Element sigElem, XMLCryptoContext context,
                           Provider provider)
        throws MarshalException
    {
        localSigElem = sigElem;
        ownerDoc = localSigElem.getOwnerDocument();

        // get Id attribute, if specified
        id = DOMUtils.getAttributeValue(localSigElem, "Id");
        // unmarshal SignedInfo
        Element siElem = DOMUtils.getFirstChildElement(localSigElem,
                                                       "SignedInfo",
                                                       XMLSignature.XMLNS);
        si = new DOMSignedInfo(siElem, context, provider);

        // unmarshal SignatureValue
        Element sigValElem = DOMUtils.getNextSiblingElement(siElem,
                                                            "SignatureValue",
                                                            XMLSignature.XMLNS);
        sv = new DOMSignatureValue(sigValElem);

        // unmarshal KeyInfo, if specified
        Element nextSibling = DOMUtils.getNextSiblingElement(sigValElem);
        if (nextSibling != null && "KeyInfo".equals(nextSibling.getLocalName())
            && XMLSignature.XMLNS.equals(nextSibling.getNamespaceURI())) {
            ki = new DOMKeyInfo(nextSibling, context, provider);
            nextSibling = DOMUtils.getNextSiblingElement(nextSibling);
        }

        // unmarshal Objects, if specified
        if (nextSibling == null) {
            objects = Collections.emptyList();
        } else {
            List<XMLObject> tempObjects = new ArrayList<>();
            while (nextSibling != null) {
                String name = nextSibling.getLocalName();
                String namespace = nextSibling.getNamespaceURI();
                if (!"Object".equals(name) || !XMLSignature.XMLNS.equals(namespace)) {
                    throw new MarshalException("Invalid element name: " + namespace + ":" + name +
                                               ", expected KeyInfo or Object");
                }
                tempObjects.add(new DOMXMLObject(nextSibling,
                                                 context, provider));
                nextSibling = DOMUtils.getNextSiblingElement(nextSibling);
            }
            objects = Collections.unmodifiableList(tempObjects);
        }
    }

    public String getId() {
        return id;
    }

    public KeyInfo getKeyInfo() {
        return ki;
    }

    public SignedInfo getSignedInfo() {
        return si;
    }

    public List<XMLObject> getObjects() {
        return objects;
    }

    public SignatureValue getSignatureValue() {
        return sv;
    }

    public KeySelectorResult getKeySelectorResult() {
        return ksr;
    }

    @Override
    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context)
        throws MarshalException
    {
        marshal(parent, null, dsPrefix, context);
    }

    public void marshal(Node parent, Node nextSibling, String dsPrefix,
                        DOMCryptoContext context)
        throws MarshalException
    {
        ownerDoc = DOMUtils.getOwnerDocument(parent);
        sigElem = DOMUtils.createElement(ownerDoc, "Signature",
                                         XMLSignature.XMLNS, dsPrefix);

        // append xmlns attribute
        if (dsPrefix == null || dsPrefix.length() == 0) {
            sigElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns",
                                   XMLSignature.XMLNS);
        } else {
            sigElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" +
                                   dsPrefix, XMLSignature.XMLNS);
        }

        // create and append SignedInfo element
        ((DOMSignedInfo)si).marshal(sigElem, dsPrefix, context);

        // create and append SignatureValue element
        ((DOMSignatureValue)sv).marshal(sigElem, dsPrefix, context);

        // create and append KeyInfo element if necessary
        if (ki != null) {
            ((DOMKeyInfo)ki).marshal(sigElem, null, dsPrefix, context);
        }

        // create and append Object elements if necessary
        for (int i = 0, size = objects.size(); i < size; i++) {
            ((DOMXMLObject)objects.get(i)).marshal(sigElem, dsPrefix, context);
        }

        // append Id attribute
        DOMUtils.setAttributeID(sigElem, "Id", id);

        parent.insertBefore(sigElem, nextSibling);
    }

    @Override
    public boolean validate(XMLValidateContext vc)
        throws XMLSignatureException
    {
        if (vc == null) {
            throw new NullPointerException("validateContext is null");
        }

        if (!(vc instanceof DOMValidateContext)) {
            throw new ClassCastException
                ("validateContext must be of type DOMValidateContext");
        }

        if (validated) {
            return validationStatus;
        }

        // validate the signature
        boolean sigValidity = sv.validate(vc);
        if (!sigValidity) {
            validationStatus = false;
            validated = true;
            return validationStatus;
        }

        // validate all References
        @SuppressWarnings("unchecked")
        List<Reference> refs = this.si.getReferences();
        boolean validateRefs = true;
        for (int i = 0, size = refs.size(); validateRefs && i < size; i++) {
            Reference ref = refs.get(i);
            boolean refValid = ref.validate(vc);
            LOG.debug("Reference [{}] is valid: {}", ref.getURI(), refValid);
            validateRefs &= refValid;
        }
        if (!validateRefs) {
            LOG.debug("Couldn't validate the References");
            validationStatus = false;
            validated = true;
            return validationStatus;
        }

        // validate Manifests, if property set
        boolean validateMans = true;
        if (Boolean.TRUE.equals(vc.getProperty
                                ("org.jcp.xml.dsig.validateManifests")))
        {
            for (int i=0, size=objects.size(); validateMans && i < size; i++) {
                XMLObject xo = objects.get(i);
                @SuppressWarnings("unchecked")
                List<XMLStructure> content = xo.getContent();
                int csize = content.size();
                for (int j = 0; validateMans && j < csize; j++) {
                    XMLStructure xs = content.get(j);
                    if (xs instanceof Manifest man) {
                        LOG.debug("validating manifest");
                        @SuppressWarnings("unchecked")
                        List<Reference> manRefs = man.getReferences();
                        int rsize = manRefs.size();
                        for (int k = 0; validateMans && k < rsize; k++) {
                            Reference ref = manRefs.get(k);
                            boolean refValid = ref.validate(vc);
                            LOG.debug(
                                "Manifest ref [{}] is valid: {}", ref.getURI(),  refValid
                            );
                            validateMans &= refValid;
                        }
                    }
                }
            }
        }

        validationStatus = validateMans;
        validated = true;
        return validationStatus;
    }

    @Override
    public void sign(XMLSignContext signContext)
        throws MarshalException, XMLSignatureException
    {
        if (signContext == null) {
            throw new NullPointerException("signContext cannot be null");
        }
        DOMSignContext context = (DOMSignContext)signContext;
        marshal(context.getParent(), context.getNextSibling(),
                DOMUtils.getSignaturePrefix(context), context);

        // generate references and signature value
        List<Reference> allReferences = new ArrayList<>();

        // traverse the Signature and register all objects with IDs that
        // may contain References
        signatureIdMap = new HashMap<>();
        signatureIdMap.put(id, this);
        signatureIdMap.put(si.getId(), si);
        @SuppressWarnings("unchecked")
        List<Reference> refs = si.getReferences();
        for (Reference ref : refs) {
            signatureIdMap.put(ref.getId(), ref);
        }
        for (XMLObject obj : objects) {
            signatureIdMap.put(obj.getId(), obj);
            @SuppressWarnings("unchecked")
            List<XMLStructure> content = obj.getContent();
            for (XMLStructure xs : content) {
                if (xs instanceof Manifest man) {
                    signatureIdMap.put(man.getId(), man);
                    @SuppressWarnings("unchecked")
                    List<Reference> manRefs = man.getReferences();
                    for (Reference ref : manRefs) {
                        allReferences.add(ref);
                        signatureIdMap.put(ref.getId(), ref);
                    }
                }
            }
        }
        // always add SignedInfo references after Manifest references so
        // that Manifest reference are digested first
        allReferences.addAll(refs);

        // generate/digest each reference
        for (Reference ref : allReferences) {
            digestReference((DOMReference)ref, signContext);
        }

        // do final sweep to digest any references that were skipped or missed
        for (Reference ref : allReferences) {
            if (((DOMReference)ref).isDigested()) {
                continue;
            }
            ((DOMReference)ref).digest(signContext);
        }

        Key signingKey = null;
        try {
            KeySelectorResult keySelectorResult = signContext.getKeySelector().select(ki,
                                                      KeySelector.Purpose.SIGN,
                                                      si.getSignatureMethod(),
                                                      signContext);
            signingKey = keySelectorResult.getKey();
            if (signingKey == null) {
                throw new XMLSignatureException("the keySelector did not " +
                                                "find a signing key");
            }
            ksr = keySelectorResult;
        } catch (KeySelectorException kse) {
            throw new XMLSignatureException("cannot find signing key", kse);
        }

        // calculate signature value
        try {
            byte[] val = ((AbstractDOMSignatureMethod)
                si.getSignatureMethod()).sign(signingKey, si, signContext);
            ((DOMSignatureValue)sv).setValue(val);
        } catch (InvalidKeyException ike) {
            throw new XMLSignatureException(ike);
        }

        this.localSigElem = sigElem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof XMLSignature osig)) {
            return false;
        }

        boolean idEqual =
            id == null ? osig.getId() == null : id.equals(osig.getId());
        boolean keyInfoEqual =
            ki == null ? osig.getKeyInfo() == null
                        : ki.equals(osig.getKeyInfo());

        return idEqual && keyInfoEqual &&
                sv.equals(osig.getSignatureValue()) &&
                si.equals(osig.getSignedInfo()) &&
                objects.equals(osig.getObjects());
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        if (ki != null) {
            result = 31 * result + ki.hashCode();
        }
        result = 31 * result + sv.hashCode();
        result = 31 * result + si.hashCode();
        result = 31 * result + objects.hashCode();

        return result;
    }

    private void digestReference(DOMReference ref, XMLSignContext signContext)
        throws XMLSignatureException
    {
        if (ref.isDigested()) {
            return;
        }
        // check dependencies
        String uri = ref.getURI();
        if (Utils.sameDocumentURI(uri)) {
            String parsedId = Utils.parseIdFromSameDocumentURI(uri);
            if (parsedId != null && signatureIdMap.containsKey(parsedId)) {
                XMLStructure xs = signatureIdMap.get(parsedId);
                if (xs instanceof DOMReference) {
                    digestReference((DOMReference)xs, signContext);
                } else if (xs instanceof Manifest man) {
                    List<Reference> manRefs = DOMManifest.getManifestReferences(man);
                    for (int i = 0, size = manRefs.size(); i < size; i++) {
                        digestReference((DOMReference)manRefs.get(i),
                                        signContext);
                    }
                }
            }
            // if uri="" and there are XPath Transforms, there may be
            // reference dependencies in the XPath Transform - so be on
            // the safe side, and skip and do at end in the final sweep
            if (uri.length() == 0) {
                List<Transform> transforms = ref.getTransforms();
                for (Transform transform : transforms) {
                    String transformAlg = transform.getAlgorithm();
                    if (transformAlg.equals(Transform.XPATH) ||
                        transformAlg.equals(Transform.XPATH2)) {
                        return;
                    }
                }
            }
        }
        ref.digest(signContext);
    }

    public class DOMSignatureValue extends DOMStructure
        implements SignatureValue
    {
        private final String id;
        private byte[] value;
        private String valueBase64;
        private Element sigValueElem;
        private boolean validated = false;
        private boolean validationStatus;

        DOMSignatureValue(String id) {
            this.id = id;
        }

        DOMSignatureValue(Element sigValueElem)
            throws MarshalException
        {
            // base64 decode signatureValue
            String content = XMLUtils.getFullTextChildrenFromNode(sigValueElem);
            value = XMLUtils.decode(content);

            Attr attr = sigValueElem.getAttributeNodeNS(null, "Id");
            if (attr != null) {
                id = attr.getValue();
                sigValueElem.setIdAttributeNode(attr, true);
            } else {
                id = null;
            }
            this.sigValueElem = sigValueElem;
        }

        public String getId() {
            return id;
        }

        public byte[] getValue() {
            return (value == null) ? null : value.clone();
        }

        public String getEncodedValue() {
            return valueBase64;
        }

        @Override
        public boolean validate(XMLValidateContext validateContext)
            throws XMLSignatureException
        {
            if (validateContext == null) {
                throw new NullPointerException("context cannot be null");
            }

            if (validated) {
                return validationStatus;
            }

            // get validating key
            SignatureMethod sm = si.getSignatureMethod();
            Key validationKey = null;
            KeySelectorResult ksResult = null;
            try {
                KeySelector keySelector = validateContext.getKeySelector();
                if (keySelector != null) {
                    ksResult = keySelector.select
                        (ki, KeySelector.Purpose.VERIFY, sm, validateContext);
                    if (ksResult != null) {
                        validationKey = ksResult.getKey();
                    }
                }
                if (validationKey == null) {
                    throw new XMLSignatureException("the keyselector did not " +
                                                    "find a validation key");
                }
            } catch (KeySelectorException kse) {
                throw new XMLSignatureException("cannot find validation " +
                                                "key", kse);
            }

            // canonicalize SignedInfo and verify signature
            try {
                validationStatus = ((AbstractDOMSignatureMethod)sm).verify
                    (validationKey, si, value, validateContext);
            } catch (Exception e) {
                throw new XMLSignatureException(e);
            }

            validated = true;
            ksr = ksResult;
            return validationStatus;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof SignatureValue osv)) {
                return false;
            }

            boolean idEqual =
                id == null ? osv.getId() == null : id.equals(osv.getId());

            //XXX compare signature values?
            return idEqual;
        }

        @Override
        public int hashCode() {
            int result = 17;
            if (id != null) {
                result = 31 * result + id.hashCode();
            }

            return result;
        }

        public void marshal(Node parent, String dsPrefix,
                            DOMCryptoContext context)
            throws MarshalException
        {
            // create SignatureValue element
            sigValueElem = DOMUtils.createElement(ownerDoc, "SignatureValue",
                                                  XMLSignature.XMLNS, dsPrefix);
            if (valueBase64 != null) {
                sigValueElem.appendChild(ownerDoc.createTextNode(valueBase64));
            }

            // append Id attribute, if specified
            DOMUtils.setAttributeID(sigValueElem, "Id", id);
            parent.appendChild(sigValueElem);
        }

        void setValue(byte[] value) {
            this.value = value;
            valueBase64 = XMLUtils.encodeToString(value);
            sigValueElem.appendChild(ownerDoc.createTextNode(valueBase64));
        }
    }
}
