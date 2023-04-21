/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.x509;

import java.lang.reflect.Field;
import sun.security.util.HexDumpEncoder;

/**
 * An extension that cannot be parsed due to decoding errors or invalid
 * content.
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
