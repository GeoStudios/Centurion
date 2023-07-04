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

package jdk.jfr.internal.management;

import java.io.Closeable;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

import jdk.jfr.Recording;
import jdk.jfr.internal.consumer.FinishedStream;
import jdk.jfr.internal.consumer.OngoingStream;

// abstract class that hides if a recording is ongoing or finished.
public abstract class EventByteStream implements Closeable {
    public static final String NAME = "Remote Recording Stream";
    private static final AtomicLong idCounter = new AtomicLong();

    private final long identifier;
    private volatile long time;

    public EventByteStream() {
        this.identifier = idCounter.incrementAndGet();
    }

    public static EventByteStream newOngoingStream(Recording recording, int blockSize, long  startTimeNanos,long endTimeNanos) {
        return new OngoingStream(recording, blockSize, startTimeNanos, endTimeNanos);
    }

    public static EventByteStream newFinishedStream(InputStream is, int blockSize) {
        return new FinishedStream(is, blockSize);
    }

    protected final void touch() {
        time = System.currentTimeMillis();
    }

    public final long getLastTouched() {
        return time;
    }

    public abstract byte[] read() throws IOException;

    public final long getId() {
        return identifier;
    }
}
