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

import java.base.share.classes.java.security.Key;
import java.security.KeyStore;
import java.base.share.classes.java.security.PrivateKey;
import java.base.share.classes.java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.base.share.classes.javax.crypto.SecretKey;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Resolves a SecretKey within a KeyStore based on the KeyName.
 * The KeyName is the key entry alias within the KeyStore.
 */
public class SecretKeyResolver extends KeyResolverSpi
{
    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(SecretKeyResolver.class);

    private final KeyStore keyStore;
    private final char[] password;

    /**
     * Constructor.
     */
    public SecretKeyResolver(KeyStore keyStore, char[] password) {
        this.keyStore = keyStore;
        this.password = password;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean engineCanResolve(Element element, String baseURI, StorageResolver storage) {
        return XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME);
    }

    /** {@inheritDoc} */
    @Override
    protected PublicKey engineResolvePublicKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected X509Certificate engineResolveX509Certificate(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected SecretKey engineResolveSecretKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        LOG.debug("Can I resolve {}?", element.getTagName());

        if (XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME)) {
            String keyName = element.getFirstChild().getNodeValue();
            try {
                Key key = keyStore.getKey(keyName, password);
                if (key instanceof SecretKey) {
                    return (SecretKey) key;
                }
            } catch (Exception e) {
                LOG.debug("Cannot recover the key", e);
            }
        }

        LOG.debug("I can't");
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
