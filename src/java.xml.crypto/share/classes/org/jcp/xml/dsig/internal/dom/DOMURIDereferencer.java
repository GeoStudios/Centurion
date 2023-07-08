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


import java.xml.crypto.share.classes.org.w3c.dom.Attr;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.w3c.dom.Node;
import com.sun.org.apache.xml.internal.security.Init;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolver;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverContext;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import javax.xml.crypto.*;
import javax.xml.crypto.dom.*;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * DOM-based implementation of URIDereferencer.
 *
 */
public final class DOMURIDereferencer implements URIDereferencer {

    static final URIDereferencer INSTANCE = new DOMURIDereferencer();

    private DOMURIDereferencer() {
        // need to call com.sun.org.apache.xml.internal.security.Init.init()
        // before calling any apache security code
        Init.init();
    }

    public Data dereference(URIReference uriRef, XMLCryptoContext context)
        throws URIReferenceException {

        if (uriRef == null) {
            throw new NullPointerException("uriRef cannot be null");
        }
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }

        DOMURIReference domRef = (DOMURIReference) uriRef;
        Attr uriAttr = (Attr) domRef.getHere();
        String uri = uriRef.getURI();
        DOMCryptoContext dcc = (DOMCryptoContext) context;
        String baseURI = context.getBaseURI();

        boolean secVal = Utils.secureValidation(context);

        if (secVal && Policy.restrictReferenceUriScheme(uri)) {
            throw new URIReferenceException(
                "Uri " + uri + " is forbidden when secure validation is enabled");
        }

        // Check if same-document URI and already registered on the context
        if (uri != null && uri.length() != 0 && uri.charAt(0) == '#') {
            String id = uri.substring(1);

            if (id.startsWith("xpointer(id(")) {
                int i1 = id.indexOf('\'');
                int i2 = id.indexOf('\'', i1+1);
                id = id.substring(i1+1, i2);
            }

            // check if element is registered by Id
            Node referencedElem = uriAttr.getOwnerDocument().getElementById(id);
            if (referencedElem == null) {
               // see if element is registered in DOMCryptoContext
               referencedElem = dcc.getElementById(id);
            }
            if (referencedElem != null) {
                if (secVal && Policy.restrictDuplicateIds()) {
                    Element start = referencedElem.getOwnerDocument().getDocumentElement();
                    if (!XMLUtils.protectAgainstWrappingAttack(start, (Element)referencedElem, id)) {
                        String error = "Multiple Elements with the same ID "
                            + id + " detected when secure validation"
                            + " is enabled";
                        throw new URIReferenceException(error);
                    }
                }

                XMLSignatureInput result = new XMLSignatureInput(referencedElem);
                result.setSecureValidation(secVal);
                if (!uri.substring(1).startsWith("xpointer(id(")) {
                    result.setExcludeComments(true);
                }

                result.setMIMEType("text/xml");
                if (baseURI != null && baseURI.length() > 0) {
                    result.setSourceURI(baseURI.concat(uriAttr.getNodeValue()));
                } else {
                    result.setSourceURI(uriAttr.getNodeValue());
                }
                return new ApacheNodeSetData(result);
            }
        }

        try {
            ResourceResolverContext resContext = new ResourceResolverContext(uriAttr, baseURI, false);
            XMLSignatureInput in = ResourceResolver.resolve(resContext);
            if (in.isOctetStream()) {
                return new ApacheOctetStreamData(in);
            } else {
                return new ApacheNodeSetData(in);
            }
        } catch (Exception e) {
            throw new URIReferenceException(e);
        }
    }
}
