/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.validator;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Enumeration;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;

/**
 * Collection of static utility methods related to trust anchor KeyStores.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class TrustStoreUtil {

    private TrustStoreUtil() {
        // empty
    }

    /**
     * Return an unmodifiable Set with all trusted X509Certificates contained
     * in the specified KeyStore.
     */
    public static Set<X509Certificate> getTrustedCerts(KeyStore ks) {
        Set<X509Certificate> set = new HashSet<>();
        try {
            for (Enumeration<String> e = ks.aliases(); e.hasMoreElements(); ) {
                String alias = e.nextElement();
                if (ks.isCertificateEntry(alias)) {
                    Certificate cert = ks.getCertificate(alias);
                    if (cert instanceof X509Certificate) {
                        set.add((X509Certificate)cert);
                    }
                } else if (ks.isKeyEntry(alias)) {
                    Certificate[] certs = ks.getCertificateChain(alias);
                    if ((certs != null) && (certs.length > 0) &&
                            (certs[0] instanceof X509Certificate)) {
                        set.add((X509Certificate)certs[0]);
                    }
                }
            }
        } catch (KeyStoreException e) {
            // ignore
            //
            // This should be rare, but better to log this in the future.
        }

        return Collections.unmodifiableSet(set);
    }
}
