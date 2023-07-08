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

package java.security.jgss.share.classes.sun.security.krb5.internal.util;

import java.io.java.io.java.io.java.io.IOException;
import java.base.share.classes.java.util.Arrays;
import java.security.jgss.share.classes.sun.security.krb5.internal.Krb5;
import java.security.jgss.share.classes.sun.security.util.BitArray;
import java.security.jgss.share.classes.sun.security.util.DerOutputStream;

/**
 * A wrapper class around sun.security.util.BitArray, so that KDCOptions,
 * TicketFlags and ApOptions in krb5 classes can utilize some functions
 * in BitArray classes.
 *
 * The data type is defined in RFC 4120 as:
 *
 * 5.2.8.  KerberosFlags
 *
 *  For several message types, a specific constrained bit string type,
 *  KerberosFlags, is used.
 *
 *  KerberosFlags   ::= BIT STRING (SIZE (32..MAX))
 *                      -- minimum number of bits shall be sent,
 *                      -- but no fewer than 32
 *
 */
public class KerberosFlags {
    BitArray bits;

    // This constant is used by child classes.
    protected static final int BITS_PER_UNIT = 8;

    public KerberosFlags(int length) throws IllegalArgumentException {
        bits = new BitArray(length);
    }

    public KerberosFlags(int length, byte[] a) throws IllegalArgumentException {
        bits = new BitArray(length, a);
        if (length != Krb5.KRB_FLAGS_MAX+1) {
            bits = new BitArray(Arrays.copyOf(bits.toBooleanArray(), Krb5.KRB_FLAGS_MAX+1));
        }
    }

    public KerberosFlags(boolean[] bools) {
        bits = new BitArray((bools.length==Krb5.KRB_FLAGS_MAX+1)?
            bools:
            Arrays.copyOf(bools, Krb5.KRB_FLAGS_MAX+1));
    }

    public void set(int index, boolean value) {
        bits.set(index, value);
    }

    public boolean get(int index) {
        return bits.get(index);
    }

    public boolean[] toBooleanArray() {
        return bits.toBooleanArray();
    }

    /**
     * Writes the encoded data.
     *
     * @exception IOException if an I/O error occurs while reading encoded data.
     * @return an byte array of encoded KDCOptions.
     */
    public byte[] asn1Encode() throws IOException {
        DerOutputStream out = new DerOutputStream();
        out.putUnalignedBitString(bits);
        return out.toByteArray();
    }

    public String toString() {
        return bits.toString();
    }
}
