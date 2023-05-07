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

import java.nio.ByteBuffer;

/**
 * This interface allows GHASH.java and GCTR.java to easily operate to
 * better operate with GaloisCounterMode.java
 * 
 * @since Alpha cdk-1.1
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
