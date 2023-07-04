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
import java.io.PrintWriter;

import jdk.test.lib.JDKToolFinder;
import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;

/*
 * Test to redefine java/lang/Object and verify that it doesn't crash on vtable
 * call on basic array type.
 * Test to redefine java/lang/ClassLoader and java/lang/reflect/Method to make
 * sure cached versions used afterward are the current version.
 *
 * @test
 * @bug 8005056 8009728 8218399
 * @requires vm.jvmti
 * @requires !vm.graal.enabled
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 *          java.instrument
 *          java.management
 * @build Agent
 * @run driver jdk.test.lib.helpers.ClassFileInstaller Agent
 * @run driver TestRedefineObject
 * @run main/othervm -javaagent:agent.jar -Xlog:redefine+class+load=debug,redefine+class+timer=info Agent
 */
public class TestRedefineObject {
    public static void main(String[] args) throws Exception  {

      PrintWriter pw = new PrintWriter("MANIFEST.MF");
      pw.println("Premain-Class: Agent");
      pw.println("Can-Retransform-Classes: true");
      pw.close();

      ProcessBuilder pb = new ProcessBuilder();
      pb.command(new String[] { JDKToolFinder.getJDKTool("jar"), "cmf", "MANIFEST.MF", "agent.jar", "Agent.class"});
      pb.start().waitFor();
    }
}
