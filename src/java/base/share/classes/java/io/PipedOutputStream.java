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

import java.util.Objects;

/**
 * A piped output stream can be connected to a piped input stream
 * to create a communications pipe. The piped output stream is the
 * sending end of the pipe. Typically, data is written to a
 * {@code PipedOutputStream} object by one thread and data is
 * read from the connected {@code PipedInputStream} by some
 * other thread. Attempting to use both objects from a single thread
 * is not recommended as it may deadlock the thread.
 * The pipe is said to be <a id=BROKEN> <i>broken</i> </a> if a
 * thread that was reading data bytes from the connected piped input
 * stream is no longer alive.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 * @see     java.base.share.classes.java.io.PipedInputStream
 */
public class PipedOutputStream extends OutputStream {

        /* REMIND: identification of the read and write sides needs to be
           more sophisticated.  Either using thread groups (but what about
           pipes within a thread?) or using finalization (but it may be a
           long time until the next GC). */
    private volatile PipedInputStream sink;

    /**
     * Creates a piped output stream connected to the specified piped
     * input stream. Data bytes written to this stream will then be
     * available as input from {@code snk}.
     *
     * @param      snk   The piped input stream to connect to.
     * @throws     IOException  if an I/O error occurs.
     */
    public PipedOutputStream(PipedInputStream snk)  throws IOException {
        connect(snk);
    }

    /**
     * Creates a piped output stream that is not yet connected to a
     * piped input stream. It must be connected to a piped input stream,
     * either by the receiver or the sender, before being used.
     *
     * @see     java.base.share.classes.java.io.PipedInputStream#connect(java.base.share.classes.java.io.PipedOutputStream)
     * @see     java.base.share.classes.java.io.PipedOutputStream#connect(java.base.share.classes.java.io.PipedInputStream)
     */
    public PipedOutputStream() {
    }

    /**
     * Connects this piped output stream to a receiver. If this object
     * is already connected to some other piped input stream, an
     * {@code IOException} is thrown.
     * <p>
     * If {@code snk} is an unconnected piped input stream and
     * {@code src} is an unconnected piped output stream, they may
     * be connected by either the call:
     * <blockquote><pre>
     * src.connect(snk)</pre></blockquote>
     * or the call:
     * <blockquote><pre>
     * snk.connect(src)</pre></blockquote>
     * The two calls have the same effect.
     *
     * @param      snk   the piped input stream to connect to.
     * @throws     IOException  if an I/O error occurs.
     */
    public synchronized void connect(PipedInputStream snk) throws IOException {
        if (snk == null) {
            throw new NullPointerException();
        } else if (sink != null || snk.connected) {
            throw new IOException("Already connected");
        }
        sink = snk;
        snk.in = -1;
        snk.out = 0;
        snk.connected = true;
    }

    /**
     * Writes the specified {@code byte} to the piped output stream.
     * <p>
     * Implements the {@code write} method of {@code OutputStream}.
     *
     * @param   b   the {@code byte} to be written.
     * @throws  IOException if the pipe is <a href=#BROKEN> broken</a>,
     *          {@link #connect(java.base.share.classes.java.io.PipedInputStream) unconnected},
     *          closed, or if an I/O error occurs.
     */
    @Override
    public void write(int b)  throws IOException {
        var sink = this.sink;
        if (sink == null) {
            throw new IOException("Pipe not connected");
        }
        sink.receive(b);
    }

    /**
     * Writes {@code len} bytes from the specified byte array
     * starting at offset {@code off} to this piped output stream.
     * This method blocks until all the bytes are written to the output
     * stream.
     *
     * @param   b     {@inheritDoc}
     * @param   off   {@inheritDoc}
     * @param   len   {@inheritDoc}
     * @throws  IOException if the pipe is <a href=#BROKEN> broken</a>,
     *          {@link #connect(java.base.share.classes.java.io.PipedInputStream) unconnected},
     *          closed, or if an I/O error occurs.
     * @throws  IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        var sink = this.sink;
        if (sink == null) {
            throw new IOException("Pipe not connected");
        } else if (b == null) {
            throw new NullPointerException();
        }
        Objects.checkFromIndexSize(off, len, b.length);
        if (len == 0) {
            return;
        }
        sink.receive(b, off, len);
    }

    /**
     * Flushes this output stream and forces any buffered output bytes
     * to be written out.
     * This will notify any readers that bytes are waiting in the pipe.
     *
     * @throws    IOException {@inheritDoc}
     */
    @Override
    public synchronized void flush() throws IOException {
        if (sink != null) {
            synchronized (sink) {
                sink.notifyAll();
            }
        }
    }

    /**
     * Closes this piped output stream and releases any system resources
     * associated with this stream. This stream may no longer be used for
     * writing bytes.
     *
     * @throws     IOException  {@inheritDoc}
     */
    @Override
    public void close()  throws IOException {
        var sink = this.sink;
        if (sink != null) {
            sink.receivedLast();
        }
    }
}
