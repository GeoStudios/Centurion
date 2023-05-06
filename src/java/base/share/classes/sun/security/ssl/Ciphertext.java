/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;

/**
 * Ciphertext
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class Ciphertext {
    final byte contentType;
    final byte handshakeType;
    final long recordSN;

    HandshakeStatus handshakeStatus;    // null if not used or not handshaking

    private Ciphertext() {
        this.contentType = 0;
        this.handshakeType = -1;
        this.recordSN = -1L;
        this.handshakeStatus = null;
    }

    Ciphertext(byte contentType, byte handshakeType, long recordSN) {
        this.contentType = contentType;
        this.handshakeType = handshakeType;
        this.recordSN = recordSN;
        this.handshakeStatus = null;
    }
}
