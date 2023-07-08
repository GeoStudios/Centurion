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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations;


import java.base.share.classes.java.security.PrivateKey;
import java.base.share.classes.java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content.keyvalues.RSAKeyValue;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





public class RSAKeyValueResolver extends KeyResolverSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(RSAKeyValueResolver.class);

    /** {@inheritDoc} */
    @Override
    protected boolean engineCanResolve(Element element, String baseURI, StorageResolver storage) {
        return XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYVALUE)
            || XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_RSAKEYVALUE);
    }

    /** {@inheritDoc} */
    @Override
    protected PublicKey engineResolvePublicKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        if (element == null) {
            return null;
        }

        LOG.debug("Can I resolve {}", element.getTagName());

        boolean isKeyValue = XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYVALUE);
        Element rsaKeyElement = null;
        if (isKeyValue) {
            rsaKeyElement =
                XMLUtils.selectDsNode(element.getFirstChild(), Constants._TAG_RSAKEYVALUE, 0);
        } else if (XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_RSAKEYVALUE)) {
            // this trick is needed to allow the RetrievalMethodResolver to eat a
            // ds:RSAKeyValue directly (without KeyValue)
            rsaKeyElement = element;
        }

        if (rsaKeyElement == null) {
            return null;
        }

        try {
            RSAKeyValue rsaKeyValue = new RSAKeyValue(rsaKeyElement, baseURI);

            return rsaKeyValue.getPublicKey();
        } catch (XMLSecurityException ex) {
            LOG.debug("XMLSecurityException", ex);
        }

        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected X509Certificate engineResolveX509Certificate(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected javax.crypto.SecretKey engineResolveSecretKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected PrivateKey engineResolvePrivateKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        return null;
    }
}
