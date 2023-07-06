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

package jdk.jfr.api.flightrecorder;


import jdk.jfr.FlightRecorder;
import jdk.jfr.Recording;
import jdk.jfr.RecordingState;














/**
 * @test
 * @key jfr
 * @requires vm.hasJFR
 * @library /test/lib /test/jdk
 * @run main/othervm jdk.jfr.api.flightrecorder.TestListener
 */
public class TestListener {

    public static void main(String[] args) throws Throwable {
        MyListener listener1 = new MyListener();
        MyListener listener2 = new MyListener();
        FlightRecorder.addListener(listener1);
        FlightRecorder.addListener(listener2);

        Recording r1 = new Recording();
        listener1.verifyState(0, RecordingState.NEW);
        listener2.verifyState(0, RecordingState.NEW);

        r1.start();
        listener1.verifyState(1, RecordingState.RUNNING);
        listener2.verifyState(1, RecordingState.RUNNING);

        FlightRecorder.removeListener(listener1); // listener1 should not get more callbacks.
        r1.stop();
        listener1.verifyState(1, RecordingState.RUNNING);
        listener2.verifyState(2, RecordingState.STOPPED);

        r1.close();
        listener1.verifyState(1, RecordingState.RUNNING);
        listener2.verifyState(3, RecordingState.CLOSED);
        FlightRecorder.removeListener(listener2);
    }

}