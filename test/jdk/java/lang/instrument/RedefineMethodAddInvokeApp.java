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

import java.io.*;
import java.lang.instrument.*;

public class RedefineMethodAddInvokeApp {
    public static void main(String args[]) throws Exception {
        System.out.println("Hello from RedefineMethodAddInvokeApp!");

        new RedefineMethodAddInvokeApp().doTest();

        System.exit(0);
    }

    private void doTest() throws Exception {
        RedefineMethodAddInvokeTarget target =
            new RedefineMethodAddInvokeTarget();

        System.out.println("RedefineMethodAddInvokeApp: invoking myMethod()");
        target.test(0);  // invoke the original myMethod()

        // add myMethod1()
        do_redefine(1);

        System.out.println("RedefineMethodAddInvokeApp: invoking myMethod1()");
        target.test(1);  // invoke myMethod1()

        // add myMethod2()
        do_redefine(2);

        System.out.println("RedefineMethodAddInvokeApp: invoking myMethod2()");
        target.test(2);  // invoke myMethod2()
    }

    private static void do_redefine(int counter) throws Exception {
        File f = new File("RedefineMethodAddInvokeTarget_" + counter +
            ".class");
        System.out.println("Reading test class from " + f);
        InputStream redefineStream = new FileInputStream(f);

        byte[] redefineBuffer = NamedBuffer.loadBufferFromStream(redefineStream);

        ClassDefinition redefineParamBlock = new ClassDefinition(
            RedefineMethodAddInvokeTarget.class, redefineBuffer);

        RedefineMethodAddInvokeAgent.getInstrumentation().redefineClasses(
            new ClassDefinition[] {redefineParamBlock});
    }
}
