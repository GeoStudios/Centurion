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
package com.sun.org.apache.xml.internal.security.keys.storage.implementations;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverSpi;

/**
 * This {@link StorageResolverSpi} makes a single {@link X509Certificate}
 * available to the {@link com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver}.
 */
public class SingleCertificateResolver extends StorageResolverSpi {

    /** Field certificate */
    private final X509Certificate certificate;

    /**
     * @param x509cert the single {@link X509Certificate}
     */
    public SingleCertificateResolver(X509Certificate x509cert) {
        this.certificate = x509cert;
    }

    /** {@inheritDoc} */
    public Iterator<Certificate> getIterator() {
        return new InternalIterator(this.certificate);
    }

    /**
     * Class InternalIterator
     */
    static class InternalIterator implements Iterator<Certificate> {

        /** Field alreadyReturned */
        private boolean alreadyReturned;

        /** Field certificate */
        private final X509Certificate certificate;

        /**
         * Constructor InternalIterator
         *
         * @param x509cert
         */
        public InternalIterator(X509Certificate x509cert) {
            this.certificate = x509cert;
        }

        /** {@inheritDoc} */
        public boolean hasNext() {
            return !this.alreadyReturned;
        }

        /** {@inheritDoc} */
        public Certificate next() {
            if (this.alreadyReturned) {
                throw new NoSuchElementException();
            }
            this.alreadyReturned = true;
            return this.certificate;
        }

        /**
         * Method remove
         */
        public void remove() {
            throw new UnsupportedOperationException("Can't remove keys from KeyStore");
        }
    }
}
