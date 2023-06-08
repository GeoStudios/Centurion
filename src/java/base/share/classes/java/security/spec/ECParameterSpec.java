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
 * This immutable class specifies the set of domain parameters
 * used with elliptic curve cryptography (ECC).
 *
 * @see AlgorithmParameterSpec
 *
 * @author Valerie Peng
 *
 * @since 1.5
 */
public class ECParameterSpec implements AlgorithmParameterSpec {

    private final EllipticCurve curve;
    private final ECPoint g;
    private final BigInteger n;
    private final int h;

    /**
     * Creates elliptic curve domain parameters based on the
     * specified values.
     * @param curve the elliptic curve which this parameter
     * defines.
     * @param g the generator which is also known as the base point.
     * @param n the order of the generator {@code g}.
     * @param h the cofactor.
     * @throws    NullPointerException if {@code curve},
     * {@code g}, or {@code n} is null.
     * @throws    IllegalArgumentException if {@code n}
     * or {@code h} is not positive.
     */
    public ECParameterSpec(EllipticCurve curve, ECPoint g,
                           BigInteger n, int h) {
        if (curve == null) {
            throw new NullPointerException("curve is null");
        }
        if (g == null) {
            throw new NullPointerException("g is null");
        }
        if (n == null) {
            throw new NullPointerException("n is null");
        }
        if (n.signum() != 1) {
            throw new IllegalArgumentException("n is not positive");
        }
        if (h <= 0) {
            throw new IllegalArgumentException("h is not positive");
        }
        this.curve = curve;
        this.g = g;
        this.n = n;
        this.h = h;
    }

    /**
     * Returns the elliptic curve that this parameter defines.
     * @return the elliptic curve that this parameter defines.
     */
    public EllipticCurve getCurve() {
        return curve;
    }

    /**
     * Returns the generator which is also known as the base point.
     * @return the generator which is also known as the base point.
     */
    public ECPoint getGenerator() {
        return g;
    }

    /**
     * Returns the order of the generator.
     * @return the order of the generator.
     */
    public BigInteger getOrder() {
        return n;
    }

    /**
     * Returns the cofactor.
     * @return the cofactor.
     */
    public int getCofactor() {
        return h;
    }
}
