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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class Block extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type      = db.lookupType("Block");
    nodesField = type.getAddressField("_nodes");
    succsField = type.getAddressField("_succs");
    numSuccsField = new CIntField(type.getCIntegerField("_num_succs"), 0);
    preOrderField = new CIntField(type.getCIntegerField("_pre_order"), 0);
    domDepthField = new CIntField(type.getCIntegerField("_dom_depth"), 0);
    idomField = type.getAddressField("_idom");
    freqField = type.getJDoubleField("_freq");
  }

  private static AddressField nodesField;
  private static AddressField succsField;
  private static CIntField numSuccsField;
  private static CIntField preOrderField;
  private static CIntField domDepthField;
  private static AddressField idomField;
  private static JDoubleField freqField;

  public Block(Address addr) {
    super(addr);
  }

  public int preOrder() {
    return (int)preOrderField.getValue(getAddress());
  }

  public double freq() {
    return freqField.getValue(getAddress());
  }

  public Node_List nodes() {
    return new Node_List(getAddress().addOffsetTo(nodesField.getOffset()));
  }

  public void dump(PrintStream out) {
    out.print("B" + preOrder());
    out.print(" Freq: " + freq());
    out.println();
    Node_List nl = nodes();
    int cnt = nl.size();
    for( int i=0; i<cnt; i++ )
      nl.at(i).dump(out);
    out.print("\n");
  }
}
