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

public class BytecodeIf extends BytecodeJmp {
  BytecodeIf(Method method, int bci) {
    super(method, bci);
  }

  public int getTargetBCI() {
    return bci() + javaShortAt(1);
  }

  public void verify() {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(isValid(), "check if");
    }
  }

  public boolean isValid() {
    int jcode = javaCode();
    return (jcode >= Bytecodes._ifeq && jcode <= Bytecodes._if_acmpne) ||
           jcode == Bytecodes._ifnull || jcode == Bytecodes._ifnonnull;
  }

  public static BytecodeIf at(Method method, int bci) {
    BytecodeIf b = new BytecodeIf(method, bci);
    if (Assert.ASSERTS_ENABLED) {
      b.verify();
    }
    return b;
  }

  /** Like at, but returns null if the BCI is not at some ifxxx bytecode  */
  public static BytecodeIf atCheck(Method method, int bci) {
    BytecodeIf b = new BytecodeIf(method, bci);
    return (b.isValid() ? b : null);
  }

  public static BytecodeIf at(BytecodeStream bcs) {
    return new BytecodeIf(bcs.method(), bcs.bci());
  }
}
