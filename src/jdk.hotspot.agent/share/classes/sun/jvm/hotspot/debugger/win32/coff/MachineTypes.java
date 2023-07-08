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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.win32.coff;

















public interface MachineTypes {
  /** Contents assumed to be applicable to any machine type. */
  short IMAGE_FILE_MACHINE_UNKNOWN = (short) 0x0;
  /** Alpha AXP. */
  short IMAGE_FILE_MACHINE_ALPHA = (short) 0x184;
  short IMAGE_FILE_MACHINE_ARM = (short) 0x1c0;
  /** Alpha AXP 64-bit. */
  short IMAGE_FILE_MACHINE_ALPHA64 = (short) 0x284;
  /** Intel 386 or later, and compatible processors. */
  short IMAGE_FILE_MACHINE_I386 = (short) 0x14c;
  /** Motorola 68000 series. */
  short IMAGE_FILE_MACHINE_M68K = (short) 0x268;
  short IMAGE_FILE_MACHINE_MIPS16 = (short) 0x266;
  /** MIPS with FPU */
  short IMAGE_FILE_MACHINE_MIPSFPU = (short) 0x366;
  /** MIPS16 with FPU */
  short IMAGE_FILE_MACHINE_MIPSFPU16 = (short) 0x466;
  /** Power PC, little endian. */
  short IMAGE_FILE_MACHINE_POWERPC = (short) 0x1f0;
  short IMAGE_FILE_MACHINE_R3000 = (short) 0x162;
  /** MIPS little endian. */
  short IMAGE_FILE_MACHINE_R4000 = (short) 0x166;
  short IMAGE_FILE_MACHINE_R10000 = (short) 0x168;
  /** Hitachi SH3 */
  short IMAGE_FILE_MACHINE_SH3 = (short) 0x1a2;
  /** Hitachi SH4 */
  short IMAGE_FILE_MACHINE_SH4 = (short) 0x1a6;
  short IMAGE_FILE_MACHINE_THUMB = (short) 0x1c2;
}
