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
 * class CK_SESSION_INFO provides information about a session.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SESSION_INFO {&nbsp;&nbsp;
 *   CK_SLOT_ID slotID;&nbsp;&nbsp;
 *   CK_STATE state;&nbsp;&nbsp;
 *   CK_FLAGS flags;&nbsp;&nbsp;
 *   CK_ULONG ulDeviceError;&nbsp;&nbsp;
 * } CK_SESSION_INFO;
 * </PRE>
 *
 */
public class CK_SESSION_INFO {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_SLOT_ID slotID;
     * </PRE>
     */
    public long slotID;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_STATE state;
     * </PRE>
     */
    public long state;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;          /* see below */

    /* ulDeviceError was changed from CK_USHORT to CK_ULONG for
     * v2.0 */
    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulDeviceError;
     * </PRE>
     */
    public long ulDeviceError;  /* device-dependent error code */

    public CK_SESSION_INFO(long slotID, long state,
                           long flags, long ulDeviceError) {
        this.slotID = slotID;
        this.state = state;
        this.flags = flags;
        this.ulDeviceError = ulDeviceError;
    }

    /**
     * Returns the string representation of CK_SESSION_INFO.
     *
     * @return the string representation of CK_SESSION_INFO
     */
    public String toString() {

        String sb = Constants.INDENT +
                "slotID: " +
                slotID +
                Constants.NEWLINE +
                Constants.INDENT +
                "state: " +
                Functions.sessionStateToString(state) +
                Constants.NEWLINE +
                Constants.INDENT +
                "flags: " +
                Functions.sessionInfoFlagsToString(flags) +
                Constants.NEWLINE +
                Constants.INDENT +
                "ulDeviceError: " +
                Functions.toHexString(ulDeviceError);
        //buffer.append(Constants.NEWLINE);

        return sb;
    }

}
