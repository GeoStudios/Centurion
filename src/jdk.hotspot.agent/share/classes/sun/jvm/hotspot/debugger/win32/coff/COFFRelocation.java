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

public interface COFFRelocation {
  /** Address of the item to which relocation is applied: this is the
      offset from the beginning of the section, plus the value of the
      section's RVA/Offset field (see {@link
      sun.jvm.hotspot.debugger.win32.coff.SectionHeader}.) For example, if
      the first byte of the section has an address of 0x10, the third
      byte has an address of 0x12. */
  int getVirtualAddress();

  /** A zero-based index into the symbol table. This symbol gives the
      address to be used for the relocation. If the specified symbol
      has section storage class, then the symbol's address is the
      address with the first section of the same name. */
  int getSymbolTableIndex();

  /** A value indicating what kind of relocation should be
      performed. Valid relocation types depend on machine type. See
      {@link sun.jvm.hotspot.debugger.win32.coff.TypeIndicators}. */
  short getType();
}
