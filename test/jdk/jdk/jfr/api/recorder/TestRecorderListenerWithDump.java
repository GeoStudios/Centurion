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

package jdk.jfr.api.recorder;

import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

import jdk.jfr.FlightRecorder;
import jdk.jfr.FlightRecorderListener;
import jdk.jfr.Recording;
/**
 * @test TestRecorderListenerWithDump
 *
 * @key jfr
 * @requires vm.hasJFR
 * @run main/othervm jdk.jfr.api.recorder.TestRecorderListenerWithDump
 */
public class TestRecorderListenerWithDump {

    public static void main(String... args) throws Exception {
        AtomicBoolean nullRecording = new AtomicBoolean();
        FlightRecorder.addListener(new FlightRecorderListener() {
            public void recordingStateChanged(Recording r) {
                if (r == null) {
                    nullRecording.set(true);
                } else {
                    System.out.println("Recording " + r.getName() + " " + r.getState());
                }
            }
        });
        try (Recording r = new Recording()) {
            r.start();
            r.dump(Paths.get("dump.jfr"));
        }
        if (nullRecording.get()) {
            throw new Exception("FlightRecorderListener returned null recording");
        }
    }
}
