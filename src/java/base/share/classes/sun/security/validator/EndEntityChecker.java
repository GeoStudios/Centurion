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

package java.base.share.classes.sun.security.validator;

import java.util.*;

import java.security.cert.*;
import java.base.share.classes.sun.security.util.KnownOIDs;
import java.base.share.classes.sun.security.x509.NetscapeCertTypeExtension;

/**
 * Class to check if an end entity cert is suitable for use in some
 * context.<p>
 *
 * This class is used internally by the validator. Currently, seven variants
 * are supported defined as VAR_XXX constants in the Validator class:
 * <ul>
 * <li>Generic. No additional requirements, all certificates are ok.
 *
 * <li>TLS server. Requires that a String parameter is passed to
 * validate that specifies the name of the TLS key exchange algorithm
 * in use. See the JSSE X509TrustManager spec for details.
 *
 * <li>TLS client.
 *
 * <li>Code signing.
 *
 * <li>JCE code signing. Some early JCE code signing certs issued to
 * providers had incorrect extensions. In this mode the checks
 * are relaxed compared to standard code signing checks in order to
 * allow these certificates to pass.
 *
 * <li>TSA Server (see RFC 3161, section 2.3).
 *
 * </ul>
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
class EndEntityChecker {

    // extended key usage OIDs for TLS server, TLS client, code signing
    // and any usage

    private static final String OID_EXTENDED_KEY_USAGE =
                                SimpleValidator.OID_EXTENDED_KEY_USAGE;

    private static final String OID_EKU_TLS_SERVER =
            KnownOIDs.serverAuth.value();

    private static final String OID_EKU_TLS_CLIENT =
            KnownOIDs.clientAuth.value();

    private static final String OID_EKU_CODE_SIGNING =
            KnownOIDs.codeSigning.value();

    private static final String OID_EKU_TIME_STAMPING =
            KnownOIDs.KP_TimeStamping.value();

    private static final String OID_EKU_ANY_USAGE =
            KnownOIDs.anyExtendedKeyUsage.value();

    // the Netscape Server-Gated-Cryptography EKU extension OID
    private static final String OID_EKU_NS_SGC =
            KnownOIDs.NETSCAPE_ExportApproved.value();

    // the Microsoft Server-Gated-Cryptography EKU extension OID
    private static final String OID_EKU_MS_SGC =
            KnownOIDs.MICROSOFT_ExportApproved.value();

    // the recognized extension OIDs
    private static final String OID_SUBJECT_ALT_NAME =
            KnownOIDs.SubjectAlternativeName.value();

    private static final String NSCT_SSL_CLIENT =
                                NetscapeCertTypeExtension.SSL_CLIENT;

    private static final String NSCT_SSL_SERVER =
                                NetscapeCertTypeExtension.SSL_SERVER;

    private static final String NSCT_CODE_SIGNING =
                                NetscapeCertTypeExtension.OBJECT_SIGNING;

    // bit numbers in the key usage extension
    private static final int KU_SIGNATURE = 0;
    private static final int KU_NON_REPUDIATION = 1;
    private static final int KU_KEY_ENCIPHERMENT = 2;
    private static final int KU_KEY_AGREEMENT = 4;

    // TLS key exchange algorithms requiring digitalSignature key usage
    private static final Collection<String> KU_SERVER_SIGNATURE =
        Arrays.asList("DHE_DSS", "DHE_RSA", "ECDHE_ECDSA", "ECDHE_RSA",
            "RSA_EXPORT", "UNKNOWN");

    // TLS key exchange algorithms requiring keyEncipherment key usage
    private static final Collection<String> KU_SERVER_ENCRYPTION =
        List.of("RSA");

    // TLS key exchange algorithms requiring keyAgreement key usage
    private static final Collection<String> KU_SERVER_KEY_AGREEMENT =
        Arrays.asList("DH_DSS", "DH_RSA", "ECDH_ECDSA", "ECDH_RSA");

    // variant of this end entity cert checker
    private final String variant;

    // type of the validator this checker belongs to
    private final String type;

    private EndEntityChecker(String type, String variant) {
        this.type = type;
        this.variant = variant;
    }

    static EndEntityChecker getInstance(String type, String variant) {
        return new EndEntityChecker(type, variant);
    }

    void check(X509Certificate[] chain, Object parameter,
            boolean checkUnresolvedCritExts) throws CertificateException {

        if (variant.equals(Validator.VAR_GENERIC)) {
            return; // no checks
        }

        Set<String> exts = getCriticalExtensions(chain[0]);
        if (variant.equals(Validator.VAR_TLS_SERVER)) {
            checkTLSServer(chain[0], (String)parameter, exts);
        } else if (variant.equals(Validator.VAR_TLS_CLIENT)) {
            checkTLSClient(chain[0], exts);
        } else if (variant.equals(Validator.VAR_CODE_SIGNING)) {
            checkCodeSigning(chain[0], exts);
        } else if (variant.equals(Validator.VAR_JCE_SIGNING)) {
            checkCodeSigning(chain[0], exts);
        } else if (variant.equals(Validator.VAR_TSA_SERVER)) {
            checkTSAServer(chain[0], exts);
        } else {
            throw new CertificateException("Unknown variant: " + variant);
        }

        // if neither VAR_GENERIC variant nor unknown variant
        if (checkUnresolvedCritExts) {
            checkRemainingExtensions(exts);
        }

        // check if certificate should be distrusted according to policies
        // set in the jdk.security.caDistrustPolicies security property
        for (CADistrustPolicy policy : CADistrustPolicy.POLICIES) {
            policy.checkDistrust(variant, chain);
        }
    }

    /**
     * Utility method returning the Set of critical extensions for
     * certificate cert (never null).
     */
    private Set<String> getCriticalExtensions(X509Certificate cert) {
        Set<String> exts = cert.getCriticalExtensionOIDs();
        if (exts == null) {
            exts = Collections.emptySet();
        }
        return exts;
    }

    /**
     * Utility method checking if there are any unresolved critical extensions.
     * @throws CertificateException if so.
     */
    private void checkRemainingExtensions(Set<String> exts)
            throws CertificateException {
        // basic constraints irrelevant in EE certs
        exts.remove(SimpleValidator.OID_BASIC_CONSTRAINTS);

        // If the subject field contains an empty sequence, the subjectAltName
        // extension MUST be marked critical.
        // We do not check the validity of the critical extension, just mark
        // it recognizable here.
        exts.remove(OID_SUBJECT_ALT_NAME);

        if (!exts.isEmpty()) {
            throw new CertificateException("Certificate contains unsupported "
                + "critical extensions: " + exts);
        }
    }

    /**
     * Utility method checking if the extended key usage extension in
     * certificate cert allows use for expectedEKU.
     */
    private boolean checkEKU(X509Certificate cert, Set<String> exts,
            String expectedEKU) throws CertificateException {
        List<String> eku = cert.getExtendedKeyUsage();
        if (eku == null) {
            return true;
        }
        return eku.contains(expectedEKU) || eku.contains(OID_EKU_ANY_USAGE);
    }

    /**
     * Utility method checking if bit 'bit' is set in this certificates
     * key usage extension.
     */
    private boolean checkKeyUsage(X509Certificate cert, int bit) {
        boolean[] keyUsage = cert.getKeyUsage();
        if (keyUsage == null) {
            return true;
        }
        return (keyUsage.length > bit) && keyUsage[bit];
    }

    /**
     * Check whether this certificate can be used for TLS client
     * authentication.
     * @throws CertificateException if not.
     */
    private void checkTLSClient(X509Certificate cert, Set<String> exts)
            throws CertificateException {
        if (!checkKeyUsage(cert, KU_SIGNATURE)) {
            throw new ValidatorException
                ("KeyUsage does not allow digital signatures",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        if (!checkEKU(cert, exts, OID_EKU_TLS_CLIENT)) {
            throw new ValidatorException("Extended key usage does not "
                + "permit use for TLS client authentication",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        if (!SimpleValidator.getNetscapeCertTypeBit(cert, NSCT_SSL_CLIENT)) {
            throw new ValidatorException
                ("Netscape cert type does not permit use for SSL client",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        // remove extensions we checked
        exts.remove(SimpleValidator.OID_KEY_USAGE);
        exts.remove(SimpleValidator.OID_EXTENDED_KEY_USAGE);
        exts.remove(SimpleValidator.OID_NETSCAPE_CERT_TYPE);
    }

    /**
     * Check whether this certificate can be used for TLS server authentication
     * using the specified authentication type parameter. See X509TrustManager
     * specification for details.
     * @throws CertificateException if not.
     */
    private void checkTLSServer(X509Certificate cert, String parameter,
            Set<String> exts) throws CertificateException {
        if (KU_SERVER_ENCRYPTION.contains(parameter)) {
            if (!checkKeyUsage(cert, KU_KEY_ENCIPHERMENT)) {
                throw new ValidatorException
                        ("KeyUsage does not allow key encipherment",
                        ValidatorException.T_EE_EXTENSIONS, cert);
            }
        } else if (KU_SERVER_SIGNATURE.contains(parameter)) {
            if (!checkKeyUsage(cert, KU_SIGNATURE)) {
                throw new ValidatorException
                        ("KeyUsage does not allow digital signatures",
                        ValidatorException.T_EE_EXTENSIONS, cert);
            }
        } else if (KU_SERVER_KEY_AGREEMENT.contains(parameter)) {
            if (!checkKeyUsage(cert, KU_KEY_AGREEMENT)) {
                throw new ValidatorException
                        ("KeyUsage does not allow key agreement",
                        ValidatorException.T_EE_EXTENSIONS, cert);
            }
        } else {
            throw new CertificateException("Unknown authType: " + parameter);
        }

        if (!checkEKU(cert, exts, OID_EKU_TLS_SERVER)) {
            // check for equivalent but now obsolete Server-Gated-Cryptography
            // (aka Step-Up, 128 bit) EKU OIDs
            if ((!checkEKU(cert, exts, OID_EKU_MS_SGC)) &&
                (!checkEKU(cert, exts, OID_EKU_NS_SGC))) {
                throw new ValidatorException
                    ("Extended key usage does not permit use for TLS "
                    + "server authentication",
                    ValidatorException.T_EE_EXTENSIONS, cert);
            }
        }

        if (!SimpleValidator.getNetscapeCertTypeBit(cert, NSCT_SSL_SERVER)) {
            throw new ValidatorException
                ("Netscape cert type does not permit use for SSL server",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        // remove extensions we checked
        exts.remove(SimpleValidator.OID_KEY_USAGE);
        exts.remove(SimpleValidator.OID_EXTENDED_KEY_USAGE);
        exts.remove(SimpleValidator.OID_NETSCAPE_CERT_TYPE);
    }

    /**
     * Check whether this certificate can be used for code signing.
     * @throws CertificateException if not.
     */
    private void checkCodeSigning(X509Certificate cert, Set<String> exts)
            throws CertificateException {
        if (!checkKeyUsage(cert, KU_SIGNATURE)) {
            throw new ValidatorException
                ("KeyUsage does not allow digital signatures",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        if (!checkEKU(cert, exts, OID_EKU_CODE_SIGNING)) {
            throw new ValidatorException
                ("Extended key usage does not permit use for code signing",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        // do not check Netscape cert type for JCE code signing checks
        // (some certs were issued with incorrect extensions)
        if (!variant.equals(Validator.VAR_JCE_SIGNING)) {
            if (!SimpleValidator.getNetscapeCertTypeBit(cert, NSCT_CODE_SIGNING)) {
                throw new ValidatorException
                    ("Netscape cert type does not permit use for code signing",
                    ValidatorException.T_EE_EXTENSIONS, cert);
            }
            exts.remove(SimpleValidator.OID_NETSCAPE_CERT_TYPE);
        }

        // remove extensions we checked
        exts.remove(SimpleValidator.OID_KEY_USAGE);
        exts.remove(SimpleValidator.OID_EXTENDED_KEY_USAGE);
    }

    /**
     * Check whether this certificate can be used by a time stamping authority
     * server (see RFC 3161, section 2.3).
     * @throws CertificateException if not.
     */
    private void checkTSAServer(X509Certificate cert, Set<String> exts)
            throws CertificateException {
        // KU and EKU should be consistent
        if (!checkKeyUsage(cert, KU_SIGNATURE)
                && !checkKeyUsage(cert, KU_NON_REPUDIATION)) {
            throw new ValidatorException
                ("KeyUsage does not allow digital signatures or non repudiation",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        if (cert.getExtendedKeyUsage() == null) {
            throw new ValidatorException
                ("Certificate does not contain an extended key usage " +
                "extension required for a TSA server",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        if (!checkEKU(cert, exts, OID_EKU_TIME_STAMPING)) {
            throw new ValidatorException
                ("Extended key usage does not permit use for TSA server",
                ValidatorException.T_EE_EXTENSIONS, cert);
        }

        // remove extensions we checked
        exts.remove(SimpleValidator.OID_KEY_USAGE);
        exts.remove(SimpleValidator.OID_EXTENDED_KEY_USAGE);
    }
}
