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

package sun.security.pkcs11.wrapper;

/**
 * CK_TLS12_KEY_MAT_PARAMS from PKCS#11 v2.40.
 */
public class CK_TLS12_KEY_MAT_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMacSizeInBits;
     * </PRE>
     */
    public long ulMacSizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulKeySizeInBits;
     * </PRE>
     */
    public long ulKeySizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulIVSizeInBits;
     * </PRE>
     */
    public long ulIVSizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BBOOL bIsExport;
     * </PRE>
     */
    public boolean bIsExport;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_SSL3_RANDOM_DATA RandomInfo;
     * </PRE>
     */
    public CK_SSL3_RANDOM_DATA RandomInfo;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_SSL3_KEY_MAT_OUT_PTR pReturnedKeyMaterial;
     * </PRE>
     */
    public CK_SSL3_KEY_MAT_OUT pReturnedKeyMaterial;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_MECHANISM_TYPE prfHashMechanism;
     * </PRE>
     */
    public long prfHashMechanism;

    public CK_TLS12_KEY_MAT_PARAMS(
            int macSize, int keySize, int ivSize, boolean export,
            CK_SSL3_RANDOM_DATA random, long prfHashMechanism) {
        ulMacSizeInBits = macSize;
        ulKeySizeInBits = keySize;
        ulIVSizeInBits = ivSize;
        bIsExport = export;
        RandomInfo = random;
        pReturnedKeyMaterial = new CK_SSL3_KEY_MAT_OUT();
        if (ivSize != 0) {
            int n = ivSize >> 3;
            pReturnedKeyMaterial.pIVClient = new byte[n];
            pReturnedKeyMaterial.pIVServer = new byte[n];
        }
        this.prfHashMechanism = prfHashMechanism;
    }

    /**
     * Returns the string representation of CK_TLS12_KEY_MAT_PARAMS.
     *
     * @return the string representation of CK_TLS12_KEY_MAT_PARAMS
     */
    public String toString() {

        String buffer = Constants.INDENT +
                "ulMacSizeInBits: " +
                ulMacSizeInBits +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulKeySizeInBits: " +
                ulKeySizeInBits +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulIVSizeInBits: " +
                ulIVSizeInBits +
                Constants.NEWLINE +
                Constants.INDENT +
                "bIsExport: " +
                bIsExport +
                Constants.NEWLINE +
                Constants.INDENT +
                "RandomInfo: " +
                RandomInfo +
                Constants.NEWLINE +
                Constants.INDENT +
                "pReturnedKeyMaterial: " +
                pReturnedKeyMaterial +
                Constants.NEWLINE +
                Constants.INDENT +
                "prfHashMechanism: " +
                prfHashMechanism;

        return buffer;
    }

}
