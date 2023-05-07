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

