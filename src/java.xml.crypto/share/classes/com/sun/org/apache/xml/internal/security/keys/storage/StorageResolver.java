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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage;


import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Iterator;
import java.util.java.util.java.util.java.util.List;
import java.util.NoSuchElementException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage.implementations.KeyStoreResolver;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage.implementations.SingleCertificateResolver;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * This class collects customized resolvers for Certificates.
 */
public class StorageResolver {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(StorageResolver.class);

    /** Field storageResolvers */
    private final List<StorageResolverSpi> storageResolvers = new ArrayList<>();

    /**
     * Constructor StorageResolver
     *
     * @param resolver
     */
    public StorageResolver(StorageResolverSpi resolver) {
        this.add(resolver);
    }

    /**
     * Constructor StorageResolver
     *
     * @param keyStore
     */
    public StorageResolver(KeyStore keyStore) {
        this.add(keyStore);
    }

    /**
     * Constructor StorageResolver
     *
     * @param x509certificate
     */
    public StorageResolver(X509Certificate x509certificate) {
        this.add(x509certificate);
    }

    /**
     * Method addResolver
     *
     * @param resolver
     */
    public void add(StorageResolverSpi resolver) {
        this.storageResolvers.add(resolver);
    }

    /**
     * Method addKeyStore
     *
     * @param keyStore
     */
    public void add(KeyStore keyStore) {
        try {
            this.add(new KeyStoreResolver(keyStore));
        } catch (StorageResolverException ex) {
            LOG.error("Could not add KeyStore because of: ", ex);
        }
    }

    /**
     * Method addCertificate
     *
     * @param x509certificate
     */
    public void add(X509Certificate x509certificate) {
        this.add(new SingleCertificateResolver(x509certificate));
    }

    /**
     * Method getIterator
     * @return the iterator for the resolvers.
     */
    public Iterator<Certificate> getIterator() {
        return new StorageResolverIterator(this.storageResolvers.iterator());
    }

    /**
     * Class StorageResolverIterator
     * This iterates over all the Certificates found in all the resolvers.
     */
    static class StorageResolverIterator implements Iterator<Certificate> {

        /** Field resolvers */
        private final Iterator<StorageResolverSpi> resolvers;

        /** Field currentResolver */
        private Iterator<Certificate> currentResolver;

        /**
         * Constructor StorageResolverIterator
         *
         * @param resolvers
         */
        public StorageResolverIterator(Iterator<StorageResolverSpi> resolvers) {
            this.resolvers = resolvers;
            currentResolver = findNextResolver();
        }

        /** {@inheritDoc} */
        public boolean hasNext() {
            if (currentResolver == null) {
                return false;
            }

            if (currentResolver.hasNext()) {
                return true;
            }

            currentResolver = findNextResolver();
            return currentResolver != null;
        }

        /** {@inheritDoc} */
        public Certificate next() {
            if (hasNext()) {
                return currentResolver.next();
            }

            throw new NoSuchElementException();
        }

        /**
         * Method remove
         */
        public void remove() {
            throw new UnsupportedOperationException("Can't remove keys from KeyStore");
        }

        // Find the next storage with at least one element and return its Iterator
        private Iterator<Certificate> findNextResolver() {
            while (resolvers.hasNext()) {
                StorageResolverSpi resolverSpi = resolvers.next();
                Iterator<Certificate> iter = resolverSpi.getIterator();
                if (iter.hasNext()) {
                    return iter;
                }
            }

            return null;
        }
    }
}
