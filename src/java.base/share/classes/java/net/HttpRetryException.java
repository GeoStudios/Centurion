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

package java.base.share.classes.java.net;


import java.base.share.classes.java.io.java.io.java.io.java.io.IOException;















/**
 * Thrown to indicate that a HTTP request needs to be retried
 * but cannot be retried automatically, due to streaming mode
 * being enabled.
 *
 */
public class HttpRetryException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = -9186022286469111381L;

    /**
     * The response code.
     */
    private final int responseCode;

    /**
     * The URL to be redirected to.
     */
    private String location;

    /**
     * Constructs a new {@code HttpRetryException} from the
     * specified response code and exception detail message
     *
     * @param   detail   the detail message.
     * @param   code   the HTTP response code from server.
     */
    public HttpRetryException(String detail, int code) {
        super(detail);
        responseCode = code;
    }

    /**
     * Constructs a new {@code HttpRetryException} with detail message
     * responseCode and the contents of the Location response header field.
     *
     * @param   detail   the detail message.
     * @param   code   the HTTP response code from server.
     * @param   location   the URL to be redirected to
     */
    public HttpRetryException(String detail, int code, String location) {
        super (detail);
        responseCode = code;
        this.location = location;
    }

    /**
     * Returns the http response code
     *
     * @return  The http response code.
     */
    public int responseCode() {
        return responseCode;
    }

    /**
     * Returns a string explaining why the http request could
     * not be retried.
     *
     * @return  The reason string
     */
    public String getReason() {
        return super.getMessage();
    }

    /**
     * Returns the value of the Location header field if the
     * error resulted from redirection.
     *
     * @return The location string
     */
    public String getLocation() {
        return location;
    }
}
