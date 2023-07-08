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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.interpreter;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;

// any bytecode with constant pool index

public abstract class BytecodeWithCPIndex extends Bytecode {
  BytecodeWithCPIndex(Method method, int bci) {
    super(method, bci);
  }

  // the constant pool index for this bytecode
  public int index() {
    if (code() == Bytecodes._invokedynamic) {
      int index = getIndexU4();
      if (ConstantPool.isInvokedynamicIndex(index)) {
        return ConstantPool.decodeInvokedynamicIndex(index);
      } else {
        return index;
      }
    } else {
      return getIndexU2(code(), false);
    }
  }

  protected int indexForFieldOrMethod() {
     ConstantPoolCache cpCache = method().getConstants().getCache();
     // get ConstantPool index from ConstantPoolCacheIndex at given bci
     int cpCacheIndex = index();
     if (cpCache == null) {
        return cpCacheIndex;
     } else {
      return cpCache.getEntryAt(0xFFFF & cpCacheIndex).getConstantPoolIndex();
     }
  }
}
