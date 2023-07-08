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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















// ArrayKlass is the abstract class for all array classes

public class ArrayKlass extends Klass {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type          = db.lookupType("ArrayKlass");
    dimension          = new CIntField(type.getCIntegerField("_dimension"), 0);
    higherDimension    = new MetadataField(type.getAddressField("_higher_dimension"), 0);
    lowerDimension     = new MetadataField(type.getAddressField("_lower_dimension"), 0);
    javaLangCloneableName = null;
    javaLangObjectName = null;
    javaIoSerializableName = null;
  }

  public ArrayKlass(Address addr) {
    super(addr);
  }

  public boolean isArrayKlass()     { return true; }
  private static CIntField dimension;
  private static MetadataField  higherDimension;
  private static MetadataField  lowerDimension;

  public Klass getJavaSuper() {
    SystemDictionary sysDict = VM.getVM().getSystemDictionary();
    return SystemDictionary.getObjectKlass();
  }

  public long  getDimension()       { return         dimension.getValue(this); }
  public Klass getHigherDimension() { return (Klass) higherDimension.getValue(this); }
  public Klass getLowerDimension()  { return (Klass) lowerDimension.getValue(this); }

  // constant class names - javaLangCloneable, javaIoSerializable, javaLangObject
  // Initialized lazily to avoid initialization ordering dependencies between ArrayKlass and String
  private static String javaLangCloneableName;
  private static String javaLangObjectName;
  private static String javaIoSerializableName;
  private static String javaLangCloneableName() {
    if (javaLangCloneableName == null) {
      javaLangCloneableName = "java/lang/Cloneable";
    }
    return javaLangCloneableName;
  }

  private static String javaLangObjectName() {
    if (javaLangObjectName == null) {
      javaLangObjectName = "java/lang/Object";
    }
    return javaLangObjectName;
  }

  private static String javaIoSerializableName() {
    if (javaIoSerializableName == null) {
      javaIoSerializableName = "java/io/Serializable";
    }
    return javaIoSerializableName;
  }

  public int getClassStatus() {
     return JVMDIClassStatus.VERIFIED | JVMDIClassStatus.PREPARED | JVMDIClassStatus.INITIALIZED;
  }

  public long computeModifierFlags() {
     return JVM_ACC_ABSTRACT | JVM_ACC_FINAL | JVM_ACC_PUBLIC;
  }

  public long getArrayHeaderInBytes() {
    return Bits.maskBits(getLayoutHelper() >> LH_HEADER_SIZE_SHIFT, 0xFF);
  }

  public int getLog2ElementSize() {
    return Bits.maskBits(getLayoutHelper() >> LH_LOG2_ELEMENT_SIZE_SHIFT, 0xFF);
  }

  public int getElementType() {
    return Bits.maskBits(getLayoutHelper() >> LH_ELEMENT_TYPE_SHIFT, 0xFF);
  }

  boolean computeSubtypeOf(Klass k) {
    // An array is a subtype of Serializable, Clonable, and Object
    Symbol name = k.getName();
    return name != null && (name.equals(javaIoSerializableName()) ||
            name.equals(javaLangCloneableName()) ||
            name.equals(javaLangObjectName()));
  }

  public void printValueOn(PrintStream tty) {
    tty.print("ArrayKlass");
  }

  public void iterateFields(MetadataVisitor visitor) {
    super.iterateFields(visitor);
      visitor.doCInt(dimension, true);
    visitor.doMetadata(higherDimension, true);
    visitor.doMetadata(lowerDimension, true);
    }
  }
