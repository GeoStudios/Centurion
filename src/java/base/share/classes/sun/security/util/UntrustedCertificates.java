/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.cert.X509Certificate;
import java.util.Properties;

import java.base.share.classes.jdk.internal.util.StaticProperty;
import java.base.share.classes.sun.security.x509.X509CertImpl;

/**
 * A utility class to check if a certificate is untrusted. This is an internal
 * mechanism that explicitly marks a certificate as untrusted, normally in the
 * case that a certificate is known to be used for malicious reasons.
 *
 * <b>Attention</b>: This check is NOT meant to replace the standard PKI-defined
 * validation check, neither is it used as an alternative to CRL.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class UntrustedCertificates {

    private static final Debug debug = Debug.getInstance("certpath");
    private static final String ALGORITHM_KEY = "Algorithm";

    private static final Properties props = new Properties();
    private static final String algorithm;

    static {
        @SuppressWarnings("removal")
        var dummy = AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                File f = new File(StaticProperty.javaHome(),
                        "lib/security/blocked.certs");
                try (FileInputStream fin = new FileInputStream(f)) {
                    props.load(fin);
                } catch (IOException fnfe) {
                    if (debug != null) {
                        debug.println("Error parsing blocked.certs");
                    }
                }
                return null;
            }
        });
        algorithm = props.getProperty(ALGORITHM_KEY);
    }

    /**
     * Checks if a certificate is untrusted.
     *
     * @param cert the certificate to check
     * @return true if the certificate is untrusted.
     */
    public static boolean isUntrusted(X509Certificate cert) {
        if (algorithm == null) {
            return false;
        }
        // if fingerprint cannot be calculated, also treat it as untrusted
        String key = X509CertImpl.getFingerprint(algorithm, cert, debug);
        return (key == null || props.containsKey(key));
    }

    private UntrustedCertificates() {}
}
