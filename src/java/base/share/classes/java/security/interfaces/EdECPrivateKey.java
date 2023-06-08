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
package java.base.share.classes.java.security.interfaces;

import java.security.PrivateKey;
import java.util.Optional;

/**
 * An interface for an elliptic curve private key as defined by
 * <a href="https://tools.ietf.org/html/rfc8032">RFC 8032: Edwards-Curve
 * Digital Signature Algorithm (EdDSA)</a>. These keys are distinct from the
 * keys represented by {@code ECPrivateKey}, and they are intended for use
 * with algorithms based on RFC 8032 such as the EdDSA {@code Signature}
 * algorithm.
 * <p>
 * An Edwards-Curve private key is a bit string. This interface only supports bit
 * string lengths that are a multiple of 8, and the key is represented using
 * a byte array.
 *
 * @since 15
 */
public interface EdECPrivateKey extends EdECKey, PrivateKey {

    /**
     * Get a copy of the byte array representing the private key. This method
     * may return an empty {@code Optional} if the implementation is not
     * willing to produce the private key value.
     *
     * @return an {@code Optional} containing the private key byte array.
     * If the key is not available, then an empty {@code Optional}.
     */
    Optional<byte[]> getBytes();
}
