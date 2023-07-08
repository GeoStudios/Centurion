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

public class BytecodeNewArray extends Bytecode {
  BytecodeNewArray(Method method, int bci) {
    super(method, bci);
  }

  public int getType() {
    return javaByteAt(1);
  }

  public void verify() {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(isValid(), "check newarray");
    }
  }

  public boolean isValid() {
    boolean result = javaCode() == Bytecodes._newarray;
    if (!result) return false;
    switch (getType()) {
       case TypeArrayKlass.T_BOOLEAN:
       case TypeArrayKlass.T_CHAR:
       case TypeArrayKlass.T_FLOAT:
       case TypeArrayKlass.T_DOUBLE:
       case TypeArrayKlass.T_BYTE:
       case TypeArrayKlass.T_SHORT:
       case TypeArrayKlass.T_INT:
       case TypeArrayKlass.T_LONG:
          break;
       default:
          return false;
     }

     return true;
  }

  public String getTypeName() {
     String result;
     switch (getType()) {
       case TypeArrayKlass.T_BOOLEAN:
          result = "boolean";
          break;

       case TypeArrayKlass.T_CHAR:
          result = "char";
          break;

       case TypeArrayKlass.T_FLOAT:
          result = "float";
          break;

       case TypeArrayKlass.T_DOUBLE:
          result = "double";
          break;

       case TypeArrayKlass.T_BYTE:
          result = "byte";
          break;

       case TypeArrayKlass.T_SHORT:
          result = "short";
          break;

       case TypeArrayKlass.T_INT:
          result = "int";
          break;

       case TypeArrayKlass.T_LONG:
          result = "long";
          break;

       default: // should not happen
          result = "<invalid>";
          break;
     }

     return result;
  }

  public static BytecodeNewArray at(Method method, int bci) {
    BytecodeNewArray b = new BytecodeNewArray(method, bci);
    if (Assert.ASSERTS_ENABLED) {
      b.verify();
    }
    return b;
  }

  /** Like at, but returns null if the BCI is not at newarray  */
  public static BytecodeNewArray atCheck(Method method, int bci) {
    BytecodeNewArray b = new BytecodeNewArray(method, bci);
    return (b.isValid() ? b : null);
  }

  public static BytecodeNewArray at(BytecodeStream bcs) {
    return new BytecodeNewArray(bcs.method(), bcs.bci());
  }

  public String toString() {
      String buf = "newarray" +
              spaces +
              getTypeName();
    return buf;
  }
}
