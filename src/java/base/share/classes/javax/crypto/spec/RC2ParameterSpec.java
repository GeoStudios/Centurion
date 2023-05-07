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

import java.security.spec.AlgorithmParameterSpec;

/**
 * This class specifies the parameters used with the
 * <a href="http://www.ietf.org/rfc/rfc2268.txt"><i>RC2</i></a>
 * algorithm.
 *
 * <p> The parameters consist of an effective key size and optionally
 * an 8-byte initialization vector (IV) (only in feedback mode).
 *
 * <p> This class can be used to initialize a {@code Cipher} object that
 * implements the <i>RC2</i> algorithm.
 *
 * @author Jan Luehe
 *
 * @since 1.4
 */
public class RC2ParameterSpec implements AlgorithmParameterSpec {

    private byte[] iv = null;
    private final int effectiveKeyBits;

    /**
     * Constructs a parameter set for RC2 from the given effective key size
     * (in bits).
     *
     * @param effectiveKeyBits the effective key size in bits.
     */
    public RC2ParameterSpec(int effectiveKeyBits) {
        this.effectiveKeyBits = effectiveKeyBits;
    }

    /**
     * Constructs a parameter set for RC2 from the given effective key size
     * (in bits) and an 8-byte IV.
     *
     * <p> The bytes that constitute the IV are those between
     * {@code iv[0]} and {@code iv[7]} inclusive.
     *
     * @param effectiveKeyBits the effective key size in bits.
     * @param iv the buffer with the 8-byte IV. The first 8 bytes of
     * the buffer are copied to protect against subsequent modification.
     * @exception IllegalArgumentException if {@code iv} is null.
     */
    public RC2ParameterSpec(int effectiveKeyBits, byte[] iv) {
        this(effectiveKeyBits, iv, 0);
    }

    /**
     * Constructs a parameter set for RC2 from the given effective key size
     * (in bits) and IV.
     *
     * <p> The IV is taken from {@code iv}, starting at
     * {@code offset} inclusive.
     * The bytes that constitute the IV are those between
     * {@code iv[offset]} and {@code iv[offset+7]} inclusive.
     *
     * @param effectiveKeyBits the effective key size in bits.
     * @param iv the buffer with the IV. The first 8 bytes
     * of the buffer beginning at {@code offset} inclusive
     * are copied to protect against subsequent modification.
     * @param offset the offset in {@code iv} where the 8-byte IV
     * starts.
     * @exception IllegalArgumentException if {@code iv} is null.
     */
    public RC2ParameterSpec(int effectiveKeyBits, byte[] iv, int offset) {
        this.effectiveKeyBits = effectiveKeyBits;
        if (iv == null) throw new IllegalArgumentException("IV missing");
        int blockSize = 8;
        if (iv.length - offset < blockSize) {
            throw new IllegalArgumentException("IV too short");
        }
        this.iv = new byte[blockSize];
        System.arraycopy(iv, offset, this.iv, 0, blockSize);
    }

    /**
     * Returns the effective key size in bits.
     *
     * @return the effective key size in bits.
     */
    public int getEffectiveKeyBits() {
        return this.effectiveKeyBits;
    }

    /**
     * Returns the IV or null if this parameter set does not contain an IV.
     *
     * @return the IV or null if this parameter set does not contain an IV.
     * Returns a new array each time this method is called.
     */
    public byte[] getIV() {
        return (iv == null? null:iv.clone());
    }

    /**
     * Tests for equality between the specified object and this
     * object. Two RC2ParameterSpec objects are considered equal if their
     * effective key sizes and IVs are equal.
     * (Two IV references are considered equal if both are {@code null}.)
     *
     * @param obj the object to test for equality with this object.
     *
     * @return true if the objects are considered equal, false if
     * {@code obj} is null or otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RC2ParameterSpec other)) {
            return false;
        }

        return ((effectiveKeyBits == other.effectiveKeyBits) &&
                java.util.Arrays.equals(iv, other.iv));
    }

    /**
     * Calculates a hash code value for the object.
     * Objects that are equal will also have the same hashcode.
     */
    public int hashCode() {
        int retval = 0;
        if (iv != null) {
            for (int i = 1; i < iv.length; i++) {
                retval += iv[i] * i;
            }
        }
        return retval + effectiveKeyBits;
    }
}
