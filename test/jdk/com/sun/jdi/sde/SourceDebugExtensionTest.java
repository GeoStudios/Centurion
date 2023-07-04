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

/**
 * @test
 * @bug 4390869
 * @bug 4460328
 * @summary Test the new SourceDebugExtension facility
 * @author Robert Field
 *
 * @library ..
 *
 * @run build TestScaffold VMConnection TargetListener TargetAdapter InstallSDE
 * @run compile SourceDebugExtensionTest.java
 * @run compile -g SourceDebugExtensionTarg.java
 * @run driver SourceDebugExtensionTest
 */
import com.sun.jdi.*;
import com.sun.jdi.event.*;
import com.sun.jdi.request.*;

import java.util.*;
import java.io.File;

public class SourceDebugExtensionTest extends TestScaffold {
    ReferenceType targetClass;

    SourceDebugExtensionTest (String args[]) {
        super(args);
    }

    public static void main(String[] args)      throws Exception {
        testSetUp();
        new SourceDebugExtensionTest(args).startTests();
    }

    /********** test set-up **********/

    static void testSetUp() throws Exception {
        InstallSDE.install(new File(System.getProperty("test.classes", "."),
                                    "SourceDebugExtensionTarg.class"),
                           new File(System.getProperty("test.src", "."),
                                    "testString"));
    }

    /********** test core **********/

    protected void runTests() throws Exception {
        /*
         * Get to the top of main()
         * to determine targetClass
         */
        BreakpointEvent bpe = startToMain("SourceDebugExtensionTarg");
        targetClass = bpe.location().declaringType();

        if (!vm().canGetSourceDebugExtension()) {
            failure("FAIL: canGetSourceDebugExtension() is false");
        } else {
            println("canGetSourceDebugExtension() is true");
        }

        String expected = "An expected attribute string";
        String sde = targetClass.sourceDebugExtension();
        if (!sde.equals(expected)) {
            failure("FAIL: got '" + sde +
                    "' expected: '" + expected + "'");
        }

        /*
         * resume the target listening for events
         */
        listenUntilVMDisconnect();

        /*
         * deal with results of test
         * if anything has called failure("foo") testFailed will be true
         */
        if (!testFailed) {
            println("SourceDebugExtensionTest: passed");
        } else {
            throw new Exception("SourceDebugExtensionTest: failed");
        }
    }
}
