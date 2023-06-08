/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.java.io;

/**
 * ObjectInput extends the DataInput interface to include the reading of
 * objects. DataInput includes methods for the input of primitive types,
 * ObjectInput extends that interface to include objects, arrays, and Strings.
 *
 * @see java.base.share.classes.java.io.InputStream
 * @see java.base.share.classes.java.io.ObjectOutputStream
 * @see java.base.share.classes.java.io.ObjectInputStream
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public interface ObjectInput extends DataInput, AutoCloseable {
    /**
     * Read and return an object. The class that implements this interface
     * defines where the object is "read" from.
     *
     * @return    the object read from the stream
     * @throws    java.lang.ClassNotFoundException If the class of a serialized
     *            object cannot be found.
     * @throws    IOException If any of the usual Input/Output
     *            related exceptions occur.
     */
    public Object readObject()
        throws ClassNotFoundException, IOException;

    /**
     * Reads a byte of data. This method will block if no input is
     * available.
     * @return  the byte read, or -1 if the end of the
     *          stream is reached.
     * @throws  IOException If an I/O error has occurred.
     */
    public int read() throws IOException;

    /**
     * Reads into an array of bytes.  This method will
     * block until some input is available.
     * @param   b the buffer into which the data is read
     * @return  the total number of bytes read into the buffer, or
     *          {@code -1} if there is no more data because the end of
     *          the stream has been reached.
     * @throws  IOException If an I/O error has occurred.
     */
    public int read(byte[] b) throws IOException;

    /**
     * Reads into an array of bytes.  This method will
     * block until some input is available.
     * @param   b the buffer into which the data is read
     * @param   off the start offset of the data
     * @param   len the maximum number of bytes read
     * @return  the total number of bytes read into the buffer, or
     *          {@code -1} if there is no more data because the end of
     *          the stream has been reached.
     * @throws  IOException If an I/O error has occurred.
     * @throws  IndexOutOfBoundsException If {@code off} is negative,
     *          {@code len} is negative, or {@code len} is greater than
     *          {@code b.length - off}
     */
    public int read(byte[] b, int off, int len) throws IOException;

    /**
     * Skips n bytes of input.
     * @param   n the number of bytes to be skipped
     * @return  the actual number of bytes skipped.
     * @throws   IOException If an I/O error has occurred.
     */
    public long skip(long n) throws IOException;

    /**
     * Returns the number of bytes that can be read
     * without blocking.
     * @return  the number of available bytes.
     * @throws  IOException If an I/O error has occurred.
     */
    public int available() throws IOException;

    /**
     * Closes the input stream. Must be called
     * to release any resources associated with
     * the stream.
     * @throws    IOException If an I/O error has occurred.
     */
    public void close() throws IOException;
}
