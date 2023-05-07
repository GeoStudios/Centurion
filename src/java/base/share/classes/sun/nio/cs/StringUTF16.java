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
