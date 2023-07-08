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















/** <P> This specialization of BasicType implements the CIntegerType
    interface and describes all C integer types. */

public class BasicCIntegerType extends BasicType implements CIntegerType {
  private boolean isUnsigned;

  public BasicCIntegerType(BasicTypeDataBase db, String name, boolean isUnsigned) {
    super(db, name, null);

    this.isUnsigned = isUnsigned;
  }

  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }

    if (!(obj instanceof BasicCIntegerType arg)) {
      return false;
    }

    return isUnsigned == arg.isUnsigned;
  }

  public String toString() {
    String prefix = null;
    if (isUnsigned) {
      prefix = "unsigned";
    }

    if (prefix != null) {
      return prefix + " " + getName();
    }

    return getName();
  }

  public boolean isCIntegerType() {
    return true;
  }

  public boolean isUnsigned() {
    return isUnsigned;
  }

  /** This should be called at most once, and only by the builder of
      the TypeDataBase */
  public void setIsUnsigned(boolean isUnsigned) {
    this.isUnsigned = isUnsigned;
  }

  public long maxValue() {
    return db.cIntegerTypeMaxValue(getSize(), isUnsigned());
  }

  public long minValue() {
    return db.cIntegerTypeMinValue(getSize(), isUnsigned());
  }
}
