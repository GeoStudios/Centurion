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
 * class CK_TOKEN_INFO provides information about a token.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_TOKEN_INFO {&nbsp;&nbsp;
 *   CK_UTF8CHAR label[32];&nbsp;&nbsp;
 *   CK_UTF8CHAR manufacturerID[32];&nbsp;&nbsp;
 *   CK_UTF8CHAR model[16];&nbsp;&nbsp;
 *   CK_CHAR serialNumber[16];&nbsp;&nbsp;
 *   CK_FLAGS flags;&nbsp;&nbsp;
 *   CK_ULONG ulMaxSessionCount;&nbsp;&nbsp;
 *   CK_ULONG ulSessionCount;&nbsp;&nbsp;
 *   CK_ULONG ulMaxRwSessionCount;&nbsp;&nbsp;
 *   CK_ULONG ulRwSessionCount;&nbsp;&nbsp;
 *   CK_ULONG ulMaxPinLen;&nbsp;&nbsp;
 *   CK_ULONG ulMinPinLen;&nbsp;&nbsp;
 *   CK_ULONG ulTotalPublicMemory;&nbsp;&nbsp;
 *   CK_ULONG ulFreePublicMemory;&nbsp;&nbsp;
 *   CK_ULONG ulTotalPrivateMemory;&nbsp;&nbsp;
 *   CK_ULONG ulFreePrivateMemory;&nbsp;&nbsp;
 *   CK_VERSION hardwareVersion;&nbsp;&nbsp;
 *   CK_VERSION firmwareVersion;&nbsp;&nbsp;
 *   CK_CHAR utcTime[16];&nbsp;&nbsp;
 * } CK_TOKEN_INFO;
 * &nbsp;&nbsp;
 * </PRE>
 *
 */
public class CK_TOKEN_INFO {

    /* label, manufacturerID, and model have been changed from
     * CK_CHAR to CK_UTF8CHAR for v2.11. */
    /**
     * must be blank padded and only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR label[32];
     * </PRE>
     */
    public char[] label;           /* blank padded */

    /**
     * must be blank padded and only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR manufacturerID[32];
     * </PRE>
     */
    public char[] manufacturerID;  /* blank padded */

    /**
     * must be blank padded and only the first 16 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR model[16];
     * </PRE>
     */
    public char[] model;           /* blank padded */

    /**
     * must be blank padded and only the first 16 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR serialNumber[16];
     * </PRE>
     */
    public char[] serialNumber;    /* blank padded */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;               /* see below */

    /* ulMaxSessionCount, ulSessionCount, ulMaxRwSessionCount,
     * ulRwSessionCount, ulMaxPinLen, and ulMinPinLen have all been
     * changed from CK_USHORT to CK_ULONG for v2.0 */
    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMaxSessionCount;
     * </PRE>
     */
    public long ulMaxSessionCount;     /* max open sessions */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulSessionCount;
     * </PRE>
     */
    public long ulSessionCount;        /* sess. now open */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMaxRwSessionCount;
     * </PRE>
     */
    public long ulMaxRwSessionCount;   /* max R/W sessions */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulRwSessionCount;
     * </PRE>
     */
    public long ulRwSessionCount;      /* R/W sess. now open */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMaxPinLen;
     * </PRE>
     */
    public long ulMaxPinLen;           /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMinPinLen;
     * </PRE>
     */
    public long ulMinPinLen;           /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulTotalPublicMemory;
     * </PRE>
     */
    public long ulTotalPublicMemory;   /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulFreePublicMemory;
     * </PRE>
     */
    public long ulFreePublicMemory;    /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulTotalPrivateMemory;
     * </PRE>
     */
    public long ulTotalPrivateMemory;  /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulFreePrivateMemory;
     * </PRE>
     */
    public long ulFreePrivateMemory;   /* in bytes */

    /* hardwareVersion, firmwareVersion, and time are new for
     * v2.0 */
    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION hardwareVersion;
     * </PRE>
     */
    public CK_VERSION    hardwareVersion;       /* version of hardware */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION firmwareVersion;
     * </PRE>
     */
    public CK_VERSION    firmwareVersion;       /* version of firmware */

    /**
     * only the first 16 chars will be used
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR utcTime[16];
     * </PRE>
     */
    public char[] utcTime;           /* time */

