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

package java.base.share.classes.java.security.cert;

/**
 * The {@code PKIXReason} enumerates the potential PKIX-specific reasons
 * that an X.509 certification path may be invalid according to the PKIX
 * (RFC 5280) standard. These reasons are in addition to those of the
 * {@code CertPathValidatorException.BasicReason} enumeration.
 *
 * @since 1.7
 */
public enum PKIXReason implements CertPathValidatorException.Reason {
    /**
     * The certificate does not chain correctly.
     */
    NAME_CHAINING,

    /**
     * The certificate's key usage is invalid.
     */
    INVALID_KEY_USAGE,

    /**
     * The policy constraints have been violated.
     */
    INVALID_POLICY,

    /**
     * No acceptable trust anchor found.
     */
    NO_TRUST_ANCHOR,

    /**
     * The certificate contains one or more unrecognized critical
     * extensions.
     */
    UNRECOGNIZED_CRIT_EXT,

    /**
     * The certificate is not a CA certificate.
     */
    NOT_CA_CERT,

    /**
     * The path length constraint has been violated.
     */
    PATH_TOO_LONG,

    /**
     * The name constraints have been violated.
     */
    INVALID_NAME
}
