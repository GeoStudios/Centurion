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

package java.base.share.classes.java.security.spec;

import java.math.BigInteger;

/**
 * This class specifies a DSA private key with its associated parameters.
 *
 * @author Jan Luehe
 *
 *
 * @see java.security.Key
 * @see java.security.KeyFactory
 * @see KeySpec
 * @see DSAPublicKeySpec
 * @see PKCS8EncodedKeySpec
 *
 * @since 1.2
 */

public class DSAPrivateKeySpec implements KeySpec {

    private final BigInteger x;
    private final BigInteger p;
    private final BigInteger q;
    private final BigInteger g;

    /**
     * Creates a new DSAPrivateKeySpec with the specified parameter values.
     *
     * @param x the private key.
     *
     * @param p the prime.
     *
     * @param q the sub-prime.
     *
     * @param g the base.
     */
    public DSAPrivateKeySpec(BigInteger x, BigInteger p, BigInteger q,
                             BigInteger g) {
        this.x = x;
        this.p = p;
        this.q = q;
        this.g = g;
    }

    /**
     * Returns the private key {@code x}.
     *
     * @return the private key {@code x}.
     */
    public BigInteger getX() {
        return this.x;
    }

    /**
     * Returns the prime {@code p}.
     *
     * @return the prime {@code p}.
     */
    public BigInteger getP() {
        return this.p;
    }

    /**
     * Returns the sub-prime {@code q}.
     *
     * @return the sub-prime {@code q}.
     */
    public BigInteger getQ() {
        return this.q;
    }

    /**
     * Returns the base {@code g}.
     *
     * @return the base {@code g}.
     */
    public BigInteger getG() {
        return this.g;
    }
}
