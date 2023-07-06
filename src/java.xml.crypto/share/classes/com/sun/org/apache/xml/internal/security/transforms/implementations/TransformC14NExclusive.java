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

import java.io.ByteArrayOutputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.OutputStream;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315Excl;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315ExclOmitComments;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.TransformSpi;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.Transforms;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.params.InclusiveNamespaces;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Class TransformC14NExclusive
 *
 */
public class TransformC14NExclusive extends TransformSpi {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String engineGetURI() {
        return Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected XMLSignatureInput enginePerformTransform(
        XMLSignatureInput input, OutputStream os, Element transformElement,
        String baseURI, boolean secureValidation
    ) throws CanonicalizationException {
        try {
            String inclusiveNamespaces = null;

            if (length(transformElement,
                InclusiveNamespaces.ExclusiveCanonicalizationNamespace,
                InclusiveNamespaces._TAG_EC_INCLUSIVENAMESPACES) == 1
            ) {
                Element inclusiveElement =
                    XMLUtils.selectNode(
                        transformElement.getFirstChild(),
                        InclusiveNamespaces.ExclusiveCanonicalizationNamespace,
                        InclusiveNamespaces._TAG_EC_INCLUSIVENAMESPACES,
                        0
                    );

                inclusiveNamespaces =
                    new InclusiveNamespaces(
                        inclusiveElement, baseURI).getInclusiveNamespaces();
            }

            Canonicalizer20010315Excl c14n = getCanonicalizer();

            if (os == null) {
                try (ByteArrayOutputStream writer = new ByteArrayOutputStream()) {
                    c14n.engineCanonicalize(input, inclusiveNamespaces, writer, secureValidation);
                    writer.flush();
                    XMLSignatureInput output = new XMLSignatureInput(writer.toByteArray());
                    output.setSecureValidation(secureValidation);
                    return output;
                } catch (IOException ex) {
                    throw new CanonicalizationException("empty", new Object[] {ex.getMessage()});
                }
            } else {
                c14n.engineCanonicalize(input, inclusiveNamespaces, os, secureValidation);
                XMLSignatureInput output = new XMLSignatureInput((byte[])null);
                output.setSecureValidation(secureValidation);
                output.setOutputStream(os);
                return output;
            }
        } catch (XMLSecurityException ex) {
            throw new CanonicalizationException(ex);
        }
    }

    protected Canonicalizer20010315Excl getCanonicalizer() {
        return new Canonicalizer20010315ExclOmitComments();
    }

    /**
     * Method length
     *
     * @param namespace
     * @param localname
     * @return the number of elements {namespace}:localname under this element
     */
    private int length(Element element, String namespace, String localname) {
        int number = 0;
        Node sibling = element.getFirstChild();
        while (sibling != null) {
            if (localname.equals(sibling.getLocalName())
                && namespace.equals(sibling.getNamespaceURI())) {
                number++;
            }
            sibling = sibling.getNextSibling();
        }
        return number;
    }
}
