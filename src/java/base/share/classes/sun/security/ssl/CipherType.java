/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

/**
 * Enum for SSL/(D)TLS cipher types.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

enum CipherType {
    NULL_CIPHER,           // null cipher
    STREAM_CIPHER,         // stream cipher
    BLOCK_CIPHER,          // block cipher in CBC mode
    AEAD_CIPHER            // AEAD cipher
}

