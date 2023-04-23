/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.net;

import java.io.IOException;
import java.net.spi.InetAddressResolver.LookupPolicy;

/*
 * Package private interface to "implementation" used by
 * {@link InetAddress}.
 * <p>
 * See {@link java.net.Inet4AddressImp} and
 * {@link java.net.Inet6AddressImp}.
 *
 * @since 1.4
 */
sealed interface InetAddressImpl permits Inet4AddressImpl, Inet6AddressImpl {

    String getLocalHostName() throws UnknownHostException;
    InetAddress[]
        lookupAllHostAddr(String hostname, LookupPolicy lookupPolicy) throws UnknownHostException;
    String getHostByAddr(byte[] addr) throws UnknownHostException;

    InetAddress anyLocalAddress();
    InetAddress loopbackAddress();
    boolean isReachable(InetAddress addr, int timeout, NetworkInterface netif,
                        int ttl) throws IOException;
}
