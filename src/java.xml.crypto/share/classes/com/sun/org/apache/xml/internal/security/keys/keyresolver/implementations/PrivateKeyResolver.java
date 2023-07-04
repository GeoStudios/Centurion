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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;
import javax.crypto.SecretKey;
import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.keys.content.X509Data;
import com.sun.org.apache.xml.internal.security.keys.content.x509.XMLX509Certificate;
import com.sun.org.apache.xml.internal.security.keys.content.x509.XMLX509IssuerSerial;
import com.sun.org.apache.xml.internal.security.keys.content.x509.XMLX509SKI;
import com.sun.org.apache.xml.internal.security.keys.content.x509.XMLX509SubjectName;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverException;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Resolves a PrivateKey within a KeyStore based on the KeyInfo hints.
 * For X509Data hints, the certificate associated with the private key entry must match.
 * For a KeyName hint, the KeyName must match the alias of a PrivateKey entry within the KeyStore.
 */
public class PrivateKeyResolver extends KeyResolverSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(PrivateKeyResolver.class);

    private final KeyStore keyStore;
    private final char[] password;

    /**
     * Constructor.
     */
    public PrivateKeyResolver(KeyStore keyStore, char[] password) {
        this.keyStore = keyStore;
        this.password = password;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean engineCanResolve(Element element, String baseURI, StorageResolver storage) {
        return XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_X509DATA)
            || XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME);
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
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public PrivateKey engineResolvePrivateKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {

        if (XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_X509DATA)) {
            PrivateKey privKey = resolveX509Data(element, baseURI);
            return privKey;
        } else if (XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME)) {
            LOG.debug("Can I resolve KeyName?");
            String keyName = element.getFirstChild().getNodeValue();

            try {
                Key key = keyStore.getKey(keyName, password);
                if (key instanceof PrivateKey) {
                    return (PrivateKey) key;
                }
            } catch (Exception e) {
                LOG.debug("Cannot recover the key", e);
            }
        }

        return null;
    }

    private PrivateKey resolveX509Data(Element element, String baseURI) {
        LOG.debug("Can I resolve X509Data?");

        try {
            X509Data x509Data = new X509Data(element, baseURI);

            int len = x509Data.lengthSKI();
            for (int i = 0; i < len; i++) {
                XMLX509SKI x509SKI = x509Data.itemSKI(i);
                PrivateKey privKey = resolveX509SKI(x509SKI);
                if (privKey != null) {
                    return privKey;
                }
            }

            len = x509Data.lengthIssuerSerial();
            for (int i = 0; i < len; i++) {
                XMLX509IssuerSerial x509Serial = x509Data.itemIssuerSerial(i);
                PrivateKey privKey = resolveX509IssuerSerial(x509Serial);
                if (privKey != null) {
                    return privKey;
                }
            }

            len = x509Data.lengthSubjectName();
            for (int i = 0; i < len; i++) {
                XMLX509SubjectName x509SubjectName = x509Data.itemSubjectName(i);
                PrivateKey privKey = resolveX509SubjectName(x509SubjectName);
                if (privKey != null) {
                    return privKey;
                }
            }

            len = x509Data.lengthCertificate();
            for (int i = 0; i < len; i++) {
                XMLX509Certificate x509Cert = x509Data.itemCertificate(i);
                PrivateKey privKey = resolveX509Certificate(x509Cert);
                if (privKey != null) {
                    return privKey;
                }
            }
        } catch (XMLSecurityException e) {
            LOG.debug("XMLSecurityException", e);
        } catch (KeyStoreException e) {
            LOG.debug("KeyStoreException", e);
        }

        return null;
    }

    /*
     * Search for a private key entry in the KeyStore with the same Subject Key Identifier
     */
    private PrivateKey resolveX509SKI(XMLX509SKI x509SKI) throws XMLSecurityException, KeyStoreException {
        LOG.debug("Can I resolve X509SKI?");

        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (keyStore.isKeyEntry(alias)) {

                Certificate cert = keyStore.getCertificate(alias);
                if (cert instanceof X509Certificate) {
                    XMLX509SKI certSKI = new XMLX509SKI(x509SKI.getDocument(), (X509Certificate) cert);

                    if (certSKI.equals(x509SKI)) {
                        LOG.debug("match !!! ");

                        try {
                            Key key = keyStore.getKey(alias, password);
                            if (key instanceof PrivateKey) {
                                return (PrivateKey) key;
                            }
                        } catch (Exception e) {
                            LOG.debug("Cannot recover the key", e);
                            // Keep searching
                        }
                    }
                }
            }
        }

        return null;
    }

    /*
     * Search for a private key entry in the KeyStore with the same Issuer/Serial Number pair.
     */
    private PrivateKey resolveX509IssuerSerial(XMLX509IssuerSerial x509Serial) throws KeyStoreException {
        LOG.debug("Can I resolve X509IssuerSerial?");

        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (keyStore.isKeyEntry(alias)) {

                Certificate cert = keyStore.getCertificate(alias);
                if (cert instanceof X509Certificate) {
                    XMLX509IssuerSerial certSerial =
                        new XMLX509IssuerSerial(x509Serial.getDocument(), (X509Certificate) cert);

                    if (certSerial.equals(x509Serial)) {
                        LOG.debug("match !!! ");

                        try {
                            Key key = keyStore.getKey(alias, password);
                            if (key instanceof PrivateKey) {
                                return (PrivateKey) key;
                            }
                        } catch (Exception e) {
                            LOG.debug("Cannot recover the key", e);
                            // Keep searching
                        }
                    }
                }
            }
        }

        return null;
    }

    /*
     * Search for a private key entry in the KeyStore with the same Subject Name.
     */
    private PrivateKey resolveX509SubjectName(XMLX509SubjectName x509SubjectName) throws KeyStoreException {
        LOG.debug("Can I resolve X509SubjectName?");

        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (keyStore.isKeyEntry(alias)) {

                Certificate cert = keyStore.getCertificate(alias);
                if (cert instanceof X509Certificate) {
                    XMLX509SubjectName certSN =
                        new XMLX509SubjectName(x509SubjectName.getDocument(), (X509Certificate) cert);

                    if (certSN.equals(x509SubjectName)) {
                        LOG.debug("match !!! ");

                        try {
                            Key key = keyStore.getKey(alias, password);
                            if (key instanceof PrivateKey) {
                                return (PrivateKey) key;
                            }
                        } catch (Exception e) {
                            LOG.debug("Cannot recover the key", e);
                            // Keep searching
                        }
                    }
                }
            }
        }

        return null;
    }

    /*
     * Search for a private key entry in the KeyStore with the same Certificate.
     */
    private PrivateKey resolveX509Certificate(
        XMLX509Certificate x509Cert
    ) throws XMLSecurityException, KeyStoreException {
        LOG.debug("Can I resolve X509Certificate?");
        byte[] x509CertBytes = x509Cert.getCertificateBytes();

        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (keyStore.isKeyEntry(alias)) {

                Certificate cert = keyStore.getCertificate(alias);
                if (cert instanceof X509Certificate) {
                    byte[] certBytes = null;

                    try {
                        certBytes = cert.getEncoded();
                    } catch (CertificateEncodingException e1) {
                        LOG.debug("Cannot recover the key", e1);
                    }

                    if (certBytes != null && Arrays.equals(certBytes, x509CertBytes)) {
                        LOG.debug("match !!! ");

                        try {
                            Key key = keyStore.getKey(alias, password);
                            if (key instanceof PrivateKey) {
                                return (PrivateKey) key;
                            }
                        }
                        catch (Exception e) {
                            LOG.debug("Cannot recover the key", e);
                            // Keep searching
                        }
                    }
                }
            }
        }

        return null;
    }
}
