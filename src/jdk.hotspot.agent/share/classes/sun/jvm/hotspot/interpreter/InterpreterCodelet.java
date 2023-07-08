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

import java.io.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.code.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

/** An InterpreterCodelet is a piece of interpreter code. All
    interpreter code is generated into little codelets which contain
    extra information for debugging and printing purposes. */

public class InterpreterCodelet extends Stub {
  private static long          instanceSize;
  private static CIntegerField sizeField;             // the size in bytes
  private static AddressField  descriptionField;      // a description of the codelet, for debugging & printing
  private static CIntegerField bytecodeField;         // associated bytecode if any

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("InterpreterCodelet");

    sizeField            = type.getCIntegerField("_size");
    descriptionField     = type.getAddressField("_description");
    bytecodeField        = type.getCIntegerField("_bytecode");

    instanceSize = type.getSize();
  }

  public InterpreterCodelet(Address addr) {
    super(addr);
  }

  public long getSize() {
    return sizeField.getValue(addr);
  }

  public Address codeBegin() {
    return addr.addOffsetTo(instanceSize);
  }

  public Address codeEnd() {
    return addr.addOffsetTo(getSize());
  }

  public long codeSize() {
    return codeEnd().minus(codeBegin());
  }

  public String getDescription() {
    return CStringUtilities.getString(descriptionField.getValue(addr));
  }

  public void verify() {
  }

  public void printOn(PrintStream tty) {
    String desc = getDescription();
    if (desc != null) {
      tty.print(desc);
    }
    // FIXME: add printing of bytecode
    tty.println(" [" + codeBegin() + ", " + codeEnd() + ")  " +
                codeSize() + " bytes  ");
    // FIXME: add disassembly
  }
}
