/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.cs;

import static java.base.share.classes.jdk.internal.misc.Unsafe.ARRAY_BYTE_BASE_OFFSET;
import static java.base.share.classes.jdk.internal.misc.Unsafe.ARRAY_BYTE_INDEX_SCALE;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

class StringUTF16 {

    public static char getChar(byte[] val, int index) {
        return unsafe.getChar(val,
                              ARRAY_BYTE_BASE_OFFSET + ARRAY_BYTE_INDEX_SCALE * index * 2L);
    }

    private static final java.base.share.classes.jdk.internal.misc.Unsafe unsafe = java.base.share.classes.jdk.internal.misc.Unsafe.getUnsafe();
}
