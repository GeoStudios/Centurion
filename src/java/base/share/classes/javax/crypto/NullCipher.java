/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package javax.crypto;

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
