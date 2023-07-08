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


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObject;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;















// The class for an C int field simply provides access to the value.
public class CIntField extends Field {

  public CIntField(sun.jvm.hotspot.types.CIntegerField vmField, long startOffset) {
    super(new NamedFieldIdentifier(vmField.getName()), vmField.getOffset() + startOffset, true);
    size       = vmField.getSize();
    isUnsigned = ((sun.jvm.hotspot.types.CIntegerType) vmField.getType()).isUnsigned();
  }

  private final long size;
  private final boolean isUnsigned;

  public long getValue(Oop obj) {
    return getValue(obj.getHandle());
  }
  public long getValue(VMObject obj) {
    return getValue(obj.getAddress());
  }
  public long getValue(Address addr) {
    return addr.getCIntegerAt(getOffset(), size, isUnsigned);
  }
  public void setValue(Oop obj, long value) throws MutationException {
    // Fix this: set* missing in Address
  }
}
