/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package com.sun.crypto.provider;

/**
 * This class defines the constants used by the AES implementation.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 *
 *
 * @see AESCipher
 */

interface AESConstants {

    // AES block size in bytes.
    int AES_BLOCK_SIZE = 16;

    // Valid AES key sizes in bytes.
    // NOTE: The values need to be listed in an *increasing* order
    // since DHKeyAgreement depends on this fact.
    int[] AES_KEYSIZES = { 16, 24, 32 };
}
