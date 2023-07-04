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

/** Models the information stored in the COFF header of either a
    Portable Executable or object file. */

public interface COFFHeader {
  /** Returns one of the constants in {@link
      sun.jvm.hotspot.debugger.win32.coff.MachineTypes}. */
  short getMachineType();

  /** Number of sections; indicates size of the Section Table, which
      immediately follows the headers. */
  short getNumberOfSections();

  /** Time and date the file was created. */
  int getTimeDateStamp();

  /** File offset of the COFF symbol table or 0 if none is present. */
  int getPointerToSymbolTable();

  /** Number of entries in the symbol table. This data can be used in
      locating the string table, which immediately follows the symbol
      table. */
  int getNumberOfSymbols();

  /** Size of the optional header, which is required for executable
      files but not for object files. An object file should have a
      value of 0 here. */
  short getSizeOfOptionalHeader();

  /** Returns the optional header if one is present or null if not. */
  OptionalHeader getOptionalHeader() throws COFFException;

  /** Gets the union of all characteristics set for this object or
      image file. See {@link
      sun.jvm.hotspot.debugger.win32.coff.Characteristics}. */
  short getCharacteristics();

  /** Indicates whether this file has the given characteristic. The
      argument must be one of the constants specified in {@link
      sun.jvm.hotspot.debugger.win32.coff.Characteristics}. */
  boolean hasCharacteristic(short characteristic);

  /** Retrieves the section header at the given index, between
      1 and getNumberOfSections(). <B>NOTE</B>: This index is one-based,
      so the first section is numbered one, not zero. */
  SectionHeader getSectionHeader(int index);

  /** Retrieves the COFF symbol at the given index, between 0 and
      getNumberOfSymbols() - 1. This is distinct from CodeView
      information. */
  COFFSymbol getCOFFSymbol(int index);

  /** Returns the number of strings in the String Table, which
      immediately follows the COFF Symbol Table. */
  int getNumberOfStrings();

  /** Retrieves the <i>i</i>th string (0..{@link #getNumberOfStrings} - 1)
      from the string table. */
  String getString(int i);
}
