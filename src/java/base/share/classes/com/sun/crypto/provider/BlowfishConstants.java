/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package com.sun.crypto.provider;

/**
 * This class defines the constants used by the Blowfish algorithm
 * implementation.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 * @see BlowfishCipher
 * @see BlowfishCrypt
 */

interface BlowfishConstants {
    int BLOWFISH_BLOCK_SIZE = 8; // number of bytes
    int BLOWFISH_MAX_KEYSIZE = 56; // number of bytes
}
