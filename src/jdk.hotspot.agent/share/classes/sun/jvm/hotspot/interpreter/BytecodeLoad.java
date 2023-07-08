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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;















public class BytecodeLoad extends BytecodeLoadStore {
  BytecodeLoad(Method method, int bci) {
    super(method, bci);
  }

  public void verify() {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(isValid(), "check load");
    }
  }

  public boolean isValid() {
    int jcode = javaCode();
    switch (jcode) {
       case Bytecodes._iload:
       case Bytecodes._lload:
       case Bytecodes._fload:
       case Bytecodes._dload:
       case Bytecodes._aload:
          return true;
       default:
          return false;
    }
  }

  public static BytecodeLoad at(Method method, int bci) {
    BytecodeLoad b = new BytecodeLoad(method, bci);
    if (Assert.ASSERTS_ENABLED) {
      b.verify();
    }
    return b;
  }

  /** Like at, but returns null if the BCI is not at load  */
  public static BytecodeLoad atCheck(Method method, int bci) {
    BytecodeLoad b = new BytecodeLoad(method, bci);
    return (b.isValid() ? b : null);
  }

  public static BytecodeLoad at(BytecodeStream bcs) {
    return new BytecodeLoad(bcs.method(), bcs.bci());
  }
}
