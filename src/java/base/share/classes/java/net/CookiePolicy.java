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

/**
 * CookiePolicy implementations decide which cookies should be accepted
 * and which should be rejected. Three pre-defined policy implementations
 * are provided, namely ACCEPT_ALL, ACCEPT_NONE and ACCEPT_ORIGINAL_SERVER.
 *
 * <p>See RFC 2965 sec. 3.3 and 7 for more detail.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public interface CookiePolicy {
    /**
     * One pre-defined policy which accepts all cookies.
     */
    public static final CookiePolicy ACCEPT_ALL = new CookiePolicy(){
        public boolean shouldAccept(URI uri, HttpCookie cookie) {
            return true;
        }
    };

    /**
     * One pre-defined policy which accepts no cookies.
     */
    public static final CookiePolicy ACCEPT_NONE = new CookiePolicy(){
        public boolean shouldAccept(URI uri, HttpCookie cookie) {
            return false;
        }
    };

    /**
     * One pre-defined policy which only accepts cookies from original server.
     */
    public static final CookiePolicy ACCEPT_ORIGINAL_SERVER  = new CookiePolicy(){
        public boolean shouldAccept(URI uri, HttpCookie cookie) {
            if (uri == null || cookie == null)
                return false;
            return HttpCookie.domainMatches(cookie.getDomain(), uri.getHost());
        }
    };


    /**
     * Will be called to see whether or not this cookie should be accepted.
     *
     * @param uri       the URI to consult accept policy with
     * @param cookie    the HttpCookie object in question
     * @return          {@code true} if this cookie should be accepted;
     *                  otherwise, {@code false}
     */
    public boolean shouldAccept(URI uri, HttpCookie cookie);
}
