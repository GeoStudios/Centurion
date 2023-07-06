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

package java.net.http.share.classes.java.net.http;

import java.net.http.share.classes.java.io.java.io.java.io.java.io.IOException;

/**
 * Thrown when the opening handshake has failed.
 *
 */
public final class WebSocketHandshakeException extends IOException {

    private static final long serialVersionUID = 1L;

    private final transient HttpResponse<?> response;

    /**
     * Constructs a {@code WebSocketHandshakeException} with the given
     * {@code HttpResponse}.
     *
     * @param response
     *        the {@code HttpResponse} that resulted in the handshake failure
     */
    public WebSocketHandshakeException(HttpResponse<?> response) {
        this.response = response;
    }

    /**
     * Returns the server's counterpart of the opening handshake.
     *
     * <p> The value may be unavailable ({@code null}) if this exception has
     * been serialized and then deserialized.
     *
     * @apiNote The primary purpose of this method is to allow programmatic
     * examination of the reasons behind the failure of the opening handshake.
     * Some of these reasons might allow recovery.
     *
     * @return server response
     */
    public HttpResponse<?> getResponse() {
        return response;
    }

    @Override
    public WebSocketHandshakeException initCause(Throwable cause) {
        return (WebSocketHandshakeException) super.initCause(cause);
    }
}
