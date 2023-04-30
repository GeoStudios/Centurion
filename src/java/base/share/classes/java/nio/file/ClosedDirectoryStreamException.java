/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.nio.file;

/**
 * Unchecked exception thrown when an attempt is made to invoke an operation on
 * a directory stream that is closed.
 *
 * @since 1.7
 */

public class ClosedDirectoryStreamException
    extends IllegalStateException
{
    @java.io.Serial
    static final long serialVersionUID = 4228386650900895400L;

    /**
     * Constructs an instance of this class.
     */
    public ClosedDirectoryStreamException() {
    }
}
