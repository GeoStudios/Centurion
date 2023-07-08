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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops;


import java.io.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















public class MethodCounters extends Metadata {
  public MethodCounters(Address addr) {
    super(addr);
  }

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type      = db.lookupType("MethodCounters");

    if (VM.getVM().isServerCompiler()) {
      interpreterThrowoutCountField = new CIntField(type.getCIntegerField("_interpreter_throwout_count"), 0);
    }
    if (!VM.getVM().isCore()) {
      invocationCounter        = new CIntField(type.getCIntegerField("_invocation_counter"), 0);
      backedgeCounter          = new CIntField(type.getCIntegerField("_backedge_counter"), 0);
    }
  }

  private static CIntField interpreterThrowoutCountField;
  private static CIntField invocationCounter;
  private static CIntField backedgeCounter;

  public int interpreterThrowoutCount() {
      if (interpreterThrowoutCountField != null) {
        return (int) interpreterThrowoutCountField.getValue(this);
      } else {
        return 0;
      }
  }
  public long getInvocationCounter() {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(!VM.getVM().isCore(), "must not be used in core build");
    }
    return invocationCounter.getValue(this);
  }
  public long getBackedgeCounter() {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(!VM.getVM().isCore(), "must not be used in core build");
    }
    return backedgeCounter.getValue(this);
  }

  public void printValueOn(PrintStream tty) {
  }
}

