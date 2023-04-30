/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.reflect;

class ByteVectorFactory {
    static ByteVector create() {
        return new ByteVectorImpl();
    }

    static ByteVector create(int sz) {
        return new ByteVectorImpl(sz);
    }
}
