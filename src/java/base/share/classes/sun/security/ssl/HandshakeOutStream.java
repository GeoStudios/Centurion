/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Output stream for handshake data.  This is used only internally
 * to the SSL classes.
 *
 * MT note:  one thread at a time is presumed be writing handshake
 * messages, but (after initial connection setup) it's possible to
 * have other threads reading/writing application data.  It's the
 * SSLSocketImpl class that synchronizes record writes.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

public class HandshakeOutStream extends ByteArrayOutputStream {

    OutputRecord outputRecord;      // May be null if not actually used to
                                    // output handshake message records.

    HandshakeOutStream(OutputRecord outputRecord) {
        super();
        this.outputRecord = outputRecord;
    }

    // Complete a handshaking message write. Called by HandshakeMessage.
    void complete() throws IOException {
        if (size() < 4) {       // 4: handshake message header size
            // internal_error alert will be triggered
            throw new RuntimeException("handshake message is not available");
        }

        if (outputRecord != null) {
            if (!outputRecord.isClosed()) {
                outputRecord.encodeHandshake(buf, 0, count);
            } else {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                    SSLLogger.warning("outbound has closed, ignore outbound " +
                        "handshake messages", ByteBuffer.wrap(buf, 0, count));
                }
            }

            // reset the byte array output stream
            reset();
        }   // otherwise, the handshake output stream is temporarily used only.
    }

    //
    // overridden ByteArrayOutputStream methods
    //

    @Override
    public void write(byte[] b, int off, int len) {
        // The maximum fragment size is 24 bytes.
        checkOverflow(len, Record.OVERFLOW_OF_INT24);
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        if (outputRecord != null) {
            outputRecord.flush();
        }
    }

    //
    // handshake output stream management functions
    //

    /*
     * Put integers encoded in standard 8, 16, 24, and 32 bit
     * big endian formats. Note that OutputStream.write(int) only
     * writes the least significant 8 bits and ignores the rest.
     */
    void putInt8(int i) throws IOException {
        checkOverflow(i, Record.OVERFLOW_OF_INT08);
        super.write(i);
    }

    void putInt16(int i) throws IOException {
        checkOverflow(i, Record.OVERFLOW_OF_INT16);
        super.write(i >> 8);
        super.write(i);
    }

    void putInt24(int i) {
        checkOverflow(i, Record.OVERFLOW_OF_INT24);
        super.write(i >> 16);
        super.write(i >> 8);
        super.write(i);
    }

    void putInt32(int i) {
        super.write(i >> 24);
        super.write(i >> 16);
        super.write(i >> 8);
        super.write(i);
    }

    /*
     * Put byte arrays with length encoded as 8, 16, 24 bit
     * integers in big-endian format.
     */
    void putBytes8(byte[] b) throws IOException {
        if (b == null) {
            putInt8(0);
        } else {
            putInt8(b.length);
            super.write(b, 0, b.length);
        }
    }

    public void putBytes16(byte[] b) throws IOException {
        if (b == null) {
            putInt16(0);
        } else {
            putInt16(b.length);
            super.write(b, 0, b.length);
        }
    }

    void putBytes24(byte[] b) {
        if (b == null) {
            putInt24(0);
        } else {
            putInt24(b.length);
            super.write(b, 0, b.length);
        }
    }

    /*
     * Does the specified length overflow the limitation?
     */
    private static void checkOverflow(int length, int limit) {
        if (length >= limit) {
            // internal_error alert will be triggered
            throw new RuntimeException(
                    "Field length overflow, the field length (" +
                    length + ") should be less than " + limit);
        }
    }
}
