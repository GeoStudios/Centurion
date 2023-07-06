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

package java.base.share.classes.java.security.spec;


import java.base.share.classes.java.math.BigInteger;
import java.base.share.classes.java.util.java.util.java.util.java.util.Arrays;















/**
 * This immutable class defines an elliptic curve (EC) prime
 * finite field.
 *
 * @see ECField
 *
 *
 */
public class ECFieldFp implements ECField {

    private final BigInteger p;

    /**
     * Creates an elliptic curve prime finite field
     * with the specified prime {@code p}.
     * @param p the prime.
     * @throws    NullPointerException if {@code p} is null.
     * @throws    IllegalArgumentException if {@code p}
     * is not positive.
     */
    public ECFieldFp(BigInteger p) {
        if (p.signum() != 1) {
            throw new IllegalArgumentException("p is not positive");
        }
        this.p = p;
    }

    /**
     * Returns the field size in bits which is size of prime p
     * for this prime finite field.
     * @return the field size in bits.
     */
    public int getFieldSize() {
        return p.bitLength();
    }

    /**
     * Returns the prime {@code p} of this prime finite field.
     * @return the prime.
     */
    public BigInteger getP() {
        return p;
    }

    /**
     * Compares this prime finite field for equality with the
     * specified object.
     * @param obj the object to be compared.
     * @return true if {@code obj} is an instance
     * of ECFieldFp and the prime value match, false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj)  return true;

        return obj instanceof ECFieldFp other
                && p.equals(other.p);
    }

    /**
     * Returns a hash code value for this prime finite field.
     * @return a hash code value.
     */
    public int hashCode() {
        return p.hashCode();
    }
}
