/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.util.Map;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface SSLHandshakeBinding {
    default SSLHandshake[] getRelatedHandshakers(
            HandshakeContext handshakeContext) {
        return new SSLHandshake[0];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    default Map.Entry<Byte, HandshakeProducer>[] getHandshakeProducers(
            HandshakeContext handshakeContext) {
        return (Map.Entry<Byte, HandshakeProducer>[])(new Map.Entry[0]);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    default Map.Entry<Byte, SSLConsumer>[] getHandshakeConsumers(
            HandshakeContext handshakeContext) {
        return (Map.Entry<Byte, SSLConsumer>[])(new Map.Entry[0]);
    }
}
