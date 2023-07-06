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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.util.Set;
import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.TransformService;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.transforms.Transform;
import java.xml.crypto.share.classes.org.w3c.dom.Document;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

public abstract class ApacheCanonicalizer extends TransformService {

    static {
        com.sun.org.apache.xml.internal.security.Init.init();
    }

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(ApacheCanonicalizer.class);
    protected Canonicalizer canonicalizer;
    private Transform apacheTransform;
    protected String inclusiveNamespaces;
    protected C14NMethodParameterSpec params;
    protected Document ownerDoc;
    protected Element transformElem;

    public final AlgorithmParameterSpec getParameterSpec()
    {
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
            ((javax.xml.crypto.dom.DOMStructure)parent).getNode();
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
            ((javax.xml.crypto.dom.DOMStructure)parent).getNode();
        ownerDoc = DOMUtils.getOwnerDocument(transformElem);
    }

    public Data canonicalize(Data data, XMLCryptoContext xc)
        throws TransformException
    {
        return canonicalize(data, xc, null);
    }

    public Data canonicalize(Data data, XMLCryptoContext xc, OutputStream os)
        throws TransformException
    {
        if (canonicalizer == null) {
            try {
                canonicalizer = Canonicalizer.getInstance(getAlgorithm());
                LOG.debug("Created canonicalizer for algorithm: {}", getAlgorithm());
            } catch (InvalidCanonicalizerException ice) {
                throw new TransformException
                    ("Couldn't find Canonicalizer for: " + getAlgorithm() +
                     ": " + ice.getMessage(), ice);
            }
        }

        boolean isByteArrayOutputStream = os == null;
        OutputStream writer = isByteArrayOutputStream ? new ByteArrayOutputStream() : os;
        try {
            boolean secVal = Utils.secureValidation(xc);
            Set<Node> nodeSet = null;
            if (data instanceof ApacheData) {
                XMLSignatureInput in =
                    ((ApacheData)data).getXMLSignatureInput();
                if (in.isElement()) {
                    if (inclusiveNamespaces != null) {
                        canonicalizer.canonicalizeSubtree(in.getSubNode(), inclusiveNamespaces, writer);
                        return new OctetStreamData(new ByteArrayInputStream(getC14nBytes(writer, isByteArrayOutputStream)));
                    } else {
                        canonicalizer.canonicalizeSubtree(in.getSubNode(), writer);
                        return new OctetStreamData(new ByteArrayInputStream(getC14nBytes(writer, isByteArrayOutputStream)));
                    }
                } else if (in.isNodeSet()) {
                    nodeSet = in.getNodeSet();
                } else {
                    canonicalizer.canonicalize(Utils.readBytesFromStream(in.getOctetStream()), writer, secVal);
                    return new OctetStreamData(new ByteArrayInputStream(getC14nBytes(writer, isByteArrayOutputStream)));
                }
            } else if (data instanceof DOMSubTreeData subTree) {
                if (inclusiveNamespaces != null) {
                    canonicalizer.canonicalizeSubtree(subTree.getRoot(), inclusiveNamespaces, writer);
                    return new OctetStreamData(new ByteArrayInputStream(getC14nBytes(writer, isByteArrayOutputStream)));
                } else {
                    canonicalizer.canonicalizeSubtree(subTree.getRoot(), writer);
                    return new OctetStreamData(new ByteArrayInputStream(getC14nBytes(writer, isByteArrayOutputStream)));
                }
            } else if (data instanceof NodeSetData<?> nsd) {
                // convert Iterator to Set
                nodeSet = Utils.toNodeSet(nsd.iterator());
                LOG.debug("Canonicalizing {} nodes", nodeSet.size());
            } else {
                canonicalizer.canonicalize(Utils.readBytesFromStream(((OctetStreamData)data).getOctetStream()), writer, secVal);
                return new OctetStreamData(new ByteArrayInputStream(getC14nBytes(writer, isByteArrayOutputStream)));
            }

            if (inclusiveNamespaces != null) {
                canonicalizer.canonicalizeXPathNodeSet(nodeSet, inclusiveNamespaces, writer);
                return new OctetStreamData(new ByteArrayInputStream(getC14nBytes(writer, isByteArrayOutputStream)));
            } else {
                canonicalizer.canonicalizeXPathNodeSet(nodeSet, writer);
                return new OctetStreamData(new ByteArrayInputStream(getC14nBytes(writer, isByteArrayOutputStream)));
            }
        } catch (Exception e) {
            throw new TransformException(e);
        }
    }

    private byte[] getC14nBytes(OutputStream outputStream, boolean isByteArrayOutputStream) {    // NOPMD - preserving previous behavior here
        if (isByteArrayOutputStream) {
            return ((ByteArrayOutputStream)outputStream).toByteArray();
        }
        return null;
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

        if (ownerDoc == null) {
            throw new TransformException("transform must be marshalled");
        }

        if (apacheTransform == null) {
            try {
                apacheTransform =
                    new Transform(ownerDoc, getAlgorithm(), transformElem.getChildNodes());
                apacheTransform.setElement(transformElem, xc.getBaseURI());
                LOG.debug("Created transform for algorithm: {}", getAlgorithm());
            } catch (Exception ex) {
                throw new TransformException
                    ("Couldn't find Transform for: " + getAlgorithm(), ex);
            }
        }

        XMLSignatureInput in;
        if (data instanceof ApacheData) {
            LOG.debug("ApacheData = true");
            in = ((ApacheData)data).getXMLSignatureInput();
        } else if (data instanceof NodeSetData) {
            LOG.debug("isNodeSet() = true");
            if (data instanceof DOMSubTreeData subTree) {
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
            in = apacheTransform.performTransform(in, os, secVal);
            if (!in.isNodeSet() && !in.isElement()) {
                return null;
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