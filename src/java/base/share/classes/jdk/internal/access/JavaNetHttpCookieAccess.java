/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.access;

import java.net.HttpCookie;
import java.util.List;

public interface JavaNetHttpCookieAccess {
    /*
     * Constructs cookies from Set-Cookie or Set-Cookie2 header string,
     * retaining the original header String in the cookie itself.
     */
    public List<HttpCookie> parse(String header);

    /*
     * Returns the original header this cookie was constructed from, if it was
     * constructed by parsing a header, otherwise null.
     */
    public String header(HttpCookie cookie);
}

