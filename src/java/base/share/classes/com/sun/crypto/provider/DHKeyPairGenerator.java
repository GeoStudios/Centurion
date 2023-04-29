/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.com.sun.crypto.provider;

import java.math.BigInteger;
import java.security.*;
import java.base.share.classes.java.security.spec.AlgorithmParameterSpec;
import java.base.share.classes.java.security.spec.InvalidParameterSpecException;
import java.base.share.classes.javax.crypto.spec.DHParameterSpec;
import java.base.share.classes.javax.crypto.spec.DHGenParameterSpec;

import java.base.share.classes.sun.security.provider.ParameterCache;
import static java.base.share.classes.sun.security.util.SecurityProviderConstants.DEF_DH_KEY_SIZE;
import static java.base.share.classes.sun.security.util.SecurityProviderConstants.getDefDHPrivateExpSize;

/**
 * This class represents the key pair generator for Diffie-Hellman key pairs.
 *
 * <p>This key pair generator may be initialized in two different ways:
 *
 * <ul>
 * <li>By providing the size in bits of the prime modulus -
 * This will be used to create a prime modulus and base generator, which will
 * then be used to create the Diffie-Hellman key pair.
 * <li>By providing a prime modulus and base generator
 * </ul>
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 *
 * @see java.security.KeyPairGenerator
 */
public final class DHKeyPairGenerator extends KeyPairGeneratorSpi {

    // parameters to use or null if not specified
    private DHParameterSpec params;

    // The size in bits of the prime modulus
    private int pSize;

    // The source of randomness
    private SecureRandom random;

    public DHKeyPairGenerator() {
        super();
        initialize(DEF_DH_KEY_SIZE, null);
    }

    // pkg private; used by DHParameterGenerator class as well
    static void checkKeySize(int keysize, int expSize)
            throws InvalidParameterException {

        if ((keysize < 512) || (keysize > 8192) || ((keysize & 0x3F) != 0)) {
            throw new InvalidParameterException(
                    "DH key size must be multiple of 64, and can only range " +
                    "from 512 to 8192 (inclusive). " +
                    "The specific key size " + keysize + " is not supported");
        }

        // optional, could be 0 if not specified
        if ((expSize < 0) || (expSize > keysize)) {
            throw new InvalidParameterException
                    ("Exponent size must be positive and no larger than" +
                    " modulus size");
        }
    }

    /**
     * Initializes this key pair generator for a certain keysize and source of
     * randomness.
     * The keysize is specified as the size in bits of the prime modulus.
     *
     * @param keysize the keysize (size of prime modulus) in bits
     * @param random the source of randomness
     */
    public void initialize(int keysize, SecureRandom random) {
        checkKeySize(keysize, 0);

        try {
            // Use the built-in parameters (ranging from 512 to 8192)
            // when available.
            this.params = ParameterCache.getDHParameterSpec(keysize, random);
        } catch (GeneralSecurityException e) {
            throw new InvalidParameterException(e.getMessage());
        }

        this.pSize = keysize;
        this.random = random;
    }

    /**
     * Initializes this key pair generator for the specified parameter
     * set and source of randomness.
     *
     * <p>The given parameter set contains the prime modulus, the base
     * generator, and optionally the requested size in bits of the random
     * exponent (private value).
     *
     * @param algParams the parameter set used to generate the key pair
     * @param random the source of randomness
     *
     * @exception InvalidAlgorithmParameterException if the given parameters
     * are inappropriate for this key pair generator
     */
    public void initialize(AlgorithmParameterSpec algParams,
            SecureRandom random) throws InvalidAlgorithmParameterException {
        if (!(algParams instanceof DHParameterSpec)){
            throw new InvalidAlgorithmParameterException
                ("Inappropriate parameter type");
        }

        params = (DHParameterSpec) algParams;
        pSize = params.getP().bitLength();
        try {
            checkKeySize(pSize, params.getL());
        } catch (InvalidParameterException ipe) {
            throw new InvalidAlgorithmParameterException(ipe.getMessage());
        }
        this.random = random;
    }

    /**
     * Generates a key pair.
     *
     * @return the new key pair
     */
    public KeyPair generateKeyPair() {
        if (random == null) {
            random = SunJCE.getRandom();
        }

        BigInteger p = params.getP();
        BigInteger g = params.getG();

        int lSize = params.getL();
        if (lSize == 0) { // not specified; use our own default
            lSize = getDefDHPrivateExpSize(params);
        }

        BigInteger x;
        BigInteger pMinus2 = p.subtract(BigInteger.TWO);

        //
        // PKCS#3 section 7.1 "Private-value generation"
        // Repeat if either of the following does not hold:
        //     0 < x < p-1
        //     2^(lSize-1) <= x < 2^(lSize)
        //
        do {
            // generate random x up to 2^lSize bits long
            x = new BigInteger(lSize, random);
        } while ((x.compareTo(BigInteger.ONE) < 0) ||
            ((x.compareTo(pMinus2) > 0)) || (x.bitLength() != lSize));

        // calculate public value y
        BigInteger y = g.modPow(x, p);

        DHPublicKey pubKey = new DHPublicKey(y, p, g, lSize);
        DHPrivateKey privKey = new DHPrivateKey(x, p, g, lSize);
        return new KeyPair(pubKey, privKey);
    }
}
