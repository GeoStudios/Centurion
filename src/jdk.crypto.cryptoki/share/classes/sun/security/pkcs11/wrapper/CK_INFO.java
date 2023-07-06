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
 * class  CK_INFO provides general information about Cryptoki.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_INFO {&nbsp;&nbsp;
 *    CK_VERSION cryptokiVersion;&nbsp;&nbsp;
 *    CK_UTF8CHAR manufacturerID[32];&nbsp;&nbsp;
 *    CK_FLAGS flags;&nbsp;&nbsp;
 *    CK_UTF8CHAR libraryDescription[32];&nbsp;&nbsp;
 *    CK_VERSION libraryVersion;&nbsp;&nbsp;
 *  } CK_INFO;
 * </PRE>
 *
 */
public class CK_INFO {

    /**
     * Cryptoki interface version number<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION cryptokiVersion;
     * </PRE>
     */
    public CK_VERSION cryptokiVersion;

    /**
     * ID of the Cryptoki library manufacturer. must be blank
     * padded - only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR manufacturerID[32];
     * </PRE>
     */
    public char[] manufacturerID;

    /**
     * bit flags reserved for future versions. must be zero<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;


/* libraryDescription and libraryVersion are new for v2.0 */

    /**
     * must be blank padded - only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR libraryDescription[32];
     * </PRE>
     */
    public char[] libraryDescription;

    /**
     * Cryptoki library version number<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION libraryVersion;
     * </PRE>
     */
    public CK_VERSION libraryVersion;

    public CK_INFO(CK_VERSION cryptoVer, char[] vendor, long flags,
                   char[] libDesc, CK_VERSION libVer) {
        this.cryptokiVersion = cryptoVer;
        this.manufacturerID = vendor;
        this.flags = flags;
        this.libraryDescription = libDesc;
        this.libraryVersion = libVer;
    }

    /**
     * Returns the string representation of CK_INFO.
     *
     * @return the string representation of CK_INFO
     */
    public String toString() {

        String sb = Constants.INDENT +
                "cryptokiVersion: " +
                cryptokiVersion.toString() +
                Constants.NEWLINE +
                Constants.INDENT +
                "manufacturerID: " +
                new String(manufacturerID) +
                Constants.NEWLINE +
                Constants.INDENT +
                "flags: " +
                Functions.toBinaryString(flags) +
                Constants.NEWLINE +
                Constants.INDENT +
                "libraryDescription: " +
                new String(libraryDescription) +
                Constants.NEWLINE +
                Constants.INDENT +
                "libraryVersion: " +
                libraryVersion.toString();
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
