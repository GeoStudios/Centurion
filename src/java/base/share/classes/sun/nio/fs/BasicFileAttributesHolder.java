/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.fs;

import java.nio.file.attribute.BasicFileAttributes;

/**
 * Implemented by objects that may hold or cache the attributes of a file.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public interface BasicFileAttributesHolder {
    /**
     * Returns cached attributes (may be null). If file is a symbolic link then
     * the attributes are the link attributes and not the final target of the
     * file.
     */
    BasicFileAttributes get();

    /**
     * Invalidates cached attributes
     */
    void invalidate();
}
