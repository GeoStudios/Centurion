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

import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.*;

/**
 * This is an implementation of the HMAC algorithms as defined
 * in PKCS#12 v1.1 standard (see RFC 7292 Appendix B.4).
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

abstract class HmacPKCS12PBECore extends HmacCore {

    public static final class HmacPKCS12PBE_SHA1 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA1() throws NoSuchAlgorithmException {
            super("SHA1", 64);
        }
    }

    public static final class HmacPKCS12PBE_SHA224 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA224() throws NoSuchAlgorithmException {
            super("SHA-224", 64);
        }
    }

    public static final class HmacPKCS12PBE_SHA256 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA256() throws NoSuchAlgorithmException {
            super("SHA-256", 64);
        }
    }

    public static final class HmacPKCS12PBE_SHA384 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA384() throws NoSuchAlgorithmException {
            super("SHA-384", 128);
        }
    }

    public static final class HmacPKCS12PBE_SHA512 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA512() throws NoSuchAlgorithmException {
            super("SHA-512", 128);
        }
    }

    public static final class HmacPKCS12PBE_SHA512_224 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA512_224() throws NoSuchAlgorithmException {
            super("SHA-512/224", 128);
        }
    }

    public static final class HmacPKCS12PBE_SHA512_256 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA512_256() throws NoSuchAlgorithmException {
            super("SHA-512/256", 128);
        }
    }

    private final String algorithm;
    private final int bl;

    /**
     * Standard constructor, creates a new HmacSHA1 instance.
     */
    public HmacPKCS12PBECore(String algorithm, int bl) throws NoSuchAlgorithmException {
        super(algorithm, bl);
        this.algorithm = algorithm;
        this.bl = bl;
    }

    /**
     * Initializes the HMAC with the given secret key and algorithm parameters.
     *
     * @param key the secret key.
     * @param params the algorithm parameters.
     *
     * @exception InvalidKeyException if the given key is inappropriate for
     * initializing this MAC.
     * @exception InvalidAlgorithmParameterException if the given algorithm
     * parameters are inappropriate for this MAC.
     */
    protected void engineInit(Key key, AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException {
        char[] passwdChars;
        byte[] salt = null;
        int iCount = 0;
        if (key instanceof javax.crypto.interfaces.PBEKey) {
            javax.crypto.interfaces.PBEKey pbeKey =
                (javax.crypto.interfaces.PBEKey) key;
            passwdChars = pbeKey.getPassword();
            salt = pbeKey.getSalt(); // maybe null if unspecified
            iCount = pbeKey.getIterationCount(); // maybe 0 if unspecified
        } else if (key instanceof SecretKey) {
            byte[] passwdBytes;
            if (!(key.getAlgorithm().regionMatches(true, 0, "PBE", 0, 3)) ||
                    (passwdBytes = key.getEncoded()) == null) {
                throw new InvalidKeyException("Missing password");
            }
            passwdChars = new char[passwdBytes.length];
            for (int i=0; i<passwdChars.length; i++) {
                passwdChars[i] = (char) (passwdBytes[i] & 0x7f);
            }
            Arrays.fill(passwdBytes, (byte)0x00);
        } else {
            throw new InvalidKeyException("SecretKey of PBE type required");
        }

        byte[] derivedKey;
        try {
            if (params == null) {
                // should not auto-generate default values since current
                // javax.crypto.Mac api does not have any method for caller to
                // retrieve the generated defaults.
                if ((salt == null) || (iCount == 0)) {
                    throw new InvalidAlgorithmParameterException
                            ("PBEParameterSpec required for salt and iteration count");
                }
            } else if (!(params instanceof PBEParameterSpec)) {
                throw new InvalidAlgorithmParameterException
                        ("PBEParameterSpec type required");
            } else {
                PBEParameterSpec pbeParams = (PBEParameterSpec) params;
                // make sure the parameter values are consistent
                if (salt != null) {
                    if (!Arrays.equals(salt, pbeParams.getSalt())) {
                        throw new InvalidAlgorithmParameterException
                                ("Inconsistent value of salt between key and params");
                    }
                } else {
                    salt = pbeParams.getSalt();
                }
                if (iCount != 0) {
                    if (iCount != pbeParams.getIterationCount()) {
                        throw new InvalidAlgorithmParameterException
                                ("Different iteration count between key and params");
                    }
                } else {
                    iCount = pbeParams.getIterationCount();
                }
            }
            // For security purpose, we need to enforce a minimum length
            // for salt; just require the minimum salt length to be 8-byte
            // which is what PKCS#5 recommends and openssl does.
            if (salt.length < 8) {
                throw new InvalidAlgorithmParameterException
                        ("Salt must be at least 8 bytes long");
            }
            if (iCount <= 0) {
                throw new InvalidAlgorithmParameterException
                        ("IterationCount must be a positive number");
            }
            derivedKey = PKCS12PBECipherCore.derive(passwdChars, salt,
                    iCount, engineGetMacLength(), PKCS12PBECipherCore.MAC_KEY,
                    algorithm, bl);
        } finally {
            Arrays.fill(passwdChars, '\0');
        }
        SecretKey cipherKey = new SecretKeySpec(derivedKey, "HmacSHA1");
        super.engineInit(cipherKey, null);
    }
}
