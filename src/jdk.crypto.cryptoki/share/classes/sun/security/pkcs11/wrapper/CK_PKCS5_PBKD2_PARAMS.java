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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package sun.security.pkcs11.wrapper;



/**
 * class CK_PKCS5_PBKD2_PARAMS provides the parameters to the CKM_PKCS5_PBKD2
 * mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_PKCS5_PBKD2_PARAMS {
 *   CK_PKCS5_PBKD2_SALT_SOURCE_TYPE saltSource;
 *   CK_VOID_PTR pSaltSourceData;
 *   CK_ULONG ulSaltSourceDataLen;
 *   CK_ULONG iterations;
 *   CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE prf;
 *   CK_VOID_PTR pPrfData;
 *   CK_ULONG ulPrfDataLen;
 * } CK_PKCS5_PBKD2_PARAMS;
 * </PRE>
 *
 */
public class CK_PKCS5_PBKD2_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE saltSource;
     * </PRE>
     */
    public long saltSource;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pSaltSourceData;
     *   CK_ULONG ulSaltSourceDataLen;
     * </PRE>
     */
    public byte[] pSaltSourceData;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG iterations;
     * </PRE>
     */
    public long iterations;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE prf;
     * </PRE>
     */
    public long prf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pPrfData;
     *   CK_ULONG ulPrfDataLen;
     * </PRE>
     */
    public byte[] pPrfData;

    /**
     * Returns the string representation of CK_PKCS5_PBKD2_PARAMS.
     *
     * @return the string representation of CK_PKCS5_PBKD2_PARAMS
     */
    public String toString() {

        String sb = Constants.INDENT +
                "saltSource: " +
                saltSource +
                Constants.NEWLINE +
                Constants.INDENT +
                "pSaltSourceData: " +
                Functions.toHexString(pSaltSourceData) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulSaltSourceDataLen: " +
                pSaltSourceData.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "iterations: " +
                iterations +
                Constants.NEWLINE +
                Constants.INDENT +
                "prf: " +
                prf +
                Constants.NEWLINE +
                Constants.INDENT +
                "pPrfData: " +
                Functions.toHexString(pPrfData) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulPrfDataLen: " +
                pPrfData.length;
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
