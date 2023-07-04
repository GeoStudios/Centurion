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

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;

/*
 * THE CONTENTS OF THIS FILE HAVE TO BE IN SYNC WITH THE EXAMPLES USED IN THE
 * JAVADOC OF WEBSOCKET TYPE
 *
 * @test
 * @compile WebSocketExample.java
 */
public class WebSocketExample {

    WebSocket.Listener listener = new WebSocket.Listener() {
        // ...
    };

    public void newBuilderExample0() {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create("ws://websocket.example.com"), listener);
    }

    public void newBuilderExample1() {
        InetSocketAddress addr = new InetSocketAddress("proxy.example.com", 80);
        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.of(addr))
                .build();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create("ws://websocket.example.com"), listener);
    }

    public void requestExample() {
        WebSocket.Listener listener = new WebSocket.Listener() {

            StringBuilder text = new StringBuilder();

            @Override
            public CompletionStage<?> onText(WebSocket webSocket,
                                             CharSequence message,
                                             boolean last) {
                text.append(message);
                if (last) {
                    processCompleteTextMessage(text);
                    text = new StringBuilder();
                }
                webSocket.request(1);
                return null;
            }
        };
    }

    static void processCompleteTextMessage(CharSequence result) { }
}
