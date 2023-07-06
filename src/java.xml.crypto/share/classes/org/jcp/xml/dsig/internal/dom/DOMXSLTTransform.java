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
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.Node;
import javax.xml.crypto.*;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * DOM-based implementation of XSLT Transform.
 * (Uses Apache XML-Sec Transform implementation)
 *
 */
public final class DOMXSLTTransform extends ApacheTransform {

    @Override
    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException {
        if (params == null) {
            throw new InvalidAlgorithmParameterException("params are required");
        }
        if (!(params instanceof XSLTTransformParameterSpec)) {
            throw new InvalidAlgorithmParameterException("unrecognized params");
        }
        this.params = params;
    }

    public void init(XMLStructure parent, XMLCryptoContext context)
        throws InvalidAlgorithmParameterException {

        super.init(parent, context);
        unmarshalParams(DOMUtils.getFirstChildElement(transformElem));
    }

    private void unmarshalParams(Element sheet) {
        this.params = new XSLTTransformParameterSpec
            (new javax.xml.crypto.dom.DOMStructure(sheet));
    }

    public void marshalParams(XMLStructure parent, XMLCryptoContext context)
        throws MarshalException {
        super.marshalParams(parent, context);
        XSLTTransformParameterSpec xp =
            (XSLTTransformParameterSpec) getParameterSpec();
        Node xsltElem =
            ((javax.xml.crypto.dom.DOMStructure) xp.getStylesheet()).getNode();
        DOMUtils.appendChild(transformElem, xsltElem);
    }
}
