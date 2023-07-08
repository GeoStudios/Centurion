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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.Method;















public class DebugInfoReadStream extends CompressedReadStream {
  private final NMethod code;
  private final int InvocationEntryBCI;
  private final List<ObjectValue> objectPool;

  public DebugInfoReadStream(NMethod code, int offset) {
    super(code.scopesDataBegin(), offset);
    InvocationEntryBCI = VM.getVM().getInvocationEntryBCI();
    this.code = code;
    this.objectPool = null;
  }

  public DebugInfoReadStream(NMethod code, int offset, List<ObjectValue> objectPool) {
    super(code.scopesDataBegin(), offset);
    InvocationEntryBCI = VM.getVM().getInvocationEntryBCI();
    this.code = code;
    this.objectPool = objectPool;
  }

  public OopHandle readOopHandle() {
    return code.getOopAt(readInt());
  }

  public Method readMethod() {
    return code.getMethodAt(readInt());
  }

  ScopeValue readObjectValue() {
    int id = readInt();
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(objectPool != null, "object pool does not exist");
      for (Iterator itr = objectPool.iterator(); itr.hasNext();) {
        ObjectValue ov = (ObjectValue) itr.next();
        Assert.that(ov.id() != id, "should not be read twice");
      }
    }
    ObjectValue result = new ObjectValue(id);
    // Cache the object since an object field could reference it.
    objectPool.add(result);
    result.readObject(this);
    return result;
  }

  ScopeValue getCachedObject() {
    int id = readInt();
    Assert.that(objectPool != null, "object pool does not exist");
    for (Iterator itr = objectPool.iterator(); itr.hasNext();) {
      ObjectValue ov = (ObjectValue) itr.next();
      if (ov.id() == id) {
        return ov;
      }
    }
    Assert.that(false, "should not reach here");
    return null;
  }

  public int readBCI() {
    return readInt() + InvocationEntryBCI;
  }
}
