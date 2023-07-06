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

package nsk.jvmti.RedefineClasses;


import java.io.*;














/**
 * This is new version of the redefined class
 */
public class redefclass002r {
    redefclass002r() {
        System.err.println("NEW redefclass002r (" + this +
            "): initializer of the redefined class was invoked!");
    }

    public int checkIt(PrintStream out, boolean verbose) {
        if (verbose)
            out.println("NEW redefclass002r (" + this +
                "): inside the checkIt()");
        return 73;
    }

    public void activeMethod(PipedInputStream pipeIn, PipedOutputStream pipeOut,
            PrintStream out, Object readyObj, boolean verbose) {
        PipedInputStream pIn;
        PipedOutputStream pOut;

        out.println("NEW redefclass002r (" + this +
            "): active frame was replaced!");
        try {
            pOut = new PipedOutputStream(pipeIn);
            pIn = new PipedInputStream(pipeOut);
        } catch (IOException e) {
            out.println("NEW redefclass002r (" + this +
            "): creating a pipe: caught " + e);
            return;
        }

        try {
            pOut.write(230);
            pOut.flush();
            pIn.close();
            pOut.close();
        } catch (IOException e) {
            out.println("NEW redefclass002r (" + this +
            "): caught " + e);
            return;
        }
        if (verbose)
            out.println("NEW redefclass002r (" + this +
                "): exiting...");
    }
}
