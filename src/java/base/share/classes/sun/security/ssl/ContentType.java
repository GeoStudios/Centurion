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
