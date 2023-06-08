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

package java.base.share.classes.javax.crypto.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/**
 * This class specifies the set of parameters used with the Diffie-Hellman
 * algorithm, as specified in PKCS #3: <i>Diffie-Hellman Key-Agreement
 * Standard</i>.
 *
 * <p>A central authority generates parameters and gives them to the two
 * entities seeking to generate a secret key. The parameters are a prime
 * <code>p</code>, a base <code>g</code>, and optionally the length
 * in bits of the private value, <code>l</code>.
 *
 * <p>It is possible that more than one instance of parameters may be
 * generated by a given central authority, and that there may be more than
 * one central authority. Indeed, each individual may be its own central
 * authority, with different entities having different parameters.
 *
 * <p>Note that this class does not perform any validation on specified
 * parameters. Thus, the specified values are returned directly even
 * if they are null.
 *
 * @author Jan Luehe
 *
 * @see javax.crypto.KeyAgreement
 * @since 1.4
 */
public class DHParameterSpec implements AlgorithmParameterSpec {

    // The prime modulus
    private final BigInteger p;

    // The base generator
    private final BigInteger g;

    // The size in bits of the random exponent (private value) (optional)
    private final int l;

    /**
     * Constructs a parameter set for Diffie-Hellman, using a prime modulus
     * <code>p</code> and a base generator <code>g</code>.
     *
     * @param p the prime modulus
     * @param g the base generator
     */
    public DHParameterSpec(BigInteger p, BigInteger g) {
        this.p = p;
        this.g = g;
        this.l = 0;
    }

    /**
     * Constructs a parameter set for Diffie-Hellman, using a prime modulus
     * <code>p</code>, a base generator <code>g</code>,
     * and the size in bits, <code>l</code>, of the random exponent
     * (private value).
     *
     * @param p the prime modulus
     * @param g the base generator
     * @param l the size in bits of the random exponent (private value)
     */
    public DHParameterSpec(BigInteger p, BigInteger g, int l) {
        this.p = p;
        this.g = g;
        this.l = l;
    }

    /**
     * Returns the prime modulus <code>p</code>.
     *
     * @return the prime modulus <code>p</code>
     */
    public BigInteger getP() {
        return this.p;
    }

    /**
     * Returns the base generator <code>g</code>.
     *
     * @return the base generator <code>g</code>
     */
    public BigInteger getG() {
        return this.g;
    }

    /**
     * Returns the size in bits, <code>l</code>, of the random exponent
     * (private value).
     *
     * @return the size in bits, <code>l</code>, of the random exponent
     * (private value), or 0 if this size has not been set
     */
    public int getL() {
        return this.l;
    }
}
