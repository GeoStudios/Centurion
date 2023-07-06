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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;















/** Implementation of the ObjectVisitor interface with all methods
    empty */

public class DefaultObjectVisitor implements ObjectVisitor {
  public void enterType(Type type, Address objectAddress) {}
  public void exitType() {}
  public void doBit(FieldIdentifier f, long val) {}
  public void doInt(FieldIdentifier f, long val) {}
  public void doEnum(FieldIdentifier f, long val, String enumName) {}
  public void doFloat(FieldIdentifier f, float val) {}
  public void doDouble(FieldIdentifier f, double val) {}
  public void doPointer(FieldIdentifier f, Address val) {}
  public void doArray(FieldIdentifier f, Address val) {}
  public void doRef(FieldIdentifier f, Address val) {}
  public void doCompound(FieldIdentifier f, Address addressOfEmbeddedCompoundObject) {}
}
