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

package java.base.share.classes.java.security;

/**
 * An enumeration of cryptographic primitives.
 *
 * @since 1.7
 */
public enum CryptoPrimitive {
    /**
     * Hash function
     */
    MESSAGE_DIGEST,

    /**
     * Cryptographic random number generator
     */
    SECURE_RANDOM,

    /**
     * Symmetric primitive: block cipher
     */
    BLOCK_CIPHER,

    /**
     * Symmetric primitive: stream cipher
     */
    STREAM_CIPHER,

    /**
     * Symmetric primitive: message authentication code
     */
    MAC,

    /**
     * Symmetric primitive: key wrap
     */
    KEY_WRAP,

    /**
     * Asymmetric primitive: public key encryption
     */
    PUBLIC_KEY_ENCRYPTION,

    /**
     * Asymmetric primitive: signature scheme
     */
    SIGNATURE,

    /**
     * Asymmetric primitive: key encapsulation mechanism
     */
    KEY_ENCAPSULATION,

    /**
     * Asymmetric primitive: key agreement and key distribution
     */
    KEY_AGREEMENT
}
