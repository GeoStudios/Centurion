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
 * Standard constants definitions
 *
 * @since 1.8
 */
public final class StandardConstants {

    // Suppress default constructor for noninstantiability
    private StandardConstants() {
        throw new AssertionError(
            "No java.base.share.classes.javax.net.ssl.StandardConstants instances for you!");
    }

    /**
     * The "host_name" type representing of a DNS hostname
     * (see {@link SNIHostName}) in a Server Name Indication (SNI) extension.
     * <P>
     * The SNI extension is a feature that extends the SSL/TLS protocols to
     * indicate what server name the client is attempting to connect to during
     * handshaking.  See section 3, "Server Name Indication", of <A
     * HREF="http://www.ietf.org/rfc/rfc6066.txt">TLS Extensions (RFC 6066)</A>.
     * <P>
     * The value of this constant is {@value}.
     *
     * @see SNIServerName
     * @see SNIHostName
     */
    public static final int SNI_HOST_NAME = 0x00;
}
