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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















public class GrowableArray<T> extends GenericGrowableArray {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type      = db.lookupType("GrowableArray<int>");
    dataField = type.getAddressField("_data");
  }

  private static AddressField dataField;

  private final InstanceConstructor<T> virtualConstructor;

  public static <S> GrowableArray<S> create(Address addr, InstanceConstructor<S> v) {
    if (addr == null) return null;
    return new GrowableArray<S>(addr, v);
  }

  public T at(int i) {
    if (i < 0 || i >= length()) throw new ArrayIndexOutOfBoundsException(i);
    Address data = dataField.getValue(getAddress());
    Address addr = data.getAddressAt(i * VM.getVM().getAddressSize());
    if (addr == null) return null;
    return virtualConstructor.instantiateWrapperFor(addr);
  }

  private GrowableArray(Address addr, InstanceConstructor<T> v) {
    super(addr);
    virtualConstructor = v;
  }
  public Address getData() {
    return dataField.getValue(getAddress());
  }
}
