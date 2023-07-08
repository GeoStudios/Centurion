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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ci;


import java.lang.reflect.Constructor;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















public class ciObjectFactory extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type      = db.lookupType("ciObjectFactory");
    ciMetadataField = type.getAddressField("_ci_metadata");
    symbolsField = type.getAddressField("_symbols");

    ciObjectConstructor = new VirtualBaseConstructor<ciObject>(db, db.lookupType("ciObject"), "sun.jvm.hotspot.ci", ciObject.class);
    ciMetadataConstructor = new VirtualBaseConstructor<ciMetadata>(db, db.lookupType("ciMetadata"), "sun.jvm.hotspot.ci", ciMetadata.class);
    ciSymbolConstructor = new VirtualBaseConstructor<ciSymbol>(db, db.lookupType("ciSymbol"), "sun.jvm.hotspot.ci", ciSymbol.class);
  }

  private static AddressField ciMetadataField;
  private static AddressField symbolsField;

  private static VirtualBaseConstructor<ciObject> ciObjectConstructor;
  private static VirtualBaseConstructor<ciMetadata> ciMetadataConstructor;
  private static VirtualBaseConstructor<ciSymbol> ciSymbolConstructor;

  public static ciObject get(Address addr) {
    if (addr == null) return null;

    return ciObjectConstructor.instantiateWrapperFor(addr);
  }

  public static ciMetadata getMetadata(Address addr) {
    if (addr == null) return null;

    return ciMetadataConstructor.instantiateWrapperFor(addr);
  }

  public GrowableArray<ciMetadata> objects() {
    Address addr = getAddress().addOffsetTo(ciMetadataField.getOffset());
    return GrowableArray.create(addr, ciMetadataConstructor);
  }

  public GrowableArray<ciSymbol> symbols() {
    Address addr = getAddress().addOffsetTo(symbolsField.getOffset());
    return GrowableArray.create(addr, ciSymbolConstructor);
  }

  public ciObjectFactory(Address addr) {
    super(addr);
  }
}
