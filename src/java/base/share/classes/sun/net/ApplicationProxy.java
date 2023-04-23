/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package sun.net;

import java.net.Proxy;
import java.net.SocketAddress;

/**
 * Proxy wrapper class so that we can determine application set
 * proxies by type.
 */
public final class ApplicationProxy extends Proxy {
    private ApplicationProxy(Proxy proxy) {
        super(proxy.type(), proxy.address());
    }

    public static ApplicationProxy create(Proxy proxy) {
        return new ApplicationProxy(proxy);
    }
}
