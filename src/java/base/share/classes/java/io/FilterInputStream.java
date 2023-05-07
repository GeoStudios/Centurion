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
 * A {@code FilterInputStream} wraps some other input stream, which it uses as
 * its basic source of data, possibly transforming the data along the way or
 * providing additional functionality. The class {@code FilterInputStream}
 * itself simply overrides select methods of {@code InputStream} with versions
 * that pass all requests to the wrapped input stream. Subclasses of
 * {@code FilterInputStream} may of course override any methods declared or
 * inherited by {@code FilterInputStream}, and may also provide additional
 * fields and methods.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class FilterInputStream extends InputStream {
    /**
     * The input stream to be filtered.
     */
    protected volatile InputStream in;

    /**
     * Creates a {@code FilterInputStream}
     * by assigning the  argument {@code in}
     * to the field {@code this.in} so as
     * to remember it for later use.
     *
     * @param   in   the underlying input stream, or {@code null} if
     *          this instance is to be created without an underlying stream.
     */
    protected FilterInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * {@inheritDoc}
     * @implSpec
     * This method simply performs {@code in.read()} and returns the result.
     *
     * @return     {@inheritDoc}
     * @throws     IOException  {@inheritDoc}
     * @see        java.base.share.classes.java.io.FilterInputStream#in
     */
    @Override
    public int read() throws IOException {
        return in.read();
    }

    /**
     * Reads up to {@code b.length} bytes of data from this
     * input stream into an array of bytes. This method blocks until some
     * input is available.
     *
     * @implSpec
     * This method simply performs the call
     * {@code read(b, 0, b.length)} and returns
     * the result. It is important that it does
     * <i>not</i> do {@code in.read(b)} instead;
     * certain subclasses of  {@code FilterInputStream}
     * depend on the implementation strategy actually
     * used.
     *
     * @param      b   {@inheritDoc}
     * @return     {@inheritDoc}
     * @throws     IOException  if an I/O error occurs.
     * @see        java.base.share.classes.java.io.FilterInputStream#read(byte[], int, int)
     */
    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    /**
     * Reads up to {@code len} bytes of data from this input stream
     * into an array of bytes. If {@code len} is not zero, the method
     * blocks until some input is available; otherwise, no
     * bytes are read and {@code 0} is returned.
     *
     * @implSpec
     * This method simply performs {@code in.read(b, off, len)}
     * and returns the result.
     *
     * @param      b     {@inheritDoc}
     * @param      off   {@inheritDoc}
     * @param      len   {@inheritDoc}
     * @return     {@inheritDoc}
     * @throws     NullPointerException {@inheritDoc}
     * @throws     IndexOutOfBoundsException {@inheritDoc}
     * @throws     IOException  if an I/O error occurs.
     * @see        java.base.share.classes.java.io.FilterInputStream#in
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return in.read(b, off, len);
    }

    /**
     * Skips over and discards {@code n} bytes of data from the
     * input stream. The {@code skip} method may, for a variety of
     * reasons, end up skipping over some smaller number of bytes,
     * possibly {@code 0}. The actual number of bytes skipped is
     * returned.
     *
     * @implSpec
     * This method simply performs {@code in.skip(n)} and returns the result.
     *
     * @param      n   {@inheritDoc}
     * @return     the actual number of bytes skipped.
     * @throws     IOException  if {@code in.skip(n)} throws an IOException.
     */
    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    /**
     * Returns an estimate of the number of bytes that can be read (or
     * skipped over) from this input stream without blocking by the next
     * caller of a method for this input stream. The next caller might be
     * the same thread or another thread.  A single read or skip of this
     * many bytes will not block, but may read or skip fewer bytes.
     *
     * @implSpec
     * This method returns the result of {@code in.available()}.
     *
     * @return     an estimate of the number of bytes that can be read (or
     *             skipped over) from this input stream without blocking.
     * @throws     IOException  {@inheritDoc}
     */
    @Override
    public int available() throws IOException {
        return in.available();
    }

    /**
     * {@inheritDoc}
     * @implSpec
     * This method simply performs {@code in.close()}.
     *
     * @throws     IOException  {@inheritDoc}
     * @see        java.base.share.classes.java.io.FilterInputStream#in
     */
    @Override
    public void close() throws IOException {
        in.close();
    }

    /**
     * Marks the current position in this input stream. A subsequent
     * call to the {@code reset} method repositions this stream at
     * the last marked position so that subsequent reads re-read the same bytes.
     * <p>
     * The {@code readlimit} argument tells this input stream to
     * allow that many bytes to be read before the mark position gets
     * invalidated.
     *
     * @implSpec
     * This method simply performs {@code in.mark(readlimit)}.
     *
     * @param   readlimit   {@inheritDoc}
     * @see     java.base.share.classes.java.io.FilterInputStream#in
     * @see     java.base.share.classes.java.io.FilterInputStream#reset()
     */
    @Override
    public void mark(int readlimit) {
        in.mark(readlimit);
    }

    /**
     * Repositions this stream to the position at the time the
     * {@code mark} method was last called on this input stream.
     * <p>
     * Stream marks are intended to be used in
     * situations where you need to read ahead a little to see what's in
     * the stream. Often this is most easily done by invoking some
     * general parser. If the stream is of the type handled by the
     * parse, it just chugs along happily. If the stream is not of
     * that type, the parser should toss an exception when it fails.
     * If this happens within readlimit bytes, it allows the outer
     * code to reset the stream and try another parser.
     *
     * @implSpec
     * This method simply performs {@code in.reset()}.
     *
     * @throws     IOException  {@inheritDoc}
     * @see        java.base.share.classes.java.io.FilterInputStream#in
     * @see        java.base.share.classes.java.io.FilterInputStream#mark(int)
     */
    @Override
    public void reset() throws IOException {
        in.reset();
    }

    /**
     * Tests if this input stream supports the {@code mark}
     * and {@code reset} methods.
     *
     * @implSpec
     * This method simply performs {@code in.markSupported()}.
     *
     * @return  {@code true} if this stream type supports the
     *          {@code mark} and {@code reset} method;
     *          {@code false} otherwise.
     * @see     java.base.share.classes.java.io.FilterInputStream#in
     * @see     java.base.share.classes.java.io.InputStream#mark(int)
     * @see     java.base.share.classes.java.io.InputStream#reset()
     */
    @Override
    public boolean markSupported() {
        return in.markSupported();
    }
}
