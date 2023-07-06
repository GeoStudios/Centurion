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

package jdk.jfr.api.recording.time;


import java.time.Duration;
import java.time.Instant;
import jdk.jfr.Recording;
import jdk.jfr.RecordingState;
import jdk.test.lib.Asserts;
import jdk.test.lib.jfr.CommonHelper;














/**
 * @test
 * @key jfr
 * @summary Test Recording.scheduleStart() and Recording.get*Time()
 * @requires vm.hasJFR
 * @library /test/lib
 * @run main/othervm jdk.jfr.api.recording.time.TestTimeScheduleStart
 */

public class TestTimeScheduleStart {

    public static void main(String[] args) throws Throwable {
        testScheduledRecordingIsDelayed();
        testScheduledRecordingIsRunning();
    }

    private static void testScheduledRecordingIsRunning() throws Exception {
        Recording r = new Recording();
        r.scheduleStart(Duration.ofSeconds(2));
        Asserts.assertNotNull(r.getStartTime(), "start time is null after scheduleStart()");
        CommonHelper.waitForRecordingState(r, RecordingState.RUNNING);
        Asserts.assertLessThanOrEqual(r.getStartTime(), Instant.now(), "start time should not exceed current time");
        r.stop();
        r.close();
    }

    private static void testScheduledRecordingIsDelayed() throws Exception {
        Recording r = new Recording();
        r.scheduleStart(Duration.ofHours(10));
        CommonHelper.verifyRecordingState(r, RecordingState.DELAYED);
        r.close();
    }
}