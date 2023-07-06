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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.dom3;

import java.xml.share.classes.com.sun.org.w3c.dom.ls.LSOutput;
import java.io.Writer;
import java.io.OutputStream;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This is a copy of the Xerces-2J class org.apache.xerces.dom.DOMOutputImpl.java
 *
 * This class represents an output destination for data.
 * This interface allows an application to encapsulate information about an
 * output destination in a single object, which may include a URI, a byte stream
 * (possibly with a specifiedencoding), a base URI, and/or a character stream.
 * The exact definitions of a byte stream and a character stream are binding
 * dependent.
 * The application is expected to provide objects that implement this interface
 * whenever such objects are needed. The application can either provide its
 * own objects that implement this interface, or it can use the generic factory
 * method DOMImplementationLS.createLSOutput() to create objects that
 * implement this interface.
 * The DOMSerializer will use the LSOutput object to determine where to
 * serialize the output to. The DOMSerializer will look at the different
 * outputs specified in the LSOutput in the following order to know which one
 * to output to, the first one that data can be output to will be used:
 * 1.LSOutput.characterStream
 * 2.LSOutput.byteStream
 * 3.LSOutput.systemId
 * LSOutput objects belong to the application. The DOM implementation will
 * never modify them (though it may make copies and modify the copies,
 * if necessary).
 *
 *
 * @version $Id :
 * @xsl.usage internal
 */

final class DOMOutputImpl implements LSOutput {

    private Writer fCharStream = null;
    private OutputStream fByteStream = null;
    private String fSystemId = null;
    private String fEncoding = null;

    /**
     * Default Constructor
     */
    DOMOutputImpl() {}

    /**
     * An attribute of a language and binding dependent type that represents a
     * writable stream of bytes. If the application knows the character encoding
     * of the byte stream, it should set the encoding attribute. Setting the
     * encoding in this way will override any encoding specified in an XML
     * declaration in the data.
     */

    public Writer getCharacterStream(){
        return fCharStream;
    }

    /**
     * An attribute of a language and binding dependent type that represents a
     * writable stream of bytes. If the application knows the character encoding
     * of the byte stream, it should set the encoding attribute. Setting the
     * encoding in this way will override any encoding specified in an XML
     * declaration in the data.
     */

    public void setCharacterStream(Writer characterStream){
        fCharStream = characterStream;
    }

    /**
     * Depending on the language binding in use, this attribute may not be
     * available. An attribute of a language and binding dependent type that
     * represents a writable stream to which 16-bit units can be output. The
     * application must encode the stream using UTF-16 (defined in [Unicode] and
     *  Amendment 1 of [ISO/IEC 10646]).
     */

    public OutputStream getByteStream(){
        return fByteStream;
    }

    /**
     * Depending on the language binding in use, this attribute may not be
     * available. An attribute of a language and binding dependent type that
     * represents a writable stream to which 16-bit units can be output. The
     * application must encode the stream using UTF-16 (defined in [Unicode] and
     *  Amendment 1 of [ISO/IEC 10646]).
     */

    public void setByteStream(OutputStream byteStream){
        fByteStream = byteStream;
    }

    /**
     * The system identifier, a URI reference [IETF RFC 2396], for this output
     *  destination. If the application knows the character encoding of the
     *  object pointed to by the system identifier, it can set the encoding
     *  using the encoding attribute. If the system ID is a relative URI
     *  reference (see section 5 in [IETF RFC 2396]), the behavior is
     *  implementation dependent.
     */

    public String getSystemId(){
        return fSystemId;
    }

    /**
     * The system identifier, a URI reference [IETF RFC 2396], for this output
     *  destination. If the application knows the character encoding of the
     *  object pointed to by the system identifier, it can set the encoding
     *  using the encoding attribute. If the system ID is a relative URI
     *  reference (see section 5 in [IETF RFC 2396]), the behavior is
     *  implementation dependent.
     */

    public void setSystemId(String systemId){
        fSystemId = systemId;
    }

    /**
     * The character encoding, if known. The encoding must be a string
     * acceptable for an XML encoding declaration ([XML 1.0] section 4.3.3
     * "Character Encoding in Entities"). This attribute has no effect when the
     * application provides a character stream or string data. For other sources
     * of input, an encoding specified by means of this attribute will override
     * any encoding specified in the XML declaration or the Text declaration, or
     * an encoding obtained from a higher level protocol, such as HTTP
     * [IETF RFC 2616].
     */

    public String getEncoding(){
        return fEncoding;
    }

    /**
     * The character encoding, if known. The encoding must be a string
     * acceptable for an XML encoding declaration ([XML 1.0] section 4.3.3
     * "Character Encoding in Entities"). This attribute has no effect when the
     * application provides a character stream or string data. For other sources
     * of input, an encoding specified by means of this attribute will override
     * any encoding specified in the XML declaration or the Text declaration, or
     * an encoding obtained from a higher level protocol, such as HTTP
     * [IETF RFC 2616].
     */

    public void setEncoding(String encoding){
        fEncoding = encoding;
    }

}//DOMOutputImpl