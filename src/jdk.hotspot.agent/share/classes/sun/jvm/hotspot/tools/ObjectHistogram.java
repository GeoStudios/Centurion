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


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import java.io.PrintStream;















/** A sample tool which uses the Serviceability Agent's APIs to obtain
    an object histogram from a remote or crashed VM. */
public class ObjectHistogram extends Tool {

    public ObjectHistogram() {
        super();
    }

    public ObjectHistogram(JVMDebugger d) {
        super(d);
    }

    @Override
    public String getName() {
        return "objectHistogram";
    }

    public void run() {
        run(System.out, System.err);
    }

    public void run(PrintStream out, PrintStream err) {
        // Ready to go with the database...
        ObjectHeap heap = VM.getVM().getObjectHeap();
        sun.jvm.hotspot.oops.ObjectHistogram histogram =
        new sun.jvm.hotspot.oops.ObjectHistogram();
        err.println("Iterating over heap. This may take a while...");
        long startTime = System.currentTimeMillis();
        heap.iterate(histogram);
        long endTime = System.currentTimeMillis();
        histogram.printOn(out);
        float secs = (float) (endTime - startTime) / 1000.0f;
        err.println("Heap traversal took " + secs + " seconds.");
    }

    public static void main(String[] args) {
        ObjectHistogram oh = new ObjectHistogram();
        oh.execute(args);
    }
}
