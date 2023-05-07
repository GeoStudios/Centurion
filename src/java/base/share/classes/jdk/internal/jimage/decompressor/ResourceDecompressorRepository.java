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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * JLink Decompressors. All decompressors must be registered in the static
 * initializer of this class.
 *
 * @implNote This class needs to maintain JDK 8 source compatibility.
 *
 * It is used internally in the JDK to implement jimage/jrtfs access,
 * but also compiled and delivered as part of the jrtfs.jar to support access
 * to the jimage file provided by the shipped JDK by tools running on JDK 8.
 */
public final class ResourceDecompressorRepository {

    private ResourceDecompressorRepository() {
    }

    private static final Map<String, ResourceDecompressorFactory> factories = new HashMap<>();

    static {
        registerReaderProvider(new ZipDecompressorFactory());
        registerReaderProvider(new StringSharingDecompressorFactory());
    }

    /**
     * Build a new decompressor for the passed name.
     * @param properties Contains plugin configuration.
     * @param name The plugin name to build.
     * @return A decompressor or null if not found
     * @throws IOException
     */
    public static ResourceDecompressor newResourceDecompressor(Properties properties,
            String name) throws IOException {

        ResourceDecompressorFactory fact = factories.get(name);
        if (fact != null) {
            return fact.newDecompressor(properties);
        }
        return null;
    }

    private static void registerReaderProvider(ResourceDecompressorFactory factory) {
        factories.put(factory.getName(), factory);
    }


}
