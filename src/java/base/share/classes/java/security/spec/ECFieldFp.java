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
 * This immutable class defines an elliptic curve (EC) prime
 * finite field.
 *
 * @see ECField
 *
 * @author Valerie Peng
 *
 * @since 1.5
 */
public class ECFieldFp implements ECField {

    private final BigInteger p;

    /**
     * Creates an elliptic curve prime finite field
     * with the specified prime {@code p}.
     * @param p the prime.
     * @throws    NullPointerException if {@code p} is null.
     * @throws    IllegalArgumentException if {@code p}
     * is not positive.
     */
    public ECFieldFp(BigInteger p) {
        if (p.signum() != 1) {
            throw new IllegalArgumentException("p is not positive");
        }
        this.p = p;
    }

    /**
     * Returns the field size in bits which is size of prime p
     * for this prime finite field.
     * @return the field size in bits.
     */
    public int getFieldSize() {
        return p.bitLength();
    }

    /**
     * Returns the prime {@code p} of this prime finite field.
     * @return the prime.
     */
    public BigInteger getP() {
        return p;
    }

    /**
     * Compares this prime finite field for equality with the
     * specified object.
     * @param obj the object to be compared.
     * @return true if {@code obj} is an instance
     * of ECFieldFp and the prime value match, false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj)  return true;

        return obj instanceof ECFieldFp other
                && p.equals(other.p);
    }

    /**
     * Returns a hash code value for this prime finite field.
     * @return a hash code value.
     */
    public int hashCode() {
        return p.hashCode();
    }
}
