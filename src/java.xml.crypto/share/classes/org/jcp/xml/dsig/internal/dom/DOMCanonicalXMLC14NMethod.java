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
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * DOM-based implementation of CanonicalizationMethod for Canonical XML
 * (with or without comments). Uses Apache XML-Sec Canonicalizer.
 *
 */
public final class DOMCanonicalXMLC14NMethod extends ApacheCanonicalizer {

    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException {
        if (params != null) {
            throw new InvalidAlgorithmParameterException("no parameters " +
                "should be specified for Canonical XML C14N algorithm");
        }
    }

    public Data transform(Data data, XMLCryptoContext xc)
        throws TransformException {

        // ignore comments if dereferencing same-document URI that requires
        // you to omit comments, even if the Transform says otherwise -
        // this is to be compliant with section 4.3.3.3 of W3C Rec.
        if (data instanceof DOMSubTreeData subTree) {
            if (subTree.excludeComments()) {
                try {
                    canonicalizer = Canonicalizer.getInstance
                        (CanonicalizationMethod.INCLUSIVE);
                } catch (InvalidCanonicalizerException ice) {
                    throw new TransformException
                        ("Couldn't find Canonicalizer for: " +
                         CanonicalizationMethod.INCLUSIVE + ": " +
                         ice.getMessage(), ice);
                }
            }
        }

        return canonicalize(data, xc);
    }
}
