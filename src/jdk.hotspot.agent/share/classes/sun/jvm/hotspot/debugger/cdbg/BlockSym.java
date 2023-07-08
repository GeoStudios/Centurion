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

/** A BlockSym models a lexical scope in a block-structured
    language. It is (currently) the bottommost scope type. */

public interface BlockSym extends Sym {
  /** Get the lexically enclosing block, or null if none */
  BlockSym getParent();

  /** Length in bytes of the machine code in this block */
  long getLength();

  /** Address of the first machine instruction in this block */
  Address getAddress();

  /** Name of this block, or null if none */
  String getName();

  /** Number of local variable symbols associated with this block */
  int getNumLocals();

  /** Return <i>i</i>th local (0..getNumLocals() - 1) */
  LocalSym getLocal(int i);
}
