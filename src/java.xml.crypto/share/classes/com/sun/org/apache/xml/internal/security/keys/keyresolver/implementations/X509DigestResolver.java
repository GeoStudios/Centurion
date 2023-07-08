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
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.base.share.classes.java.util.Arrays;
import java.util.Iterator;
import java.base.share.classes.javax.crypto.SecretKey;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content.X509Data;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.content.x509.XMLX509Digest;
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
 * KeyResolverSpi implementation which resolves public keys and X.509 certificates from a
 * {@code dsig11:X509Digest} element.
 *
 */
public class X509DigestResolver extends KeyResolverSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(X509DigestResolver.class);

    /** {@inheritDoc} */
    @Override
    protected boolean engineCanResolve(Element element, String baseURI, StorageResolver storage) {
        if (XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_X509DATA)) {
            try {
                X509Data x509Data = new X509Data(element, baseURI);
                return x509Data.containsDigest();
            } catch (XMLSecurityException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected PublicKey engineResolvePublicKey(Element element, String baseURI, StorageResolver storage, boolean secureValidation)
        throws KeyResolverException {

        X509Certificate cert = this.engineResolveX509Certificate(element, baseURI, storage, secureValidation);

        if (cert != null) {
            return cert.getPublicKey();
        }

        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected X509Certificate engineResolveX509Certificate(Element element, String baseURI, StorageResolver storage, boolean secureValidation)
        throws KeyResolverException {

        try {
            return resolveCertificate(element, baseURI, storage);
        } catch (XMLSecurityException e) {
            LOG.debug("XMLSecurityException", e);
        }

        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected SecretKey engineResolveSecretKey(Element element, String baseURI, StorageResolver storage, boolean secureValidation)
        throws KeyResolverException {
        return null;
    }

    /**
     * Resolves from the storage resolver the actual certificate represented by the digest.
     *
     * @param element
     * @param baseURI
     * @param storage
     * @return the certificate represented by the digest.
     * @throws XMLSecurityException
     */
    private X509Certificate resolveCertificate(Element element, String baseURI, StorageResolver storage)
        throws XMLSecurityException {

        XMLX509Digest[] x509Digests = null;

        Element[] x509childNodes = XMLUtils.selectDs11Nodes(element.getFirstChild(), Constants._TAG_X509DIGEST);

        if (x509childNodes == null || x509childNodes.length <= 0) {
            return null;
        }

        try {
            checkStorage(storage);

            x509Digests = new XMLX509Digest[x509childNodes.length];

            for (int i = 0; i < x509childNodes.length; i++) {
                x509Digests[i] = new XMLX509Digest(x509childNodes[i], baseURI);
            }

            Iterator<Certificate> storageIterator = storage.getIterator();
            while (storageIterator.hasNext()) {
                X509Certificate cert = (X509Certificate) storageIterator.next();

                for (int i = 0; i < x509Digests.length; i++) {
                    XMLX509Digest keyInfoDigest = x509Digests[i];
                    byte[] certDigestBytes = XMLX509Digest.getDigestBytesFromCert(cert, keyInfoDigest.getAlgorithm());

                    if (Arrays.equals(keyInfoDigest.getDigestBytes(), certDigestBytes)) {
                        LOG.debug("Found certificate with: {}", cert.getSubjectX500Principal().getName());
                        return cert;
                    }

                }
            }

        } catch (XMLSecurityException ex) {
            throw new KeyResolverException(ex);
        }

        return null;
    }

    /**
     * Method checkSrorage
     *
     * @param storage
     * @throws KeyResolverException
     */
    private void checkStorage(StorageResolver storage) throws KeyResolverException {
        if (storage == null) {
            Object[] exArgs = { Constants._TAG_X509DIGEST };
            KeyResolverException ex = new KeyResolverException("KeyResolver.needStorageResolver", exArgs);
            LOG.debug("", ex);
            throw ex;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected PrivateKey engineResolvePrivateKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        return null;
    }
}
