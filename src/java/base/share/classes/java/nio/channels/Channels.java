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

package java.base.share.classes.java.nio.channels;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.base.share.classes.java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.base.share.classes.sun.nio.cs.StreamDecoder;
import java.base.share.classes.sun.nio.cs.StreamEncoder;

/**
 * Utility methods for channels and streams.
 *
 * <p> This class defines static methods that support the interoperation of the
 * stream classes of the {@link java.io} package with the channel classes
 * of this package.  </p>
 *
 *
 * @author Mark Reinhold
 * @author Mike McCloskey
 * @author JSR-51 Expert Group
 * @since 1.4
 */

public final class Channels {

    private Channels() { throw new Error("no instances"); }

    // -- Byte streams from channels --

    /**
     * Constructs a stream that reads bytes from the given channel.
     *
     * <p> The {@code read} and {@code transferTo} methods of the resulting stream
     * will throw an {@link IllegalBlockingModeException} if invoked while the
     * underlying channel is in non-blocking mode. The {@code transferTo} method
     * will also throw an {@code IllegalBlockingModeException} if invoked to
     * transfer bytes to an output stream that writes to an underlying channel in
     * non-blocking mode.  The stream will not be buffered, and
     * it will not support the {@link InputStream#mark mark} or {@link
     * InputStream#reset reset} methods.  The stream will be safe for access by
     * multiple concurrent threads.  Closing the stream will in turn cause the
     * channel to be closed.  </p>
     *
     * @param  ch
     *         The channel from which bytes will be read
     *
     * @return  A new input stream
     */
    public static InputStream newInputStream(ReadableByteChannel ch) {
        Objects.requireNonNull(ch, "ch");
        return sun.nio.ch.Streams.of(ch);
    }

    /**
     * Constructs a stream that writes bytes to the given channel.
     *
     * <p> The {@code write} methods of the resulting stream will throw an
     * {@link IllegalBlockingModeException} if invoked while the underlying
     * channel is in non-blocking mode.  The stream will not be buffered.  The
     * stream will be safe for access by multiple concurrent threads.  Closing
     * the stream will in turn cause the channel to be closed.  </p>
     *
     * @param  ch
     *         The channel to which bytes will be written
     *
     * @return  A new output stream
     */
    public static OutputStream newOutputStream(WritableByteChannel ch) {
        Objects.requireNonNull(ch, "ch");
        return sun.nio.ch.Streams.of(ch);
    }

    /**
     * Constructs a stream that reads bytes from the given channel.
     *
     * <p> The stream will not be buffered, and it will not support the {@link
     * InputStream#mark mark} or {@link InputStream#reset reset} methods.  The
     * stream will be safe for access by multiple concurrent threads.  Closing
     * the stream will in turn cause the channel to be closed.  </p>
     *
     * @param  ch
     *         The channel from which bytes will be read
     *
     * @return  A new input stream
     *
     * @since 1.7
     */
    public static InputStream newInputStream(AsynchronousByteChannel ch) {
        Objects.requireNonNull(ch, "ch");
        return new InputStream() {

            private ByteBuffer bb;
            private byte[] bs;           // Invoker's previous array
            private byte[] b1;

            @Override
            public synchronized int read() throws IOException {
                if (b1 == null)
                    b1 = new byte[1];
                int n = this.read(b1);
                if (n == 1)
                    return b1[0] & 0xff;
                return -1;
            }

            @Override
            public synchronized int read(byte[] bs, int off, int len)
                    throws IOException
            {
                Objects.checkFromIndexSize(off, len, bs.length);
                if (len == 0) {
                    return 0;
                }

                ByteBuffer bb = ((this.bs == bs)
                                 ? this.bb
                                 : ByteBuffer.wrap(bs));
                bb.position(off);
                bb.limit(Math.min(off + len, bb.capacity()));
                this.bb = bb;
                this.bs = bs;

                boolean interrupted = false;
                try {
                    for (;;) {
                        try {
                            return ch.read(bb).get();
                        } catch (ExecutionException ee) {
                            throw new IOException(ee.getCause());
                        } catch (InterruptedException ie) {
                            interrupted = true;
                        }
                    }
                } finally {
                    if (interrupted)
                        Thread.currentThread().interrupt();
                }
            }

            @Override
            public void close() throws IOException {
                ch.close();
            }
        };
    }

