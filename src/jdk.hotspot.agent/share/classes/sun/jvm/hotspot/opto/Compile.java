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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.opto;

import java.util.*;
import java.io.PrintStream;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ci.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class Compile extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type      = db.lookupType("Compile");
    rootField = type.getAddressField("_root");
    uniqueField = new CIntField(type.getCIntegerField("_unique"), 0);
    entryBciField = new CIntField(type.getCIntegerField("_entry_bci"), 0);
    topField = type.getAddressField("_top");
    cfgField = type.getAddressField("_cfg");
    regallocField = type.getAddressField("_regalloc");
    methodField = type.getAddressField("_method");
    iltField = type.getAddressField("_ilt");
  }

  private static AddressField rootField;
  private static CIntField uniqueField;
  private static CIntField entryBciField;
  private static AddressField topField;
  private static AddressField cfgField;
  private static AddressField regallocField;
  private static AddressField methodField;
  private static AddressField iltField;

  public Compile(Address addr) {
    super(addr);
  }

  public Node root() {
    return new RootNode(rootField.getValue(this.getAddress()));
  }

  public int entryBci() {
    return (int)entryBciField.getValue(getAddress());
  }

  public ciMethod method() {
    return (ciMethod) ciObjectFactory.getMetadata(methodField.getValue(getAddress()));
  }

  public PhaseCFG cfg() {
    Address a = cfgField.getValue(this.getAddress());
    if (a != null) {
      return new PhaseCFG(a);
    }
    return null;
  }

  public InlineTree ilt() {
    Address a = iltField.getValue(this.getAddress());
    if (a != null) {
      return new InlineTree(a);
    }
    return null;
  }

  public void dumpInlineData(PrintStream out) {
    InlineTree inlTree = ilt();
    if (inlTree != null) {
      out.print(" inline " + inlTree.count());
      inlTree.dumpReplayData(out);
    }
  }

}
