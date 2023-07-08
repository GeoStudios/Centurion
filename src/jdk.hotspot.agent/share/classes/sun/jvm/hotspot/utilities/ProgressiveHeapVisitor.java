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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;

/** This class wraps a user's chosen HeapVisitor with the
    functionality that a chosen "thunk" is called periodically during
    the heap traversal. This allows a progress bar to be displayed
    during long heap scans. */

public class ProgressiveHeapVisitor implements HeapVisitor {
  private final HeapVisitor userHeapVisitor;
  private final HeapProgressThunk thunk;
  private long usedSize;
  private long visitedSize;
  private double lastNotificationFraction;
  private static final double MINIMUM_NOTIFICATION_FRACTION = 0.01;

  public ProgressiveHeapVisitor(HeapVisitor userHeapVisitor,
                                HeapProgressThunk thunk) {
    this.userHeapVisitor = userHeapVisitor;
    this.thunk = thunk;
  }

  public void prologue(long usedSize) {
    this.usedSize = usedSize;
    visitedSize = 0;
    userHeapVisitor.prologue(usedSize);
    thunk.heapIterationFractionUpdate(0.0);
  }

  public boolean doObj(Oop obj) {
    userHeapVisitor.doObj(obj);
    visitedSize += obj.getObjectSize();
    double curFrac = (double) visitedSize / (double) usedSize;
    if (curFrac > lastNotificationFraction + MINIMUM_NOTIFICATION_FRACTION) {
      thunk.heapIterationFractionUpdate(curFrac);
      lastNotificationFraction = curFrac;
    }
    return false;
  }

  public void epilogue() {
    userHeapVisitor.epilogue();
    thunk.heapIterationComplete();
  }
}
