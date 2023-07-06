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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime;

import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;

public class StackValueCollection {
  private final List<StackValue> list;

  public StackValueCollection()           { list = new ArrayList<>(); }
  public StackValueCollection(int length) { list = new ArrayList<>(length); }

  public void add(StackValue val) { list.add(val); }
  public int  size()              { return list.size(); }
  public boolean isEmpty()        { return (size() == 0); }
  public StackValue get(int i)    { return list.get(i); }

  // Get typed locals/expressions
  // FIXME: must figure out whether word swapping is necessary on x86
  public boolean   booleanAt(int slot)   { return (int)get(slot).getInteger() != 0; }
  public byte      byteAt(int slot)      { return (byte) get(slot).getInteger(); }
  public char      charAt(int slot)      { return (char) get(slot).getInteger(); }
  public short     shortAt(int slot)     { return (short) get(slot).getInteger(); }
  public int       intAt(int slot)       { return (int) get(slot).getInteger(); }
  public long      longAt(int slot)      { return VM.getVM().buildLongFromIntsPD((int) get(slot).getInteger(),
                                                                                 (int) get(slot+1).getInteger()); }

  public OopHandle oopHandleAt(int slot) {
    StackValue sv = get(slot);
    if (sv.getType() == BasicType.getTConflict()) {
      throw new WrongTypeException("Conflict type");
    }
    return sv.getObject();
  }

  public float     floatAt(int slot)     { return Float.intBitsToFloat(intAt(slot)); }
  public double    doubleAt(int slot)    { return Double.longBitsToDouble(longAt(slot)); }
}