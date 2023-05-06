/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.com.sun.crypto.provider;

import java.base.share.classes.java.security.SecureRandom;
import java.base.share.classes.java.security.InvalidParameterException;
import java.base.share.classes.java.security.InvalidAlgorithmParameterException;
import java.base.share.classes.java.security.InvalidKeyException;
import java.base.share.classes.java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.base.share.classes.javax.crypto.KeyGeneratorSpi;
import java.base.share.classes.javax.crypto.SecretKey;
import java.base.share.classes.javax.crypto.spec.DESKeySpec;

/**
 * This class generates a DES key.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public final class DESKeyGenerator extends KeyGeneratorSpi {

    private SecureRandom random = null;

    /**
     * Empty constructor
     */
    public DESKeyGenerator() {
    }

    /**
     * Initializes this key generator.
     *
     * @param random the source of randomness for this generator
     */
    protected void engineInit(SecureRandom random) {
        this.random = random;
    }

    /**
     * Initializes this key generator with the specified parameter
     * set and a user-provided source of randomness.
     *
     * @param params the key generation parameters
     * @param random the source of randomness for this key generator
     *
     * @exception InvalidAlgorithmParameterException if <code>params</code> is
     * inappropriate for this key generator
     */
    protected void engineInit(AlgorithmParameterSpec params,
                              SecureRandom random)
        throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException
                ("DES key generation does not take any parameters");
    }

    /**
     * Initializes this key generator for a certain keysize, using the given
     * source of randomness.
     *
     * @param keysize the keysize. This is an algorithm-specific
     * metric specified in number of bits.
     * @param random the source of randomness for this key generator
     */
    protected void engineInit(int keysize, SecureRandom random) {
        if (keysize != 56) {
            throw new InvalidParameterException("Wrong keysize: must "
                                                + "be equal to 56");
        }
        this.engineInit(random);
    }

    /**
     * Generates the DES key.
     *
     * @return the new DES key
     */
    protected SecretKey engineGenerateKey() {
        DESKey desKey = null;

        if (this.random == null) {
            this.random = SunJCE.getRandom();
        }

        try {
            byte[] key = new byte[DESKeySpec.DES_KEY_LEN];
            do {
                this.random.nextBytes(key);
                setParityBit(key, 0);
            } while (DESKeySpec.isWeak(key, 0));
            desKey = new DESKey(key);
            Arrays.fill(key, (byte)0);
        } catch (InvalidKeyException e) {
            // this is never thrown
        }

        return desKey;
    }

    /*
     * Does parity adjustment, using bit in position 8 as the parity bit,
     * for 8 key bytes, starting at <code>offset</code>.
     *
     * The 8 parity bits of a DES key are only used for sanity-checking
     * of the key, to see if the key could actually be a key. If you check
     * the parity of the quantity, and it winds up not having the correct
     * parity, then you'll know something went wrong.
     *
     * A key that is not parity adjusted (e.g. e4e4e4e4e4e4e4e4) produces the
     * same output as a key that is parity adjusted (e.g. e5e5e5e5e5e5e5e5),
     * because it is the 56 bits of the DES key that are cryptographically
     * significant/"effective" -- the other 8 bits are just used for parity
     * checking.
     */
    static void setParityBit(byte[] key, int offset) {
        if (key == null)
            return;

        for (int i = 0; i < DESKeySpec.DES_KEY_LEN; i++) {
            int b = key[offset] & 0xfe;
            b |= (Integer.bitCount(b) & 1) ^ 1;
            key[offset++] = (byte)b;
        }
    }
}
