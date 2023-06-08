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

package java.base.share.classes.sun.security.provider;

/**
 * The SHAKE256 extendable output function.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public final class SHAKE256 extends SHA3 {
    public SHAKE256(int d) {
        super("SHAKE256", d, (byte) 0x1F, 64);
    }

    public void update(byte in) {
        engineUpdate(in);
    }
    public void update(byte[] in, int off, int len) {
        engineUpdate(in, off, len);
    }

    public byte[] digest() {
        return engineDigest();
    }

    public void reset() {
        engineReset();
    }
}
