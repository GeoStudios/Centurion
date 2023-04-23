/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.io;

import java.io.IOException;

/**
 * A {@code Flushable} is a destination of data that can be flushed.  The
 * flush method is invoked to write any buffered output to the underlying
 * stream.
 *
 * @since 1.5
 */
public interface Flushable {

    /**
     * Flushes this stream by writing any buffered output to the underlying
     * stream.
     *
     * @throws IOException If an I/O error occurs
     */
    void flush() throws IOException;
}
