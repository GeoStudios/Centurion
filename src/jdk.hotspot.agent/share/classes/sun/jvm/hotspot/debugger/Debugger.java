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

package sun.jvm.hotspot.debugger;

import java.util.*;
import sun.jvm.hotspot.debugger.cdbg.CDebugger;

public interface Debugger extends SymbolLookup, ThreadAccess {
  /** Indicates whether this underlying debugger can provide a list of
      currently-running processes. */
  boolean hasProcessList() throws DebuggerException;

  /** Provide a snapshot of the list of currently-running processes in
      the form of a List of ProcessInfo objects. Must only be called
      if hasProcessList(), above, returns true. */
  List<ProcessInfo> getProcessList() throws DebuggerException;

  /** If an error occurs during attachment (i.e., "no such process"),
      the thrown DebuggerException will contain a description of the
      error in its message string. */
  void attach(int processID) throws DebuggerException;

  /** This attaches the debugger to the given coreFileName, which is
      assumed to have been generated from the specified
      executableName. If an error occurs during loading of the core
      file (i.e., "no such file"), the thrown DebuggerException will
      contain a description of the error in its message string. */
  void attach(String executableName, String coreFileName)
    throws DebuggerException;

  /** Detach from the remote process. Returns false if not currently
      attached. */
  boolean detach() throws DebuggerException;

  /** Parse an address from a hex string in the format "0xFFFFFFFF".
      The length of the address (i.e., 32 or 64 bits) is platform
      dependent. This method should ONLY be used by routines which
      need to interact with the user and parse a string entered by
      hand; for example, a graphical user interface. This routine
      should NOT be used to subvert the current safety mechanisms in
      the system which prevent arbitrary conversion from Address to
      long and back. */
  Address parseAddress(String addressString)
    throws NumberFormatException, DebuggerException;

  /** Returns the 64-bit value of an Address. This method should ONLY
      be used when implementing a debugger which needs to interface to
      C and which needs a unique identifier for certain objects. */
  long getAddressValue(Address addr) throws DebuggerException;

  /** Support for remote debugging. Get the name of the operating
      system on which this debugger is running (to be able to properly
      configure the local system). Typical return values are
      "linux", "win32"; see utilities/PlatformInfo.java. */
  String getOS() throws DebuggerException;

  /** Support for remote debugging. Get the name of the CPU type on
      which this debugger is running (to be able to properly configure
      the local system). Typical return value is "x86"; see
      utilities/PlatformInfo.java. */
  String getCPU() throws DebuggerException;

  /** Retrieve the machine description for the underlying hardware for
      the cases in which we need to do, for example, machine-dependent
      byte swapping */
  MachineDescription getMachineDescription() throws DebuggerException;

  /** Find out whether this debugger has a console available on which
      commands can be executed; see executeCommandOnConsole, below.
      This is an interim routine designed to allow access to the
      underlying dbx process on Solaris until we have disassembly,
      etc. in the SA. */
  boolean hasConsole() throws DebuggerException;

  /** If the underlying debugger has a console (as dbx does), this
      provides access to it. Takes in a platform-dependent String,
      executes it on the debugger's console, and returns any output as
      a String. */
  String consoleExecuteCommand(String cmd) throws DebuggerException;

  /** If the underlying debugger has a console, this returns the
      debugger-specific prompt which should be displayed. */
  String getConsolePrompt() throws DebuggerException;

  /** If this platform supports C/C++ debugging via the CDebugger
      interface, returns a CDebugger object; otherwise returns
      null. */
  CDebugger getCDebugger() throws DebuggerException;

  /**
   * Find address and executable which contains symbol.
   */
  String findSymbol(String symbol);

  /** the following methods are intended only for RemoteDebuggerClient */
  long getJBooleanSize();
  long getJByteSize();
  long getJCharSize();
  long getJDoubleSize();
  long getJFloatSize();
  long getJIntSize();
  long getJLongSize();
  long getJShortSize();
  long getHeapOopSize();
  long getNarrowOopBase();
  int  getNarrowOopShift();
  long getKlassPtrSize();
  long getNarrowKlassBase();
  int  getNarrowKlassShift();

  ReadResult readBytesFromProcess(long address, long numBytes)
    throws DebuggerException;

  void writeBytesToProcess(long address, long numBytes, byte[] data)
    throws UnmappedAddressException, DebuggerException;
}
