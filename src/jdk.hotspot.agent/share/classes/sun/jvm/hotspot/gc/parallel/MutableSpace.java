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

public class MutableSpace extends VMObject {
   static {
      VM.registerVMInitializedObserver(new Observer() {
         public void update(Observable o, Object data) {
            initialize(VM.getVM().getTypeDataBase());
         }
      });
   }

   private static synchronized void initialize(TypeDataBase db) {
      Type type = db.lookupType("MutableSpace");
      bottomField = type.getAddressField("_bottom");
      endField    = type.getAddressField("_end");
      topField    = type.getAddressField("_top");
   }

   public MutableSpace(Address addr) {
      super(addr);
   }

   // Fields
   private static AddressField bottomField;
   private static AddressField endField;
   private static AddressField topField;

   // Accessors
   public Address   bottom()       { return bottomField.getValue(addr); }
   public Address   end()          { return endField.getValue(addr);    }
   public Address   top()          { return topField.getValue(addr);    }

   /** In bytes */
   public long used() {
      return top().minus(bottom());
   }

   /** Returned value is in bytes */
   public long capacity() { return end().minus(bottom()); }

   /** Returns a subregion of the space containing all the objects in
      the space. */
   public MemRegion usedRegion() {
      return new MemRegion(bottom(), end());
   }

   /** Support for iteration over heap -- not sure how this will
      interact with GC in reflective system, but necessary for the
      debugging mechanism */
   public OopHandle bottomAsOopHandle() {
      return bottomField.getOopHandle(addr);
   }

   /** returns all MemRegions where live objects are */
   public List<MemRegion> getLiveRegions() {
      List<MemRegion> res = new ArrayList<>();
      res.add(new MemRegion(bottom(), top()));
      return res;
   }

   /** Testers */
   public boolean contains(Address p) {
      return (bottom().lessThanOrEqual(p) && end().greaterThan(p));
   }

   public void print() { printOn(System.out); }
   public void printOn(PrintStream tty) {
      tty.print(" [" + bottom() + "," +
                top() + "," + end() + "] ");
   }
}
