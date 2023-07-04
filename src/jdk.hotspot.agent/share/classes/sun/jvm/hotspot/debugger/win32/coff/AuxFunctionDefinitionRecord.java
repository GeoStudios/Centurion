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

public interface AuxFunctionDefinitionRecord extends AuxSymbolRecord {
  /** Symbol-table index of the corresponding .bf (begin function)
      symbol record. */
  int getTagIndex();

  /** Size of the executable code for the function itself. If the
      function is in its own section, the Size of Raw Data in the
      section header will be greater or equal to this field, depending
      on alignment considerations. */
  int getTotalSize();

  /** Index of the first COFF line-number entry for the function in
      the global array of line numbers (see {@link
      sun.jvm.hotspot.debugger.win32.coff.SectionHeader#getCOFFLineNumber}),
      or -1 if none exists. */
  int getPointerToLineNumber();

  /** Symbol-table index of the record for the next function. If the
      function is the last in the symbol table, this field is set to
      zero. */
  int getPointerToNextFunction();
}
