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

public interface COFFLineNumber {
  /** <P> Union of two fields: Symbol Table Index and RVA. Whether
      Symbol Table Index or RVA is used depends on the value of
      getLineNumber(). </P>

      <P> SymbolTableIndex is used when getLineNumber() is 0: index to
      symbol table entry for a function. This format is used to
      indicate the function that a group of line-number records refer
      to. </P>

      <P> VirtualAddress is used when LineNumber is non-zero: relative
      virtual address of the executable code that corresponds to the
      source line indicated. In an object file, this contains the
      virtual address within the section. </P> */
  int getType();

  /** When nonzero, this field specifies a one-based line number. When
      zero, the Type field is interpreted as a Symbol Table Index for
      a function. */
  short getLineNumber();
}