    /**
     * Constructs a stream that writes bytes to the given channel.
     *
     * <p> The stream will not be buffered. The stream will be safe for access
     * by multiple concurrent threads.  Closing the stream will in turn cause
     * the channel to be closed.  </p>
     *
     * @param  ch
     *         The channel to which bytes will be written
     *
     * @return  A new output stream
     *
     * @since 1.7
     */
    public static OutputStream newOutputStream(AsynchronousByteChannel ch) {
        Objects.requireNonNull(ch, "ch");
        return new OutputStream() {

            private ByteBuffer bb;
            private byte[] bs;   // Invoker's previous array
            private byte[] b1;

            @Override
            public synchronized void write(int b) throws IOException {
                if (b1 == null)
                    b1 = new byte[1];
                b1[0] = (byte) b;
                this.write(b1);
            }

            @Override
            public synchronized void write(byte[] bs, int off, int len)
                    throws IOException
            {
                if ((off < 0) || (off > bs.length) || (len < 0) ||
                    ((off + len) > bs.length) || ((off + len) < 0)) {
                    throw new IndexOutOfBoundsException();
                } else if (len == 0) {
                    return;
                }
                ByteBuffer bb = ((this.bs == bs)
                                 ? this.bb
                                 : ByteBuffer.wrap(bs));
                bb.limit(Math.min(off + len, bb.capacity()));
                bb.position(off);
                this.bb = bb;
                this.bs = bs;

                boolean interrupted = false;
                try {
                    while (bb.remaining() > 0) {
                        try {
                            ch.write(bb).get();
                        } catch (ExecutionException ee) {
                            throw new IOException(ee.getCause());
                        } catch (InterruptedException ie) {
                            interrupted = true;
                        }
                    }
                } finally {
                    if (interrupted)
                        Thread.currentThread().interrupt();
                }
            }

            @Override
            public void close() throws IOException {
                ch.close();
            }
        };
    }


    // -- Channels from streams --

    /**
     * Constructs a channel that reads bytes from the given stream.
     *
     * <p> The resulting channel will not be buffered; it will simply redirect
     * its I/O operations to the given stream.  Closing the channel will in
     * turn cause the stream to be closed.  </p>
     *
     * @param  in
     *         The stream from which bytes are to be read
     *
     * @return  A new readable byte channel
     */
    public static ReadableByteChannel newChannel(InputStream in) {
        Objects.requireNonNull(in, "in");

        if (in.getClass() == FileInputStream.class) {
            return ((FileInputStream) in).getChannel();
        }

        return new ReadableByteChannelImpl(in);
    }

    private static class ReadableByteChannelImpl
        extends AbstractInterruptibleChannel    // Not really interruptible
        implements ReadableByteChannel
    {
        private final InputStream in;
        private static final int TRANSFER_SIZE = 8192;
        private byte[] buf = new byte[0];
        private final Object readLock = new Object();

        ReadableByteChannelImpl(InputStream in) {
            this.in = in;
        }

        @Override
        public int read(ByteBuffer dst) throws IOException {
            if (!isOpen()) {
                throw new ClosedChannelException();
            }
            if (dst.isReadOnly()) {
                throw new IllegalArgumentException();
            }

            int len = dst.remaining();
            int totalRead = 0;
            int bytesRead = 0;
            synchronized (readLock) {
                while (totalRead < len) {
                    int bytesToRead = Math.min((len - totalRead),
                                               TRANSFER_SIZE);
                    if (buf.length < bytesToRead)
                        buf = new byte[bytesToRead];
                    if ((totalRead > 0) && !(in.available() > 0))
                        break; // block at most once
                    try {
                        begin();
                        bytesRead = in.read(buf, 0, bytesToRead);
                    } finally {
                        end(bytesRead > 0);
                    }
                    if (bytesRead < 0)
                        break;
                    else
                        totalRead += bytesRead;
                    dst.put(buf, 0, bytesRead);
                }
                if ((bytesRead < 0) && (totalRead == 0))
                    return -1;

                return totalRead;
            }
        }

        @Override
        protected void implCloseChannel() throws IOException {
            in.close();
        }
    }


