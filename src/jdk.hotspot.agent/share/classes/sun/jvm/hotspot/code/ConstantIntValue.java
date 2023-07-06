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

import java.io.*;

/** A ConstantIntValue describes a constant int; i.e., the
    corresponding logical entity is either a source constant or its
    computation has been constant-folded. */

public class ConstantIntValue extends ScopeValue {
  private final int value;

  public ConstantIntValue(int value) {
    this.value = value;
  }

  public boolean isConstantInt() {
    return true;
  }

  public int getValue() {
    return value;
  }

  /** Serialization of debugging information */
  ConstantIntValue(DebugInfoReadStream stream) {
    value = stream.readSignedInt();
  }

  // FIXME: not yet implementable
  // void write_on(DebugInfoWriteStream* stream);

  // Printing

  public void print() {
    printOn(System.out);
  }

  public void printOn(PrintStream tty) {
    tty.print(value);
  }
}