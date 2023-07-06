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
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.*;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * DOM-based implementation of CanonicalizationMethod for Exclusive
 * Canonical XML algorithm (with or without comments).
 * Uses Apache XML-Sec Canonicalizer.
 *
 */
public final class DOMExcC14NMethod extends ApacheCanonicalizer {

    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException
    {
        if (params != null) {
            if (!(params instanceof ExcC14NParameterSpec)) {
                throw new InvalidAlgorithmParameterException
                    ("params must be of type ExcC14NParameterSpec");
            }
            this.params = (C14NMethodParameterSpec)params;
        }
    }

    public void init(XMLStructure parent, XMLCryptoContext context)
        throws InvalidAlgorithmParameterException
    {
        super.init(parent, context);
        Element paramsElem = DOMUtils.getFirstChildElement(transformElem);
        if (paramsElem == null) {
            this.params = null;
            this.inclusiveNamespaces = null;
            return;
        }
        unmarshalParams(paramsElem);
    }

    private void unmarshalParams(Element paramsElem) {
        String prefixListAttr = paramsElem.getAttributeNS(null, "PrefixList");
        this.inclusiveNamespaces = prefixListAttr;
        int begin = 0;
        int end = prefixListAttr.indexOf(' ');
        List<String> prefixList = new ArrayList<>();
        while (end != -1) {
            prefixList.add(prefixListAttr.substring(begin, end));
            begin = end + 1;
            end = prefixListAttr.indexOf(' ', begin);
        }
        if (begin <= prefixListAttr.length()) {
            prefixList.add(prefixListAttr.substring(begin));
        }
        this.params = new ExcC14NParameterSpec(prefixList);
    }

    @SuppressWarnings("unchecked")
    public List<String> getParameterSpecPrefixList(ExcC14NParameterSpec paramSpec) {
        return paramSpec.getPrefixList();
    }

    @Override
    public void marshalParams(XMLStructure parent, XMLCryptoContext context)
        throws MarshalException
    {
        super.marshalParams(parent, context);
        AlgorithmParameterSpec spec = getParameterSpec();
        if (spec == null) {
            return;
        }

        String prefix = DOMUtils.getNSPrefix(context,
                                             CanonicalizationMethod.EXCLUSIVE);
        Element eElem = DOMUtils.createElement(ownerDoc,
                                               "InclusiveNamespaces",
                                               CanonicalizationMethod.EXCLUSIVE,
                                               prefix);
        if (prefix == null || prefix.length() == 0) {
            eElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns",
                                 CanonicalizationMethod.EXCLUSIVE);
        } else {
            eElem.setAttributeNS("http://www.w3.org/2000/xmlns/",
                                   "xmlns:" + prefix,
                                   CanonicalizationMethod.EXCLUSIVE);
        }

        ExcC14NParameterSpec params = (ExcC14NParameterSpec)spec;
        StringBuilder prefixListAttr = new StringBuilder();
        List<String> prefixList = getParameterSpecPrefixList(params);
        for (int i = 0, size = prefixList.size(); i < size; i++) {
            prefixListAttr.append(prefixList.get(i));
            if (i < size - 1) {
                prefixListAttr.append(' ');
            }
        }
        DOMUtils.setAttribute(eElem, "PrefixList", prefixListAttr.toString());
        this.inclusiveNamespaces = prefixListAttr.toString();
        transformElem.appendChild(eElem);
    }

    public String getParamsNSURI() {
        return CanonicalizationMethod.EXCLUSIVE;
    }

    public Data transform(Data data, XMLCryptoContext xc)
        throws TransformException
    {
        // ignore comments if dereferencing same-document URI that require
        // you to omit comments, even if the Transform says otherwise -
        // this is to be compliant with section 4.3.3.3 of W3C Rec.
        if (data instanceof DOMSubTreeData subTree) {
            if (subTree.excludeComments()) {
                try {
                    canonicalizer = Canonicalizer.getInstance
                        (CanonicalizationMethod.EXCLUSIVE);
                } catch (InvalidCanonicalizerException ice) {
                    throw new TransformException
                        ("Couldn't find Canonicalizer for: " +
                         CanonicalizationMethod.EXCLUSIVE + ": " +
                         ice.getMessage(), ice);
                }
            }
        }

        return canonicalize(data, xc);
    }
}
