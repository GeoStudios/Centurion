/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.security.spec.AlgorithmParameterSpec;

/**
 * This immutable class is used when randomly generating a key pair and the
 * consumer only specifies the length of the key and therefore a curve for that
 * key size must be picked from the list of supported curves using this spec.
 *
 * @see AlgorithmParameterSpec
 * @see ECGenParameterSpec
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class ECKeySizeParameterSpec implements AlgorithmParameterSpec {

    private final int keySize;

    /**
     * Creates a parameter specification for EC curve
     * generation using a standard (or predefined) key size
     * <code>keySize</code> in order to generate the corresponding
     * (precomputed) elliptic curve.
     * <p>
     * Note, if the curve of the specified length is not supported,
     * <code>AlgorithmParameters.init</code> will throw an exception.
     *
     * @param keySize the key size of the curve to lookup
     */
    public ECKeySizeParameterSpec(int keySize) {
        this.keySize = keySize;
    }

    /**
     * Returns the key size of this spec.
     *
     * @return the standard or predefined key size.
     */
    public int getKeySize() {
        return keySize;
    }
}
