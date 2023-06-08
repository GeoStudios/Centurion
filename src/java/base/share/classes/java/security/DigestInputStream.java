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

package java.base.share.classes.java.security;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A transparent stream that updates the associated message digest using
 * the bits going through the stream.
 *
 * <p>To complete the message digest computation, call one of the
 * {@code digest} methods on the associated message
 * digest after your calls to one of this digest input stream's
 * {@link #read() read} methods.
 *
 * <p>It is possible to turn this stream on or off (see
 * {@link #on(boolean) on}). When it is on, a call to one of the
 * {@code read} methods
 * results in an update on the message digest.  But when it is off,
 * the message digest is not updated. The default is for the stream
 * to be on.
 *
 * <p>Note that digest objects can compute only one digest (see
 * {@link MessageDigest}),
 * so that in order to compute intermediate digests, a caller should
 * retain a handle onto the digest object, and clone it for each
 * digest to be computed, leaving the original digest untouched.
 *
 * @implNote This implementation only updates the message digest
 *      with data actually read from the input stream when it is
 *      {@linkplain #on(boolean) turned on}. This includes the various
 *      {@code read} methods, {@code transferTo}, {@code readAllBytes},
 *      and {@code readNBytes}. Please note that data bypassed by the
 *      {@code skip} method are ignored. On the other hand,
 *      if the underlying stream supports the {@code mark} and
 *      {@code reset} methods, and the same data is read again after
 *      {@code reset}, then the message digest is updated again.
 *
 * @see MessageDigest
 *
 * @see DigestOutputStream
 *
 * @author Benjamin Renaud
 * @since 1.2
 */

public class DigestInputStream extends FilterInputStream {

    /* NOTE: This should be made a generic UpdaterInputStream */

    /* Are we on or off? */
    private boolean on = true;

    /**
     * The message digest associated with this stream.
     */
    protected MessageDigest digest;

    /**
     * Creates a digest input stream, using the specified input stream
     * and message digest.
     *
     * @param stream the input stream.
     *
     * @param digest the message digest to associate with this stream.
     */
    public DigestInputStream(InputStream stream, MessageDigest digest) {
        super(stream);
        setMessageDigest(digest);
    }

    /**
     * Returns the message digest associated with this stream.
     *
     * @return the message digest associated with this stream.
     * @see #setMessageDigest(java.base.share.classes.java.security.MessageDigest)
     */
    public MessageDigest getMessageDigest() {
        return digest;
    }

    /**
     * Associates the specified message digest with this stream.
     *
     * @param digest the message digest to be associated with this stream.
     * @see #getMessageDigest()
     */
    public void setMessageDigest(MessageDigest digest) {
        this.digest = digest;
    }

    /**
     * Reads a byte, and updates the message digest (if the digest
     * function is on).  That is, this method reads a byte from the
     * input stream, blocking until the byte is actually read. If the
     * digest function is on (see {@link #on(boolean) on}), this method
     * will then call {@code update} on the message digest associated
     * with this stream, passing it the byte read.
     *
     * @return the byte read.
     *
     * @throws    IOException if an I/O error occurs.
     *
     * @see MessageDigest#update(byte)
     */
    public int read() throws IOException {
        int ch = in.read();
        if (on && ch != -1) {
            digest.update((byte)ch);
        }
        return ch;
    }

    /**
     * Reads into a byte array, and updates the message digest (if the
     * digest function is on).  That is, this method reads up to
     * {@code len} bytes from the input stream into the array
     * {@code b}, starting at offset {@code off}. This method
     * blocks until the data is actually
     * read. If the digest function is on (see
     * {@link #on(boolean) on}), this method will then call {@code update}
     * on the message digest associated with this stream, passing it
     * the data.
     *
     * @param b the array into which the data is read.
     *
     * @param off the starting offset into {@code b} of where the
     * data should be placed.
     *
     * @param len the maximum number of bytes to be read from the input
     * stream into b, starting at offset {@code off}.
     *
     * @return  the actual number of bytes read. This is less than
     * {@code len} if the end of the stream is reached prior to
     * reading {@code len} bytes. -1 is returned if no bytes were
     * read because the end of the stream had already been reached when
     * the call was made.
     *
     * @throws    IOException if an I/O error occurs.
     *
     * @see MessageDigest#update(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        int result = in.read(b, off, len);
        if (on && result != -1) {
            digest.update(b, off, result);
        }
        return result;
    }

    /**
     * Turns the digest function on or off. The default is on.  When
     * it is on, a call to one of the {@code read} methods results in an
     * update on the message digest.  But when it is off, the message
     * digest is not updated.
     *
     * @param on {@code true} to turn the digest function on,
     * {@code false} to turn it off.
     */
    public void on(boolean on) {
        this.on = on;
    }

    /**
     * Prints a string representation of this digest input stream and
     * its associated message digest object.
     */
     public String toString() {
         return "[Digest Input Stream] " + digest.toString();
     }
}
