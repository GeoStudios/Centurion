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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.basic;


import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;















public abstract class BasicVtblAccess implements VtblAccess {
  protected SymbolLookup symbolLookup;
  protected String[] dllNames;

  private final Map<Type, Object> typeToVtblMap = new HashMap<>();

  public BasicVtblAccess(SymbolLookup symbolLookup,
                         String[] dllNames) {
    this.symbolLookup = symbolLookup;
    this.dllNames = dllNames;
  }

  static Object nullAddress = new Object();

  public Address getVtblForType(Type type) {
    if (type == null) {
      return null;
    }
    Object result = typeToVtblMap.get(type);
    if (result == nullAddress) {
        return null;
    }
    if (result != null) {
      return (Address)result;
    }
    String vtblSymbol = vtblSymbolForType(type);
    if (vtblSymbol == null) {
      typeToVtblMap.put(type, nullAddress);
      return null;
    }
    for (int i = 0; i < dllNames.length; i++) {
      Address addr = symbolLookup.lookup(dllNames[i], vtblSymbol);
      if (addr != null) {
        typeToVtblMap.put(type, addr);
        return addr;
      }
    }
    typeToVtblMap.put(type, nullAddress);
    return null;
  }

  public void clearCaches() {
    typeToVtblMap.clear();
  }

  protected abstract String vtblSymbolForType(Type type);
}
