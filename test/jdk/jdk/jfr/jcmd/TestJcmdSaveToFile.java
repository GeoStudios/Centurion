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

package jdk.jfr.jcmd;


import java.io.File;
import jdk.test.lib.jfr.FileHelper;
import jdk.test.lib.process.OutputAnalyzer;














/**
 * @test
 * @summary The test verifies that recording can be written to a file both with JFR.start and JFR.stop
 * @key jfr
 * @requires vm.hasJFR
 * @library /test/lib /test/jdk
 * @run main/othervm jdk.jfr.jcmd.TestJcmdSaveToFile
 */
public class TestJcmdSaveToFile {

    public static void main(String[] args) throws Exception {
        testStartAndSave();
        testStopAndSave();
    }

    private static void testStartAndSave() throws Exception {
        String name = "testStartAndSave";
        File recording = new File(name + ".jfr");
        OutputAnalyzer output = JcmdHelper.jcmd("JFR.start",
                "name=" + name,
                "duration=1h",
                "filename=" + recording.getAbsolutePath());
        JcmdAsserts.assertRecordingHasStarted(output);
        JcmdHelper.waitUntilRunning(name);
        JcmdHelper.stopAndCheck(name);
        FileHelper.verifyRecording(recording);
    }

    private static void testStopAndSave() throws Exception {
        String name = "testStopAndSave";
        File recording = new File(name + ".jfr");
        OutputAnalyzer output = JcmdHelper.jcmd("JFR.start", "name=" + name);
        JcmdAsserts.assertRecordingHasStarted(output);
        JcmdHelper.waitUntilRunning(name);
        JcmdHelper.stopWriteToFileAndCheck(name, recording);
        FileHelper.verifyRecording(recording);
    }
}