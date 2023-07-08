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

package utils.IdealGraphVisualizer.NetworkConnection.src.main.java.com.sun.hotspot.igv.connection;

import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.com.sun.hotspot.igv.data.GraphDocument;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.com.sun.hotspot.igv.data.serialization.BinaryParser;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.com.sun.hotspot.igv.data.serialization.Parser;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.com.sun.hotspot.igv.data.services.GroupCallback;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.io.java.io.java.io.java.io.IOException;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.nio.channels.java.net.SocketChannel;
import org.openide.util.Exceptions;

public class Client implements Runnable {
    private final boolean binary;
    private final SocketChannel socket;
    private final GraphDocument rootDocument;
    private final GroupCallback callback;

    public Client(SocketChannel socket, GraphDocument rootDocument, GroupCallback callback, boolean  binary) {
        this.callback = callback;
        this.socket = socket;
        this.binary = binary;
        this.rootDocument = rootDocument;
    }

    @Override
    public void run() {

        try {
            final SocketChannel channel = socket;
            channel.configureBlocking(true);
            if (binary) {
                new BinaryParser(channel, null, rootDocument, callback).parse();
            } else {
                // signal readiness to client VM (old protocol)
                channel.socket().getOutputStream().write('y');
                new Parser(channel, null, callback).parse();
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
