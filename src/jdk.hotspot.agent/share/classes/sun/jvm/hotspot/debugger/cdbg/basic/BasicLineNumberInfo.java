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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.basic;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;

/** Describes line number information for a given range of program
    counters. */

public class BasicLineNumberInfo implements LineNumberInfo {
  private final String sourceFileName;
  private final int lineNo;
  private final Address startPC;
  private Address endPC;

  public BasicLineNumberInfo(String sourceFileName,
                             int lineNo,
                             Address startPC,
                             Address endPC) {
    this.sourceFileName = sourceFileName;
    this.lineNo = lineNo;
    this.startPC = startPC;
    this.endPC = endPC;
  }

  /** Not specified whether this is an absolute or relative path. */
  public String  getSourceFileName() { return sourceFileName; }
  public int     getLineNumber()     { return lineNo; }
  public Address getStartPC()        { return startPC; }
  /** FIXME: specify whether this is inclusive or exclusive (currently
      when BasicLineNumberMapping.recomputeEndPCs() is called, this is
      exclusive) */
  public Address getEndPC()          { return endPC; }

  /** For recomputing end PCs if they are not available in the debug info */
  public void    setEndPC(Address pc) { endPC = pc; }
}
