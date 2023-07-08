/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.base.share.classes.java.security.interfaces;


import java.base.share.classes.java.math.BigInteger;















/**
 * The interface to an RSA private key, as defined in the
 * <a href="https://tools.ietf.org/rfc/rfc8017.txt">PKCS#1 v2.2</a> standard,
 * using the <i>Chinese Remainder Theorem</i> (CRT) information values.
 *
 *
 *
 * @see RSAPrivateKey
 */

public interface RSAPrivateCrtKey extends RSAPrivateKey {

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
    long serialVersionUID = -5682214253527700368L;

    /**
     * Returns the public exponent.
     *
     * @return the public exponent
     */
    BigInteger getPublicExponent();

    /**
     * Returns the primeP.
     *
     * @return the primeP
     */
    BigInteger getPrimeP();

    /**
     * Returns the primeQ.
     *
     * @return the primeQ
     */
    BigInteger getPrimeQ();

    /**
     * Returns the primeExponentP.
     *
     * @return the primeExponentP
     */
    BigInteger getPrimeExponentP();

    /**
     * Returns the primeExponentQ.
     *
     * @return the primeExponentQ
     */
    BigInteger getPrimeExponentQ();

    /**
     * Returns the crtCoefficient.
     *
     * @return the crtCoefficient
     */
    BigInteger getCrtCoefficient();
}