    /**
     * Constructs a channel that writes bytes to the given stream.
     *
     * <p> The resulting channel will not be buffered; it will simply redirect
     * its I/O operations to the given stream.  Closing the channel will in
     * turn cause the stream to be closed.  </p>
     *
     * @param  out
     *         The stream to which bytes are to be written
     *
     * @return  A new writable byte channel
     */
    public static WritableByteChannel newChannel(OutputStream out) {
        Objects.requireNonNull(out, "out");

        if (out.getClass() == FileOutputStream.class) {
            return ((FileOutputStream) out).getChannel();
        }

        return new WritableByteChannelImpl(out);
    }

    private static class WritableByteChannelImpl
        extends AbstractInterruptibleChannel    // Not really interruptible
        implements WritableByteChannel
    {
        private final OutputStream out;
        private static final int TRANSFER_SIZE = 8192;
        private byte[] buf = new byte[0];
        private final Object writeLock = new Object();

        WritableByteChannelImpl(OutputStream out) {
            this.out = out;
        }

        @Override
        public int write(ByteBuffer src) throws IOException {
            if (!isOpen()) {
                throw new ClosedChannelException();
            }

            int len = src.remaining();
            int totalWritten = 0;
            synchronized (writeLock) {
                while (totalWritten < len) {
                    int bytesToWrite = Math.min((len - totalWritten),
                                                TRANSFER_SIZE);
                    if (buf.length < bytesToWrite)
                        buf = new byte[bytesToWrite];
                    src.get(buf, 0, bytesToWrite);
                    try {
                        begin();
                        out.write(buf, 0, bytesToWrite);
                    } finally {
                        end(bytesToWrite > 0);
                    }
                    totalWritten += bytesToWrite;
                }
                return totalWritten;
            }
        }

        @Override
        protected void implCloseChannel() throws IOException {
            out.close();
        }
    }


    // -- Character streams from channels --

    /**
     * Constructs a reader that decodes bytes from the given channel using the
     * given decoder.
     *
     * <p> The resulting stream will contain an internal input buffer of at
     * least {@code minBufferCap} bytes.  The stream's {@code read} methods
     * will, as needed, fill the buffer by reading bytes from the underlying
     * channel; if the channel is in non-blocking mode when bytes are to be
     * read then an {@link IllegalBlockingModeException} will be thrown.  The
     * resulting stream will not otherwise be buffered, and it will not support
     * the {@link Reader#mark mark} or {@link Reader#reset reset} methods.
     * Closing the stream will in turn cause the channel to be closed.  </p>
     *
     * @param  ch
     *         The channel from which bytes will be read
     *
     * @param  dec
     *         The charset decoder to be used
     *
     * @param  minBufferCap
     *         The minimum capacity of the internal byte buffer,
     *         or {@code -1} if an implementation-dependent
     *         default capacity is to be used
     *
     * @return  A new reader
     */
    public static Reader newReader(ReadableByteChannel ch,
                                   CharsetDecoder dec,
                                   int minBufferCap)
    {
        Objects.requireNonNull(ch, "ch");
        return StreamDecoder.forDecoder(ch, dec.reset(), minBufferCap);
    }

    /**
     * Constructs a reader that decodes bytes from the given channel according
     * to the named charset.
     *
     * <p> An invocation of this method of the form
     *
     * <pre> {@code
     *     Channels.newReader(ch, csname)
     * } </pre>
     *
     * behaves in exactly the same way as the expression
     *
     * <pre> {@code
     *     Channels.newReader(ch, Charset.forName(csName))
     * } </pre>
     *
     * @param  ch
     *         The channel from which bytes will be read
     *
     * @param  csName
     *         The name of the charset to be used
     *
     * @return  A new reader
     *
     * @throws  UnsupportedCharsetException
     *          If no support for the named charset is available
     *          in this instance of the Java virtual machine
     */
    public static Reader newReader(ReadableByteChannel ch,
                                   String csName)
    {
        Objects.requireNonNull(csName, "csName");
        return newReader(ch, Charset.forName(csName).newDecoder(), -1);
    }

