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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.code;


import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















public abstract class CompiledMethod extends CodeBlob {
  private static AddressField  methodField;
  private static AddressField  deoptHandlerBeginField;
  private static AddressField  deoptMhHandlerBeginField;
  private static AddressField  scopesDataBeginField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static void initialize(TypeDataBase db) {
    Type type = db.lookupType("CompiledMethod");

    methodField                 = type.getAddressField("_method");
    deoptHandlerBeginField      = type.getAddressField("_deopt_handler_begin");
    deoptMhHandlerBeginField    = type.getAddressField("_deopt_mh_handler_begin");
    scopesDataBeginField        = type.getAddressField("_scopes_data_begin");
  }

  public CompiledMethod(Address addr) {
    super(addr);
  }

  public Method getMethod() {
    return (Method)Metadata.instantiateWrapperFor(methodField.getValue(addr));
  }

  public Address deoptHandlerBegin()    { return deoptHandlerBeginField.getValue(addr);              }
  public Address deoptMhHandlerBegin()  { return deoptMhHandlerBeginField.getValue(addr);            }
  public Address scopesDataBegin()      { return scopesDataBeginField.getValue(addr);                }

  public static int getMethodOffset()                { return (int) methodField.getOffset();                }

  @Override
  public boolean isCompiled() {
    return true;
  }
}
