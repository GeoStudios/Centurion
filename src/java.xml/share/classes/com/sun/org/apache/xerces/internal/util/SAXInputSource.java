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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;

import java.io.InputStream;
import java.io.Reader;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.xml.share.classes.com.sun.org.xml.sax.InputSource;
import java.xml.share.classes.com.sun.org.xml.sax.XMLReader;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>An <code>XMLInputSource</code> analogue to <code>javax.xml.transform.sax.SAXSource</code>.</p>
 *
 */
public final class SAXInputSource extends XMLInputSource {

    private XMLReader fXMLReader;
    private InputSource fInputSource;

    public SAXInputSource() {
        this(null);
    }

    public SAXInputSource(InputSource inputSource) {
        this(null, inputSource);
    }

    public SAXInputSource(XMLReader reader, InputSource inputSource) {
        super(inputSource != null ? inputSource.getPublicId() : null,
                inputSource != null ? inputSource.getSystemId() : null, null,
                false);
        if (inputSource != null) {
            setByteStream(inputSource.getByteStream());
            setCharacterStream(inputSource.getCharacterStream());
            setEncoding(inputSource.getEncoding());
        }
        fInputSource = inputSource;
        fXMLReader = reader;
    }

    public void setXMLReader(XMLReader reader) {
        fXMLReader = reader;
    }

    public XMLReader getXMLReader() {
        return fXMLReader;
    }

    public void setInputSource(InputSource inputSource) {
        if (inputSource != null) {
            setPublicId(inputSource.getPublicId());
            setSystemId(inputSource.getSystemId());
            setByteStream(inputSource.getByteStream());
            setCharacterStream(inputSource.getCharacterStream());
            setEncoding(inputSource.getEncoding());
        }
        else {
            setPublicId(null);
            setSystemId(null);
            setByteStream(null);
            setCharacterStream(null);
            setEncoding(null);
        }
        fInputSource = inputSource;
    }

    public InputSource getInputSource() {
        return fInputSource;
    }

    /**
     * Sets the public identifier.
     *
     * @param publicId The new public identifier.
     */
    public void setPublicId(String publicId) {
        super.setPublicId(publicId);
        if (fInputSource == null) {
            fInputSource = new InputSource();
        }
        fInputSource.setPublicId(publicId);
    } // setPublicId(String)

    /**
     * Sets the system identifier.
     *
     * @param systemId The new system identifier.
     */
    public void setSystemId(String systemId) {
        super.setSystemId(systemId);
        if (fInputSource == null) {
            fInputSource = new InputSource();
        }
        fInputSource.setSystemId(systemId);
    } // setSystemId(String)

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
        super.setByteStream(byteStream);
        if (fInputSource == null) {
            fInputSource = new InputSource();
        }
        fInputSource.setByteStream(byteStream);
    } // setByteStream(InputStream)

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
        super.setCharacterStream(charStream);
        if (fInputSource == null) {
            fInputSource = new InputSource();
        }
        fInputSource.setCharacterStream(charStream);
    } // setCharacterStream(Reader)

    /**
     * Sets the encoding of the stream.
     *
     * @param encoding The new encoding.
     */
    public void setEncoding(String encoding) {
        super.setEncoding(encoding);
        if (fInputSource == null) {
            fInputSource = new InputSource();
        }
        fInputSource.setEncoding(encoding);
    } // setEncoding(String)

} // SAXInputSource
