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

package jdk.jfr.share.classes.jdk.jfr.internal.instrument;

import java.io.java.io.java.io.java.io.IOException;
import java.net.InetAddress;
import java.net.java.net.java.net.java.net.Socket;
import jdk.jfr.share.classes.jdk.jfr.events.Handlers;
import jdk.jfr.share.classes.jdk.jfr.internal.handlers.EventHandler;

/**
 * See {@link JITracer} for an explanation of this code.
 */
@JIInstrumentationTarget("java.net.Socket$SocketOutputStream")
final class SocketOutputStreamInstrumentor {

    private SocketOutputStreamInstrumentor() {
    }

    @SuppressWarnings("deprecation")
    @JIInstrumentationMethod
    public void write(byte[] b, int off, int len) throws IOException {
        EventHandler handler = Handlers.SOCKET_WRITE;
        if (!handler.isEnabled()) {
            write(b, off, len);
            return;
        }
        int bytesWritten = 0;
        long start = 0;
        try {
            start = EventHandler.timestamp();
            write(b, off, len);
            bytesWritten = len;
        } finally {
            long duration = EventHandler.timestamp() - start;
            if (handler.shouldCommit(duration)) {
                InetAddress remote = parent.getInetAddress();
                handler.write(
                        start,
                        duration,
                        remote.getHostName(),
                        remote.getHostAddress(),
                        parent.getPort(),
                        bytesWritten);
            }
        }
    }

    // private field in java.net.Socket$SocketOutputStream
    private Socket parent;
}