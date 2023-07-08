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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.io;


import java.io.InputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.Reader;
import java.base.share.classes.java.util.Locale;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.MessageFormatter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import java.xml.share.classes.com.sun.xml.internal.stream.util.BufferAllocator;
import java.xml.share.classes.com.sun.xml.internal.stream.util.ThreadLocalBufferAllocator;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * A simple ASCII byte reader. This is an optimized reader for reading
 * byte streams that only contain 7-bit ASCII characters.
 *
 * @xerces.internal
 *
 *
 */
public class ASCIIReader
    extends Reader {

    //
    // Constants
    //

    /** Default byte buffer size (2048). */
    public static final int DEFAULT_BUFFER_SIZE = 2048;

    //
    // Data
    //

    /** Input stream. */
    protected InputStream fInputStream;

    /** Byte buffer. */
    protected byte[] fBuffer;

    // message formatter; used to produce localized
    // exception messages
    private MessageFormatter fFormatter = null;

    //Locale to use for messages
    private Locale fLocale = null;

    //
    // Constructors
    //

    /**
     * Constructs an ASCII reader from the specified input stream
     * using the default buffer size.
     *
     * @param inputStream The input stream.
     * @param messageFormatter  the MessageFormatter to use to message reporting.
     * @param locale    the Locale for which messages are to be reported
     */
    public ASCIIReader(InputStream inputStream, MessageFormatter messageFormatter,
            Locale locale) {
        this(inputStream, DEFAULT_BUFFER_SIZE, messageFormatter, locale);
    } // <init>(InputStream, MessageFormatter, Locale)

    /**
     * Constructs an ASCII reader from the specified input stream
     * and buffer size.
     *
     * @param inputStream The input stream.
     * @param size        The initial buffer size.
     * @param messageFormatter  the MessageFormatter to use to message reporting.
     * @param locale    the Locale for which messages are to be reported
     */
    public ASCIIReader(InputStream inputStream, int size,
            MessageFormatter messageFormatter, Locale locale) {
        fInputStream = inputStream;
        BufferAllocator ba = ThreadLocalBufferAllocator.getBufferAllocator();
        fBuffer = ba.getByteBuffer(size);
        if (fBuffer == null) {
            fBuffer = new byte[size];
        }
        fFormatter = messageFormatter;
        fLocale = locale;
    } // <init>(InputStream,int, MessageFormatter, Locale)

    //
    // Reader methods
    //

    /**
     * Read a single character.  This method will block until a character is
     * available, an I/O error occurs, or the end of the stream is reached.
     *
     * <p> Subclasses that intend to support efficient single-character input
     * should override this method.
     *
     * @return     The character read, as an integer in the range 0 to 127
     *             (<tt>0x00-0x7f</tt>), or -1 if the end of the stream has
     *             been reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    public int read() throws IOException {
        int b0 = fInputStream.read();
        if (b0 >= 0x80) {
            throw new MalformedByteSequenceException(fFormatter,
                fLocale, XMLMessageFormatter.XML_DOMAIN,
                "InvalidASCII", new Object [] {Integer.toString(b0)});
        }
        return b0;
    } // read():int

    /**
     * Read characters into a portion of an array.  This method will block
     * until some input is available, an I/O error occurs, or the end of the
     * stream is reached.
     *
     * @param      ch     Destination buffer
     * @param      offset Offset at which to start storing characters
     * @param      length Maximum number of characters to read
     *
     * @return     The number of characters read, or -1 if the end of the
     *             stream has been reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    public int read(char[] ch, int offset, int length) throws IOException {
        if (length > fBuffer.length) {
            length = fBuffer.length;
        }
        int count = fInputStream.read(fBuffer, 0, length);
        for (int i = 0; i < count; i++) {
            int b0 = fBuffer[i];
            if (b0 < 0) {
                throw new MalformedByteSequenceException(fFormatter,
                    fLocale, XMLMessageFormatter.XML_DOMAIN,
                    "InvalidASCII", new Object [] {Integer.toString(b0 & 0x0FF)});
            }
            ch[offset + i] = (char)b0;
        }
        return count;
    } // read(char[],int,int)

    /**
     * Skip characters.  This method will block until some characters are
     * available, an I/O error occurs, or the end of the stream is reached.
     *
     * @param  n  The number of characters to skip
     *
     * @return    The number of characters actually skipped
     *
     * @exception  IOException  If an I/O error occurs
     */
    public long skip(long n) throws IOException {
        return fInputStream.skip(n);
    } // skip(long):long

    /**
     * Tell whether this stream is ready to be read.
     *
     * @return True if the next read() is guaranteed not to block for input,
     * false otherwise.  Note that returning false does not guarantee that the
     * next read will block.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public boolean ready() throws IOException {
            return false;
    } // ready()

    /**
     * Tell whether this stream supports the mark() operation.
     */
    public boolean markSupported() {
            return fInputStream.markSupported();
    } // markSupported()

    /**
     * Mark the present position in the stream.  Subsequent calls to reset()
     * will attempt to reposition the stream to this point.  Not all
     * character-input streams support the mark() operation.
     *
     * @param  readAheadLimit  Limit on the number of characters that may be
     *                         read while still preserving the mark.  After
     *                         reading this many characters, attempting to
     *                         reset the stream may fail.
     *
     * @exception  IOException  If the stream does not support mark(),
     *                          or if some other I/O error occurs
     */
    public void mark(int readAheadLimit) throws IOException {
            fInputStream.mark(readAheadLimit);
    } // mark(int)

    /**
     * Reset the stream.  If the stream has been marked, then attempt to
     * reposition it at the mark.  If the stream has not been marked, then
     * attempt to reset it in some way appropriate to the particular stream,
     * for example by repositioning it to its starting point.  Not all
     * character-input streams support the reset() operation, and some support
     * reset() without supporting mark().
     *
     * @exception  IOException  If the stream has not been marked,
     *                          or if the mark has been invalidated,
     *                          or if the stream does not support reset(),
     *                          or if some other I/O error occurs
     */
    public void reset() throws IOException {
        fInputStream.reset();
    } // reset()

    /**
     * Close the stream.  Once a stream has been closed, further read(),
     * ready(), mark(), or reset() invocations will throw an IOException.
     * Closing a previously-closed stream, however, has no effect.
     *
     * @exception  IOException  If an I/O error occurs
     */
     public void close() throws IOException {
         BufferAllocator ba = ThreadLocalBufferAllocator.getBufferAllocator();
         ba.returnByteBuffer(fBuffer);
         fBuffer = null;
         fInputStream.close();
     } // close()

} // class ASCIIReader
