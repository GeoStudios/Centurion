/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.security.InvalidKeyException;
import javax.crypto.SecretKey;

/**
 * Special interface for additional MessageDigestSpi method(s).
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public interface MessageDigestSpi2 {

    /**
     * Updates the digest using the specified key.
     * This is used for SSL 3.0 only, we may deprecate and remove the support
     * of this in the future
     *
     * @param key  the key whose value is to be digested.
     */
    void engineUpdate(SecretKey key) throws InvalidKeyException;
}
