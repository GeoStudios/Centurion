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

/** Database for C and C++ debug information. This is being kept as
    minimal as possible for now. It is not complete; for example, it
    will have to be extended to support scoped information (module
    scope, namespace scope). */

public interface CDebugInfoDataBase {
  /** Name-to-type mapping */
  Type lookupType(String name);

  /** Name-to-type mapping with const/volatile qualifications */
  Type lookupType(String name, int cvAttributes);

  /** Iteration through all types */
  void iterate(TypeVisitor t);

  /** Return debug info (closest lexically-enclosing block) for
      current program counter. Returns null if no debug information
      found or available. */
  BlockSym debugInfoForPC(Address pc);

  /** Look up global or module-local symbol by name. FIXME: need some
      way to identify modules -- has not been thought through yet
      because it isn't clear exactly how these are represented in the
      Visual C++ debug info. */
  GlobalSym lookupSym(String name);

  /** Returns line number information for the given PC, including
      source file name (not specified whether this is an absolute or
      relative path) and start and end PCs for this line. Returns null
      if no line number information is available. */
  LineNumberInfo lineNumberForPC(Address pc) throws DebuggerException;

  /** Iteration through all line number information in this
      database. */
  void iterate(LineNumberVisitor v);
}
