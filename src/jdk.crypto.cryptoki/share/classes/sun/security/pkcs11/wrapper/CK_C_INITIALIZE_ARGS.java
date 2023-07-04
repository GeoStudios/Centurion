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
 * class CK_C_INITIALIZE_ARGS contains the optional arguments for the
 * C_Initialize function.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_C_INITIALIZE_ARGS {&nbsp;&nbsp;
 *   CK_CREATEMUTEX CreateMutex;&nbsp;&nbsp;
 *   CK_DESTROYMUTEX DestroyMutex;&nbsp;&nbsp;
 *   CK_LOCKMUTEX LockMutex;&nbsp;&nbsp;
 *   CK_UNLOCKMUTEX UnlockMutex;&nbsp;&nbsp;
 *   CK_FLAGS flags;&nbsp;&nbsp;
 *   CK_VOID_PTR pReserved;&nbsp;&nbsp;
 * } CK_C_INITIALIZE_ARGS;
 * </PRE>
 *
 */
public class CK_C_INITIALIZE_ARGS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CREATEMUTEX CreateMutex;
     * </PRE>
     */
    public CK_CREATEMUTEX  CreateMutex;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_DESTROYMUTEX DestroyMutex;
     * </PRE>
     */
    public CK_DESTROYMUTEX DestroyMutex;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_LOCKMUTEX LockMutex;
     * </PRE>
     */
    public CK_LOCKMUTEX    LockMutex;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UNLOCKMUTEX UnlockMutex;
     * </PRE>
     */
    public CK_UNLOCKMUTEX  UnlockMutex;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long            flags;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pReserved;
     * </PRE>
     */
    public Object          pReserved;

}
