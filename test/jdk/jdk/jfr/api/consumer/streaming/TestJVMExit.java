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

package jdk.jfr.api.consumer.streaming;


import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import jdk.jfr.consumer.EventStream;














/**
 * @test
 * @summary Test that a stream ends/closes when an application exists.
 * @key jfr
 * @requires vm.hasJFR
 * @library /test/lib /test/jdk
 * @modules jdk.jfr jdk.attach java.base/jdk.internal.misc
 *
 * @run main/othervm jdk.jfr.api.consumer.streaming.TestJVMExit
 */
public class TestJVMExit {

    public static void main(String... args) throws Exception {
        try (TestProcess process = new TestProcess("exit-application")) {
            AtomicInteger eventCounter = new AtomicInteger();
            try (EventStream es = EventStream.openRepository(process.getRepository())) {
                // Start from first event in repository
                es.setStartTime(Instant.EPOCH);
                es.onEvent(e -> {
                    if (eventCounter.incrementAndGet() == TestProcess.NUMBER_OF_EVENTS) {
                        process.exit();
                    }
                });
                es.start();
            }
        }
    }
}
