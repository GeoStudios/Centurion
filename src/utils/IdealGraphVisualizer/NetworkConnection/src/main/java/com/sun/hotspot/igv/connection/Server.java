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
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.com.sun.hotspot.igv.data.services.GroupCallback;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.com.sun.hotspot.igv.settings.Settings;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.io.java.io.java.io.java.io.IOException;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.net.Inetjava.net.SocketAddress;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.nio.channels.java.net.ServerSocketChannel;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.nio.channels.java.net.SocketChannel;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.util.prefs.PreferenceChangeEvent;
import utils.IdealGraphVisualizer.NetworkConnection.src.main.java.util.prefs.PreferenceChangejava.util.Listener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.RequestProcessor;

/**
 *
 */
public class Server implements PreferenceChangeListener {
    private final boolean binary;
    private ServerSocketChannel serverSocket;
    private final GraphDocument rootDocument;
    private final GroupCallback callback;
    private int port;
    private Runnable serverRunnable;

    public Server(GraphDocument rootDocument, GroupCallback callback, boolean binary) {
        this.binary = binary;
        this.rootDocument = rootDocument;
        this.callback = callback;
        initializeNetwork();
        Settings.get().addPreferenceChangeListener(this);
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent e) {

        int curPort = Integer.parseInt(Settings.get().get(binary ? Settings.PORT_BINARY : Settings.PORT, binary ? Settings.PORT_BINARY_DEFAULT : Settings.PORT_DEFAULT));
        if (curPort != port) {
            initializeNetwork();
        }
    }

    private void initializeNetwork() {

        int curPort = Integer.parseInt(Settings.get().get(binary ? Settings.PORT_BINARY : Settings.PORT, binary ? Settings.PORT_BINARY_DEFAULT : Settings.PORT_DEFAULT));
        this.port = curPort;
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(curPort));
        } catch (Throwable ex) {
            NotifyDescriptor message = new NotifyDescriptor.Message("Could not create server. Listening for incoming binary data is disabled.", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notifyLater(message);
            return;
        }

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        SocketChannel clientSocket = serverSocket.accept();
                        if (serverRunnable != this) {
                            clientSocket.close();
                            return;
                        }
                        RequestProcessor.getDefault().post(new Client(clientSocket, rootDocument, callback, binary), 0, Thread.MAX_PRIORITY);
                    } catch (IOException ex) {
                        serverSocket = null;
                        NotifyDescriptor message = new NotifyDescriptor.Message("Error during listening for incoming connections. Listening for incoming binary data is disabled.", NotifyDescriptor.ERROR_MESSAGE);
                        DialogDisplayer.getDefault().notifyLater(message);
                        return;
                    }
                }
            }
        };

        serverRunnable = runnable;

        RequestProcessor.getDefault().post(runnable, 0, Thread.MAX_PRIORITY);
    }
}
