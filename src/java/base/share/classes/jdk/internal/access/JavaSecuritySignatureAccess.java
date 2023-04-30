/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.access;

import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

public interface JavaSecuritySignatureAccess {

    void initVerify(Signature s, PublicKey publicKey, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException;

    void initVerify(Signature s, java.security.cert.Certificate certificate,
             AlgorithmParameterSpec params)
             throws InvalidKeyException, InvalidAlgorithmParameterException;

    void initSign(Signature s, PrivateKey privateKey,
             AlgorithmParameterSpec params, SecureRandom random)
             throws InvalidKeyException, InvalidAlgorithmParameterException;
}
