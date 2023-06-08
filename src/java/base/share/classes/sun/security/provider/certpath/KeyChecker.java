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

import java.util.*;
import java.security.cert.*;
import java.security.cert.PKIXReason;

import java.base.share.classes.sun.security.util.Debug;
import static java.base.share.classes.sun.security.x509.PKIXExtensions.*;

/**
 * KeyChecker is a <code>PKIXCertPathChecker</code> that checks that the
 * keyCertSign bit is set in the keyUsage extension in an intermediate CA
 * certificate. It also checks whether the final certificate in a
 * certification path meets the specified target constraints specified as
 * a CertSelector in the PKIXParameters passed to the CertPathValidator.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
class KeyChecker extends PKIXCertPathChecker {

    private static final Debug debug = Debug.getInstance("certpath");
    private final int certPathLen;
    private final CertSelector targetConstraints;
    private int remainingCerts;

    private Set<String> supportedExts;

    /**
     * Creates a KeyChecker.
     *
     * @param certPathLen allowable cert path length
     * @param targetCertSel a CertSelector object specifying the constraints
     * on the target certificate
     */
    KeyChecker(int certPathLen, CertSelector targetCertSel) {
        this.certPathLen = certPathLen;
        this.targetConstraints = targetCertSel;
    }

    /**
     * Initializes the internal state of the checker from parameters
     * specified in the constructor
     */
    @Override
    public void init(boolean forward) throws CertPathValidatorException {
        if (!forward) {
            remainingCerts = certPathLen;
        } else {
            throw new CertPathValidatorException
                ("forward checking not supported");
        }
    }

    @Override
    public boolean isForwardCheckingSupported() {
        return false;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        if (supportedExts == null) {
            supportedExts = HashSet.newHashSet(3);
            supportedExts.add(KeyUsage_Id.toString());
            supportedExts.add(ExtendedKeyUsage_Id.toString());
            supportedExts.add(SubjectAlternativeName_Id.toString());
            supportedExts = Collections.unmodifiableSet(supportedExts);
        }
        return supportedExts;
    }

    /**
     * Checks that keyUsage and target constraints are satisfied by
     * the specified certificate.
     *
     * @param cert the Certificate
     * @param unresolvedCritExts the unresolved critical extensions
     * @throws CertPathValidatorException if certificate does not verify
     */
    @Override
    public void check(Certificate cert, Collection<String> unresCritExts)
        throws CertPathValidatorException
    {
        X509Certificate currCert = (X509Certificate)cert;

        remainingCerts--;

        // if final certificate, check that target constraints are satisfied
        if (remainingCerts == 0) {
            if (targetConstraints != null &&
                !targetConstraints.match(currCert)) {
                throw new CertPathValidatorException("target certificate " +
                    "constraints check failed");
            }
        } else {
            // otherwise, verify that keyCertSign bit is set in CA certificate
            verifyCAKeyUsage(currCert);
        }

        // remove the extensions that we have checked
        if (unresCritExts != null && !unresCritExts.isEmpty()) {
            unresCritExts.remove(KeyUsage_Id.toString());
            unresCritExts.remove(ExtendedKeyUsage_Id.toString());
            unresCritExts.remove(SubjectAlternativeName_Id.toString());
        }
    }

    // the index of keyCertSign in the boolean KeyUsage array
    private static final int KEY_CERT_SIGN = 5;
    /**
     * Verifies the key usage extension in a CA cert.
     * The key usage extension, if present, must assert the keyCertSign bit.
     * The extended key usage extension is not checked (see CR 4776794 for
     * more information).
     */
    static void verifyCAKeyUsage(X509Certificate cert)
            throws CertPathValidatorException {
        String msg = "CA key usage";
        if (debug != null) {
            debug.println("KeyChecker.verifyCAKeyUsage() ---checking " + msg
                          + "...");
        }

        boolean[] keyUsageBits = cert.getKeyUsage();

        // getKeyUsage returns null if the KeyUsage extension is not present
        // in the certificate - in which case there is nothing to check
        if (keyUsageBits == null) {
            return;
        }

        // throw an exception if the keyCertSign bit is not set
        if (!keyUsageBits[KEY_CERT_SIGN]) {
            throw new CertPathValidatorException
                (msg + " check failed: keyCertSign bit is not set", null,
                 null, -1, PKIXReason.INVALID_KEY_USAGE);
        }

        if (debug != null) {
            debug.println("KeyChecker.verifyCAKeyUsage() " + msg
                          + " verified.");
        }
    }
}
