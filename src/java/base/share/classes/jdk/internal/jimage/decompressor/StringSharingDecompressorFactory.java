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
package java.base.share.classes.jdk.internal.jimage.decompressor;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * Constant Pool strings sharing Decompressor factory.
 *
 * @implNote This class needs to maintain JDK 8 source compatibility.
 *
 * It is used internally in the JDK to implement jimage/jrtfs access,
 * but also compiled and delivered as part of the jrtfs.jar to support access
 * to the jimage file provided by the shipped JDK by tools running on JDK 8.
 */
public class StringSharingDecompressorFactory extends ResourceDecompressorFactory {

    public static final String NAME = "compact-cp";
    public StringSharingDecompressorFactory() {
        super(NAME);
    }

    @Override
    public ResourceDecompressor newDecompressor(Properties properties)
            throws IOException {
        return new StringSharingDecompressor(properties);
    }
}
