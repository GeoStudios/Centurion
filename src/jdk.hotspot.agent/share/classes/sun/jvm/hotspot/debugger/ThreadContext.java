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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;















/** This is a placeholder interface for a thread's context, containing
    only integer registers (no floating-point ones). What it contains
    is platform-dependent. Not all registers are guaranteed to be
    present in the context or read from the target process in all
    situations. However, the routines in it are exposed to allow
    platform-independent iteration. */

public interface ThreadContext {
  /** Number of integer registers in the context */
  int getNumRegisters();

  /** Get the name of the specified register (0..getNumRegisters() -
      1) */
  String getRegisterName(int i);

  /** Get the value of the specified register (0..getNumRegisters() -
      1) */
  long getRegister(int index);

  /** Set the value of the specified register (0..getNumRegisters() -
      1) */
  void setRegister(int index, long value);

  /** Get the value of the specified register (0..getNumRegisters() -
      1) as an Address */
  Address getRegisterAsAddress(int index);

  /** Set the value of the specified register (0..getNumRegisters() -
      1) as an Address */
  void setRegisterAsAddress(int index, Address value);

  CFrame getTopFrame(Debugger dbg);
}
