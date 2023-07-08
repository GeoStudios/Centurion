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
 * class CK_SSL3_RANDOM_DATA provides information about the random data of a
 * client and a server in an SSL context. This class is used by both the
 * CKM_SSL3_MASTER_KEY_DERIVE and the CKM_SSL3_KEY_AND_MAC_DERIVE mechanisms.
 * <p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SSL3_RANDOM_DATA {
 *   CK_BYTE_PTR pClientRandom;
 *   CK_ULONG ulClientRandomLen;
 *   CK_BYTE_PTR pServerRandom;
 *   CK_ULONG ulServerRandomLen;
 * } CK_SSL3_RANDOM_DATA;
 * </PRE>
 *
 */
public class CK_SSL3_RANDOM_DATA {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pClientRandom;
     *   CK_ULONG ulClientRandomLen;
     * </PRE>
     */
    public byte[] pClientRandom;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pServerRandom;
     *   CK_ULONG ulServerRandomLen;
     * </PRE>
     */
    public byte[] pServerRandom;

    public CK_SSL3_RANDOM_DATA(byte[] clientRandom, byte[] serverRandom) {
        pClientRandom = clientRandom;
        pServerRandom = serverRandom;
    }

    /**
     * Returns the string representation of CK_SSL3_RANDOM_DATA.
     *
     * @return the string representation of CK_SSL3_RANDOM_DATA
     */
    public String toString() {

        String buffer = Constants.INDENT +
                "pClientRandom: " +
                Functions.toHexString(pClientRandom) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulClientRandomLen: " +
                pClientRandom.length +
                Constants.NEWLINE +
                Constants.INDENT +
                "pServerRandom: " +
                Functions.toHexString(pServerRandom) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulServerRandomLen: " +
                pServerRandom.length;
        //buffer.append(Constants.NEWLINE);

        return buffer;
    }

}
