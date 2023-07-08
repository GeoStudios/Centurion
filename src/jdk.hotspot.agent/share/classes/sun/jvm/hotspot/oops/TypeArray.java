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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops;

import java.io.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

// A TypeArray is an array containing basic types (non oop elements).
// It is used for arrays of {characters, singles, doubles, bytes, shorts, integers, longs}

public class TypeArray extends Array {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type = db.lookupType("typeArrayOop");
  }

  TypeArray(OopHandle handle, ObjectHeap heap) {
    super(handle, heap);
  }

  public boolean isTypeArray()         { return true; }

  public byte getByteAt(long index) {
    if (index < 0 || index >= getLength()) {
      throw new ArrayIndexOutOfBoundsException(index + " " + getLength());
    }
    long offset = baseOffsetInBytes(BasicType.T_BYTE) + index * getHeap().getByteSize();
    return getHandle().getJByteAt(offset);
  }

  public boolean getBooleanAt(long index) {
    long offset = baseOffsetInBytes(BasicType.T_BOOLEAN) + index * getHeap().getBooleanSize();
    return getHandle().getJBooleanAt(offset);
  }

  public char getCharAt(long index) {
    long offset = baseOffsetInBytes(BasicType.T_CHAR) + index * getHeap().getCharSize();
    return getHandle().getJCharAt(offset);
  }

  public int getIntAt(long index) {
    long offset = baseOffsetInBytes(BasicType.T_INT) + index * getHeap().getIntSize();
    return getHandle().getJIntAt(offset);
  }

  public short getShortAt(long index) {
    long offset = baseOffsetInBytes(BasicType.T_SHORT) + index * getHeap().getShortSize();
    return getHandle().getJShortAt(offset);
  }

  public long getLongAt(long index) {
    long offset = baseOffsetInBytes(BasicType.T_LONG) + index * getHeap().getLongSize();
    return getHandle().getJLongAt(offset);
  }

  public float getFloatAt(long index) {
    long offset = baseOffsetInBytes(BasicType.T_FLOAT) + index * getHeap().getFloatSize();
    return getHandle().getJFloatAt(offset);
  }

  public double getDoubleAt(long index) {
    long offset = baseOffsetInBytes(BasicType.T_DOUBLE) + index * getHeap().getDoubleSize();
    return getHandle().getJDoubleAt(offset);
  }

  public void printValueOn(PrintStream tty) {
    TypeArrayKlass klass = (TypeArrayKlass) getKlass();
    tty.print(klass.getTypeName());
  }

  public void iterateFields(OopVisitor visitor, boolean doVMFields) {
    super.iterateFields(visitor, doVMFields);
    TypeArrayKlass klass = (TypeArrayKlass) getKlass();
    int length = (int) getLength();
    int type   = klass.getElementType();
    for (int index = 0; index < length; index++) {
      FieldIdentifier id = new IndexableFieldIdentifier(index);
      switch (type) {
      case TypeArrayKlass.T_BOOLEAN: {
        long offset = baseOffsetInBytes(BasicType.T_BOOLEAN) + (long) index * getHeap().getBooleanSize();
        visitor.doBoolean(new BooleanField(id, offset, false), false);
        break;
      }
      case TypeArrayKlass.T_CHAR: {
        long offset = baseOffsetInBytes(BasicType.T_CHAR) + (long) index * getHeap().getCharSize();
        visitor.doChar(new CharField(id, offset, false), false);
        break;
      }
      case TypeArrayKlass.T_FLOAT: {
        long offset = baseOffsetInBytes(BasicType.T_FLOAT) + (long) index * getHeap().getFloatSize();
        visitor.doFloat(new FloatField(id, offset, false), false);
        break;
      }
      case TypeArrayKlass.T_DOUBLE: {
        long offset = baseOffsetInBytes(BasicType.T_DOUBLE) + (long) index * getHeap().getDoubleSize();
        visitor.doDouble(new DoubleField(id, offset, false), false);
        break;
      }
      case TypeArrayKlass.T_BYTE: {
        long offset = baseOffsetInBytes(BasicType.T_BYTE) + (long) index * getHeap().getByteSize();
        visitor.doByte(new ByteField(id, offset, false), false);
        break;
      }
      case TypeArrayKlass.T_SHORT: {
        long offset = baseOffsetInBytes(BasicType.T_SHORT) + (long) index * getHeap().getShortSize();
        visitor.doShort(new ShortField(id, offset, false), false);
        break;
      }
      case TypeArrayKlass.T_INT: {
        long offset = baseOffsetInBytes(BasicType.T_INT) + (long) index * getHeap().getIntSize();
        visitor.doInt(new IntField(id, offset, false), false);
        break;
      }
      case TypeArrayKlass.T_LONG: {
        long offset = baseOffsetInBytes(BasicType.T_LONG) + (long) index * getHeap().getLongSize();
        visitor.doLong(new LongField(id, offset, false), false);
        break;
      }
      }
    }
  }
}
