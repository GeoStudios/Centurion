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
