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

package jdk.crypto.cryptoki.share.classes.sun.security.pkcs11.wrapper;

/**
 * class CK_ECDH1_DERIVE_PARAMS provides the parameters to the
 * CKM_ECDH1_DERIVE and CKM_ECDH1_COFACTOR_DERIVE mechanisms.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_ECDH1_DERIVE_PARAMS {
 *   CK_EC_KDF_TYPE kdf;
 *   CK_ULONG ulSharedDataLen;
 *   CK_BYTE_PTR pSharedData;
 *   CK_ULONG ulPublicDataLen;
 *   CK_BYTE_PTR pPublicData;
 * } CK_ECDH1_DERIVE_PARAMS;
 * </PRE>
 *
 */
public class CK_ECDH1_DERIVE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_EC_KDF_TYPE kdf;
     * </PRE>
     */
    public long kdf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulSharedDataLen;
     *   CK_BYTE_PTR pSharedData;
     * </PRE>
     */
    public byte[] pSharedData;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublicDataLen;
     *   CK_BYTE_PTR pPublicData;
     * </PRE>
     */
    public byte[] pPublicData;

    public CK_ECDH1_DERIVE_PARAMS(long kdf, byte[] pSharedData, byte[] pPublicData) {
        this.kdf = kdf;
        this.pSharedData = pSharedData;
        this.pPublicData = pPublicData;
    }

    /**
     * Returns the string representation of CK_PKCS5_PBKD2_PARAMS.
     *
     * @return the string representation of CK_PKCS5_PBKD2_PARAMS
     */
    public String toString() {

        String sb = Constants.INDENT +
                "kdf: 0x" +
                Functions.toFullHexString(kdf) +
                Constants.NEWLINE +
                Constants.INDENT +
                "pSharedDataLen: " +
                pSharedData.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "pSharedData: " +
                Functions.toHexString(pSharedData) +
                Constants.NEWLINE +
                Constants.INDENT +
                "pPublicDataLen: " +
                pPublicData.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "pPublicData: " +
                Functions.toHexString(pPublicData);
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
