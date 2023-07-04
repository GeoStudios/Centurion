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

import sun.jvm.hotspot.HotSpotAgent;
import sun.jvm.hotspot.oops.Oop;
import sun.jvm.hotspot.runtime.ObjectMonitor;
import sun.jvm.hotspot.runtime.ObjectSynchronizer;
import sun.jvm.hotspot.runtime.VM;

import jdk.test.lib.apps.LingeredApp;
import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.SA.SATestUtils;

/**
 * @test
 * @bug 8259008
 * @library /test/lib
 * @requires vm.hasSA
 * @modules jdk.hotspot.agent/sun.jvm.hotspot
 *          jdk.hotspot.agent/sun.jvm.hotspot.oops
 *          jdk.hotspot.agent/sun.jvm.hotspot.runtime
 * @run driver TestObjectMonitorIterate
 */

public class TestObjectMonitorIterate {

    private static void test(String pid) {
        HotSpotAgent agent = new HotSpotAgent();
        agent.attach(Integer.parseInt(pid));
        try {
            var heap = VM.getVM().getObjectHeap();
            var itr = ObjectSynchronizer.objectMonitorIterator();

            if (!itr.hasNext()) {
                throw new RuntimeException("Monitor not found");
            }

            while (itr.hasNext()) {
                ObjectMonitor mon = (ObjectMonitor)itr.next();
                Oop oop = heap.newOop(mon.object());
                System.out.println("Monitor found: " + oop.getKlass().getName().asString());
            }
        } finally {
            agent.detach();
        }
    }

    private static void createAnotherToAttach(long lingeredAppPid) throws Exception {
        // Start a new process to attach to the lingered app
        ProcessBuilder processBuilder = ProcessTools.createJavaProcessBuilder(
            "--add-modules=jdk.hotspot.agent",
            "--add-exports=jdk.hotspot.agent/sun.jvm.hotspot=ALL-UNNAMED",
            "--add-exports=jdk.hotspot.agent/sun.jvm.hotspot.oops=ALL-UNNAMED",
            "--add-exports=jdk.hotspot.agent/sun.jvm.hotspot.runtime=ALL-UNNAMED",
            "TestObjectMonitorIterate",
             Long.toString(lingeredAppPid));
        SATestUtils.addPrivilegesIfNeeded(processBuilder);
        OutputAnalyzer SAOutput = ProcessTools.executeProcess(processBuilder);
        SAOutput.shouldHaveExitValue(0);
        System.out.println(SAOutput.getOutput());
    }

    public static void main (String... args) throws Exception {
        SATestUtils.skipIfCannotAttach(); // throws SkippedException if attach not expected to work.

        if (args == null || args.length == 0) {
            LingeredApp app = new LingeredAppWithLock();
            try {
                LingeredApp.startApp(app, "-XX:+UsePerfData");
                createAnotherToAttach(app.getPid());
            } finally {
                LingeredApp.stopApp(app);
            }
        } else {
            test(args[0]);
        }
    }
}
