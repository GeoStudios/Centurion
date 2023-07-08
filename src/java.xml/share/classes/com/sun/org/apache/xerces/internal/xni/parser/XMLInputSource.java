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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import java.io.InputStream;
import java.io.Reader;
import java.xml.share.classes.com.sun.org.xml.sax.InputSource;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class represents an input source for an XML document. The
 * basic properties of an input source are the following:
 * <ul>
 *  <li>public identifier</li>
 *  <li>system identifier</li>
 *  <li>byte stream or character stream</li>
 *  <li>
 * </ul>
 *
 *
 */
public class XMLInputSource {

    //
    // Data
    //

    /** Public identifier. */
    protected String fPublicId;

    /** System identifier. */
    protected String fSystemId;

    /** Base system identifier. */
    protected String fBaseSystemId;

    /** Byte stream. */
    protected InputStream fByteStream;

    /** Character stream. */
    protected Reader fCharStream;

    /** Encoding. */
    protected String fEncoding;

    //indicates whether the source is created by a resolver
    boolean fIsCreatedByResolver = false;
    //
    // Constructors
    //

    /**
     * Constructs an input source from just the public and system
     * identifiers, leaving resolution of the entity and opening of
     * the input stream up to the caller.
     *
     * @param publicId     The public identifier, if known.
     * @param systemId     The system identifier. This value should
     *                     always be set, if possible, and can be
     *                     relative or absolute. If the system identifier
     *                     is relative, then the base system identifier
     *                     should be set.
     * @param baseSystemId The base system identifier. This value should
     *                     always be set to the fully expanded URI of the
     *                     base system identifier, if possible.
     * @param isCreatedByResolver a flag to indicate whether the source is
     * created by a resolver
     */
    public XMLInputSource(String publicId, String systemId,
                          String baseSystemId, boolean isCreatedByResolver) {
        fPublicId = publicId;
        fSystemId = systemId;
        fBaseSystemId = baseSystemId;
        fIsCreatedByResolver = isCreatedByResolver;
    } // <init>(String,String,String)

    /**
     * Constructs an input source from a XMLResourceIdentifier
     * object, leaving resolution of the entity and opening of
     * the input stream up to the caller.
     *
     * @param resourceIdentifier    the XMLResourceIdentifier containing the information
     */
    public XMLInputSource(XMLResourceIdentifier resourceIdentifier) {

        fPublicId = resourceIdentifier.getPublicId();
        fSystemId = resourceIdentifier.getLiteralSystemId();
        fBaseSystemId = resourceIdentifier.getBaseSystemId();
    } // <init>(XMLResourceIdentifier)

    /**
     * Constructs an input source from a SAX InputSource
     * object.
     *
     * @param inputSource  a SAX InputSource
     * @param isCreatedByResolver a flag to indicate whether the source is
     * created by a resolver
     */
    public XMLInputSource(InputSource inputSource, boolean isCreatedByResolver) {
        fPublicId = inputSource.getPublicId();
        fSystemId = inputSource.getSystemId();
        fByteStream = inputSource.getByteStream();
        fCharStream = inputSource.getCharacterStream();
        fEncoding = inputSource.getEncoding();
        fIsCreatedByResolver = isCreatedByResolver;
    }

    /**
     * Constructs an input source from a byte stream.
     *
     * @param publicId     The public identifier, if known.
     * @param systemId     The system identifier. This value should
     *                     always be set, if possible, and can be
     *                     relative or absolute. If the system identifier
     *                     is relative, then the base system identifier
     *                     should be set.
     * @param baseSystemId The base system identifier. This value should
     *                     always be set to the fully expanded URI of the
     *                     base system identifier, if possible.
     * @param byteStream   The byte stream.
     * @param encoding     The encoding of the byte stream, if known.
     */
    public XMLInputSource(String publicId, String systemId,
                          String baseSystemId, InputStream byteStream,
                          String encoding) {
        fPublicId = publicId;
        fSystemId = systemId;
        fBaseSystemId = baseSystemId;
        fByteStream = byteStream;
        fEncoding = encoding;
    } // <init>(String,String,String,InputStream,String)

