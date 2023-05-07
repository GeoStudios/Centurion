/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.java.net;

import java.io.IOException;

/**
 * Thrown to indicate that a HTTP request needs to be retried
 * but cannot be retried automatically, due to streaming mode
 * being enabled.
 *
 * @author  Michael McMahon
 * @since   1.5
 */
public class HttpRetryException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = -9186022286469111381L;

    /**
     * The response code.
     */
    private int responseCode;

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
