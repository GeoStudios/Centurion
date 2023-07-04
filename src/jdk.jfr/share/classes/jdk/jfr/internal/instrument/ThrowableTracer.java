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

package jdk.jfr.internal.instrument;

import java.util.concurrent.atomic.AtomicLong;

import jdk.jfr.events.Handlers;
import jdk.jfr.internal.handlers.EventHandler;

public final class ThrowableTracer {

    private static final AtomicLong numThrowables = new AtomicLong();

    public static void traceError(Error e, String message) {
        if (e instanceof OutOfMemoryError) {
            return;
        }
        long timestamp = EventHandler.timestamp();

        EventHandler h1 = Handlers.ERROR_THROWN;
        if (h1.isEnabled()) {
            h1.write(timestamp, 0L, message, e.getClass());
        }
        EventHandler h2 = Handlers.EXCEPTION_THROWN;
        if (h2.isEnabled()) {
            h2.write(timestamp, 0L, message, e.getClass());
        }
        numThrowables.incrementAndGet();
    }

    public static void traceThrowable(Throwable t, String message) {
        EventHandler h = Handlers.EXCEPTION_THROWN;
        if (h.isEnabled()) {
            long timestamp = EventHandler.timestamp();
            h.write(timestamp, 0L, message, t.getClass());
        }
        numThrowables.incrementAndGet();
    }

    public static long numThrowables() {
        return numThrowables.get();
    }
}
