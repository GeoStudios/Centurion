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

import java.base.share.classes.java.security.ProviderException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * class CK_MECHANISM_INFO provides information about a particular mechanism.
 * <p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_MECHANISM_INFO {&nbsp;&nbsp;
 *   CK_ULONG ulMinKeySize;&nbsp;&nbsp;
 *   CK_ULONG ulMaxKeySize;&nbsp;&nbsp;
 *   CK_FLAGS flags;&nbsp;&nbsp;
 * } CK_MECHANISM_INFO;
 * </PRE>
 *
 */
public class CK_MECHANISM_INFO {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMinKeySize;
     * </PRE>
     */
    public long ulMinKeySize;

    // the integer version of ulMinKeySize for doing the actual range
    // check in SunPKCS11 provider, defaults to 0
    public final int iMinKeySize;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMaxKeySize;
     * </PRE>
     */
    public long ulMaxKeySize;

    // the integer version of ulMaxKeySize for doing the actual range
    // check in SunPKCS11 provider, defaults to Integer.MAX_VALUE
    public final int iMaxKeySize;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;

    public CK_MECHANISM_INFO(long minKeySize, long maxKeySize,
                             long flags) {
        this.ulMinKeySize = minKeySize;
        this.ulMaxKeySize = maxKeySize;
        this.iMinKeySize = ((minKeySize < Integer.MAX_VALUE && minKeySize > 0)?
                (int)minKeySize : 0);
        this.iMaxKeySize = ((maxKeySize < Integer.MAX_VALUE && maxKeySize > 0)?
                (int)maxKeySize : Integer.MAX_VALUE);
        this.flags = flags;
    }

    /**
     * Returns the string representation of CK_MECHANISM_INFO.
     *
     * @return the string representation of CK_MECHANISM_INFO
     */
    public String toString() {

        String sb = Constants.INDENT +
                "ulMinKeySize: " +
                ulMinKeySize +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulMaxKeySize: " +
                ulMaxKeySize +
                Constants.NEWLINE +
                Constants.INDENT +
                "flags: " +
                flags +
                " = " +
                Functions.mechanismInfoFlagsToString(flags);
        //buffer.append(Constants.NEWLINE);

        return sb;
    }
}
