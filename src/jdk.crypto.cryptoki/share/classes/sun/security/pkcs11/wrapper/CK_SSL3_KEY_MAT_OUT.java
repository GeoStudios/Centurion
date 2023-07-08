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
 * class CK_SSL3_KEY_MAT_OUT contains the resulting key handles and
 * initialization vectors after performing a C_DeriveKey function with the
 * CKM_SSL3_KEY_AND_MAC_DERIVE mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SSL3_KEY_MAT_OUT {
 *   CK_OBJECT_HANDLE hClientMacSecret;
 *   CK_OBJECT_HANDLE hServerMacSecret;
 *   CK_OBJECT_HANDLE hClientKey;
 *   CK_OBJECT_HANDLE hServerKey;
 *   CK_BYTE_PTR pIVClient;
 *   CK_BYTE_PTR pIVServer;
 * } CK_SSL3_KEY_MAT_OUT;
 * </PRE>
 *
 */
public class CK_SSL3_KEY_MAT_OUT{

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hClientMacSecret;
     * </PRE>
     */
    public long hClientMacSecret;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hServerMacSecret;
     * </PRE>
     */
    public long hServerMacSecret;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hClientKey;
     * </PRE>
     */
    public long hClientKey;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hServerKey;
     * </PRE>
     */
    public long hServerKey;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pIVClient;
     * </PRE>
     */
    public byte[] pIVClient;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pIVServer;
     * </PRE>
     */
    public byte[] pIVServer;

    /**
     * Returns the string representation of CK_SSL3_KEY_MAT_OUT.
     *
     * @return the string representation of CK_SSL3_KEY_MAT_OUT
     */
    public String toString() {

        String buffer = Constants.INDENT +
                "hClientMacSecret: " +
                hClientMacSecret +
                Constants.NEWLINE +
                Constants.INDENT +
                "hServerMacSecret: " +
                hServerMacSecret +
                Constants.NEWLINE +
                Constants.INDENT +
                "hClientKey: " +
                hClientKey +
                Constants.NEWLINE +
                Constants.INDENT +
                "hServerKey: " +
                hServerKey +
                Constants.NEWLINE +
                Constants.INDENT +
                "pIVClient: " +
                Functions.toHexString(pIVClient) +
                Constants.NEWLINE +
                Constants.INDENT +
                "pIVServer: " +
                Functions.toHexString(pIVServer);
        //buffer.append(Constants.NEWLINE);

        return buffer;
    }

}
