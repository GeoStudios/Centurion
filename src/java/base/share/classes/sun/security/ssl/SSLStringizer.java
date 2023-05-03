/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.nio.ByteBuffer;

/**
 * Interface to decode a {@code ByteBuffer} into legible {@code String}.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface SSLStringizer {
    /**
     * Returns a legible string representation of a {@code ByteBuffer}.
     *
     * Note that the implementation MUST not change the internal status of
     * the {@code buffer}.
     */
    String toString(HandshakeContext handshakeContext, ByteBuffer buffer);
}
