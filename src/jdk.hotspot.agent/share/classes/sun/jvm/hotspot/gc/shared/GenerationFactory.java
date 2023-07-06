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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared;


import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.serial.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















/** Factory containing a VirtualConstructor suitable for instantiating
    wrapper objects for all types of generations */

public class GenerationFactory {
  private static VirtualConstructor ctor;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    ctor = new VirtualConstructor(db);

    ctor.addMapping("DefNewGeneration", DefNewGeneration.class);
    ctor.addMapping("TenuredGeneration", TenuredGeneration.class);
  }

  public static Generation newObject(Address addr) {
      try {
          return (Generation) ctor.instantiateWrapperFor(addr);
      } catch (WrongTypeException e) {
          return new Generation(addr) {
                  public String name() {
                      return "unknown generation type";
                  }
                  public void spaceIterate(SpaceClosure blk, boolean usedOnly) {
                  }
                  public void liveRegionsIterate(LiveRegionsClosure closure) {
                  }
                  public void printOn(java.io.PrintStream tty) {
                      tty.println("unknown subtype of Generation @ " + getAddress() + " (" +
                                  virtualSpace().low() + "," + virtualSpace().high() + ")");
                  }
                  public long used() {
                      return 0;
                  }
                  public long free() {
                      return 0;
                  }
                  public long capacity() {
                      return 0;
                  }
                  public long contiguousAvailable() {
                      return 0;
                  }

              };
      }
  }
}
