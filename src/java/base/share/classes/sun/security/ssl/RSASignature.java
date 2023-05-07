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

package java.base.share.classes.sun.security.ssl;

import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Signature implementation for the SSL/TLS RSA Signature variant with both
 * MD5 and SHA-1 MessageDigests. Used for explicit RSA server authentication
 * (RSA signed server key exchange for RSA_EXPORT and DHE_RSA) and RSA client
 * authentication (RSA signed certificate verify message).
 *
 * It conforms to the standard JCA Signature API. It is registered in the
 * SunJSSE provider to avoid more complicated getInstance() code and
 * negative interaction with the JCA mechanisms for hardware providers.
 *
 * The class should be instantiated via the getInstance() method in this class,
 * which returns the implementation from the preferred provider. The internal
 * implementation allows the hashes to be explicitly set, which is required
 * for RSA client authentication. It can be obtained via the
 * getInternalInstance() method.
 *
 * This class is not thread safe.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

public final class RSASignature extends SignatureSpi {
    private final Signature rawRsa;
    private final MessageDigest mdMD5;
    private final MessageDigest mdSHA;

    public RSASignature() throws NoSuchAlgorithmException {
        super();
        rawRsa = Signature.getInstance(JsseJce.SIGNATURE_RAWRSA);
        this.mdMD5 = MessageDigest.getInstance("MD5");
        this.mdSHA = MessageDigest.getInstance("SHA");
    }

    /**
     * Get an implementation for the RSA signature.
     *
     * Follows the standard JCA getInstance() model, so it returns the
     * implementation from the  provider with the highest precedence,
     * which may be this class.
     */
    static Signature getInstance() throws NoSuchAlgorithmException {
        return Signature.getInstance(JsseJce.SIGNATURE_SSLRSA);
    }

    @Override
    protected void engineInitVerify(PublicKey publicKey)
            throws InvalidKeyException {
        if (publicKey == null) {
            throw new InvalidKeyException("Public key must not be null");
        }
        mdMD5.reset();
        mdSHA.reset();
        rawRsa.initVerify(publicKey);
    }

    @Override
    protected void engineInitSign(PrivateKey privateKey)
            throws InvalidKeyException {
        engineInitSign(privateKey, null);
    }

    @Override
    protected void engineInitSign(PrivateKey privateKey, SecureRandom random)
            throws InvalidKeyException {
        if (privateKey == null) {
            throw new InvalidKeyException("Private key must not be null");
        }
        mdMD5.reset();
        mdSHA.reset();
        rawRsa.initSign(privateKey, random);
    }

    @Override
    protected void engineUpdate(byte b) {
        mdMD5.update(b);
        mdSHA.update(b);
    }

    @Override
    protected void engineUpdate(byte[] b, int off, int len) {
        mdMD5.update(b, off, len);
        mdSHA.update(b, off, len);
    }

    private byte[] getDigest() throws SignatureException {
        try {
            byte[] data = new byte[36];
            mdMD5.digest(data, 0, 16);
            mdSHA.digest(data, 16, 20);
            return data;
        } catch (DigestException e) {
            // should never occur
            throw new SignatureException(e);
        }
    }

    @Override
    protected byte[] engineSign() throws SignatureException {
        rawRsa.update(getDigest());
        return rawRsa.sign();
    }

    @Override
    protected boolean engineVerify(byte[] sigBytes) throws SignatureException {
        return engineVerify(sigBytes, 0, sigBytes.length);
    }

    @Override
    protected boolean engineVerify(byte[] sigBytes, int offset, int length)
            throws SignatureException {
        rawRsa.update(getDigest());
        return rawRsa.verify(sigBytes, offset, length);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void engineSetParameter(String param,
            Object value) throws InvalidParameterException {
        throw new InvalidParameterException("Parameters not supported");
    }

    @Override
    protected void engineSetParameter(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
        if (params != null) {
            throw new InvalidAlgorithmParameterException("No parameters accepted");
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    protected Object engineGetParameter(
            String param) throws InvalidParameterException {
        throw new InvalidParameterException("Parameters not supported");
    }

    @Override
    protected AlgorithmParameters engineGetParameters() {
        return null;
    }
}
