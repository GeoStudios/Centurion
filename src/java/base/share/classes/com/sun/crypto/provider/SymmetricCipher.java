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

import java.security.InvalidKeyException;

/**
 * This abstract class represents the core of all block ciphers. It allows to
 * initialize the cipher and encrypt/decrypt single blocks. Larger quantities
 * are handled by modes, which are subclasses of FeedbackCipher.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 *
 *
 * @see AESCrypt
 * @see DESCrypt
 * @see DESedeCrypt
 * @see BlowfishCrypt
 * @see FeedbackCipher
 */
abstract class SymmetricCipher {

    SymmetricCipher() {
        // empty
    }

    /**
     * Retrieves this cipher's block size.
     *
     * @return the block size of this cipher
     */
    abstract int getBlockSize();

    /**
     * Initializes the cipher in the specified mode with the given key.
     *
     * @param decrypting flag indicating encryption or decryption
     * @param algorithm the algorithm name
     * @param key the key
     *
     * @exception InvalidKeyException if the given key is inappropriate for
     * initializing this cipher
     */
    abstract void init(boolean decrypting, String algorithm, byte[] key)
        throws InvalidKeyException;

    /**
     * Encrypt one cipher block.
     *
     * <p>The input <code>plain</code>, starting at <code>plainOffset</code>
     * and ending at <code>(plainOffset+blockSize-1)</code>, is encrypted.
     * The result is stored in <code>cipher</code>, starting at
     * <code>cipherOffset</code>.
     *
     * @param plain the input buffer with the data to be encrypted
     * @param plainOffset the offset in <code>plain</code>
     * @param cipher the buffer for the encryption result
     * @param cipherOffset the offset in <code>cipher</code>
     */
    abstract void encryptBlock(byte[] plain, int plainOffset,
                          byte[] cipher, int cipherOffset);

    /**
     * Decrypt one cipher block.
     *
     * <p>The input <code>cipher</code>, starting at <code>cipherOffset</code>
     * and ending at <code>(cipherOffset+blockSize-1)</code>, is decrypted.
     * The result is stored in <code>plain</code>, starting at
     * <code>plainOffset</code>.
     *
     * @param cipher the input buffer with the data to be decrypted
     * @param cipherOffset the offset in <code>cipher</code>
     * @param plain the buffer for the decryption result
     * @param plainOffset the offset in <code>plain</code>
     */
    abstract void decryptBlock(byte[] cipher, int cipherOffset,
                          byte[] plain, int plainOffset);
}
