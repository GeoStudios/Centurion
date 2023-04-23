/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/**
 * Provides classes and interfaces for key specifications and
 * algorithm parameter specifications.
 *
 * <p>A key specification is a transparent representation of the key
 * material that constitutes a key. A key may be specified in an
 * algorithm-specific way, or in an algorithm-independent encoding
 * format (such as ASN.1).  This package contains key specifications
 * for Diffie-Hellman public and private keys, as well as key
 * specifications for DES, Triple DES, and PBE secret keys.
 *
 * <p>An algorithm parameter specification is a transparent
 * representation of the sets of parameters used with an
 * algorithm. This package contains algorithm parameter specifications
 * for parameters used with the Diffie-Hellman, DES, Triple DES, PBE,
 * RC2 and RC5 algorithms.
 *
 *
 * <ul>
 * <li>PKCS #1: RSA Cryptography Specifications, Version 2.2 (RFC 8017)</li>
 * <li>PKCS #3: Diffie-Hellman Key-Agreement Standard, Version 1.4,
 * November 1993.</li>
 * <li>PKCS #5: Password-Based Encryption Standard, Version 1.5,
 * November 1993.</li>
 * <li>Federal Information Processing Standards Publication (FIPS PUB) 46-2:
 * Data Encryption Standard (DES) </li>
 * </ul>
 *
 * <h2>Related Documentation</h2>
 *
 * For documentation that includes information about algorithm
 * parameter and key specifications, please see:
 *
 * <ul>
 * <li>
 *    {@extLink security_guide_jca
 *      Java Cryptography Architecture (JCA) Reference Guide} </li>
 * <li>
 *    {@extLink security_guide_impl_provider
 *      How to Implement a Provider in the Java Cryptography Architecture}</li>
 * </ul>
 *
 * @since 1.4
 */
package javax.crypto.spec;
