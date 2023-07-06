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


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.ArrayKlass;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.CIntField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.Oop;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObject;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.AddressField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.WrongTypeException;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















/**
 * The base class for the mirrors of the Array<T> C++ classes.
 */
public abstract class GenericArray extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
      public void update(Observable o, Object data) {
        initialize(VM.getVM().getTypeDataBase());
      }
    });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    // Array<int> is arbitrarily chosen to get the fields in Array<T>.
    Type type = db.lookupType("Array<int>");
    lengthField = new CIntField(type.getCIntegerField("_length"), 0);
  }

  private static long sizeOfArray;
  private static CIntField lengthField;

  private final long dataFieldOffset;

  public GenericArray(Address addr, long dataOffset) {
    super(addr);
    dataFieldOffset = dataOffset;
  }

  public int length() {
    return (int)lengthField.getValue(this);
  }

  // for compatibility with TypeArray
  public int getLength() {
    return length();
  }

  /**
   * Gets the element at the given index.
   */
  protected long getIntegerAt(int index) {
    if (index < 0 || index >= length()) throw new ArrayIndexOutOfBoundsException(index + " " + length());

    Type elemType = getElemType();
    if (!getElemType().isCIntegerType()) throw new RuntimeException("elemType must be of CInteger type");

    Address data = getAddress().addOffsetTo(dataFieldOffset);
    long elemSize = elemType.getSize();

    return data.getCIntegerAt(index * elemSize, elemSize, false);
  }

  protected Address getAddressAt(int index) {
    if (index < 0 || index >= length()) throw new ArrayIndexOutOfBoundsException(index);

    Type elemType = getElemType();
    if (getElemType().isCIntegerType()) throw new RuntimeException("elemType must not be of CInteger type");

    Address data = getAddress().addOffsetTo(dataFieldOffset);
    long elemSize = elemType.getSize();

    return data.getAddressAt(index * elemSize);
  }

  private long byteSizeof(int length) { return sizeOfArray + length * getElemType().getSize(); }

  public long getSize() {
    return VM.getVM().alignUp(byteSizeof(length()), VM.getVM().getBytesPerWord()) / VM.getVM().getBytesPerWord();
  }

  /**
   * The element type of this array.
   */
  public abstract Type getElemType();
}
