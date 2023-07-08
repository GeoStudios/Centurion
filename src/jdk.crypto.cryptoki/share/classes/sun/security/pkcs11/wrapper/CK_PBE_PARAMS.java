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
 * class CK_PBE_PARAMS provides all of the necessary information required byte
 * the CKM_PBE mechanisms and the CKM_PBA_SHA1_WITH_SHA1_HMAC mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_PBE_PARAMS {
 *   CK_CHAR_PTR pInitVector;
 *   CK_CHAR_PTR pPassword;
 *   CK_ULONG ulPasswordLen;
 *   CK_CHAR_PTR pSalt;
 *   CK_ULONG ulSaltLen;
 *   CK_ULONG ulIteration;
 * } CK_PBE_PARAMS;
 * </PRE>
 *
 */
public class CK_PBE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pInitVector;
     * </PRE>
     */
    public char[] pInitVector;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pPassword;
     *   CK_ULONG ulPasswordLen;
     * </PRE>
     */
    public char[] pPassword;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pSalt
     *   CK_ULONG ulSaltLen;
     * </PRE>
     */
    public char[] pSalt;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulIteration;
     * </PRE>
     */
    public long ulIteration;

    /**
     * Returns the string representation of CK_PBE_PARAMS.
     *
     * @return the string representation of CK_PBE_PARAMS
     */
    public String toString() {

        String sb = Constants.INDENT +
                "pInitVector: " +
                String.valueOf(pInitVector) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulPasswordLen: " +
                pPassword.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "pPassword: " +
                String.valueOf(pPassword) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulSaltLen: " +
                pSalt.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "pSalt: " +
                String.valueOf(pSalt) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulIteration: " +
                ulIteration;
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
