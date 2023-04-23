/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.nio.file;

/**
 * Unchecked exception thrown when an attempt is made to invoke an operation on
 * a watch service that is closed.
 *
 * @since 1.7
 */

public class ClosedWatchServiceException
    extends IllegalStateException
{
    @java.io.Serial
    static final long serialVersionUID = 1853336266231677732L;

    /**
     * Constructs an instance of this class.
     */
    public ClosedWatchServiceException() {
    }
}
