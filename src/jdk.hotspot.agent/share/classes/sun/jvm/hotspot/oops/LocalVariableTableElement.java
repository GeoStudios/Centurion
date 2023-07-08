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

public class LocalVariableTableElement {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type                 = db.lookupType("LocalVariableTableElement");
    offsetOfStartBCI          = type.getCIntegerField("start_bci").getOffset();
    offsetOfLength            = type.getCIntegerField("length").getOffset();
    offsetOfNameCPIndex       = type.getCIntegerField("name_cp_index").getOffset();
    offsetOfDescriptorCPIndex = type.getCIntegerField("descriptor_cp_index").getOffset();
    offsetOfSignatureCPIndex  = type.getCIntegerField("signature_cp_index").getOffset();
    offsetOfSlot              = type.getCIntegerField("slot").getOffset();
  }

  private static long offsetOfStartBCI;
  private static long offsetOfLength;
  private static long offsetOfNameCPIndex;
  private static long offsetOfDescriptorCPIndex;
  private static long offsetOfSignatureCPIndex;
  private static long offsetOfSlot;

  private final Address   handle;
  private final long      offset;

  public LocalVariableTableElement(Address handle, long offset) {
    this.handle = handle;
    this.offset = offset;
  }

  public int getStartBCI() {
    return (int) handle.getCIntegerAt(offset + offsetOfStartBCI, 2, true);
  }

  public int getLength() {
    return (int) handle.getCIntegerAt(offset + offsetOfLength, 2, true);
  }

  public int getNameCPIndex() {
    return (int) handle.getCIntegerAt(offset + offsetOfNameCPIndex, 2, true);
  }

  public int getDescriptorCPIndex() {
    return (int) handle.getCIntegerAt(offset + offsetOfDescriptorCPIndex, 2, true);
  }

  public int getSignatureCPIndex() {
    return (int) handle.getCIntegerAt(offset + offsetOfSignatureCPIndex, 2, true);
  }

  public int getSlot() {
    return (int) handle.getCIntegerAt(offset + offsetOfSlot, 2, true);
  }
}
