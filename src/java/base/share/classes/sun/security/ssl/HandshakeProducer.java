/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;
import java.base.share.classes.sun.security.ssl.SSLHandshake.HandshakeMessage;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface HandshakeProducer {
    // return the encoded producing if it has not been dumped to the context
    //
    // message: the handshake message responded to, can be null for producing
    //          of kickstart handshake message
    byte[] produce(ConnectionContext context,
            HandshakeMessage message) throws IOException;
}
