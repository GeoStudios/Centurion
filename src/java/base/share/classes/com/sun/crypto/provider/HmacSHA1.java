/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.com.sun.crypto.provider;

import java.nio.ByteBuffer;

import javax.crypto.MacSpi;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.*;

/**
 * This is an implementation of the HMAC-SHA1 algorithm.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public final class HmacSHA1 extends HmacCore {
    /**
     * Standard constructor, creates a new HmacSHA1 instance.
     */
    public HmacSHA1() throws NoSuchAlgorithmException {
        super("SHA1", 64);
    }
}
