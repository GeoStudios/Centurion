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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.basic;

import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;

public class BasicBlockSym extends BasicSym implements BlockSym {
  private BlockSym parent;
  private final long     length;
  private final Address  addr;

  private List<LocalSym> locals;

  /** Creates a new BlockSym. Parent can be null. */
  public BasicBlockSym(BlockSym parent, long length, Address addr, String name) {
    super(name);
    this.parent = parent;
    this.length = length;
    this.addr   = addr;
  }

  public BlockSym asBlock()   { return this; }

  public BlockSym getParent() { return parent; }
  public long getLength()     { return length; }
  public Address getAddress() { return addr; }

  public int getNumLocals() {
    if (locals == null) {
      return 0;
    }

    return locals.size();
  }

  public LocalSym getLocal(int i) {
    return locals.get(i);
  }

  public void addLocal(LocalSym local) {
    if (locals == null) {
      locals = new ArrayList<>();
    }
    locals.add(local);
  }

  public void resolve(BasicCDebugInfoDataBase db, ResolveListener listener) {
    parent = (BlockSym) db.resolveSym(this, parent, listener, "resolving parent of block");
    if (locals != null) {
      for (Iterator iter = locals.iterator(); iter.hasNext(); ) {
        ((BasicLocalSym) iter.next()).resolve(db, listener);
      }
    }
  }
}