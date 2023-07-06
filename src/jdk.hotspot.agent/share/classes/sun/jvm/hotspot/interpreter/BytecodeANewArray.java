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

public class BytecodeANewArray extends BytecodeWithKlass {
  BytecodeANewArray(Method method, int bci) {
    super(method, bci);
  }

  public Klass getKlass() {
    return super.getKlass();
  }

  public void verify() {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(isValid(), "check anewarray");
    }
  }

  public boolean isValid() {
    return javaCode() == Bytecodes._anewarray;
  }

  public static BytecodeANewArray at(Method method, int bci) {
    BytecodeANewArray b = new BytecodeANewArray(method, bci);
    if (Assert.ASSERTS_ENABLED) {
      b.verify();
    }
    return b;
  }

  /** Like at, but returns null if the BCI is not at anewarray  */
  public static BytecodeANewArray atCheck(Method method, int bci) {
    BytecodeANewArray b = new BytecodeANewArray(method, bci);
    return (b.isValid() ? b : null);
  }

  public static BytecodeANewArray at(BytecodeStream bcs) {
    return new BytecodeANewArray(bcs.method(), bcs.bci());
  }
}
