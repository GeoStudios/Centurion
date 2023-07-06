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

package jdk.compiler.share.classes.com.sun.tools.javac.jvm;

/** The CharacterRangeTable flags indicating type of an entry.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public interface CRTFlags {

    /** CRTEntry flags.
     */
    int CRT_STATEMENT       = 0x0001;
    int CRT_BLOCK           = 0x0002;
    int CRT_ASSIGNMENT      = 0x0004;
    int CRT_FLOW_CONTROLLER = 0x0008;
    int CRT_FLOW_TARGET     = 0x0010;
    int CRT_INVOKE          = 0x0020;
    int CRT_CREATE          = 0x0040;
    int CRT_BRANCH_TRUE     = 0x0080;
    int CRT_BRANCH_FALSE    = 0x0100;

    /** The mask for valid flags
     */
    int CRT_VALID_FLAGS = CRT_STATEMENT | CRT_BLOCK | CRT_ASSIGNMENT |
                                              CRT_FLOW_CONTROLLER | CRT_FLOW_TARGET | CRT_INVOKE |
                                              CRT_CREATE | CRT_BRANCH_TRUE | CRT_BRANCH_FALSE;
}