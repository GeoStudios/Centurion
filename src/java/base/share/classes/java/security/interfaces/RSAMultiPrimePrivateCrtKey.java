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
import java.security.spec.RSAOtherPrimeInfo;

/**
 * The interface to an RSA multi-prime private key, as defined in the
 * <a href="https://tools.ietf.org/rfc/rfc8017.txt">PKCS#1 v2.2</a> standard,
 * using the <i>Chinese Remainder Theorem</i> (CRT) information values.
 *
 * @author Valerie Peng
 *
 *
 * @see java.security.spec.RSAPrivateKeySpec
 * @see java.security.spec.RSAMultiPrimePrivateCrtKeySpec
 * @see RSAPrivateKey
 * @see RSAPrivateCrtKey
 *
 * @since 1.4
 */

public interface RSAMultiPrimePrivateCrtKey extends RSAPrivateKey {

    /**
     * The type fingerprint that is set to indicate
     * serialization compatibility with a previous
     * version of the type.
     *
     * @deprecated A {@code serialVersionUID} field in an interface is
     * ineffectual. Do not use; no replacement.
     */
    @Deprecated
    @SuppressWarnings("serial")
    @java.io.Serial
    long serialVersionUID = 618058533534628008L;

    /**
     * Returns the public exponent.
     *
     * @return the public exponent.
     */
    BigInteger getPublicExponent();

    /**
     * Returns the primeP.
     *
     * @return the primeP.
     */
    BigInteger getPrimeP();

    /**
     * Returns the primeQ.
     *
     * @return the primeQ.
     */
    BigInteger getPrimeQ();

    /**
     * Returns the primeExponentP.
     *
     * @return the primeExponentP.
     */
    BigInteger getPrimeExponentP();

    /**
     * Returns the primeExponentQ.
     *
     * @return the primeExponentQ.
     */
    BigInteger getPrimeExponentQ();

    /**
     * Returns the crtCoefficient.
     *
     * @return the crtCoefficient.
     */
    BigInteger getCrtCoefficient();

    /**
     * Returns the otherPrimeInfo or null if there are only
     * two prime factors (p and q).
     *
     * @return the otherPrimeInfo.
     */
    RSAOtherPrimeInfo[] getOtherPrimeInfo();
}
