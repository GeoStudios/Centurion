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

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

public interface JavaNetInetAddressAccess {
    /**
     * Return the original application specified hostname of
     * the given InetAddress object.
     */
    String getOriginalHostName(InetAddress ia);

    /**
     * Returns the 32-bit IPv4 address.
     */
    int addressValue(Inet4Address inet4Address);

    /**
     * Returns a reference to the byte[] with the IPv6 address.
     */
    byte[] addressBytes(Inet6Address inet6Address);
}
