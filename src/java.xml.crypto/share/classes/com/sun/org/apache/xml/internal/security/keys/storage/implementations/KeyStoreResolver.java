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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage.implementations;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.java.util.java.util.java.util.List;
import java.util.NoSuchElementException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverSpi;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Makes the Certificates from a JAVA {@link KeyStore} object available to the
 * {@link com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver}.
 */
public class KeyStoreResolver extends StorageResolverSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(KeyStoreResolver.class);

    /** Field keyStore */
    private final KeyStore keyStore;

    /**
     * Constructor KeyStoreResolver
     *
     * @param keyStore is the keystore which contains the Certificates
     * @throws StorageResolverException
     */
    public KeyStoreResolver(KeyStore keyStore) throws StorageResolverException {
        this.keyStore = keyStore;
        // Do a quick check on the keystore
        try {
            keyStore.aliases();
        } catch (KeyStoreException ex) {
            throw new StorageResolverException(ex);
        }
    }

    /** {@inheritDoc} */
    public Iterator<Certificate> getIterator() {
        return new KeyStoreIterator(this.keyStore);
    }

    /**
     * Class KeyStoreIterator
     */
    static class KeyStoreIterator implements Iterator<Certificate> {

        private final List<Certificate> certs;

        private int i;

        /**
         * Constructor KeyStoreIterator
         *
         * @param keyStore
         */
        public KeyStoreIterator(KeyStore keyStore) {

            List<Certificate> tmpCerts = new ArrayList<>();
            try {
                Enumeration<String> aliases = keyStore.aliases();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    Certificate cert = keyStore.getCertificate(alias);
                    if (cert != null) {
                        tmpCerts.add(cert);
                    }
                }
            } catch (KeyStoreException ex) {
                LOG.debug("Error reading certificates: {}", ex.getMessage());
            }

            certs = Collections.unmodifiableList(tmpCerts);
            this.i = 0;
        }

        /** {@inheritDoc} */
        public boolean hasNext() {
            return this.i < this.certs.size();
        }

        /** {@inheritDoc} */
        public Certificate next() {
            if (hasNext()) {
                return this.certs.get(this.i++);
            }

            throw new NoSuchElementException();
        }

        /**
         * Method remove
         */
        public void remove() {
            throw new UnsupportedOperationException("Can't remove keys from KeyStore");
        }

    }

}
