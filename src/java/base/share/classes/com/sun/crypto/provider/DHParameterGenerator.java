/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.com.sun.crypto.provider;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.base.share.classes.javax.crypto.spec.DHParameterSpec;
import java.base.share.classes.javax.crypto.spec.DHGenParameterSpec;

import static java.base.share.classes.sun.security.util.SecurityProviderConstants.DEF_DH_KEY_SIZE;

/**
 * This class generates parameters for the Diffie-Hellman algorithm.
 * The parameters are a prime, a base, and optionally the length in bits of
 * the private value.
 *
 * <p>The Diffie-Hellman parameter generation accepts the size in bits of the
 * prime modulus and the size in bits of the random exponent as input.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 3/5/2023
 *
 *
 * @see java.security.AlgorithmParameters
 * @see java.security.spec.AlgorithmParameterSpec
 * @see DHParameters
 */

public final class DHParameterGenerator extends AlgorithmParameterGeneratorSpi {

    // The size in bits of the prime modulus
    private int primeSize = DEF_DH_KEY_SIZE;

    // The size in bits of the random exponent (private value)
    private int exponentSize = 0;

    // The source of randomness
    private SecureRandom random = null;

    private static void checkSupport(int keysize, int exponentSize)
            throws InvalidParameterException {

        boolean supported = ((keysize == 2048) || (keysize == 3072) ||
            ((keysize >= 512) && (keysize <= 1024) && ((keysize & 0x3F) == 0)));

        if (!supported) {
            throw new InvalidParameterException(
                    "Supported DH key size must be multiple of 64 and range " +
                    "from 512 to 1024 (inclusive), or 2048, 3072. " +
                    "The specified key size " + keysize + " is not supported");
        }

        if (exponentSize != 0) {
            DHKeyPairGenerator.checkKeySize(keysize, exponentSize);
        }
    }

    /**
     * Initializes this parameter generator for a certain keysize
     * and source of randomness.
     * The keysize is specified as the size in bits of the prime modulus.
     *
     * @param keysize the keysize (size of prime modulus) in bits
     * @param random the source of randomness
     */
    @Override
    protected void engineInit(int keysize, SecureRandom random) {
        checkSupport(keysize, 0);

        this.primeSize = keysize;
        this.random = random;
    }

    /**
     * Initializes this parameter generator with a set of parameter
     * generation values, which specify the size of the prime modulus and
     * the size of the random exponent, both in bits.
     *
     * @param genParamSpec the set of parameter generation values
     * @param random the source of randomness
     *
     * @exception InvalidAlgorithmParameterException if the given parameter
     * generation values are inappropriate for this parameter generator
     */
    @Override
    protected void engineInit(AlgorithmParameterSpec genParamSpec,
            SecureRandom random) throws InvalidAlgorithmParameterException {

        if (!(genParamSpec instanceof DHGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException
                ("Inappropriate parameter type");
        }

        DHGenParameterSpec dhParamSpec = (DHGenParameterSpec) genParamSpec;
        int primeSize = dhParamSpec.getPrimeSize();
        int exponentSize = dhParamSpec.getExponentSize();
        try {
            checkSupport(primeSize, exponentSize);
        } catch (InvalidParameterException ipe) {
            throw new InvalidAlgorithmParameterException(ipe.getMessage());
        }

        this.primeSize = primeSize;
        this.exponentSize = exponentSize;
        this.random = random;
    }

    /**
     * Generates the parameters.
     *
     * @return the new AlgorithmParameters object
     */
    @Override
    protected AlgorithmParameters engineGenerateParameters() {

        if (random == null) {
            random = SunJCE.getRandom();
        }

        BigInteger paramP = null;
        BigInteger paramG = null;
        try {
            AlgorithmParameterGenerator dsaParamGen =
                    AlgorithmParameterGenerator.getInstance("DSA");
            dsaParamGen.init(primeSize, random);
            AlgorithmParameters dsaParams = dsaParamGen.generateParameters();
            DSAParameterSpec dsaParamSpec =
                    dsaParams.getParameterSpec(DSAParameterSpec.class);

            DHParameterSpec dhParamSpec;
            if (this.exponentSize > 0) {
                dhParamSpec = new DHParameterSpec(dsaParamSpec.getP(),
                                                  dsaParamSpec.getG(),
                                                  this.exponentSize);
            } else {
                dhParamSpec = new DHParameterSpec(dsaParamSpec.getP(),
                                                  dsaParamSpec.getG());
            }
            AlgorithmParameters algParams =
                    AlgorithmParameters.getInstance("DH", SunJCE.getInstance());
            algParams.init(dhParamSpec);

            return algParams;
        } catch (Exception ex) {
            throw new ProviderException("Unexpected exception", ex);
        }
    }
}
