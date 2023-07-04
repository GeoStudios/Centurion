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

package sun.jvm.hotspot.debugger.remote;

import java.rmi.*;
import java.util.*;

import sun.jvm.hotspot.debugger.*;

/** <P> This interface describes the methods which are used in a
    remote debugging scenario. It is only necessary because RMI
    requires that all such methods throw RemoteException, which is a
    checked (i.e., not a Runtime) exception. Since we already have a
    suitable runtime exception (DebuggerException) present in the
    signatures for all of the debugger-related methods, we would like
    to repurpose this to wrap RemoteExceptions. This is done by
    wrapping the actual remote debugger object
    </P>

    <P> NOTE that this interface currently assumes that the debugger
    on the remote machine has already been attached to the target
    process or has opened the desired core file. This implies that the
    machine hosting the user interface can not effect
    attaching/detaching. Currently this restriction has been enforced
    to make the user interface less confusing, but there will also be
    security concerns with allowing clients to attach to arbitrary
    remote processes. </P>
*/

public interface RemoteDebugger extends Remote {
  String    getOS() throws RemoteException;
  String    getCPU() throws RemoteException;
  MachineDescription getMachineDescription() throws RemoteException;
  long      lookupInProcess(String objectName, String symbol) throws RemoteException;
  ReadResult readBytesFromProcess(long address, long numBytes) throws RemoteException;
  boolean   hasConsole() throws RemoteException;
  String    getConsolePrompt() throws RemoteException;
  String    consoleExecuteCommand(String cmd) throws RemoteException;
  long      getJBooleanSize() throws RemoteException;
  long      getJByteSize() throws RemoteException;
  long      getJCharSize() throws RemoteException;
  long      getJDoubleSize() throws RemoteException;
  long      getJFloatSize() throws RemoteException;
  long      getJIntSize() throws RemoteException;
  long      getJLongSize() throws RemoteException;
  long      getJShortSize() throws RemoteException;
  long      getHeapOopSize() throws RemoteException;
  long      getNarrowOopBase() throws RemoteException;
  int       getNarrowOopShift() throws RemoteException;
  long      getKlassPtrSize() throws RemoteException;
  long      getNarrowKlassBase() throws RemoteException;
  int       getNarrowKlassShift() throws RemoteException;

  boolean   areThreadsEqual(long addrOrId1, boolean isAddress1,
                                   long addrOrId2, boolean isAddress2) throws RemoteException;
  int       getThreadHashCode(long addrOrId, boolean isAddress) throws RemoteException;
  long[]    getThreadIntegerRegisterSet(long addrOrId, boolean isAddress) throws RemoteException;

  default String execCommandOnServer(String command, Map<String, Object> options) throws RemoteException {
    throw new DebuggerException("Command execution is not supported");
  }
}
