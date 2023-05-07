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

package java.base.share.classes.com.sun.crypto.provider;

/**
 * This class defines the constants used by the AES implementation.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
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
