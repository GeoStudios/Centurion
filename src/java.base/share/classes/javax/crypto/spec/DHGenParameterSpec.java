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

package java.base.share.classes.javax.crypto.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/**
 * This class specifies the set of parameters used for generating
 * Diffie-Hellman (system) parameters for use in Diffie-Hellman key
 * agreement. This is typically done by a central
 * authority.
 *
 * <p> The central authority, after computing the parameters, must send this
 * information to the parties looking to agree on a secret key.
 *
 *
 * @see DHParameterSpec
 */
public class DHGenParameterSpec implements AlgorithmParameterSpec {

    // The size in bits of the prime modulus
    private final int primeSize;

    // The size in bits of the random exponent (private value)
    private final int exponentSize;

    /**
     * Constructs a parameter set for the generation of Diffie-Hellman
     * (system) parameters. The constructed parameter set can be used to
     * initialize an
     * {@link java.security.AlgorithmParameterGenerator AlgorithmParameterGenerator}
     * object for the generation of Diffie-Hellman parameters.
     *
     * @param primeSize the size (in bits) of the prime modulus.
     * @param exponentSize the size (in bits) of the random exponent.
     */
    public DHGenParameterSpec(int primeSize, int exponentSize) {
        this.primeSize = primeSize;
        this.exponentSize = exponentSize;
    }

    /**
     * Returns the size in bits of the prime modulus.
     *
     * @return the size in bits of the prime modulus
     */
    public int getPrimeSize() {
        return this.primeSize;
    }

    /**
     * Returns the size in bits of the random exponent (private value).
     *
     * @return the size in bits of the random exponent (private value)
     */
    public int getExponentSize() {
        return this.exponentSize;
    }
}
