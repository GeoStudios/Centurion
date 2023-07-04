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

package sun.jvm.hotspot.runtime;

import java.io.*;
import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.*;

public interface JavaThreadPDAccess {
  Address getLastJavaFP(Address addr);
  Address getLastJavaPC(Address addr);
  Address getBaseOfStackPointer(Address addr);
  Frame getLastFramePD(JavaThread thread, Address addr);
  RegisterMap newRegisterMap(JavaThread thread, boolean updateMap);
  Frame getCurrentFrameGuess(JavaThread thread, Address addr);
  void printThreadIDOn(Address addr, PrintStream tty);
  void printInfoOn(Address threadAddr, PrintStream tty);
  Address getLastSP(Address addr);
  ThreadProxy getThreadProxy(Address addr);
}
