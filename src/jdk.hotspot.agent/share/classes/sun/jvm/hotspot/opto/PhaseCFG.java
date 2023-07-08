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















public class PhaseCFG extends Phase {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type      = db.lookupType("PhaseCFG");
    numBlocksField = new CIntField(type.getCIntegerField("_number_of_blocks"), 0);
    blocksField = type.getAddressField("_blocks");
    bbsField = type.getAddressField("_node_to_block_mapping");
    brootField = type.getAddressField("_root_block");
  }

  private static CIntField numBlocksField;
  private static AddressField blocksField;
  private static AddressField bbsField;
  private static AddressField brootField;

  public PhaseCFG(Address addr) {
    super(addr);
  }

  public void dump(PrintStream out) {
    int addressSize = (int)VM.getVM().getAddressSize();
    int numBlocks = (int)numBlocksField.getValue(getAddress());
    Block_List blocks = new Block_List(getAddress().addOffsetTo(blocksField.getOffset()));
    int offset = 0;
    for (int i  = 0; i < numBlocks; i++) {
      blocks.at(i).dump(out);
    }
  }
}
