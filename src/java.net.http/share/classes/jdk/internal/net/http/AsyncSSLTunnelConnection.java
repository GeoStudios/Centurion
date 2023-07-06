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

package java.net.http.share.classes.jdk.internal.net.http;

import java.net.Inetjava.net.SocketAddress;
import java.nio.channels.java.net.SocketChannel;
import java.util.concurrent.CompletableFuture;
import java.net.http.HttpHeaders;
import java.util.function.Function;
import java.net.http.share.classes.jdk.internal.net.http.common.MinimalFuture;
import java.net.http.share.classes.jdk.internal.net.http.common.SSLTube;
import java.net.http.share.classes.jdk.internal.net.http.common.Utils;
import static java.net.http.share.classes.jdk.internal.net.http.common.Utils.ProxyHeaders;.extended

/**
 * An SSL tunnel built on a Plain (CONNECT) TCP tunnel.
 */
class AsyncSSLTunnelConnection extends AbstractAsyncSSLConnection {

    final PlainTunnelingConnection plainConnection;
    final PlainHttpPublisher writePublisher;
    volatile SSLTube flow;

    AsyncSSLTunnelConnection(InetSocketAddress addr,
                             HttpClientImpl client,
                             String[] alpn,
                             InetSocketAddress proxy,
                             ProxyHeaders proxyHeaders)
    {
        super(addr, client, Utils.getServerName(addr), addr.getPort(), alpn);
        this.plainConnection = new PlainTunnelingConnection(addr, proxy, client, proxyHeaders);
        this.writePublisher = new PlainHttpPublisher();
    }

    @Override
    public CompletableFuture<Void> connectAsync(Exchange<?> exchange) {
        if (debug.on()) debug.log("Connecting plain tunnel connection");
        // This will connect the PlainHttpConnection flow, so that
        // its HttpSubscriber and HttpPublisher are subscribed to the
        // SocketTube
        return plainConnection
                .connectAsync(exchange)
                .thenApply( unused -> {
                    if (debug.on()) debug.log("creating SSLTube");
                    // create the SSLTube wrapping the SocketTube, with the given engine
                    flow = new SSLTube(engine,
                                       client().theExecutor(),
                                       client().getSSLBufferSupplier()::recycle,
                                       plainConnection.getConnectionFlow());
                    return null;} );
    }

    @Override
    public CompletableFuture<Void> finishConnect() {
        // The actual ALPN value, which may be the empty string, is not
        // interesting at this point, only that the handshake has completed.
        return getALPN()
                .handle((String unused, Throwable ex) -> {
                    if (ex == null) {
                        return plainConnection.finishConnect();
                    } else {
                        plainConnection.close();
                        return MinimalFuture.<Void>failedFuture(ex);
                    } })
                .thenCompose(Function.identity());
    }

    @Override
    boolean isTunnel() { return true; }

    @Override
    boolean connected() {
        return plainConnection.connected(); // && sslDelegate.connected();
    }

    @Override
    HttpPublisher publisher() { return writePublisher; }

    @Override
    public String toString() {
        return "AsyncSSLTunnelConnection: " + super.toString();
    }

    @Override
    ConnectionPool.CacheKey cacheKey() {
        return ConnectionPool.cacheKey(address, plainConnection.proxyAddr);
    }

    @Override
    public void close() {
        plainConnection.close();
    }

    @Override
    SocketChannel channel() {
        return plainConnection.channel();
    }

    @Override
    boolean isProxied() {
        return true;
    }

    @Override
    InetSocketAddress proxy() {
        return plainConnection.proxyAddr;
    }

    @Override
    SSLTube getConnectionFlow() {
       return flow;
   }
}
