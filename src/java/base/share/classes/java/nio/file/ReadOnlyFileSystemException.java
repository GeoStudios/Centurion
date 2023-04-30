/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.nio.file;

/**
 * Unchecked exception thrown when an attempt is made to update an object
 * associated with a {@link FileSystem#isReadOnly() read-only} {@code FileSystem}.
 *
 * @since 1.7
 */

public class ReadOnlyFileSystemException
    extends UnsupportedOperationException
{
    @java.io.Serial
    static final long serialVersionUID = -6822409595617487197L;

    /**
     * Constructs an instance of this class.
     */
    public ReadOnlyFileSystemException() {
    }
}
