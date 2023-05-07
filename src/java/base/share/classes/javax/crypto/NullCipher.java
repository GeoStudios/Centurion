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

package java.base.share.classes.javax.crypto;

/**
 * The {@code NullCipher} class is a class that provides an
 * "identity cipher" -- one that does not transform the plain text.  As
 * a consequence, the ciphertext is identical to the plaintext.  All
 * initialization methods do nothing, while the blocksize is set to 1
 * byte.
 *
 * @author  Li Gong
 * @since 1.4
 */

public class NullCipher extends Cipher {

    /**
     * Creates a {@code NullCipher} object.
     */
    public NullCipher() {
        super(new NullCipherSpi(), null);
    }
}
