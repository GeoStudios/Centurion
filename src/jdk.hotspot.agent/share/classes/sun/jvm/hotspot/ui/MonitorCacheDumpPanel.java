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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ui;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;

/** Provides information about monitor cache. */

public class MonitorCacheDumpPanel extends JPanel {
  public MonitorCacheDumpPanel() {
    super();

    setLayout(new BorderLayout());

    // Simple at first
    JScrollPane scroller = new JScrollPane();
    JTextArea textArea = new JTextArea();
    textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    scroller.getViewport().add(textArea);
    add(scroller, BorderLayout.CENTER);

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    PrintStream tty = new PrintStream(bos);
    tty.println("Monitor Cache Dump (not including JVMTI raw monitors):");
    tty.println();
    dumpOn(tty);

    textArea.setText(bos.toString());
  }

  private static void dumpMonitor(PrintStream tty, ObjectMonitor mon, boolean raw) {
    tty.print("ObjectMonitor@" + mon.getAddress());
    if (raw) tty.print("(Raw Monitor)");
    tty.println();
    tty.println("  _header: 0x" + Long.toHexString(mon.header().value()));
    OopHandle obj = mon.object();
    Oop oop = heap.newOop(obj);
    tty.println("  _object: " + obj + ", a " + oop.getKlass().getName().asString());
    Address owner = mon.owner();
    tty.println("  _owner: " + owner);
    if (!raw && owner != null) {
      JavaThread thread = threads.owningThreadFromMonitor(mon);
      if (thread != null) {
        tty.println("    owning thread: " + thread.getThreadName());
        if (!thread.getAddress().equals(owner)) {
          if (!thread.isLockOwned(owner)) {
            tty.println("    WARNING! _owner doesn't fall in " + thread +
                        "'s stack space");
          }
        }
      }
    }
    tty.println("  _contentions: " + mon.contentions());
    tty.println("  _waiters: " + mon.waiters());
    tty.println("  _recursions: " + mon.recursions());
  }

  private void dumpOn(PrintStream tty) {
    Iterator i = ObjectSynchronizer.objectMonitorIterator();
    if (i == null) {
      tty.println("This version of HotSpot VM doesn't support monitor cache dump.");
      tty.println("You need 1.4.0_04, 1.4.1_01 or later versions");
      return;
    }
    ObjectMonitor mon;
    while (i.hasNext()) {
      mon = (ObjectMonitor)i.next();
      if (mon.contentions() != 0 || mon.waiters() != 0 || mon.owner() != null) {
        OopHandle object = mon.object();
        dumpMonitor(tty, mon, object == null);
      }
    }
  }

  private static final Threads threads = VM.getVM().getThreads();
  private static final ObjectHeap heap = VM.getVM().getObjectHeap();
}
