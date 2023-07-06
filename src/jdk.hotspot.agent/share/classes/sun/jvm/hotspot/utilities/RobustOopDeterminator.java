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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.Metadata;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.Klass;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

/** This class determines to the best of its ability, and in a
    reasonably robust fashion, whether a given pointer is an intact
    oop or not. It does this by checking the integrity of the
    metaclass hierarchy. This is only intended for use in the
    debugging system. It may provide more resilience to unexpected VM
    states than the ObjectHeap code. */

public class RobustOopDeterminator {
  private static AddressField klassField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static void initialize(TypeDataBase db) {
    Type type = db.lookupType("oopDesc");

    if (VM.getVM().isCompressedKlassPointersEnabled()) {
      klassField = type.getAddressField("_metadata._compressed_klass");
    } else {
      klassField = type.getAddressField("_metadata._klass");
    }
  }

  public static boolean oopLooksValid(OopHandle oop) {
    if (oop == null) {
      return false;
    }
    if (!VM.getVM().getUniverse().isIn(oop)) {
      return false;
    }
    try {
      // Try to instantiate the Klass
      if (VM.getVM().isCompressedKlassPointersEnabled()) {
        Metadata.instantiateWrapperFor(oop.getCompKlassAddressAt(klassField.getOffset()));
      } else {
        Metadata.instantiateWrapperFor(klassField.getValue(oop));
      }
      return true;
    } catch (AddressException e) {
      return false;
    } catch (WrongTypeException e) {
      return false;
    }
  }
}
