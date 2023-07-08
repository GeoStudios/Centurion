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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities;

import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

// Superclass for symbol and string tables.

public class BasicHashtable extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("BasicHashtable<mtInternal>");
    tableSizeField = type.getCIntegerField("_table_size");
    bucketsField   = type.getAddressField("_buckets");
    bucketSize = db.lookupType("HashtableBucket<mtInternal>").getSize();
  }

  // Fields
  private static CIntegerField tableSizeField;
  private static AddressField  bucketsField;
  private static long bucketSize;

  // Accessors
  protected int tableSize() {
    return (int) tableSizeField.getValue(addr);
  }

  protected BasicHashtableEntry bucket(int i) {
    if (Assert.ASSERTS_ENABLED) {
       Assert.that(i >= 0 && i < tableSize(), "Invalid bucket id");
    }
    Address tmp = bucketsField.getValue(addr);
    tmp = tmp.addOffsetTo(i * bucketSize);
    HashtableBucket bucket = VMObjectFactory.newObject(
                                              HashtableBucket.class, tmp);
    return bucket.getEntry(getHashtableEntryClass());
  }

  // derived class may return Class<? extends BasicHashtableEntry>
  protected Class<? extends BasicHashtableEntry> getHashtableEntryClass() {
    return BasicHashtableEntry.class;
  }

  public BasicHashtable(Address addr) {
    super(addr);
  }
}
