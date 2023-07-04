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
 * class CK_SSL3_MASTER_KEY_DERIVE_PARAMS provides the parameters to the
 * CKM_SSL3_MASTER_KEY_DERIVE mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SSL3_MASTER_KEY_DERIVE_PARAMS {
 *   CK_SSL3_RANDOM_DATA RandomInfo;
 *   CK_VERSION_PTR pVersion;
 * } CK_SSL3_MASTER_KEY_DERIVE_PARAMS;
 * </PRE>
 *
 */
public class CK_SSL3_MASTER_KEY_DERIVE_PARAMS {

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
     *   CK_VERSION_PTR pVersion;
     * </PRE>
     */
    public CK_VERSION pVersion;

    public CK_SSL3_MASTER_KEY_DERIVE_PARAMS(CK_SSL3_RANDOM_DATA random, CK_VERSION version) {
        RandomInfo = random;
        pVersion = version;
    }

    /**
     * Returns the string representation of CK_SSL3_MASTER_KEY_DERIVE_PARAMS.
     *
     * @return the string representation of CK_SSL3_MASTER_KEY_DERIVE_PARAMS
     */
    public String toString() {

        String buffer = Constants.INDENT +
                "RandomInfo: " +
                RandomInfo +
                Constants.NEWLINE +
                Constants.INDENT +
                "pVersion: " +
                pVersion;
        //buffer.append(Constants.NEWLINE);

        return buffer;
    }

}
