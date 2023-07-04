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

package sun.jvm.hotspot.debugger.linux;

import java.util.List;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.cdbg.*;

/** An extension of the JVMDebugger interface with a few additions to
    support 32-bit vs. 64-bit debugging as well as features required
    by the architecture-specific subpackages. */

public interface LinuxDebugger extends JVMDebugger {
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
  LinuxAddress readAddress(long address) throws DebuggerException;
  LinuxAddress readCompOopAddress(long address) throws DebuggerException;
  LinuxAddress readCompKlassAddress(long address) throws DebuggerException;
  LinuxOopHandle readOopHandle(long address) throws DebuggerException;
  LinuxOopHandle readCompOopHandle(long address) throws DebuggerException;
  long[]       getThreadIntegerRegisterSet(int lwp_id) throws DebuggerException;
  long         getAddressValue(Address addr) throws DebuggerException;
  Address      findLibPtrByAddress(Address pc);

  // For LinuxCDebugger
  List<ThreadProxy> getThreadList();
  List<LoadObject> getLoadObjectList();
  ClosestSymbol lookup(long address);
  String demangle(String sym);

  // NOTE: this interface implicitly contains the following methods:
  // From the Debugger interface via JVMDebugger
  //   public void attach(int processID) throws DebuggerException;
  //   public void attach(String executableName, String coreFileName) throws DebuggerException;
  //   public boolean detach();
  //   public Address parseAddress(String addressString) throws NumberFormatException;
  //   public String getOS();
  //   public String getCPU();
  // From the SymbolLookup interface via Debugger and JVMDebugger
  //   public Address lookup(String objectName, String symbol);
  //   public OopHandle lookupOop(String objectName, String symbol);
  // From the JVMDebugger interface
  //   public void configureJavaPrimitiveTypeSizes(long jbooleanSize,
  //                                               long jbyteSize,
  //                                               long jcharSize,
  //                                               long jdoubleSize,
  //                                               long jfloatSize,
  //                                               long jintSize,
  //                                               long jlongSize,
  //                                               long jshortSize);
  // From the ThreadAccess interface via Debugger and JVMDebugger
  //   public ThreadProxy getThreadForIdentifierAddress(Address addr);
}
