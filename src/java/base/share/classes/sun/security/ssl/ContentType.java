/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

/**
 * Enum for SSL/(D)TLS content types.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

enum ContentType {
    INVALID             ((byte)0,   "invalid",
                            ProtocolVersion.PROTOCOLS_OF_13),
    CHANGE_CIPHER_SPEC  ((byte)20,  "change_cipher_spec",
                            ProtocolVersion.PROTOCOLS_TO_12),
    ALERT               ((byte)21,  "alert",
                            ProtocolVersion.PROTOCOLS_TO_13),
    HANDSHAKE           ((byte)22,  "handshake",
                            ProtocolVersion.PROTOCOLS_TO_13),
    APPLICATION_DATA    ((byte)23,  "application_data",
                            ProtocolVersion.PROTOCOLS_TO_13);

    final byte id;
    final String name;
    final ProtocolVersion[] supportedProtocols;

    ContentType(byte id, String name,
            ProtocolVersion[] supportedProtocols) {
        this.id = id;
        this.name = name;
        this.supportedProtocols = supportedProtocols;
    }

    static ContentType valueOf(byte id) {
        for (ContentType ct : ContentType.values()) {
            if (ct.id == id) {
                return ct;
            }
        }

        return null;
    }

    static String nameOf(byte id) {
        for (ContentType ct : ContentType.values()) {
            if (ct.id == id) {
                return ct.name;
            }
        }

        return "<UNKNOWN CONTENT TYPE: " + (id & 0x0FF) + ">";
    }
}
