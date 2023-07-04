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
 * class CK_SLOT_INFO provides information about a slot.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_SLOT_INFO {&nbsp;&nbsp;
 *    CK_UTF8CHAR slotDescription[64];&nbsp;&nbsp;
 *    CK_UTF8CHAR manufacturerID[32];&nbsp;&nbsp;
 *    CK_FLAGS flags;&nbsp;&nbsp;
 *    CK_VERSION hardwareVersion;&nbsp;&nbsp;
 *    CK_VERSION firmwareVersion;&nbsp;&nbsp;
 *  } CK_SLOT_INFO;
 * </PRE>
 *
 */
public class CK_SLOT_INFO {

    /* slotDescription and manufacturerID have been changed from
     * CK_CHAR to CK_UTF8CHAR for v2.11. */

    /**
     * must be blank padded and only the first 64 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR slotDescription[64];
     * </PRE>
     */
    public char[] slotDescription;

    /**
     * must be blank padded and only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR manufacturerID[32];
     * </PRE>
     */
    public char[] manufacturerID;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;

    /* hardwareVersion and firmwareVersion are new for v2.0 */
    /**
     * version of hardware<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION hardwareVersion;
     * </PRE>
     */
    public CK_VERSION hardwareVersion;

    /**
     * version of firmware<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION firmwareVersion;
     * </PRE>
     */
    public CK_VERSION firmwareVersion;

    public CK_SLOT_INFO(char[] slotDesc, char[] vendor,
                        long flags, CK_VERSION hwVer, CK_VERSION fwVer) {
        this.slotDescription = slotDesc;
        this.manufacturerID = vendor;
        this.flags = flags;
        this.hardwareVersion = hwVer;
        this.firmwareVersion = fwVer;
    }

    /**
     * Returns the string representation of CK_SLOT_INFO.
     *
     * @return the string representation of CK_SLOT_INFO
     */
    public String toString() {

        String sb = Constants.INDENT +
                "slotDescription: " +
                new String(slotDescription) +
                Constants.NEWLINE +
                Constants.INDENT +
                "manufacturerID: " +
                new String(manufacturerID) +
                Constants.NEWLINE +
                Constants.INDENT +
                "flags: " +
                Functions.slotInfoFlagsToString(flags) +
                Constants.NEWLINE +
                Constants.INDENT +
                "hardwareVersion: " +
                hardwareVersion.toString() +
                Constants.NEWLINE +
                Constants.INDENT +
                "firmwareVersion: " +
                firmwareVersion.toString();
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
