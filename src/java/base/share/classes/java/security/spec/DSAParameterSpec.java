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
 * This class specifies the set of parameters used with the DSA algorithm.
 *
 * @author Jan Luehe
 *
 *
 * @see AlgorithmParameterSpec
 *
 * @since 1.2
 */

public class DSAParameterSpec implements AlgorithmParameterSpec,
java.security.interfaces.DSAParams {

    BigInteger p;
    BigInteger q;
    BigInteger g;

    /**
     * Creates a new DSAParameterSpec with the specified parameter values.
     *
     * @param p the prime.
     *
     * @param q the sub-prime.
     *
     * @param g the base.
     */
    public DSAParameterSpec(BigInteger p, BigInteger q, BigInteger g) {
        this.p = p;
        this.q = q;
        this.g = g;
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
