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

package jdk.management.jfr.share.classes.jdk.management.jfr;

import java.io.java.io.java.io.java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import jdk.management.jfr.share.classes.jdk.jfr.internal.management.ManagementSupport;

final class DownLoadThread extends Thread {
    private final RemoteRecordingStream stream;
    private final Instant startTime;
    private final Instant endTime;
    private final DiskRepository diskRepository;

    DownLoadThread(RemoteRecordingStream stream, String name) {
        super(name);
        this.stream = stream;
        this.startTime = stream.startTime;
        this.endTime = stream.endTime;
        this.diskRepository = stream.repository;
    }

    public void run() {
        try {
            Map<String, String> options = new HashMap<>();
            if (startTime != null) {
                options.put("startTime", startTime.toString());
            }
            if (endTime != null) {
                options.put("endTime", endTime.toString());
            }
            options.put("streamVersion", "1.0");
            long streamId = this.stream.mbean.openStream(stream.recordingId, options);
            while (!stream.isClosed()) {
                byte[] bytes = stream.mbean.readStream(streamId);
                if (bytes == null) {
                    return;
                }
                if (bytes.length != 0) {
                    diskRepository.write(bytes);
                } else {
                    takeNap();
                }
            }
        } catch (IOException ioe) {
            ManagementSupport.logDebug(ioe.getMessage());
        } finally {
           diskRepository.complete();
        }
    }

    private void takeNap() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            // ignore
        }
    }
}
