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

public interface DebugTypes {

  /** Unknown value, ignored by all tools. */
  int IMAGE_DEBUG_TYPE_UNKNOWN = 0;

  /** COFF debug information (line numbers, symbol table, and string
      table). This type of debug information is also pointed to by
      fields in the file headers. */
  int IMAGE_DEBUG_TYPE_COFF = 1;

  /** CodeView debug information. The format of the data block is
      described by the CV4 specification. */
  int IMAGE_DEBUG_TYPE_CODEVIEW = 2;

  /** Frame Pointer Omission (FPO) information. This information tells
      the debugger how to interpret non-standard stack frames, which
      use the EBP register for a purpose other than as a frame
      pointer. */
  int IMAGE_DEBUG_TYPE_FPO = 3;

  int IMAGE_DEBUG_TYPE_MISC = 4;
  int IMAGE_DEBUG_TYPE_EXCEPTION = 5;
  int IMAGE_DEBUG_TYPE_FIXUP = 6;
  int IMAGE_DEBUG_TYPE_OMAP_TO_SRC = 7;
  int IMAGE_DEBUG_TYPE_OMAP_FROM_SRC = 8;
  int IMAGE_DEBUG_TYPE_BORLAND = 9;
}
