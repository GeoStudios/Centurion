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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.bsd_aarch64;

import java.io.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.aarch64.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.bsd.BsdDebugger;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.aarch64.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class BsdAARCH64JavaThreadPDAccess implements JavaThreadPDAccess {
  private static AddressField  lastJavaFPField;
  private static AddressField  osThreadField;

  // Fields from OSThread
  private static CIntegerField osThreadThreadIDField;
  private static CIntegerField osThreadUniqueThreadIDField;

  // This is currently unneeded but is being kept in case we change
  // the currentFrameGuess algorithm
  private static final long GUESS_SCAN_RANGE = 128 * 1024;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("JavaThread");
    osThreadField           = type.getAddressField("_osthread");

    Type anchorType = db.lookupType("JavaFrameAnchor");
    lastJavaFPField         = anchorType.getAddressField("_last_Java_fp");

    Type osThreadType = db.lookupType("OSThread");
    osThreadThreadIDField = osThreadType.getCIntegerField("_thread_id");
    osThreadUniqueThreadIDField = osThreadType.getCIntegerField("_unique_thread_id");
  }

  public Address getLastJavaFP(Address addr) {
    return lastJavaFPField.getValue(addr.addOffsetTo(sun.jvm.hotspot.runtime.JavaThread.getAnchorField().getOffset()));
  }

  public Address getLastJavaPC(Address addr) {
    return null;
  }

  public Address getBaseOfStackPointer(Address addr) {
    return null;
  }

  public Frame getLastFramePD(JavaThread thread, Address addr) {
    Address fp = thread.getLastJavaFP();
    if (fp == null) {
      return null; // no information
    }
    return new AARCH64Frame(thread.getLastJavaSP(), fp);
  }

  public RegisterMap newRegisterMap(JavaThread thread, boolean updateMap) {
    return new AARCH64RegisterMap(thread, updateMap);
  }

  public Frame getCurrentFrameGuess(JavaThread thread, Address addr) {
    ThreadProxy t = getThreadProxy(addr);
    AARCH64ThreadContext context = (AARCH64ThreadContext) t.getContext();
    AARCH64CurrentFrameGuess guesser = new AARCH64CurrentFrameGuess(context, thread);
    if (!guesser.run(GUESS_SCAN_RANGE)) {
      return null;
    }
    if (guesser.getPC() == null) {
      return new AARCH64Frame(guesser.getSP(), guesser.getFP());
    } else {
      return new AARCH64Frame(guesser.getSP(), guesser.getFP(), guesser.getPC());
    }
  }

  public void printThreadIDOn(Address addr, PrintStream tty) {
    tty.print(getThreadProxy(addr));
  }

  public void printInfoOn(Address threadAddr, PrintStream tty) {
    tty.print("Thread id: ");
    printThreadIDOn(threadAddr, tty);
//    tty.println("\nPostJavaState: " + getPostJavaState(threadAddr));
  }

  public Address getLastSP(Address addr) {
    ThreadProxy t = getThreadProxy(addr);
    AARCH64ThreadContext context = (AARCH64ThreadContext) t.getContext();
    return context.getRegisterAsAddress(AARCH64ThreadContext.SP);
  }

  public ThreadProxy getThreadProxy(Address addr) {
    // Addr is the address of the JavaThread.
    // Fetch the OSThread (for now and for simplicity, not making a
    // separate "OSThread" class in this package)
    Address osThreadAddr = osThreadField.getValue(addr);
    // Get the address of the _thread_id from the OSThread
    Address threadIdAddr = osThreadAddr.addOffsetTo(osThreadThreadIDField.getOffset());
    Address uniqueThreadIdAddr = osThreadAddr.addOffsetTo(osThreadUniqueThreadIDField.getOffset());

    BsdDebuggerLocal debugger = (BsdDebuggerLocal) VM.getVM().getDebugger();
    return debugger.getThreadForIdentifierAddress(threadIdAddr, uniqueThreadIdAddr);
  }
}