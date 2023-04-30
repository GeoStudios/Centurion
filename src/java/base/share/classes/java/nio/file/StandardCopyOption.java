/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.nio.file;

/**
 * Defines the standard copy options.
 *
 * @since 1.7
 */

public enum StandardCopyOption implements CopyOption {
    /**
     * Replace an existing file if it exists.
     */
    REPLACE_EXISTING,
    /**
     * Copy attributes to the new file.
     */
    COPY_ATTRIBUTES,
    /**
     * Move the file as an atomic file system operation.
     */
    ATOMIC_MOVE;
}
