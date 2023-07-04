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

public class RedefineSubclassWithTwoInterfacesApp {
    public static void main(String args[]) throws Exception {
        System.out.println("Hello from RedefineSubclassWithTwoInterfacesApp!");

        new RedefineSubclassWithTwoInterfacesApp().doTest();

        System.exit(0);
    }

    private void doTest() throws Exception {
        RedefineSubclassWithTwoInterfacesImpl impl
            = new RedefineSubclassWithTwoInterfacesImpl();
        RedefineSubclassWithTwoInterfacesRemote remote
            = new RedefineSubclassWithTwoInterfacesRemote(impl, impl);
        String mesg;

        // make echo() calls before any redefinitions:
        mesg = remote.echo1("test message #1.1");
        System.out.println("RedefineSubclassWithTwoInterfacesApp: echo1 mesg='"
            + mesg + "' before any redefines");
        mesg = remote.echo2("test message #2.1");
        System.out.println("RedefineSubclassWithTwoInterfacesApp: echo2 mesg='"
            + mesg + "' before any redefines");


        // redefining RedefineSubclassWithTwoInterfacesImpl before
        // RedefineSubclassWithTwoInterfacesTarget fails:
        do_redefine("RedefineSubclassWithTwoInterfacesImpl", 1);
        do_redefine("RedefineSubclassWithTwoInterfacesTarget", 1);

        mesg = remote.echo1("test message #1.2");
        System.out.println("RedefineSubclassWithTwoInterfacesApp: echo1 mesg='"
            + mesg + "' after redefine");
        mesg = remote.echo2("test message #2.2");
        System.out.println("RedefineSubclassWithTwoInterfacesApp: echo2 mesg='"
            + mesg + "' after redefine");
    }

    private static void do_redefine(String className, int counter)
            throws Exception {
        File f = new File(className + "_" + counter + ".class");
        System.out.println("Reading test class from " + f);
        InputStream redefineStream = new FileInputStream(f);

        byte[] redefineBuffer
            = NamedBuffer.loadBufferFromStream(redefineStream);

        ClassDefinition redefineParamBlock = new ClassDefinition(
            Class.forName(className), redefineBuffer);

        RedefineSubclassWithTwoInterfacesAgent.getInstrumentation().
            redefineClasses(new ClassDefinition[] {redefineParamBlock});
    }
}
