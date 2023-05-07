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
import java.base.share.classes.java.net.spi.InetAddressResolver.LookupPolicy;

/*
 * Package private interface to "implementation" used by
 * {@link InetAddress}.
 * <p>
 * See {@link java.base.share.classes.java.net.Inet4AddressImp} and
 * {@link java.base.share.classes.java.net.Inet6AddressImp}.
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