    /**
     * Constructs a reader that decodes bytes from the given channel according
     * to the given charset.
     *
     * <p> An invocation of this method of the form
     *
     * <pre> {@code
     *     Channels.newReader(ch, charset)
     * } </pre>
     *
     * behaves in exactly the same way as the expression
     *
     * <pre> {@code
     *     Channels.newReader(ch, Charset.forName(csName).newDecoder(), -1)
     * } </pre>
     *
     * <p> The reader's default action for malformed-input and unmappable-character
     * errors is to {@linkplain java.nio.charset.CodingErrorAction#REPORT report}
     * them. When more control over the error handling is required, the constructor
     * that takes a {@linkplain java.nio.charset.CharsetDecoder} should be used.
     *
     * @param  ch The channel from which bytes will be read
     *
     * @param  charset The charset to be used
     *
     * @return  A new reader
     */
    public static Reader newReader(ReadableByteChannel ch, Charset charset) {
        Objects.requireNonNull(charset, "charset");
        return newReader(ch, charset.newDecoder(), -1);
    }

    /**
     * Constructs a writer that encodes characters using the given encoder and
     * writes the resulting bytes to the given channel.
     *
     * <p> The resulting stream will contain an internal output buffer of at
     * least {@code minBufferCap} bytes.  The stream's {@code write} methods
     * will, as needed, flush the buffer by writing bytes to the underlying
     * channel; if the channel is in non-blocking mode when bytes are to be
     * written then an {@link IllegalBlockingModeException} will be thrown.
     * The resulting stream will not otherwise be buffered.  Closing the stream
     * will in turn cause the channel to be closed.  </p>
     *
     * @param  ch
     *         The channel to which bytes will be written
     *
     * @param  enc
     *         The charset encoder to be used
     *
     * @param  minBufferCap
     *         The minimum capacity of the internal byte buffer,
     *         or {@code -1} if an implementation-dependent
     *         default capacity is to be used
     *
     * @return  A new writer
     */
    public static Writer newWriter(WritableByteChannel ch,
                                   CharsetEncoder enc,
                                   int minBufferCap)
    {
        Objects.requireNonNull(ch, "ch");
        return StreamEncoder.forEncoder(ch, enc.reset(), minBufferCap);
    }

    /**
     * Constructs a writer that encodes characters according to the named
     * charset and writes the resulting bytes to the given channel.
     *
     * <p> An invocation of this method of the form
     *
     * <pre> {@code
     *     Channels.newWriter(ch, csname)
     * } </pre>
     *
     * behaves in exactly the same way as the expression
     *
     * <pre> {@code
     *     Channels.newWriter(ch, Charset.forName(csName))
     * } </pre>
     *
     * @param  ch
     *         The channel to which bytes will be written
     *
     * @param  csName
     *         The name of the charset to be used
     *
     * @return  A new writer
     *
     * @throws  UnsupportedCharsetException
     *          If no support for the named charset is available
     *          in this instance of the Java virtual machine
     */
    public static Writer newWriter(WritableByteChannel ch,
                                   String csName)
    {
        Objects.requireNonNull(csName, "csName");
        return newWriter(ch, Charset.forName(csName).newEncoder(), -1);
    }

    /**
     * Constructs a writer that encodes characters according to the given
     * charset and writes the resulting bytes to the given channel.
     *
     * <p> An invocation of this method of the form
     *
     * <pre> {@code
     *     Channels.newWriter(ch, charset)
     * } </pre>
     *
     * behaves in exactly the same way as the expression
     *
     * <pre> {@code
     *     Channels.newWriter(ch, Charset.forName(csName).newEncoder(), -1)
     * } </pre>
     *
     * <p> The writer's default action for malformed-input and unmappable-character
     * errors is to {@linkplain java.nio.charset.CodingErrorAction#REPORT report}
     * them. When more control over the error handling is required, the constructor
     * that takes a {@linkplain java.nio.charset.CharsetEncoder} should be used.
     *
     * @param  ch
     *         The channel to which bytes will be written
     *
     * @param  charset
     *         The charset to be used
     *
     * @return  A new writer
     */
    public static Writer newWriter(WritableByteChannel ch, Charset charset) {
        Objects.requireNonNull(charset, "charset");
        return newWriter(ch, charset.newEncoder(), -1);
}
}
