/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;
import java.base.share.classes.sun.security.ssl.SSLHandshake.HandshakeMessage;

/**
 * Interface for handshake message or extension absence on handshake
 * message processing.
 *
 * This is typically used after the SSLSession object created, so that the
 * extension can update/impact the session object.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface HandshakeAbsence {
    void absent(ConnectionContext context,
            HandshakeMessage message) throws IOException;
}

