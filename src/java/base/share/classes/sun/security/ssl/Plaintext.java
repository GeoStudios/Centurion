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

package java.base.share.classes.sun.security.ssl;

import java.nio.ByteBuffer;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;

/**
 * Plaintext
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class Plaintext {
    static final Plaintext PLAINTEXT_NULL = new Plaintext();

    final byte       contentType;
    final byte       majorVersion;
    final byte       minorVersion;
    final int        recordEpoch;     // increments on every cipher state change
    final long       recordSN;        // epoch | sequence number
    final ByteBuffer fragment;        // null if it needs to be reassembled

    HandshakeStatus  handshakeStatus; // null if not used or not handshaking

    private Plaintext() {
        this.contentType = 0;
        this.majorVersion = 0;
        this.minorVersion = 0;
        this.recordEpoch = -1;
        this.recordSN = -1;
        this.fragment = null;
        this.handshakeStatus = null;
    }

    Plaintext(byte contentType,
            byte majorVersion, byte minorVersion,
            int recordEpoch, long recordSN, ByteBuffer fragment) {

        this.contentType = contentType;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.recordEpoch = recordEpoch;
        this.recordSN = recordSN;
        this.fragment = fragment;

        this.handshakeStatus = null;
    }

    @Override
    public String toString() {
        return "contentType: " + contentType + "/" +
               "majorVersion: " + majorVersion + "/" +
               "minorVersion: " + minorVersion + "/" +
               "recordEpoch: " + recordEpoch + "/" +
               "recordSN: 0x" + Long.toHexString(recordSN) + "/" +
               "fragment: " + fragment;
    }
}
