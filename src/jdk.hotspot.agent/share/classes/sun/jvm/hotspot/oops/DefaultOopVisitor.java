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

/** A DefaultOopVisitor implements basic no-op OopVisitor
    functionality except that the setObj() and getObj() methods are
    implemented properly. */

public class DefaultOopVisitor implements OopVisitor {
  private Oop obj;

  public void prologue()                        {}

  // Called after visiting an object
  public void epilogue()                        {}

  public void setObj(Oop obj) {
    this.obj = obj;
  }

  public Oop getObj() {
    return obj;
  }

  // Callback methods for each field type in an object
  public void doMetadata(MetadataField field, boolean isVMField) {}
  public void doOop(OopField field, boolean isVMField)         {}
  public void doOop(NarrowOopField field, boolean isVMField)   {}
  public void doByte(ByteField field, boolean isVMField)       {}
  public void doChar(CharField field, boolean isVMField)       {}
  public void doBoolean(BooleanField field, boolean isVMField) {}
  public void doShort(ShortField field, boolean isVMField)     {}
  public void doInt(IntField field, boolean isVMField)         {}
  public void doLong(LongField field, boolean isVMField)       {}
  public void doFloat(FloatField field, boolean isVMField)     {}
  public void doDouble(DoubleField field, boolean isVMField)   {}
  public void doCInt(CIntField field, boolean isVMField)       {}
}