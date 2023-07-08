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

package java.xml.share.classes.org.w3c.dom.ls;

















/**
 *  This interface represents an output destination for data.
 * <p> This interface allows an application to encapsulate information about
 * an output destination in a single object, which may include a URI, a byte
 * stream (possibly with a specified encoding), a base URI, and/or a
 * character stream.
 * <p> The exact definitions of a byte stream and a character stream are
 * binding dependent.
 * <p> The application is expected to provide objects that implement this
 * interface whenever such objects are needed. The application can either
 * provide its own objects that implement this interface, or it can use the
 * generic factory method <code>DOMImplementationLS.createLSOutput()</code>
 * to create objects that implement this interface.
 * <p> The <code>LSSerializer</code> will use the <code>LSOutput</code> object
 * to determine where to serialize the output to. The
 * <code>LSSerializer</code> will look at the different outputs specified in
 * the <code>LSOutput</code> in the following order to know which one to
 * output to, the first one that is not null and not an empty string will be
 * used:
 * <ol>
 * <li> <code>LSOutput.characterStream</code>
 * </li>
 * <li>
 * <code>LSOutput.byteStream</code>
 * </li>
 * <li> <code>LSOutput.systemId</code>
 * </li>
 * </ol>
 * <p> <code>LSOutput</code> objects belong to the application. The DOM
 * implementation will never modify them (though it may make copies and
 * modify the copies, if necessary).
 * <p>See also the <a href='http://www.w3.org/TR/2004/REC-DOM-Level-3-LS-20040407'>Document Object Model (DOM) Level 3 Load
and Save Specification</a>.
 *
 */
public interface LSOutput {
    /**
     *  An attribute of a language and binding dependent type that represents
     * a writable stream to which 16-bit units can be output.
     */
    java.io.Writer getCharacterStream();
    /**
     *  An attribute of a language and binding dependent type that represents
     * a writable stream to which 16-bit units can be output.
     */
    void setCharacterStream(java.io.Writer characterStream);

    /**
     *  An attribute of a language and binding dependent type that represents
     * a writable stream of bytes.
     */
    java.io.OutputStream getByteStream();
    /**
     *  An attribute of a language and binding dependent type that represents
     * a writable stream of bytes.
     */
    void setByteStream(java.io.OutputStream byteStream);

    /**
     *  The system identifier, a URI reference [<a href='http://www.ietf.org/rfc/rfc2396.txt'>IETF RFC 2396</a>], for this
     * output destination.
     * <br> If the system ID is a relative URI reference (see section 5 in [<a href='http://www.ietf.org/rfc/rfc2396.txt'>IETF RFC 2396</a>]), the
     * behavior is implementation dependent.
     */
    String getSystemId();
    /**
     *  The system identifier, a URI reference [<a href='http://www.ietf.org/rfc/rfc2396.txt'>IETF RFC 2396</a>], for this
     * output destination.
     * <br> If the system ID is a relative URI reference (see section 5 in [<a href='http://www.ietf.org/rfc/rfc2396.txt'>IETF RFC 2396</a>]), the
     * behavior is implementation dependent.
     */
    void setSystemId(String systemId);

    /**
     *  The character encoding to use for the output. The encoding must be a
     * string acceptable for an XML encoding declaration ([<a href='https://www.w3.org/TR/xml/'>XML 1.0</a>] section
     * 4.3.3 "Character Encoding in Entities"), it is recommended that
     * character encodings registered (as charsets) with the Internet
     * Assigned Numbers Authority [<a href='http://www.iana.org/assignments/character-sets/character-sets.xhtml'>IANA-CHARSETS</a>]
     *  should be referred to using their registered names.
     */
    String getEncoding();
    /**
     *  The character encoding to use for the output. The encoding must be a
     * string acceptable for an XML encoding declaration ([<a href='https://www.w3.org/TR/xml/'>XML 1.0</a>] section
     * 4.3.3 "Character Encoding in Entities"), it is recommended that
     * character encodings registered (as charsets) with the Internet
     * Assigned Numbers Authority [<a href='http://www.iana.org/assignments/character-sets/character-sets.xhtml'>IANA-CHARSETS</a>]
     *  should be referred to using their registered names.
     */
    void setEncoding(String encoding);

}
