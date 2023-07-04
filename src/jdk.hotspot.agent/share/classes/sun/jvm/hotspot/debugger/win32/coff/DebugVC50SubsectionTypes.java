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

package sun.jvm.hotspot.debugger.win32.coff;

/** Type enumeration for subsections in Visual C++ 5.0 debug
    information. */

public interface DebugVC50SubsectionTypes {
  short SST_MODULE        = (short) 0x120;
  short SST_TYPES         = (short) 0x121;
  short SST_PUBLIC        = (short) 0x122;
  short SST_PUBLIC_SYM    = (short) 0x123;
  short SST_SYMBOLS       = (short) 0x124;
  short SST_ALIGN_SYM     = (short) 0x125;
  short SST_SRC_LN_SEG    = (short) 0x126;
  short SST_SRC_MODULE    = (short) 0x127;
  short SST_LIBRARIES     = (short) 0x128;
  short SST_GLOBAL_SYM    = (short) 0x129;
  short SST_GLOBAL_PUB    = (short) 0x12a;
  short SST_GLOBAL_TYPES  = (short) 0x12b;
  short SST_MPC           = (short) 0x12c;
  short SST_SEG_MAP       = (short) 0x12d;
  short SST_SEG_NAME      = (short) 0x12e;
  short SST_PRE_COMP      = (short) 0x12f;
  short SST_UNUSED        = (short) 0x130;
  short SST_OFFSET_MAP_16 = (short) 0x131;
  short SST_OFFSET_MAP_32 = (short) 0x132;
  short SST_FILE_INDEX    = (short) 0x133;
  short SST_STATIC_SYM    = (short) 0x134;
}
