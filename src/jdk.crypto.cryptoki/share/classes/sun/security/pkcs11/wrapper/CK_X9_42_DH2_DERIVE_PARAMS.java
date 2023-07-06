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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * class CK_X9_42_DH2_DERIVE_PARAMS provides the parameters to the
 * CKM_X9_42_DH_HYBRID_DERIVE and CKM_X9_42_MQV_DERIVE mechanisms.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_X9_42_DH2_DERIVE_PARAMS {
 *   CK_X9_42_DH_KDF_TYPE kdf;
 *   CK_ULONG ulOtherInfoLen;
 *   CK_BYTE_PTR pOtherInfo;
 *   CK_ULONG ulPublicDataLen;
 *   CK_BYTE_PTR pPublicData;
 *   CK_ULONG ulPrivateDataLen;
 *   CK_OBJECT_HANDLE hPrivateData;
 *   CK_ULONG ulPublicDataLen2;
 *   CK_BYTE_PTR pPublicData2;
 * } CK_X9_42_DH2_DERIVE_PARAMS;
 * </PRE>
 *
 */
public class CK_X9_42_DH2_DERIVE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_X9_42_DH_KDF_TYPE kdf;
     * </PRE>
     */
    public long kdf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulOtherInfoLen;
     *   CK_BYTE_PTR pOtherInfo;
     * </PRE>
     */
    public byte[] pOtherInfo;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublicDataLen;
     *   CK_BYTE_PTR pPublicData;
     * </PRE>
     */
    public byte[] pPublicData;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPrivateDataLen;
     * </PRE>
     */
    public long ulPrivateDataLen;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hPrivateData;
     * </PRE>
     */
    public long hPrivateData;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublicDataLen2;
     *   CK_BYTE_PTR pPublicData2;
     * </PRE>
     */
    public byte[] pPublicData2;

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
                "pOtherInfoLen: " +
                pOtherInfo.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "pOtherInfo: " +
                Functions.toHexString(pOtherInfo) +
                Constants.NEWLINE +
                Constants.INDENT +
                "pPublicDataLen: " +
                pPublicData.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "pPublicData: " +
                Functions.toHexString(pPublicData) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulPrivateDataLen: " +
                ulPrivateDataLen +
                Constants.NEWLINE +
                Constants.INDENT +
                "hPrivateData: " +
                hPrivateData +
                Constants.NEWLINE +
                Constants.INDENT +
                "pPublicDataLen2: " +
                pPublicData2.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "pPublicData2: " +
                Functions.toHexString(pPublicData2);
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
