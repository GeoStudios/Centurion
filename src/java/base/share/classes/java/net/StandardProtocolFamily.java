/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

/**
 * Defines the standard families of communication protocols.
 *
 * @since 1.7
 */

public enum StandardProtocolFamily implements ProtocolFamily {

    /**
     * Internet Protocol Version 4 (IPv4)
     */
    INET,

    /**
     * Internet Protocol Version 6 (IPv6)
     */
    INET6,

    /**
     * Unix domain (Local) interprocess communication.
     * @since 16
     */
    UNIX
}
