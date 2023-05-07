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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.base.share.classes.jdk.internal.jimage.decompressor.ResourceDecompressor.StringsProvider;

/**
 * Entry point to decompress resources.
 *
 * @implNote This class needs to maintain JDK 8 source compatibility.
 *
 * It is used internally in the JDK to implement jimage/jrtfs access,
 * but also compiled and delivered as part of the jrtfs.jar to support access
 * to the jimage file provided by the shipped JDK by tools running on JDK 8.
 */
public final class Decompressor {

    private final Map<Integer, ResourceDecompressor> pluginsCache = new HashMap<>();

    public Decompressor() {
    }

    /**
     * Decompress a resource.
     * @param order Byte order.
     * @param provider Strings provider
     * @param content The resource content to uncompress.
     * @return A fully uncompressed resource.
     * @throws IOException
     */
    public byte[] decompressResource(ByteOrder order, StringsProvider provider,
            byte[] content) throws IOException {
        Objects.requireNonNull(order);
        Objects.requireNonNull(provider);
        Objects.requireNonNull(content);
        CompressedResourceHeader header;
        do {
            header = CompressedResourceHeader.readFromResource(order, content);
            if (header != null) {
                ResourceDecompressor decompressor =
                        pluginsCache.get(header.getDecompressorNameOffset());
                if (decompressor == null) {
                    String pluginName =
                            provider.getString(header.getDecompressorNameOffset());
                    if (pluginName == null) {
                        throw new IOException("Plugin name not found");
                    }
                    String storedContent = header.getStoredContent(provider);
                    Properties props = new Properties();
                    if (storedContent != null) {
                        try (ByteArrayInputStream stream
                                = new ByteArrayInputStream(storedContent.
                                        getBytes(StandardCharsets.UTF_8));) {
                            props.loadFromXML(stream);
                        }
                    }
                    decompressor = ResourceDecompressorRepository.
                            newResourceDecompressor(props, pluginName);
                    if (decompressor == null) {
                        throw new IOException("Plugin not found: " + pluginName);
                    }

                    pluginsCache.put(header.getDecompressorNameOffset(), decompressor);
                }
                try {
                    content = decompressor.decompress(provider, content,
                            CompressedResourceHeader.getSize(), header.getUncompressedSize());
                } catch (Exception ex) {
                    throw new IOException(ex);
                }
            }
        } while (header != null);
        return content;
    }
}
