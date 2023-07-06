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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.interpreter;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;

/** Helper class for computing oop maps for native methods */

class MaskFillerForNative extends NativeSignatureIterator {
  MaskFillerForNative(Method method, BitMap mask, int maskSize) {
    super(method);
    this.mask = mask;
    this.size = maskSize;
  }

  public void passInt()    { /* ignore */ }
  public void passLong()   { /* ignore */ }
  public void passFloat()  { /* ignore */ }
  public void passDouble() { /* ignore */ }
  public void passObject() { mask.atPut(offset(), true); }

  public void generate() {
    super.iterate();
  }

  //----------------------------------------------------------------------
  // Internals only below this point
  //
  private final BitMap mask;
  private final int    size;
}