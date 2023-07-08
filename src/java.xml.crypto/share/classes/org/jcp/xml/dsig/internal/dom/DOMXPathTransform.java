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


import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.crypto.dsig.spec.XPathFilterParameterSpec;
import java.xml.crypto.share.classes.org.w3c.dom.Attr;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.NamedNodeMap;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * DOM-based implementation of XPath Filtering Transform.
 * (Uses Apache XML-Sec Transform implementation)
 *
 */
public final class DOMXPathTransform extends ApacheTransform {

    @Override
    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException
    {
        if (params == null) {
            throw new InvalidAlgorithmParameterException("params are required");
        } else if (!(params instanceof XPathFilterParameterSpec)) {
            throw new InvalidAlgorithmParameterException
                ("params must be of type XPathFilterParameterSpec");
        }
        this.params = params;
    }

    public void init(XMLStructure parent, XMLCryptoContext context)
        throws InvalidAlgorithmParameterException
    {
        super.init(parent, context);
        unmarshalParams(DOMUtils.getFirstChildElement(transformElem));
    }

    private void unmarshalParams(Element paramsElem) {
        String xPath = paramsElem.getFirstChild().getNodeValue();
        // create a Map of namespace prefixes
        NamedNodeMap attributes = paramsElem.getAttributes();
        if (attributes != null) {
            int length = attributes.getLength();
            Map<String, String> namespaceMap =
                new HashMap<>(length);
            for (int i = 0; i < length; i++) {
                Attr attr = (Attr)attributes.item(i);
                String prefix = attr.getPrefix();
                if ("xmlns".equals(prefix)) {
                    namespaceMap.put(attr.getLocalName(), attr.getValue());
                }
            }
            this.params = new XPathFilterParameterSpec(xPath, namespaceMap);
        } else {
            this.params = new XPathFilterParameterSpec(xPath);
        }
    }

    public void marshalParams(XMLStructure parent, XMLCryptoContext context)
        throws MarshalException
    {
        super.marshalParams(parent, context);
        XPathFilterParameterSpec xp =
            (XPathFilterParameterSpec)getParameterSpec();
        Element xpathElem = DOMUtils.createElement(ownerDoc, "XPath",
             XMLSignature.XMLNS, DOMUtils.getSignaturePrefix(context));
        xpathElem.appendChild(ownerDoc.createTextNode(xp.getXPath()));

        // add namespace attributes, if necessary
        @SuppressWarnings("unchecked")
        Set<Map.Entry<String, String>> entries =
            xp.getNamespaceMap().entrySet();
        for (Map.Entry<String, String> entry : entries) {
            xpathElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" +
                                     entry.getKey(),
                                     entry.getValue());
        }

        transformElem.appendChild(xpathElem);
    }
}
