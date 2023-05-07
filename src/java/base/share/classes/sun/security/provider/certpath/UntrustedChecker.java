/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.sun.security.provider.certpath;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.cert.CertPathValidatorException;
import java.security.cert.PKIXCertPathChecker;
import java.util.Set;
import java.util.Collection;
import java.base.share.classes.sun.security.util.Debug;
import java.base.share.classes.sun.security.util.UntrustedCertificates;

/**
 * A <code>PKIXCertPathChecker</code> implementation to check whether a
 * specified certificate is distrusted.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 * @see PKIXCertPathChecker
 * @see PKIXParameters
 */
public final class UntrustedChecker extends PKIXCertPathChecker {

    private static final Debug debug = Debug.getInstance("certpath");

    /**
     * Default Constructor
     */
    public UntrustedChecker() {
        // blank
    }

    @Override
    public void init(boolean forward) throws CertPathValidatorException {
        // Note that this class supports both forward and reverse modes.
    }

    @Override
    public boolean isForwardCheckingSupported() {
        // Note that this class supports both forward and reverse modes.
        return true;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return null;
    }

    @Override
    public void check(Certificate cert,
            Collection<String> unresolvedCritExts)
            throws CertPathValidatorException {

        X509Certificate currCert = (X509Certificate)cert;

        if (UntrustedCertificates.isUntrusted(currCert)) {
            if (debug != null) {
                debug.println("UntrustedChecker: untrusted certificate " +
                        currCert.getSubjectX500Principal());
            }

            throw new CertPathValidatorException(
                "Untrusted certificate: " + currCert.getSubjectX500Principal());
        }
    }
}