    public CK_TOKEN_INFO(char[] label, char[] vendor, char[] model,
                         char[] serialNo, long flags,
                         long sessionMax, long session,
                         long rwSessionMax, long rwSession,
                         long pinLenMax, long pinLenMin,
                         long totalPubMem, long freePubMem,
                         long totalPrivMem, long freePrivMem,
                         CK_VERSION hwVer, CK_VERSION fwVer, char[] utcTime) {
        this.label = label;
        this.manufacturerID = vendor;
        this.model = model;
        this.serialNumber = serialNo;
        this.flags = flags;
        this.ulMaxSessionCount = sessionMax;
        this.ulSessionCount = session;
        this.ulMaxRwSessionCount = rwSessionMax;
        this.ulRwSessionCount = rwSession;
        this.ulMaxPinLen = pinLenMax;
        this.ulMinPinLen = pinLenMin;
        this.ulTotalPublicMemory = totalPubMem;
        this.ulFreePublicMemory = freePubMem;
        this.ulTotalPrivateMemory = totalPrivMem;
        this.ulFreePrivateMemory = freePrivMem;
        this.hardwareVersion = hwVer;
        this.firmwareVersion = fwVer;
        this.utcTime = utcTime;
    }

    /**
     * Returns the string representation of CK_TOKEN_INFO.
     *
     * @return the string representation of CK_TOKEN_INFO
     */
    public String toString() {

        String sb = Constants.INDENT +
                "label: " +
                new String(label) +
                Constants.NEWLINE +
                Constants.INDENT +
                "manufacturerID: " +
                new String(manufacturerID) +
                Constants.NEWLINE +
                Constants.INDENT +
                "model: " +
                new String(model) +
                Constants.NEWLINE +
                Constants.INDENT +
                "serialNumber: " +
                new String(serialNumber) +
                Constants.NEWLINE +
                Constants.INDENT +
                "flags: " +
                Functions.tokenInfoFlagsToString(flags) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulMaxSessionCount: " +
                ((ulMaxSessionCount == PKCS11Constants.CK_EFFECTIVELY_INFINITE)
                        ? "CK_EFFECTIVELY_INFINITE"
                        : (ulMaxSessionCount == PKCS11Constants.CK_UNAVAILABLE_INFORMATION)
                        ? "CK_UNAVAILABLE_INFORMATION"
                        : String.valueOf(ulMaxSessionCount)) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulSessionCount: " +
                ((ulSessionCount == PKCS11Constants.CK_UNAVAILABLE_INFORMATION)
                        ? "CK_UNAVAILABLE_INFORMATION"
                        : String.valueOf(ulSessionCount)) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulMaxRwSessionCount: " +
                ((ulMaxRwSessionCount == PKCS11Constants.CK_EFFECTIVELY_INFINITE)
                        ? "CK_EFFECTIVELY_INFINITE"
                        : (ulMaxRwSessionCount == PKCS11Constants.CK_UNAVAILABLE_INFORMATION)
                        ? "CK_UNAVAILABLE_INFORMATION"
                        : String.valueOf(ulMaxRwSessionCount)) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulRwSessionCount: " +
                ((ulRwSessionCount == PKCS11Constants.CK_UNAVAILABLE_INFORMATION)
                        ? "CK_UNAVAILABLE_INFORMATION"
                        : String.valueOf(ulRwSessionCount)) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulMaxPinLen: " +
                ulMaxPinLen +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulMinPinLen: " +
                ulMinPinLen +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulTotalPublicMemory: " +
                ((ulTotalPublicMemory == PKCS11Constants.CK_UNAVAILABLE_INFORMATION)
                        ? "CK_UNAVAILABLE_INFORMATION"
                        : String.valueOf(ulTotalPublicMemory)) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulFreePublicMemory: " +
                ((ulFreePublicMemory == PKCS11Constants.CK_UNAVAILABLE_INFORMATION)
                        ? "CK_UNAVAILABLE_INFORMATION"
                        : String.valueOf(ulFreePublicMemory)) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulTotalPrivateMemory: " +
                ((ulTotalPrivateMemory == PKCS11Constants.CK_UNAVAILABLE_INFORMATION)
                        ? "CK_UNAVAILABLE_INFORMATION"
                        : String.valueOf(ulTotalPrivateMemory)) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulFreePrivateMemory: " +
                ((ulFreePrivateMemory == PKCS11Constants.CK_UNAVAILABLE_INFORMATION)
                        ? "CK_UNAVAILABLE_INFORMATION"
                        : String.valueOf(ulFreePrivateMemory)) +
                Constants.NEWLINE +
                Constants.INDENT +
                "hardwareVersion: " +
                hardwareVersion.toString() +
                Constants.NEWLINE +
                Constants.INDENT +
                "firmwareVersion: " +
                firmwareVersion.toString() +
                Constants.NEWLINE +
                Constants.INDENT +
                "utcTime: " +
                new String(utcTime);
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
