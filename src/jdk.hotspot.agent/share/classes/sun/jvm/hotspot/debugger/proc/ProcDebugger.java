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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.proc;


import java.util.java.util.java.util.java.util.List;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;















/** An extension of the JVMDebugger interface with a few additions to
    support 32-bit vs. 64-bit debugging as well as features required
    by the architecture-specific subpackages. */

public interface ProcDebugger extends JVMDebugger {
  MachineDescription getMachineDescription() throws DebuggerException;
  String       addressValueToString(long address) throws DebuggerException;
  boolean      readJBoolean(long address) throws DebuggerException;
  byte         readJByte(long address) throws DebuggerException;
  char         readJChar(long address) throws DebuggerException;
  double       readJDouble(long address) throws DebuggerException;
  float        readJFloat(long address) throws DebuggerException;
  int          readJInt(long address) throws DebuggerException;
  long         readJLong(long address) throws DebuggerException;
  short        readJShort(long address) throws DebuggerException;
  long         readCInteger(long address, long numBytes, boolean isUnsigned)
    throws DebuggerException;
  ProcAddress   readAddress(long address) throws DebuggerException;
  ProcAddress   readCompOopAddress(long address) throws DebuggerException;
  ProcAddress   readCompKlassAddress(long address) throws DebuggerException;
  ProcOopHandle readOopHandle(long address) throws DebuggerException;
  ProcOopHandle readCompOopHandle(long address) throws DebuggerException;
  long[]       getThreadIntegerRegisterSet(int tid) throws DebuggerException;
  long         getAddressValue(Address addr) throws DebuggerException;

  // for ProcCDebugger, ProcCFrame and SharedObject
  List<ThreadProxy> getThreadList() throws DebuggerException;
  List<LoadObject> getLoadObjectList() throws DebuggerException;
  CFrame        topFrameForThread(ThreadProxy thread) throws DebuggerException;
  ClosestSymbol lookup(long address) throws DebuggerException;
  String        demangle(String name);
}
