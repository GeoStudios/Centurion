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
 * This is an implementation of the HMAC-MD5 algorithm.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */
public final class HmacMD5 extends HmacCore {
    /**
     * Standard constructor, creates a new HmacMD5 instance.
     */
    public HmacMD5() throws NoSuchAlgorithmException {
        super("MD5", 64);
    }
}
