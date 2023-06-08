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

package java.base.share.classes.sun.security.x509;

import java.lang.reflect.Field;
import sun.security.util.HexDumpEncoder;

/**
 * An extension that cannot be parsed due to decoding errors or invalid
 * content.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
class UnparseableExtension extends Extension {
    private String name;
    private final String exceptionDescription;
    private final String exceptionMessage;

    UnparseableExtension(Extension ext, Throwable why) {
        super(ext);

        name = "";
        try {
            Class<?> extClass = OIDMap.getClass(ext.getExtensionId());
            if (extClass != null) {
                Field field = extClass.getDeclaredField("NAME");
                name = field.get(null) + " ";
            }
        } catch (Exception e) {
            // If we cannot find the name, just ignore it
        }

        this.exceptionDescription = why.toString();
        this.exceptionMessage = why.getMessage();
    }

    String exceptionMessage() {
        return exceptionMessage;
    }

    @Override public String toString() {
        return super.toString() +
                "Unparseable " + name + "extension due to\n" +
                exceptionDescription + "\n\n" +
                new HexDumpEncoder().encodeBuffer(getExtensionValue());
    }
}
