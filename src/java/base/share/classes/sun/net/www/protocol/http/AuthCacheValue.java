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

package java.base.share.classes.sun.net.www.protocol.http;

import java.io.Serializable;
import java.net.PasswordAuthentication;

/**
 * AuthCacheValue: interface to minimize exposure to authentication cache
 * for external users (i.e. plugin)
 *
 * @author Michael McMahon
 */

public abstract class AuthCacheValue implements Serializable {

    @java.io.Serial
    static final long serialVersionUID = 735249334068211611L;

    public enum Type {
        Proxy,
        Server
    };

    /**
     * Caches authentication info entered by user.  See cacheKey()
     */
    protected static AuthCache cache = new AuthCacheImpl();

    public static void setAuthCache (AuthCache map) {
        cache = map;
    }

    /* Package private ctor to prevent extension outside package */

    AuthCacheValue() {}

    /**
     * Proxy or Server
     */
    abstract Type getAuthType ();

    /**
     * Authentication scheme
     */
    abstract AuthScheme getAuthScheme();

   /**
    * name of server/proxy
    */
    abstract String getHost ();

   /**
    * portnumber of server/proxy
    */
    abstract int getPort();

   /**
    * realm of authentication if known
    */
    abstract String getRealm();

    /**
     * root path of realm or the request path if the root
     * is not known yet.
     */
    abstract String getPath();

    /**
     * returns http or https
     */
    abstract String getProtocolScheme();

    /**
     * the credentials associated with this authentication
     */
    abstract PasswordAuthentication credentials();
}
