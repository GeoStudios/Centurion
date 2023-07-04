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
 *      -Djdk.httpclient.sendBufferSize=8192
 *       PendingPingTextClose
 */

// This test produce huge logs (14Mb+) so disable logging by default
// *      -Djdk.internal.httpclient.debug=true
// *      -Djdk.internal.httpclient.websocket.debug=true

import org.testng.annotations.Test;

import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PendingPingTextClose extends PendingOperations {

    static boolean debug = false; // avoid too verbose output
    CompletableFuture<WebSocket> cfText;
    CompletableFuture<WebSocket> cfPing;
    CompletableFuture<WebSocket> cfClose;

    @Test(dataProvider = "booleans")
    public void pendingPingTextClose(boolean last) throws Exception {
        try {
            repeatable(() -> {
                server = Support.notReadingServer();
                server.setReceiveBufferSize(1024);
                server.open();
                webSocket = httpClient().newWebSocketBuilder()
                        .buildAsync(server.getURI(), new WebSocket.Listener() { })
                        .join();
                ByteBuffer data = ByteBuffer.allocate(125);
                boolean done = false;
                for (int i = 0; ; i++) {  // fill up the send buffer
                    long start = System.currentTimeMillis();
                    if (debug) System.out.printf("begin cycle #%s at %s%n", i, start);
                    cfPing = webSocket.sendPing(data);
                    try {
                        cfPing.get(MAX_WAIT_SEC, TimeUnit.SECONDS);
                        data.clear();
                    } catch (TimeoutException e) {
                        done = true;
                        System.out.printf("Got expected timeout after %d iterations%n", i);
                        break;
                    } finally {
                        long stop = System.currentTimeMillis();
                        if (debug || done || (stop - start) > (MAX_WAIT_SEC * 1000L)/2L)
                            System.out.printf("end cycle #%s at %s (%s ms)%n", i, stop, stop - start);
                    }
                }
                assertFails(ISE, webSocket.sendPing(ByteBuffer.allocate(125)));
                assertFails(ISE, webSocket.sendPong(ByteBuffer.allocate(125)));
                System.out.println("asserting that sendText hangs");
                cfText = webSocket.sendText("hello", last);
                assertHangs(cfText);
                System.out.println("asserting that sendClose hangs");
                cfClose = webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok");
                assertHangs(cfClose);
                System.out.println("asserting that cfPing is not completed");
                assertNotDone(cfPing);
                System.out.println("finishing");
                return null;
            }, () -> cfPing.isDone()); // can't use method ref: cfPing not initialized
            webSocket.abort();
            assertFails(IOE, cfPing);
            assertFails(IOE, cfText);
            assertFails(IOE, cfClose);
        } catch (Throwable t) {
            System.err.printf("pendingPingTextClose(%s) failed: %s%n", last, t);
            t.printStackTrace();
            throw t;
        }
    }
}
