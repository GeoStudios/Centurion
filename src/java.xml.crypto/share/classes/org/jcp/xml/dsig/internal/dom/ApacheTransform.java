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
import java.security.spec.AlgorithmParameterSpec;
import java.util.Set;
import java.xml.crypto.share.classes.org.w3c.dom.Document;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.Node;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.transforms.Transform;
import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * This is a wrapper/glue class which invokes the Apache XML-Security
 * Transform.
 *
 */
public abstract class ApacheTransform extends TransformService {

    static {
        com.sun.org.apache.xml.internal.security.Init.init();
    }

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(ApacheTransform.class);
    private Transform transform;
    protected Document ownerDoc;
    protected Element transformElem;
    protected TransformParameterSpec params;

    @Override
    public final AlgorithmParameterSpec getParameterSpec() {
        return params;
    }

    public void init(XMLStructure parent, XMLCryptoContext context)
        throws InvalidAlgorithmParameterException
    {
        if (context != null && !(context instanceof DOMCryptoContext)) {
            throw new ClassCastException
                ("context must be of type DOMCryptoContext");
        }
        if (parent == null) {
            throw new NullPointerException();
        }
        if (!(parent instanceof javax.xml.crypto.dom.DOMStructure)) {
            throw new ClassCastException("parent must be of type DOMStructure");
        }
        transformElem = (Element)
            ((javax.xml.crypto.dom.DOMStructure) parent).getNode();
        ownerDoc = DOMUtils.getOwnerDocument(transformElem);
    }

    public void marshalParams(XMLStructure parent, XMLCryptoContext context)
        throws MarshalException
    {
        if (context != null && !(context instanceof DOMCryptoContext)) {
            throw new ClassCastException
                ("context must be of type DOMCryptoContext");
        }
        if (parent == null) {
            throw new NullPointerException();
        }
        if (!(parent instanceof javax.xml.crypto.dom.DOMStructure)) {
            throw new ClassCastException("parent must be of type DOMStructure");
        }
        transformElem = (Element)
            ((javax.xml.crypto.dom.DOMStructure) parent).getNode();
        ownerDoc = DOMUtils.getOwnerDocument(transformElem);
    }

    public Data transform(Data data, XMLCryptoContext xc)
        throws TransformException
    {
        if (data == null) {
            throw new NullPointerException("data must not be null");
        }
        return transformIt(data, xc, null);
    }

    public Data transform(Data data, XMLCryptoContext xc, OutputStream os)
        throws TransformException
    {
        if (data == null) {
            throw new NullPointerException("data must not be null");
        }
        if (os == null) {
            throw new NullPointerException("output stream must not be null");
        }
        return transformIt(data, xc, os);
    }

    private Data transformIt(Data data, XMLCryptoContext xc, OutputStream os)
        throws TransformException
    {
        if (ownerDoc == null) {
            throw new TransformException("transform must be marshalled");
        }

        if (transform == null) {
            try {
                transform =
                    new Transform(ownerDoc, getAlgorithm(), transformElem.getChildNodes());
                transform.setElement(transformElem, xc.getBaseURI());
                LOG.debug("Created transform for algorithm: {}", getAlgorithm());
            } catch (Exception ex) {
                throw new TransformException("Couldn't find Transform for: " +
                                             getAlgorithm(), ex);
            }
        }

        if (Utils.secureValidation(xc)) {
            String algorithm = getAlgorithm();
            if (Policy.restrictAlg(algorithm)) {
                throw new TransformException(
                    "Transform " + algorithm + " is forbidden when secure validation is enabled"
                );
            }
        }

        XMLSignatureInput in;
        if (data instanceof ApacheData) {
            LOG.debug("ApacheData = true");
            in = ((ApacheData)data).getXMLSignatureInput();
        } else if (data instanceof NodeSetData) {
            LOG.debug("isNodeSet() = true");
            if (data instanceof DOMSubTreeData subTree) {
                LOG.debug("DOMSubTreeData = true");
                in = new XMLSignatureInput(subTree.getRoot());
                in.setExcludeComments(subTree.excludeComments());
            } else {
                @SuppressWarnings("unchecked")
                Set<Node> nodeSet =
                    Utils.toNodeSet(((NodeSetData)data).iterator());
                in = new XMLSignatureInput(nodeSet);
            }
        } else {
            LOG.debug("isNodeSet() = false");
            try {
                in = new XMLSignatureInput
                    (((OctetStreamData)data).getOctetStream());
            } catch (Exception ex) {
                throw new TransformException(ex);
            }
        }
        boolean secVal = Utils.secureValidation(xc);
        in.setSecureValidation(secVal);

        try {
            if (os != null) {
                in = transform.performTransform(in, os, secVal);
                if (!in.isNodeSet() && !in.isElement()) {
                    return null;
                }
            } else {
                in = transform.performTransform(in, secVal);
            }
            if (in.isOctetStream()) {
                return new ApacheOctetStreamData(in);
            } else {
                return new ApacheNodeSetData(in);
            }
        } catch (Exception ex) {
            throw new TransformException(ex);
        }
    }

    public final boolean isFeatureSupported(String feature) {
        if (feature == null) {
            throw new NullPointerException();
        } else {
            return false;
        }
    }
}
