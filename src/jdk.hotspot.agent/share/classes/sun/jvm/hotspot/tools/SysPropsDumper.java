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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.tools;


import java.io.PrintStream;
import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.JVMDebugger;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;















public class SysPropsDumper extends Tool {

   public SysPropsDumper() {
      super();
   }

   public SysPropsDumper(JVMDebugger d) {
      super(d);
   }

   public void run() {
      Properties sysProps = VM.getVM().getSystemProperties();
      PrintStream out = System.out;
      if (sysProps != null) {
         Enumeration keys = sysProps.keys();
         while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            out.print(key);
            out.print(" = ");
            out.println(sysProps.get(key));
         }
      } else {
         out.println("System Properties info not available!");
      }
   }

   public static void main(String[] args) {
      SysPropsDumper pd = new SysPropsDumper();
      pd.execute(args);
   }
}
