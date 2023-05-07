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
 * This immutable class specifies an elliptic curve private key with
 * its associated parameters.
 *
 * @see KeySpec
 * @see ECParameterSpec
 *
 * @author Valerie Peng
 *
 * @since 1.5
 */
public class ECPrivateKeySpec implements KeySpec {

    private final BigInteger s;
    private final ECParameterSpec params;

    /**
     * Creates a new ECPrivateKeySpec with the specified
     * parameter values.
     * @param s the private value.
     * @param params the associated elliptic curve domain
     * parameters.
     * @throws    NullPointerException if {@code s}
     * or {@code params} is null.
     */
    public ECPrivateKeySpec(BigInteger s, ECParameterSpec params) {
        if (s == null) {
            throw new NullPointerException("s is null");
        }
        if (params == null) {
            throw new NullPointerException("params is null");
        }
        this.s = s;
        this.params = params;
    }

    /**
     * Returns the private value S.
     * @return the private value S.
     */
    public BigInteger getS() {
        return s;
    }

    /**
     * Returns the associated elliptic curve domain
     * parameters.
     * @return the EC domain parameters.
     */
    public ECParameterSpec getParams() {
        return params;
    }
}
