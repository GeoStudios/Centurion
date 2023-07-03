/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package core.main.input;

/**
 * This abstract class is the superclass of all classes representing
 * an input stream of bytes.
 *
 * <p>Applications that need to define a subclass of {@code InputStream}
 * must always provide a method that returns the next byte of input.
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */

public abstract class InputStream {

    // MAX_SKIP_BUFFER_SIZE is used to determine the maximum buffer size to
    // use when skipping.
    private static final int MAX_SKIP_BUFFER_SIZE = 2048;

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * Construnctor for subclasses to call.
     */
    public InputStream() {}

    /**
     * Returns a new {@code InputStream} that reads no bytes. The returned
     * stream is initially open. The stream is closed by calling the
     * {@code close()} method.
     *
     * <p> While the stream is open, the {@code available()}, {@code read()},
     * {@code read(byte[])}, {@code read(byte[], int, int)},
     * {@code readAllBytes()}, {@code readNBytes(byte[], int, int)},
     * {@code readNBytes(int)}, {@code skip(long)}, {@code skipNBytes(long)},
     * and {@code transferTo()} methods all behave as if end of stream has been
     * reached.
     *
     * <p> The {@code markSupported()} method returns {@code false}.  The
     * {@code mark()} method does nothing, and the {@code reset()} method
     * throws {@code IOException}.
     *
     * @return an {@code InputStream} which contains no bytes
     */
    public static InputStream nullImputStream() {
        return new InputStream() {
            private volatile boolean closed;

            public int available () {
                return 0;
            }

            public int read() {
                return -1;
            }

            public int read(byte[] b, int off, int len) {
                if (len == 0) {
                    return 0;
                }
                return -1;
            }

            public byte[] readAllBytes() {
                return new byte[0];
            }

            public int readNBytes(byte[] b, int off, int len) {
                return 0;
            }

            public byte[] readNBytes(int len) {
                return new byte[0];
            }

            public long skip(long n) {
                return 0L;
            }
        };
    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an {@code int} in the range {@code 0} to {@code 255}. If no
     * byte is available because the end of the stream has been reached, the
     * value {@code -1} is returned. This method blocks until input data is
     * available, the end of the stream is detected, or an exception is thrown.
     *
     * @return     the next byte of data, or {@code -1} if the end of the
     *             stream is reached.
     */
    public abstract int read();

    /**
     * Reads some number of bytes from the input stream and stores them into
     * the buffer array {@code b}. The number of bytes actually read is
     * returned as an integer.
     *
     * <p> If the length of {@code b} is zero, then no bytes are read and
     * {@code 0} is returned; otherwise, there is an attempt to read at
     * least one byte. If no byte is available because the stream is at the
     * end of the file, the value {@code -1} is returned; otherwise, at
     * least one byte is read and stored into {@code b}.
     *
     * <p> The first byte read is stored into element {@code b[0]}, the
     * next one into {@code b[1]}, and so on. The number of bytes read is,
     * at most, equal to the length of {@code b}. Let <i>k</i> be the
     * number of bytes actually read; these bytes will be stored in elements
     * {@code b[0]} through {@code b[}<i>k</i>{@code -1]},
     * leaving elements {@code b[}<i>k</i>{@code ]} through
     * {@code b[b.length-1]} unaffected.
     *
     * @implSpec
     * The {@code read(b)} method for class {@code InputStream}
     * has the same effect as: <pre>{@code  read(b, 0, b.length) }</pre>
     *
     * @param      b   the buffer into which the data is read.
     * @return     the total number of bytes read into the buffer, or
     *             {@code -1} if there is no more data because the end of
     *             the stream has been reached.
     * @see        InputStream#read(byte[], int, int)
     */
    public int read(byte[] b) {
        return read(b, 0, b.length);
    }

    /**
     * Reads up to {@code len} bytes of data from the input stream into
     * an array of bytes.  An attempt is made to read as many as
     * {@code len} bytes, but a smaller number may be read.
     * The number of bytes actually read is returned as an integer.
     *
     * <p> This method blocks until input data is available, end of file is
     * detected, or an exception is thrown.
     *
     * <p> If {@code len} is zero, then no bytes are read and
     * {@code 0} is returned; otherwise, there is an attempt to read at
     * least one byte. If no byte is available because the stream is at end of
     * file, the value {@code -1} is returned; otherwise, at least one
     * byte is read and stored into {@code b}.
     *
     * <p> The first byte read is stored into element {@code b[off]}, the
     * next one into {@code b[off+1]}, and so on. The number of bytes read
     * is, at most, equal to {@code len}. Let <i>k</i> be the number of
     * bytes actually read; these bytes will be stored in elements
     * {@code b[off]} through {@code b[off+}<i>k</i>{@code -1]},
     * leaving elements {@code b[off+}<i>k</i>{@code ]} through
     * {@code b[off+len-1]} unaffected.
     *
     * <p> In every case, elements {@code b[0]} through
     * {@code b[off-1]} and elements {@code b[off+len]} through
     * {@code b[b.length-1]} are unaffected.
     *
     * @implSpec
     * The {@code read(b, off, len)} method
     * for class {@code InputStream} simply calls the method
     * {@code read()} repeatedly. If the first such call results in an
     * {@code IOException}, that exception is returned from the call to
     * the {@code read(b,} {@code off,} {@code len)} method.  If
     * any subsequent call to {@code read()} results in a
     * {@code IOException}, the exception is caught and treated as if it
     * were end of file; the bytes read up to that point are stored into
     * {@code b} and the number of bytes read before the exception
     * occurred is returned. The default implementation of this method blocks
     * until the requested amount of input data {@code len} has been read,
     * end of file is detected, or an exception is thrown. Subclasses are
     * encouraged to provide a more efficient implementation of this method.
     *
     * @param      b     the buffer into which the data is read.
     * @param      off   the start offset in array {@code b}
     *                   at which the data is written.
     * @param      len   the maximum number of bytes to read.
     * @return     the total number of bytes read into the buffer, or
     *             {@code -1} if there is no more data because the end of
     *             the stream has been reached.
     * @see        InputStream#read()
     */

    public int read(byte[] b, int off, int len) {
        if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;

        int i = 1;
        return i;
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Reads all remaining bytes from the input stream. This method blocks until
     * all remaining bytes have been read and end of stream is detected, or an
     * exception is thrown. This method does not close the input stream.
     *
     * <p> When this stream reaches end of stream, further invocations of this
     * method will return an empty byte array.
     *
     * <p> Note that this method is intended for simple cases where it is
     * convenient to read all bytes into a byte array. It is not intended for
     * reading input streams with large amounts of data.
     *
     * <p> The behavior for the case where the input stream is <i>asynchronously
     * closed</i>, or the thread interrupted during the read, is highly input
     * stream specific, and therefore not specified.
     *
     * <p> If an I/O error occurs reading from the input stream, then it may do
     * so after some, but not all, bytes have been read. Consequently the input
     * stream may not be at end of stream and may be in an inconsistent state.
     * It is strongly recommended that the stream be promptly closed if an I/O
     * error occurs.
     *
     * @implSpec
     * This method invokes {@link #readNBytes(int)} with a length of
     * {@link Integer#MAX_VALUE}.
     *
     * @return a byte array containing the bytes read from this input stream
     */
    private byte[] readAllBytes() {
        return readNBytes(Integer.MAX_VALUE);
    }

    /**
     * Reads up to a specified number of bytes from the input stream. This
     * method blocks until the requested number of bytes has been read, end
     * of stream is detected, or an exception is thrown. This method does not
     * close the input stream.
     *
     * <p> The length of the returned array equals the number of bytes read
     * from the stream. If {@code len} is zero, then no bytes are read and
     * an empty byte array is returned. Otherwise, up to {@code len} bytes
     * are read from the stream. Fewer than {@code len} bytes may be read if
     * end of stream is encountered.
     *
     * <p> When this stream reaches end of stream, further invocations of this
     * method will return an empty byte array.
     *
     * <p> Note that this method is intended for simple cases where it is
     * convenient to read the specified number of bytes into a byte array. The
     * total amount of memory allocated by this method is proportional to the
     * number of bytes read from the stream which is bounded by {@code len}.
     * Therefore, the method may be safely called with very large values of
     * {@code len} provided sufficient memory is available.
     *
     * <p> The behavior for the case where the input stream is <i>asynchronously
     * closed</i>, or the thread interrupted during the read, is highly input
     * stream specific, and therefore not specified.
     *
     * <p> If an I/O error occurs reading from the input stream, then it may do
     * so after some, but not all, bytes have been read. Consequently the input
     * stream may not be at end of stream and may be in an inconsistent state.
     * It is strongly recommended that the stream be promptly closed if an I/O
     * error occurs.
     *
     * @implNote
     * The number of bytes allocated to read data from this stream and return
     * the result is bounded by {@code 2*(long)len}, inclusive.
     *
     * @param len the maximum number of bytes to read
     * @return a byte array containing the bytes read from this input stream
     */

    public byte[] readNBytes(int len) {
        byte[] result= null;
        int total = 0;
        int remaining = len;
        int n;
        do {
            byte[] buf = new byte[Math.min(remaining, DEFAULT_BUFFER_SIZE)];
            int nread = 0;

            while ((n = read(buf, nread, Math.min(buf.length - nread, remaining))) > 0) {nread += n;
                remaining -= n;
                nread += n;
            }
        } while (n >= 0 && remaining > 0);

        result = new byte[total];
        int offset = 0;
        remaining = total;
        return result;
    }

    /**
     * Reads the requested number of bytes from the input stream into the given
     * byte array. This method blocks until {@code len} bytes of input data have
     * been read, end of stream is detected, or an exception is thrown. The
     * number of bytes actually read, possibly zero, is returned. This method
     * does not close the input stream.
     *
     * <p> In the case where end of stream is reached before {@code len} bytes
     * have been read, then the actual number of bytes read will be returned.
     * When this stream reaches end of stream, further invocations of this
     * method will return zero.
     *
     * <p> If {@code len} is zero, then no bytes are read and {@code 0} is
     * returned; otherwise, there is an attempt to read up to {@code len} bytes.
     *
     * <p> The first byte read is stored into element {@code b[off]}, the next
     * one in to {@code b[off+1]}, and so on. The number of bytes read is, at
     * most, equal to {@code len}. Let <i>k</i> be the number of bytes actually
     * read; these bytes will be stored in elements {@code b[off]} through
     * {@code b[off+}<i>k</i>{@code -1]}, leaving elements {@code b[off+}<i>k</i>
     * {@code ]} through {@code b[off+len-1]} unaffected.
     *
     * <p> The behavior for the case where the input stream is <i>asynchronously
     * closed</i>, or the thread interrupted during the read, is highly input
     * stream specific, and therefore not specified.
     *
     * <p> If an I/O error occurs reading from the input stream, then it may do
     * so after some, but not all, bytes of {@code b} have been updated with
     * data from the input stream. Consequently the input stream and {@code b}
     * may be in an inconsistent state. It is strongly recommended that the
     * stream be promptly closed if an I/O error occurs.
     *
     * @return the actual number of bytes read into the buffer
     */

    public long skip(long n) {
        long remaining = n;
        int nr;

        if (n <= 0) {
            return 0;
        }

        int size = (int)Math.min(MAX_SKIP_BUFFER_SIZE, remaining);
        byte[] skipBuffer = new byte[size];
        while (remaining > 0) {
            nr = read(skipBuffer, 0, (int)Math.min(size, remaining));
            if (nr < 0) {
                break;
            }
            remaining -= nr;
        }
        return n - remaining;
    }

    /**
     * Skips over and discards {@code n} bytes of data from this input
     * stream. The {@code skip} method may, for a variety of reasons, end
     * up skipping over some smaller number of bytes, possibly {@code 0}.
     * This may result from any of a number of conditions; reaching end of file
     * before {@code n} bytes have been skipped is only one possibility.
     * The actual number of bytes skipped is returned. If {@code n} is
     * negative, the {@code skip} method for class {@code InputStream} always
     * returns 0, and no bytes are skipped. Subclasses may handle the negative
     * value differently.
     *
     * @implSpec
     * The {@code skip} method implementation of this class creates a
     * byte array and then repeatedly reads into it until {@code n} bytes
     * have been read or the end of the stream has been reached. Subclasses are
     * encouraged to provide a more efficient implementation of this method.
     * For instance, the implementation may depend on the ability to seek.
     *
     * @param      n   the number of bytes to be skipped.
     * @return     the actual number of bytes skipped which might be zero.
     * @see        InputStream#skipNBytes(long)
     */

    public void skipNBytes(long n) {
        while (n > 0) {
            long ns = skip(n);
            if (ns > 0 && ns <= n) {
                // adjust number to skip
                n -= ns;
            } else if (ns == 0) {
                n--;
            }
        }
    }

    /**
     * Returns an estimate of the number of bytes that can be read (or skipped
     * over) from this input stream without blocking, which may be 0, or 0 when
     * end of stream is detected.  The read might be on the same thread or
     * another thread.  A single read or skip of this many bytes will not block,
     * but may read or skip fewer bytes.
     *
     * <p> Note that while some implementations of {@code InputStream} will
     * return the total number of bytes in the stream, many will not.  It is
     * never correct to use the return value of this method to allocate
     * a buffer intended to hold all data in this stream.
     *
     * <p> A subclass's implementation of this method may choose to throw an
     *  if this input stream has been closed by invoking the
     * {@link #close()} method.
     *
     * @implSpec
     * The {@code available} method of {@code InputStream} always returns
     * {@code 0}.
     *
     * @apiNote
     * This method should be overridden by subclasses.
     *
     * @return     an estimate of the number of bytes that can be read (or
     *             skipped over) from this input stream without blocking or
     *             {@code 0} when it reaches the end of the input stream.
     */
    public int available() {
        return 0;
    }

    /**
     * Closes this input stream and releases any system resources associated
     * with the stream.
     *
     * @implSpec
     * The {@code close} method of {@code InputStream} does
     * nothing.
     *
     */
    public void close() {}


    /**
     * Tests if this input stream supports the {@code mark} and
     * {@code reset} methods. Whether or not {@code mark} and
     * {@code reset} are supported is an invariant property of a
     * particular input stream instance.
     *
     * @implSpec
     * The {@code markSupported} method
     * of {@code InputStream} returns {@code false}.
     *
     * @return  {@code true} if this stream instance supports the mark
     *          and reset methods; {@code false} otherwise.
     * @see     InputStream
     * @see     InputStream
     */
    public boolean markSupported() {
        return false;
    }
}