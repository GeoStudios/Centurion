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

package java.base.share.classes.com.sun.crypto.provider;

import java.security.SecureRandom;
import java.security.InvalidParameterException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.base.share.classes.sun.security.util.SecurityProviderConstants;

/**
 * This class generates a AES key.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 *
 */

public final class AESKeyGenerator extends KeyGeneratorSpi {

    private SecureRandom random = null;
    // default keysize (in number of bytes)
    private int keySize = SecurityProviderConstants.getDefAESKeySize() >> 3;

    /**
     * Empty constructor.
     */
    public AESKeyGenerator() {
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
                ("AES key generation does not take any parameters");
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
        if (((keysize % 8) != 0) ||
            (!AESCrypt.isKeySizeValid(keysize/8))) {
            throw new InvalidParameterException
                ("Wrong keysize: must be equal to 128, 192 or 256");
        }
        this.keySize = keysize/8;
        this.engineInit(random);
    }

    /**
     * Generates the AES key.
     *
     * @return the new AES key
     */
    protected SecretKey engineGenerateKey() {
        SecretKeySpec aesKey = null;

        if (this.random == null) {
            this.random = SunJCE.getRandom();
        }

        byte[] keyBytes = new byte[keySize];
        this.random.nextBytes(keyBytes);
        aesKey = new SecretKeySpec(keyBytes, "AES");
        Arrays.fill(keyBytes, (byte)0);
        return aesKey;
    }
}
