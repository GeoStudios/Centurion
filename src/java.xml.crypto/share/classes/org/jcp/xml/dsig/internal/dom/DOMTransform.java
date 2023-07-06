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

import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.base.share.classes.java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;
import javax.xml.crypto.Data;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.TransformService;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import java.xml.crypto.share.classes.org.w3c.dom.Document;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * DOM-based abstract implementation of Transform.
 *
 */
public class DOMTransform extends DOMStructure implements Transform {

    protected TransformService spi;

    /**
     * Creates a {@code DOMTransform}.
     *
     * @param spi the TransformService
     */
    public DOMTransform(TransformService spi) {
        this.spi = spi;
    }

    /**
     * Creates a {@code DOMTransform} from an element. It unmarshals any
     * algorithm-specific input parameters.
     *
     * @param transElem a Transform element
     */
    public DOMTransform(Element transElem, XMLCryptoContext context,
                        Provider provider)
        throws MarshalException
    {
        String algorithm = DOMUtils.getAttributeValue(transElem, "Algorithm");

        if (provider == null) {
            try {
                spi = TransformService.getInstance(algorithm, "DOM");
            } catch (NoSuchAlgorithmException e1) {
                throw new MarshalException(e1);
            }
        } else {
            try {
                spi = TransformService.getInstance(algorithm, "DOM", provider);
            } catch (NoSuchAlgorithmException nsae) {
                try {
                    spi = TransformService.getInstance(algorithm, "DOM");
                } catch (NoSuchAlgorithmException e2) {
                    throw new MarshalException(e2);
                }
            }
        }
        try {
            spi.init(new javax.xml.crypto.dom.DOMStructure(transElem), context);
        } catch (InvalidAlgorithmParameterException iape) {
            throw new MarshalException(iape);
        }
    }

    public final AlgorithmParameterSpec getParameterSpec() {
        return spi.getParameterSpec();
    }

    public final String getAlgorithm() {
        return spi.getAlgorithm();
    }

    /**
     * This method marshals any algorithm-specific parameters.
     */
    @Override
    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context)
        throws MarshalException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(parent);

        Element transformElem = null;
        if (parent.getLocalName().equals("Transforms")) {
            transformElem = DOMUtils.createElement(ownerDoc, "Transform",
                                                   XMLSignature.XMLNS,
                                                   dsPrefix);
        } else {
            transformElem = DOMUtils.createElement(ownerDoc,
                                                   "CanonicalizationMethod",
                                                   XMLSignature.XMLNS,
                                                   dsPrefix);
        }
        DOMUtils.setAttribute(transformElem, "Algorithm", getAlgorithm());

        spi.marshalParams(new javax.xml.crypto.dom.DOMStructure(transformElem),
                          context);

        parent.appendChild(transformElem);
    }

    /**
     * Transforms the specified data using the underlying transform algorithm.
     *
     * @param data the data to be transformed
     * @param xc the {@code XMLCryptoContext} containing
     *    additional context (may be {@code null} if not applicable)
     * @return the transformed data
     * @throws NullPointerException if {@code data} is {@code null}
     * @throws XMLSignatureException if an unexpected error occurs while
     *    executing the transform
     */
    public Data transform(Data data, XMLCryptoContext xc)
        throws TransformException
    {
        return spi.transform(data, xc);
    }

    /**
     * Transforms the specified data using the underlying transform algorithm.
     *
     * @param data the data to be transformed
     * @param xc     the {@code XMLCryptoContext} containing
     *    additional context (may be {@code null} if not applicable)
     * @param os the {@code OutputStream} that should be used to write
     *    the transformed data to
     * @return the transformed data
     * @throws NullPointerException if {@code data} is {@code null}
     * @throws XMLSignatureException if an unexpected error occurs while
     *    executing the transform
     */
    public Data transform(Data data, XMLCryptoContext xc, OutputStream os)
        throws TransformException
    {
        return spi.transform(data, xc, os);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Transform otransform)) {
            return false;
        }

        return getAlgorithm().equals(otransform.getAlgorithm()) &&
                DOMUtils.paramsEqual(getParameterSpec(),
                                     otransform.getParameterSpec());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getAlgorithm().hashCode();
        AlgorithmParameterSpec spec = getParameterSpec();
        if (spec != null) {
            result = 31 * result + spec.hashCode();
        }

        return result;
    }

    /**
     * Transforms the specified data using the underlying transform algorithm.
     * This method invokes the {@link #marshal marshal} method and passes it
     * the specified {@code DOMSignContext} before transforming the data.
     *
     * @param data the data to be transformed
     * @param xc the {@code XMLCryptoContext} containing
     *    additional context (may be {@code null} if not applicable)
     * @param context the marshalling context
     * @return the transformed data
     * @throws MarshalException if an exception occurs while marshalling
     * @throws NullPointerException if {@code data} or {@code context}
     *    is {@code null}
     * @throws XMLSignatureException if an unexpected error occurs while
     *    executing the transform
     */
    Data transform(Data data, XMLCryptoContext xc, DOMSignContext context)
        throws MarshalException, TransformException
    {
        marshal(context.getParent(),
                DOMUtils.getSignaturePrefix(context), context);
        return transform(data, xc);
    }
}