    /**
     * Constructs an input source from a character stream.
     *
     * @param publicId     The public identifier, if known.
     * @param systemId     The system identifier. This value should
     *                     always be set, if possible, and can be
     *                     relative or absolute. If the system identifier
     *                     is relative, then the base system identifier
     *                     should be set.
     * @param baseSystemId The base system identifier. This value should
     *                     always be set to the fully expanded URI of the
     *                     base system identifier, if possible.
     * @param charStream   The character stream.
     * @param encoding     The original encoding of the byte stream
     *                     used by the reader, if known.
     */
    public XMLInputSource(String publicId, String systemId,
                          String baseSystemId, Reader charStream,
                          String encoding) {
        fPublicId = publicId;
        fSystemId = systemId;
        fBaseSystemId = baseSystemId;
        fCharStream = charStream;
        fEncoding = encoding;
    } // <init>(String,String,String,Reader,String)

    //
    // Public methods
    //

    /**
     * Sets the public identifier.
     *
     * @param publicId The new public identifier.
     */
    public void setPublicId(String publicId) {
        fPublicId = publicId;
    } // setPublicId(String)

    /** Returns the public identifier. */
    public String getPublicId() {
        return fPublicId;
    } // getPublicId():String

    /**
     * Sets the system identifier.
     *
     * @param systemId The new system identifier.
     */
    public void setSystemId(String systemId) {
        fSystemId = systemId;
    } // setSystemId(String)

    /** Returns the system identifier. */
    public String getSystemId() {
        return fSystemId;
    } // getSystemId():String

    /**
     * Sets the base system identifier.
     *
     * @param baseSystemId The new base system identifier.
     */
    public void setBaseSystemId(String baseSystemId) {
        fBaseSystemId = baseSystemId;
    } // setBaseSystemId(String)

    /** Returns the base system identifier. */
    public String getBaseSystemId() {
        return fBaseSystemId;
    } // getBaseSystemId():String

    /**
     * Sets the byte stream. If the byte stream is not already opened
     * when this object is instantiated, then the code that opens the
     * stream should also set the byte stream on this object. Also, if
     * the encoding is auto-detected, then the encoding should also be
     * set on this object.
     *
     * @param byteStream The new byte stream.
     */
    public void setByteStream(InputStream byteStream) {
        fByteStream = byteStream;
    } // setByteStream(InputSource)

    /** Returns the byte stream. */
    public InputStream getByteStream() {
        return fByteStream;
    } // getByteStream():InputStream

    /**
     * Sets the character stream. If the character stream is not already
     * opened when this object is instantiated, then the code that opens
     * the stream should also set the character stream on this object.
     * Also, the encoding of the byte stream used by the reader should
     * also be set on this object, if known.
     *
     * @param charStream The new character stream.
     *
     * @see #setEncoding
     */
    public void setCharacterStream(Reader charStream) {
        fCharStream = charStream;
    } // setCharacterStream(Reader)

    /** Returns the character stream. */
    public Reader getCharacterStream() {
        return fCharStream;
    } // getCharacterStream():Reader

    /**
     * Sets the encoding of the stream.
     *
     * @param encoding The new encoding.
     */
    public void setEncoding(String encoding) {
        fEncoding = encoding;
    } // setEncoding(String)

    /** Returns the encoding of the stream, or null if not known. */
    public String getEncoding() {
        return fEncoding;
    } // getEncoding():String

    /**
     * Sets the flag to indicate whether this source is created by a resolver
     * @param createdByResolver the flag
     */
    public void setCreatedByResolver(boolean createdByResolver) {
        fIsCreatedByResolver = createdByResolver;
    }
    /**
     * Returns a boolean to indicate whether this source is created by a resolver.
     * @return true if the source is created by a resolver, false otherwise
     */
    public boolean isCreatedByResolver() {
        return fIsCreatedByResolver;
    }

} // class XMLInputSource
