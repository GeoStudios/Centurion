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

/** Base interface for subsections containing symbols: sstGlobalSym,
    sstGlobalPub, sstStaticSym. */

public interface DebugVC50SSSymbolBase extends DebugVC50Subsection {
  /** Index of the symbol hash function */
  short getSymHashIndex();

  /** Index of the address hash function */
  short getAddrHashIndex();

  /** Size in bytes of the symbol table */
  int getSymTabSize();

  /** Size in bytes of the symbol hash table */
  int getSymHashSize();

  /** Size in bytes of the address hash table */
  int getAddrHashSize();

  // Following this header is the symbol information, followed by the
  // symbol hash tables, followed by the address hash tables.

  /** Retrieves an iterator over the symbols, which can be used to
      parse these platform-dependent symbols into a platform-
      independent format. Returns null if there are no symbols. */
  DebugVC50SymbolIterator getSymbolIterator();
}
