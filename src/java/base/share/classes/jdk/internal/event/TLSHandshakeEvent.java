/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.event;

/**
 * Event recording details of successful TLS handshakes.
 */

public final class TLSHandshakeEvent extends Event {
    public String peerHost;
    public int peerPort;
    public String protocolVersion;
    public String cipherSuite;
    public long certificateId;
}
