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

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;

public class BasicFunctionSym extends BasicBlockSym implements FunctionSym {
  private Type         type;
  private final boolean      isModuleLocal;

  public BasicFunctionSym(BlockSym parent, long length, Address addr, String name,
                          Type type, boolean isModuleLocal) {
    super(parent, length, addr, name);
    this.type          = type;
    this.isModuleLocal = isModuleLocal;
  }

  public FunctionSym  asFunction()    { return this; }

  public Type         getType()       { return type; }
  public boolean      isModuleLocal() { return isModuleLocal; }

  public void resolve(BasicCDebugInfoDataBase db, ResolveListener listener) {
    super.resolve(db, listener);
    type = db.resolveType(this, type, listener, "resolving type of function symbol");
  }

  public String toString() {
    if (getName() == null) {
      return null;
    }

    StringBuilder res = new StringBuilder();
    res.append(getName());
    res.append("(");
    FunctionType type = (FunctionType) getType();
    if (type != null) {
      int nargs = type.getNumArguments();
      for (int i = 0; i < nargs; i++) {
        res.append(type.getArgumentType(i).toString());
        if (i != nargs - 1) {
          res.append(", ");
        }
      }
    }
    res.append(")");
    return res.toString();
  }
}