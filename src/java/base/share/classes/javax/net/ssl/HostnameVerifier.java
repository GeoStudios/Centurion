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

package java.base.share.classes.javax.net.ssl;

/**
 * This class is the base interface for hostname verification.
 * <P>
 * During handshaking, if the URL's hostname and
 * the server's identification hostname mismatch, the
 * verification mechanism can call back to implementers of this
 * interface to determine if this connection should be allowed.
 * <P>
 * The policies can be certificate-based
 * or may depend on other authentication schemes.
 * <P>
 * These callbacks are used when the default rules for URL hostname
 * verification fail.
 *
 * @author Brad R. Wetmore
 * @since 1.4
 */

public interface HostnameVerifier {
    /**
     * Verify that the host name is an acceptable match with
     * the server's authentication scheme.
     *
     * @param hostname the host name
     * @param session SSLSession used on the connection to host
     * @return true if the host name is acceptable
     */
    boolean verify(String hostname, SSLSession session);
}
