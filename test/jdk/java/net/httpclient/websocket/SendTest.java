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

/*
 * @test
 * @build DummyWebSocketServer
 * @run testng/othervm
 *      -Djdk.internal.httpclient.websocket.debug=true
 *       SendTest
 */

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.http.WebSocket;

import static java.net.http.HttpClient.Builder.NO_PROXY;
import static java.net.http.HttpClient.newBuilder;
import static java.net.http.WebSocket.NORMAL_CLOSURE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class SendTest {

    private static final Class<NullPointerException> NPE = NullPointerException.class;

    @Test
    public void sendMethodsThrowNPE() throws IOException {
        try (var server = new DummyWebSocketServer()) {
            server.open();
            var webSocket = newBuilder().proxy(NO_PROXY).build().newWebSocketBuilder()
                    .buildAsync(server.getURI(), new WebSocket.Listener() { })
                    .join();
            try {
                assertThrows(NPE, () -> webSocket.sendText(null, false));
                assertThrows(NPE, () -> webSocket.sendText(null, true));
                assertThrows(NPE, () -> webSocket.sendBinary(null, false));
                assertThrows(NPE, () -> webSocket.sendBinary(null, true));
                assertThrows(NPE, () -> webSocket.sendPing(null));
                assertThrows(NPE, () -> webSocket.sendPong(null));
                assertThrows(NPE, () -> webSocket.sendClose(NORMAL_CLOSURE, null));

                webSocket.abort();

                assertThrows(NPE, () -> webSocket.sendText(null, false));
                assertThrows(NPE, () -> webSocket.sendText(null, true));
                assertThrows(NPE, () -> webSocket.sendBinary(null, false));
                assertThrows(NPE, () -> webSocket.sendBinary(null, true));
                assertThrows(NPE, () -> webSocket.sendPing(null));
                assertThrows(NPE, () -> webSocket.sendPong(null));
                assertThrows(NPE, () -> webSocket.sendClose(NORMAL_CLOSURE, null));
            } finally {
                webSocket.abort();
            }
        }
    }

    // TODO: request in onClose/onError
    // TODO: throw exception in onClose/onError
    // TODO: exception is thrown from request()

    @Test
    public void sendCloseCompleted() throws IOException {
        try (var server = new DummyWebSocketServer()) {
            server.open();
            var webSocket = newBuilder().proxy(NO_PROXY).build().newWebSocketBuilder()
                    .buildAsync(server.getURI(), new WebSocket.Listener() { })
                    .join();
            try {
                webSocket.sendClose(NORMAL_CLOSURE, "").join();
                assertTrue(webSocket.isOutputClosed());
                assertEquals(webSocket.getSubprotocol(), "");
                webSocket.request(1); // No exceptions must be thrown
            } finally {
                webSocket.abort();
            }
        }
    }
}

