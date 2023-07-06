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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.implementations;

import java.io.OutputStream;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.NodeFilter;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.TransformSpi;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.TransformationException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.Transforms;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Implements the {@code http://www.w3.org/2000/09/xmldsig#enveloped-signature}
 * transform.
 *
 */
public class TransformEnvelopedSignature extends TransformSpi {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String engineGetURI() {
        return Transforms.TRANSFORM_ENVELOPED_SIGNATURE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected XMLSignatureInput enginePerformTransform(
        XMLSignatureInput input, OutputStream os, Element transformElement,
        String baseURI, boolean secureValidation
    ) throws TransformationException {
        /**
         * If the actual input is an octet stream, then the application MUST
         * convert the octet stream to an XPath node-set suitable for use by
         * Canonical XML with Comments. (A subsequent application of the
         * REQUIRED Canonical XML algorithm would strip away these comments.)
         *
         * ...
         *
         * The evaluation of this expression includes all of the document's nodes
         * (including comments) in the node-set representing the octet stream.
         */

        Node signatureElement = searchSignatureElement(transformElement);
        input.setExcludeNode(signatureElement);
        input.addNodeFilter(new EnvelopedNodeFilter(signatureElement));
        return input;
    }

    /**
     * @param signatureElement
     * @return the node that is the signature
     * @throws TransformationException
     */
    private static Node searchSignatureElement(Node signatureElement)
        throws TransformationException {
        boolean found = false;

        while (true) {
            if (signatureElement == null
                || signatureElement.getNodeType() == Node.DOCUMENT_NODE) {
                break;
            }
            Element el = (Element) signatureElement;
            if (el.getNamespaceURI().equals(Constants.SignatureSpecNS)
                && el.getLocalName().equals(Constants._TAG_SIGNATURE)) {
                found = true;
                break;
            }

            signatureElement = signatureElement.getParentNode();
        }

        if (!found) {
            throw new TransformationException(
                "transform.envelopedSignatureTransformNotInSignatureElement");
        }
        return signatureElement;
    }

    static class EnvelopedNodeFilter implements NodeFilter {

        private final Node exclude;

        EnvelopedNodeFilter(Node n) {
            exclude = n;
        }

        public int isNodeIncludeDO(Node n, int level) {
            if (n == exclude) {
                return -1;
            }
            return 1;
        }

        /**
         * @see com.sun.org.apache.xml.internal.security.signature.NodeFilter#isNodeInclude(org.w3c.dom.Node)
         */
        public int isNodeInclude(Node n) {
            if (n == exclude || XMLUtils.isDescendantOrSelf(exclude, n)) {
                return -1;
            }
            return 1;
            //return !XMLUtils.isDescendantOrSelf(exclude, n);
        }
    }
}
