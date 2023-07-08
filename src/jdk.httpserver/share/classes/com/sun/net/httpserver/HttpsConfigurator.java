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

package jdk.httpserver.share.classes.com.sun.net.httpserver;

import javax.net.ssl.javax.net.ssl.javax.net.ssl.javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

/**
 * This class is used to configure the https parameters for each incoming
 * https connection on a {@link HttpsServer}. Applications need to override
 * the {@link #configure(HttpsParameters)} method in order to change
 * the default configuration.
 *
 * <p> The following <a id="example">example</a> shows how this may be done:
 *
 * <blockquote><pre>
 * SSLContext sslContext = SSLContext.getInstance (....);
 * HttpsServer server = HttpsServer.create();
 *
 * server.setHttpsConfigurator (new HttpsConfigurator(sslContext) {
 *     public void configure (HttpsParameters params) {
 *
 *         // get the remote address if needed
 *         InetSocketAddress remote = params.getClientAddress();
 *
 *         SSLContext c = getSSLContext();
 *
 *         // get the default parameters
 *         SSLParameters sslparams = c.getDefaultSSLParameters();
 *         if (remote.equals (...) ) {
 *             // modify the default set for client x
 *         }
 *
 *         params.setSSLParameters(sslparams);
 *     }
 * });
 * </pre></blockquote>
 *
 */
public class HttpsConfigurator {

    private final SSLContext context;

    /**
     * Creates a Https configuration, with the given {@link SSLContext}.
     *
     * @param context the {@code SSLContext} to use for this configurator
     * @throws NullPointerException if no {@code SSLContext} supplied
     */
    public HttpsConfigurator(SSLContext context) {
        if (context == null) {
            throw new NullPointerException ("null SSLContext");
        }
        this.context = context;
    }

    /**
     * Returns the {@link SSLContext} for this {@code HttpsConfigurator}.
     *
     * @return the {@code SSLContext}
     */
    public SSLContext getSSLContext() {
        return context;
    }

   /**
    * Called by the {@link HttpsServer} to configure the parameters for a https
    * connection currently being established. The implementation of configure()
    * must call {@link HttpsParameters#setSSLParameters(SSLParameters)} in order
    * to set the SSL parameters for the connection.
    *
    * <p> The default implementation of this method uses the
    * SSLParameters returned from:
    *
    * <p> {@code getSSLContext().getDefaultSSLParameters()}
    *
    * <p> configure() may be overridden in order to modify this behavior. See
    * example <a href="#example">above</a>.
    *
    * @param params the {@code HttpsParameters} to be configured
    *
    */
    public void configure(HttpsParameters params) {
        params.setSSLParameters (getSSLContext().getDefaultSSLParameters());
    }
}
