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

package java.security.jgss.share.classes.sun.security.krb5.internal;


import java.security.jgss.share.classes.sun.security.util.*;
import java.security.jgss.share.classes.sun.security.krb5.Asn1Exception;
import java.io.java.io.java.io.java.io.IOException;
import java.math.BigInteger;















/**
 * Implements the ASN.1 EncKrbPrivPart type.
 *
 * <pre>{@code
 *     METHOD-DATA ::=    SEQUENCE {
 *                        method-type[0]   INTEGER,
 *                        method-data[1]   OCTET STRING OPTIONAL
 *  }
 * }</pre>
 */
public class MethodData {
    private final int methodType;
    private byte[] methodData = null; //optional

    public MethodData(int type, byte[] data) {
        methodType = type;
        if (data != null) {
            methodData = data.clone();
        }
    }

    /**
     * Constructs a MethodData object.
     * @param encoding a Der-encoded data.
     * @exception Asn1Exception if an error occurs while decoding an ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     */
    public MethodData(DerValue encoding) throws Asn1Exception, IOException {
        DerValue der;
        if (encoding.getTag() != DerValue.tag_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getData().getDerValue();
        if ((der.getTag() & 0x1F) == 0x00) {
            BigInteger bint = der.getData().getBigInteger();
            methodType = bint.intValue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        if (encoding.getData().available() > 0) {
            der = encoding.getData().getDerValue();
            if ((der.getTag() & 0x1F) == 0x01) {
                methodData = der.getData().getOctetString();
            }
            else throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (encoding.getData().available() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes an MethodData object.
     * @return the byte array of encoded MethodData object.
     * @exception Asn1Exception if an error occurs while decoding an ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     */

    public byte[] asn1Encode() throws Asn1Exception, IOException {
        DerOutputStream bytes = new DerOutputStream();
        DerOutputStream temp = new DerOutputStream();
        temp.putInteger(BigInteger.valueOf(methodType));
        bytes.write(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x00), temp);
        if (methodData != null) {
            temp = new DerOutputStream();
            temp.putOctetString(methodData);
            bytes.write(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x01), temp);
        }

        temp = new DerOutputStream();
        temp.write(DerValue.tag_Sequence, bytes);
        return temp.toByteArray();
    }

}
