/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.com.sun.crypto.provider;

import java.nio.ByteBuffer;

/**
 * This interface allows GHASH.java and GCTR.java to easily operate to
 * better operate with GaloisCounterMode.java
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public interface GCM {
    int update(byte[] in, int inOfs, int inLen, byte[] out, int outOfs);
    int update(byte[] in, int inOfs, int inLen, ByteBuffer dst);
    int update(ByteBuffer src, ByteBuffer dst);
    int doFinal(byte[] in, int inOfs, int inLen, byte[] out, int outOfs);
    int doFinal(ByteBuffer src, ByteBuffer dst);
}
