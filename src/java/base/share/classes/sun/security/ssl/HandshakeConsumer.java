/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;
import java.base.share.classes.sun.security.ssl.SSLHandshake.HandshakeMessage;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface HandshakeConsumer {
    // message: the handshake message to be consumed.
    void consume(ConnectionContext context,
            HandshakeMessage message) throws IOException;
}
