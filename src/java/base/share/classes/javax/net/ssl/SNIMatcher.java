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

import java.util.Collection;

/**
 * Instances of this class represent a matcher that performs match
 * operations on an {@link SNIServerName} instance.
 * <P>
 * Servers can use Server Name Indication (SNI) information to decide if
 * specific {@link SSLSocket} or {@link SSLEngine} instances should accept
 * a connection.  For example, when multiple "virtual" or "name-based"
 * servers are hosted on a single underlying network address, the server
 * application can use SNI information to determine whether this server is
 * the exact server that the client wants to access.  Instances of this
 * class can be used by a server to verify the acceptable server names of
 * a particular type, such as host names.
 * <P>
 * {@code SNIMatcher} objects are immutable.  Subclasses should not provide
 * methods that can change the state of an instance once it has been created.
 *
 * @see SNIServerName
 * @see SNIHostName
 * @see SSLParameters#getSNIMatchers()
 * @see SSLParameters#setSNIMatchers(Collection)
 *
 * @since 1.8
 */
public abstract class SNIMatcher {

    // the type of the server name that this matcher performs on
    private final int type;

    /**
     * Creates an {@code SNIMatcher} using the specified server name type.
     *
     * @param  type
     *         the type of the server name that this matcher performs on
     *
     * @throws IllegalArgumentException if {@code type} is not in the range
     *         of 0 to 255, inclusive.
     */
    protected SNIMatcher(int type) {
        if (type < 0) {
            throw new IllegalArgumentException(
                "Server name type cannot be less than zero");
        } else if (type > 255) {
            throw new IllegalArgumentException(
                "Server name type cannot be greater than 255");
        }

        this.type = type;
    }

    /**
     * Returns the server name type of this {@code SNIMatcher} object.
     *
     * @return the server name type of this {@code SNIMatcher} object.
     *
     * @see SNIServerName
     */
    public final int getType() {
        return type;
    }

    /**
     * Attempts to match the given {@link SNIServerName}.
     *
     * @param  serverName
     *         the {@link SNIServerName} instance on which this matcher
     *         performs match operations
     *
     * @return {@code true} if, and only if, the matcher matches the
     *         given {@code serverName}
     *
     * @throws NullPointerException if {@code serverName} is {@code null}
     * @throws IllegalArgumentException if {@code serverName} is
     *         not of the given server name type of this matcher
     *
     * @see SNIServerName
     */
    public abstract boolean matches(SNIServerName serverName);
}
