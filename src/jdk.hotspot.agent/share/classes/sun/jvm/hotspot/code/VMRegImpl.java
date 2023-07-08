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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.code;

import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class VMRegImpl {

  private static VMReg stack0;
  private static int stack0Val;
  private static Address stack0Addr;
  private static AddressField regNameField;
  private static int stackSlotSize;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static void initialize(TypeDataBase db) {
    Type type = db.lookupType("VMRegImpl");
    AddressField stack0Field = type.getAddressField("stack0");
    stack0Addr = stack0Field.getValue();
    stack0Val = stack0Addr.hashCode();
    stack0 = new VMReg(stack0Val);
    regNameField = type.getAddressField("regName[0]");
    stackSlotSize = db.lookupIntConstant("VMRegImpl::stack_slot_size");
  }

  public static VMReg getStack0() {
    return stack0;
  }

  public static String getRegisterName(int index) {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(index >= 0 && index < stack0Val, "invalid index : " + index);
    }
    Address regName = regNameField.getStaticFieldAddress();
    long addrSize = VM.getVM().getAddressSize();
    return CStringUtilities.getString(regName.getAddressAt(index * addrSize));
  }

  public static int getStackSlotSize() {
    return stackSlotSize;
  }
}
