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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;















/** A LoadObject models a DSO/DLL/EXE; that is, an entity relocated by
    the run-time linker. */

public interface LoadObject {
  /** Base address at which this loadobject was relocated at run-time */
  Address getBase();

  /** Full path name of this loadobject */
  String getName();

  /** Size of the loadobject in bytes (determines the range of program
      counters and data contained within this loadobject) */
  long getSize();

  /** Returns a debug info database for this loadobject if debug info
      is present; otherwise, returns null. */
  CDebugInfoDataBase getDebugInfoDataBase() throws DebuggerException;

  /** Get debug information for the given program counter. PC must be
      contained within this loadobject or a DebuggerException is
      thrown. Returns null if there is no debug information available
      (i.e., because this is not a debug build). */
  BlockSym debugInfoForPC(Address pc) throws DebuggerException;

  /** Fetch the name of the closest exported symbol and the distance
      of the PC to that symbol. Returns null if the PC was not within
      this loadobject or if a symbol could not be found before this
      PC. FIXME: specify whether this is mangled/demangled. */
  ClosestSymbol closestSymbolToPC(Address pc) throws DebuggerException;

  /** Returns line number information for the given PC, including
      source file name (not specified whether this is an absolute or
      relative path) and start and end PCs for this line. Returns null
      if no line number information is available or if the given PC is
      not in this loadobject. */
  LineNumberInfo lineNumberForPC(Address pc) throws DebuggerException;
}
