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

package sun.security.krb5.internal.crypto;

import sun.security.krb5.Checksum;
import sun.security.krb5.EncryptedData;
import sun.security.krb5.internal.*;

public class NullEType extends EType {

    public NullEType() {
    }

    public int eType() {
        return EncryptedData.ETYPE_NULL;
    }

    public int minimumPadSize() {
        return 0;
    }

    public int confounderSize() {
        return 0;
    }

    public int checksumType() {
        return Checksum.CKSUMTYPE_NULL;
    }

    public int checksumSize() {
        return 0;
    }

    public int blockSize() {
        return 1;
    }

    public int keyType() {
        return Krb5.KEYTYPE_NULL;
    }

    public int keySize() {
        return 0;
    }

    public byte[] encrypt(byte[] data, byte[] key, int usage) {
        byte[] cipher = new byte[data.length];
        System.arraycopy(data, 0, cipher, 0, data.length);
        return cipher;
    }

    public byte[] encrypt(byte[] data, byte[] key, byte[] ivec, int usage) {
        byte[] cipher = new byte[data.length];
        System.arraycopy(data, 0, cipher, 0, data.length);
        return cipher;
    }

    public byte[] decrypt(byte[] cipher, byte[] key, int usage)
        throws KrbApErrException {
            return cipher.clone();
    }

    public byte[] decrypt(byte[] cipher, byte[] key, byte[] ivec, int usage)
        throws KrbApErrException {
            return cipher.clone();
    }
}
