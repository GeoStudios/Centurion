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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.basic.*;

public class BsdVtblAccess extends BasicVtblAccess {
  private final String vt;

  public BsdVtblAccess(SymbolLookup symbolLookup,
                         String[] dllNames) {
    super(symbolLookup, dllNames);
    boolean oldVT = false;
    boolean isDarwin = dllNames[0].lastIndexOf(".dylib") != -1;
    String vtJavaThread = isDarwin ? "_vt_10JavaThread" : "__vt_10JavaThread";
    for (String dllName : dllNames) {
       if (symbolLookup.lookup(dllName, vtJavaThread) != null) {
         oldVT = true;
         break;
       }
    }
    if (oldVT) {
       // old C++ ABI
       vt = isDarwin ? "_vt_" :  "__vt_";
    } else {
       // new C++ ABI
       vt = "_ZTV";
    }
  }

  protected String vtblSymbolForType(Type type) {
    return vt + type.getName().length() + type;
  }
}
