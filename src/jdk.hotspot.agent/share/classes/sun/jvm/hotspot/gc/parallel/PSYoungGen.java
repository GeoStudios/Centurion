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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.parallel;

import java.io.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class PSYoungGen extends VMObject {
   static {
      VM.registerVMInitializedObserver(new Observer() {
         public void update(Observable o, Object data) {
            initialize(VM.getVM().getTypeDataBase());
         }
      });
   }

   private static synchronized void initialize(TypeDataBase db) {
      Type type = db.lookupType("PSYoungGen");
      edenSpaceField = type.getAddressField("_eden_space");
      fromSpaceField = type.getAddressField("_from_space");
      toSpaceField = type.getAddressField("_to_space");
   }

   public PSYoungGen(Address addr) {
      super(addr);
   }

   // Fields
   private static AddressField edenSpaceField;
   private static AddressField fromSpaceField;
   private static AddressField toSpaceField;

   // Accessors
   public MutableSpace edenSpace() {
      return VMObjectFactory.newObject(MutableSpace.class, edenSpaceField.getValue(addr));
   }

   public MutableSpace fromSpace() {
      return VMObjectFactory.newObject(MutableSpace.class, fromSpaceField.getValue(addr));
   }

   public MutableSpace toSpace() {
      return VMObjectFactory.newObject(MutableSpace.class, toSpaceField.getValue(addr));
   }

   public long capacity() {
      return edenSpace().capacity() + fromSpace().capacity();
   }

   public long used() {
      return edenSpace().used() + fromSpace().used();
   }

   public boolean isIn(Address a) {
      if (edenSpace().contains(a)) {
         return true;
      }

      return fromSpace().contains(a);
   }

   public void printOn(PrintStream tty) {
      tty.print("PSYoungGen [ ");
      tty.print("eden = ");
      edenSpace().printOn(tty);
      tty.print(", from = ");
      fromSpace().printOn(tty);
      tty.print(", to = ");
      toSpace().printOn(tty);
      tty.print(" ] ");
   }
}
