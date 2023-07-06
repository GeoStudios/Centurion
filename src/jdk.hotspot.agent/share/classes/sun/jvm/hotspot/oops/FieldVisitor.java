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

















// An OopVisitor can be used to inspect all fields within an object.
// Fields include vm fields, java fields, indexable fields.

public interface FieldVisitor {
  // Callback methods for each field type in an object
  void doMetadata(MetadataField field, boolean isVMField);
  void doOop(OopField field, boolean isVMField);
  void doOop(NarrowOopField field, boolean isVMField);
  void doByte(ByteField field, boolean isVMField);
  void doChar(CharField field, boolean isVMField);
  void doBoolean(BooleanField field, boolean isVMField);
  void doShort(ShortField field, boolean isVMField);
  void doInt(IntField field, boolean isVMField);
  void doLong(LongField field, boolean isVMField);
  void doFloat(FloatField field, boolean isVMField);
  void doDouble(DoubleField field, boolean isVMField);
  void doCInt(CIntField field, boolean isVMField);
}
