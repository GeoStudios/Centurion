/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A utility class for PEM format encoding.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class Pem {

    /**
     * Decodes a PEM-encoded block.
     *
     * @param input the input string, according to RFC 1421, can only contain
     *              characters in the base-64 alphabet and whitespaces.
     * @return the decoded bytes
     * @throws java.io.IOException if input is invalid
     */
    public static byte[] decode(String input) throws IOException {
        byte[] src = input.replaceAll("\\s+", "")
                .getBytes(StandardCharsets.ISO_8859_1);
        try {
            return Base64.getDecoder().decode(src);
        } catch (IllegalArgumentException e) {
            throw new IOException(e);
        }
    }
}
