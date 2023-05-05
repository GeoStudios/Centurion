/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

/**
 * A simple interface which provides a mechanism to map
 * between a file name and a MIME type string.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public interface FileNameMap {

    /**
     * Gets the MIME type for the specified file name.
     * @param fileName the specified file name
     * @return a {@code String} indicating the MIME
     * type for the specified file name.
     */
    public String getContentTypeFor(String fileName);
}
