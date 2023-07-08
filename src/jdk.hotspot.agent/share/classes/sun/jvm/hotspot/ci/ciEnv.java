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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ci;

import java.io.PrintStream;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.opto.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.compiler.CompileTask;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.prims.JvmtiExport;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.GrowableArray;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class ciEnv extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type      = db.lookupType("ciEnv");
    dependenciesField = type.getAddressField("_dependencies");
    factoryField = type.getAddressField("_factory");
    compilerDataField = type.getAddressField("_compiler_data");
    taskField = type.getAddressField("_task");
  }

  private static AddressField dependenciesField;
  private static AddressField factoryField;
  private static AddressField compilerDataField;
  private static AddressField taskField;

  public ciEnv(Address addr) {
    super(addr);
  }

  public Compile compilerData() {
    Address addr = compilerDataField.getValue(this.getAddress());
    if (addr == null) {
      return null;
    }
    return new Compile(addr);
  }

  public ciObjectFactory factory() {
    return new ciObjectFactory(factoryField.getValue(this.getAddress()));
  }

  public CompileTask task() {
    return new CompileTask(taskField.getValue(this.getAddress()));
  }

  public void dumpReplayData(PrintStream out) {
    out.println("JvmtiExport can_access_local_variables " +
                (JvmtiExport.canAccessLocalVariables() ? '1' : '0'));
    out.println("JvmtiExport can_hotswap_or_post_breakpoint " +
                (JvmtiExport.canHotswapOrPostBreakpoint() ? '1' : '0'));
    out.println("JvmtiExport can_post_on_exceptions " +
                (JvmtiExport.canPostOnExceptions() ? '1' : '0'));

    GrowableArray<ciMetadata> objects = factory().objects();
    out.println("# " + objects.length() + " ciObject found");
    for (int i = 0; i < objects.length(); i++) {
      ciMetadata o = objects.at(i);
      out.println("# ciMetadata" + i + " @ " + o);
      o.dumpReplayData(out);
    }
    CompileTask task = task();
    Method method = task.method();
    int entryBci = task.osrBci();
    int compLevel = task.compLevel();
    out.print("compile " + method.nameAsAscii() + " " +
              entryBci + " " + compLevel);
    Compile compiler = compilerData();
    if (compiler != null) {
      // Dump inlining data.
      compiler.dumpInlineData(out);
    }
    out.println();
  }
}
