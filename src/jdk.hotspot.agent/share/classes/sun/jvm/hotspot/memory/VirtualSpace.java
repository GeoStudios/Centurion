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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory;

import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class VirtualSpace extends VMObject {
  private static AddressField lowField;
  private static AddressField highField;
  private static AddressField lowBoundaryField;
  private static AddressField highBoundaryField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("VirtualSpace");

    lowField          = type.getAddressField("_low");
    highField         = type.getAddressField("_high");
    lowBoundaryField  = type.getAddressField("_low_boundary");
    highBoundaryField = type.getAddressField("_high_boundary");
  }

  public VirtualSpace(Address addr) {
    super(addr);
  }

  public Address low()                          { return lowField.getValue(addr);          }
  public Address high()                         { return highField.getValue(addr);         }
  public Address lowBoundary()                  { return lowBoundaryField.getValue(addr);  }
  public Address highBoundary()                 { return highBoundaryField.getValue(addr); }

  /** Testers (all sizes are byte sizes) */
  public long committedSize()                   { return high().minus(low());                                    }
  public long reservedSize()                    { return highBoundary().minus(lowBoundary());                    }
  public long uncommittedSize()                 { return reservedSize() - committedSize();                       }
  public boolean contains(Address addr)         { return (low().lessThanOrEqual(addr) && addr.lessThan(high())); }
}
