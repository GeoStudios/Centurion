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

package sun.jvm.hotspot.tools;

import sun.jvm.hotspot.debugger.JVMDebugger;

public class JStack extends Tool {
    public JStack(boolean mixedMode, boolean concurrentLocks) {
        this.mixedMode = mixedMode;
        this.concurrentLocks = concurrentLocks;
    }

    public JStack() {
        this(true, true);
    }

    public JStack(JVMDebugger d) {
        super(d);
    }

    protected boolean needsJavaPrefix() {
        return false;
    }

    @Override
    public String getName() {
        return "jstack";
    }

    protected void printFlagsUsage() {
       System.out.println("    -l\tto print java.util.concurrent locks");
       System.out.println("    -m\tto print both java and native frames (mixed mode)");
       super.printFlagsUsage();
    }

    public void run() {
        Tool tool = null;
        if (mixedMode) {
            tool = new PStack(false, concurrentLocks);
        } else {
            tool = new StackTrace(false, concurrentLocks);
        }
        tool.setAgent(getAgent());
        tool.setDebugeeType(getDebugeeType());
        tool.run();
    }

    public void runWithArgs(String... args) {
        int used = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-m")) {
                mixedMode = true;
                used++;
            } else if (args[i].equals("-l")) {
                concurrentLocks = true;
                used++;
            }
        }

        if (used != 0) {
            String[] newArgs = new String[args.length - used];
            System.arraycopy(args, 0 + used, newArgs, 0, newArgs.length);
            args = newArgs;
        }

        execute(args);
    }

    public static void main(String[] args) {
        JStack jstack = new JStack();
        jstack.runWithArgs(args);
    }

    private boolean mixedMode;
    private boolean concurrentLocks;
}
