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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.code;

import java.io.*;

public class MonitorValue {
  private final ScopeValue owner;
  private final Location   basicLock;
  private final boolean    eliminated;

  // FIXME: not useful yet
  //  MonitorValue(ScopeValue* owner, Location basic_lock);

  public MonitorValue(DebugInfoReadStream stream) {
    basicLock = new Location(stream);
    owner     = ScopeValue.readFrom(stream);
    eliminated= stream.readBoolean();
  }

  public ScopeValue owner()     { return owner; }
  public Location   basicLock() { return basicLock; }
  public boolean   eliminated() { return eliminated; }

  // FIXME: not yet implementable
  //  void write_on(DebugInfoWriteStream* stream);

  public void printOn(PrintStream tty) {
    tty.print("monitor{");
    owner().printOn(tty);
    tty.print(",");
    basicLock().printOn(tty);
    tty.print("}");
    if (eliminated) {
      tty.print(" (eliminated)");
    }
  }
}
