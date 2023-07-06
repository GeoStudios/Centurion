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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.interpreter.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class ExceptionTableElement {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type            = db.lookupType("ExceptionTableElement");
    offsetOfStartPC      = type.getCIntegerField("start_pc").getOffset();
    offsetOfEndPC        = type.getCIntegerField("end_pc").getOffset();
    offsetOfHandlerPC    = type.getCIntegerField("handler_pc").getOffset();
    offsetOfCatchTypeIndex = type.getCIntegerField("catch_type_index").getOffset();
  }

  private static long offsetOfStartPC;
  private static long offsetOfEndPC;
  private static long offsetOfHandlerPC;
  private static long offsetOfCatchTypeIndex;

  private final Address handle;
  private final long      offset;

  public ExceptionTableElement(Address handle, long offset) {
    this.handle = handle;
    this.offset = offset;
  }

  public int getStartPC() {
    return (int) handle.getCIntegerAt(offset + offsetOfStartPC, 2, true);
  }

  public int getEndPC() {
    return (int) handle.getCIntegerAt(offset + offsetOfEndPC, 2, true);
  }

  public int getHandlerPC() {
    return (int) handle.getCIntegerAt(offset + offsetOfHandlerPC, 2, true);
  }

  public int getCatchTypeIndex() {
    return (int) handle.getCIntegerAt(offset + offsetOfCatchTypeIndex, 2, true);
  }
}
