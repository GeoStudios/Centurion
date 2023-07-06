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


import java.io.*;
import java.util.*;
import java.util.stream.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.JVMDebugger;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;















public class JSnap extends Tool {

    private boolean all;

    public JSnap() {
        super();
    }

    public JSnap(JVMDebugger d) {
        super(d);
    }

    public void run() {
        final PrintStream out = System.out;
        if (PerfMemory.initialized()) {
            PerfDataPrologue prologue = PerfMemory.prologue();
            if (prologue.accessible()) {
                PerfMemory.iterate(new PerfMemory.PerfDataEntryVisitor() {
                        public boolean visit(PerfDataEntry pde) {
                            if (all || pde.supported()) {
                                out.print(pde.name());
                                out.print('=');
                                out.println(pde.valueAsString());
                            }
                            // goto next entry
                            return true;
                        }
                    });
            } else {
                out.println("PerfMemory is not accessible");
            }
        } else {
            out.println("PerfMemory is not initialized");
        }
    }

    @Override
    protected void printFlagsUsage() {
        System.out.println("    -a\tto print all performance counters");
        super.printFlagsUsage();
    }

    public static void main(String[] args) {
        JSnap js = new JSnap();
        js.all = Arrays.stream(args)
                       .anyMatch(s -> s.equals("-a"));

        if (js.all) {
            args = Arrays.stream(args)
                         .filter(s -> !s.equals("-a"))
                         .collect(Collectors.toList())
                         .toArray(new String[0]);
        }

        js.execute(args);
    }
}
