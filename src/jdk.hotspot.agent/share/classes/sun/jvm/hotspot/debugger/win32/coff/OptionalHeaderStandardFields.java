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

public interface OptionalHeaderStandardFields {
  byte getMajorLinkerVersion();
  byte getMinorLinkerVersion();

  /** Size of the code (text) section, or the sum of all code sections
      if there are multiple sections. */
  int getSizeOfCode();

  /** Size of the initialized data section, or the sum of all such
      sections if there are multiple data sections. */
  int getSizeOfInitializedData();

  /** Size of the uninitialized data section (BSS), or the sum of all
      such sections if there are multiple BSS sections. */
  int getSizeOfUninitializedData();

  /** Address of entry point, relative to image base, when executable
      file is loaded into memory. For program images, this is the
      starting address. For device drivers, this is the address of the
      initialization function. An entry point is optional for DLLs.
      When none is present this field should be 0. */
  int getAddressOfEntryPoint();

  /** Address, relative to image base, of beginning of code section,
      when loaded into memory. */
  int getBaseOfCode();

  /** Onle present in PE32 files; absent in PE32+ files. Address,
      relative to image base, of beginning of data section, when
      loaded into memory. */
  int getBaseOfData() throws COFFException;
}
