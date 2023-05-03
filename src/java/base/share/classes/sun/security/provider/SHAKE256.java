/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.provider;

/**
 * The SHAKE256 extendable output function.
 * 
 * @since Java 2
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
