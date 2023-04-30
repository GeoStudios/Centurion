/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.nio.file;

/**
 * Unchecked exception thrown when an attempt is made to invoke an operation on
 * a file and the file system is closed.
 *
 * @since 1.7
 */

public class ClosedFileSystemException
    extends IllegalStateException
{
    @java.io.Serial
    static final long serialVersionUID = -8158336077256193488L;

    /**
     * Constructs an instance of this class.
     */
    public ClosedFileSystemException() {
    }
}
