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
 * class CK_RSA_PKCS_OAEP_PARAMS provides the parameters to the
 * CKM_RSA_PKCS_OAEP mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_RSA_PKCS_OAEP_PARAMS {
 *   CK_MECHANISM_TYPE hashAlg;
 *   CK_RSA_PKCS_OAEP_MGF_TYPE mgf;
 *   CK_RSA_PKCS_OAEP_SOURCE_TYPE source;
 *   CK_VOID_PTR pSourceData;
 *   CK_ULONG ulSourceDataLen;
 * } CK_RSA_PKCS_OAEP_PARAMS;
 * </PRE>
 *
 */
public class CK_RSA_PKCS_OAEP_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_MECHANISM_TYPE hashAlg;
     * </PRE>
     */
    public long hashAlg;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_RSA_PKCS_OAEP_MGF_TYPE mgf;
     * </PRE>
     */
    public long mgf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_RSA_PKCS_OAEP_SOURCE_TYPE source;
     * </PRE>
     */
    public long source;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pSourceData;
     *   CK_ULONG ulSourceDataLen;
     * </PRE>
     */
    public byte[] pSourceData;

    //CK_ULONG ulSourceDataLen;
    // ulSourceDataLen == pSourceData.length

    /**
     * Returns the string representation of CK_RSA_PKCS_OAEP_PARAMS.
     *
     * @return the string representation of CK_RSA_PKCS_OAEP_PARAMS
     */
    public String toString() {

        String sb = Constants.INDENT +
                "hashAlg: " +
                hashAlg +
                Constants.NEWLINE +
                Constants.INDENT +
                "mgf: " +
                mgf +
                Constants.NEWLINE +
                Constants.INDENT +
                "source: " +
                source +
                Constants.NEWLINE +
                Constants.INDENT +
                "pSourceData: " +
                pSourceData.toString() +
                Constants.NEWLINE +
                Constants.INDENT +
                "pSourceDataLen: " +
                Functions.toHexString(pSourceData);
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
