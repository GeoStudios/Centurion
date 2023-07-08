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
import java.base.share.classes.java.security.spec.AlgorithmParameterSpec;















/**
 * The interface to a public or private key in
 * <a href="https://tools.ietf.org/rfc/rfc8017.txt">PKCS#1 v2.2</a> standard,
 * such as those for RSA, or RSASSA-PSS algorithms.
 *
 *
 * @see RSAPublicKey
 * @see RSAPrivateKey
 *
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
     */
    default AlgorithmParameterSpec getParams() {
        return null;
    }
}
