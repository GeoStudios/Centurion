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

package java.base.share.classes.java.security.interfaces;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/**
 * The interface to a public or private key in
 * <a href="https://tools.ietf.org/rfc/rfc8017.txt">PKCS#1 v2.2</a> standard,
 * such as those for RSA, or RSASSA-PSS algorithms.
 *
 * @author Jan Luehe
 *
 * @see RSAPublicKey
 * @see RSAPrivateKey
 *
 * @since 1.3
 */

public interface RSAKey {

    /**
     * Returns the modulus.
     *
     * @return the modulus
     */
    BigInteger getModulus();

    /**
     * Returns the parameters associated with this key.
     * The parameters are optional and may be either
     * explicitly specified or implicitly created during
     * key pair generation.
     *
     * @implSpec
     * The default implementation returns {@code null}.
     *
     * @return the associated parameters, may be null
     * @since 11
     */
    default AlgorithmParameterSpec getParams() {
        return null;
    }
}
