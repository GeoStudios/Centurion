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


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;















/**
 * This class represents an input source for an XML resource
 * retrievable over HTTP. In addition to the properties
 * provided by an <code>XMLInputSource</code> an HTTP input
 * source also has HTTP request properties and a preference
 * whether HTTP redirects will be followed. Note that these
 * properties will only be used if reading this input source
 * will induce an HTTP connection.
 *
 *
 */
public final class HTTPInputSource extends XMLInputSource {

    //
    // Data
    //

    /** Preference for whether HTTP redirects should be followed. **/
    protected boolean fFollowRedirects = true;

    /** HTTP request properties. **/
    protected Map<String, String> fHTTPRequestProperties = new HashMap<>();

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
     */
    public HTTPInputSource(String publicId, String systemId, String baseSystemId) {
        super(publicId, systemId, baseSystemId, false);
    } // <init>(String,String,String)

    /**
     * Constructs an input source from a XMLResourceIdentifier
     * object, leaving resolution of the entity and opening of
     * the input stream up to the caller.
     *
     * @param resourceIdentifier the XMLResourceIdentifier containing the information
     */
    public HTTPInputSource(XMLResourceIdentifier resourceIdentifier) {
        super(resourceIdentifier);
    } // <init>(XMLResourceIdentifier)

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
    public HTTPInputSource(String publicId, String systemId,
            String baseSystemId, InputStream byteStream, String encoding) {
        super(publicId, systemId, baseSystemId, byteStream, encoding);
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
    public HTTPInputSource(String publicId, String systemId,
            String baseSystemId, Reader charStream, String encoding) {
        super(publicId, systemId, baseSystemId, charStream, encoding);
    } // <init>(String,String,String,Reader,String)

    //
    // Public methods
    //

    /**
     * Returns the preference whether HTTP redirects should
     * be followed. By default HTTP redirects will be followed.
     */
    public boolean getFollowHTTPRedirects() {
        return fFollowRedirects;
    } // getFollowHTTPRedirects():boolean


    /**
     * Sets the preference whether HTTP redirects should
     * be followed. By default HTTP redirects will be followed.
     */
    public void setFollowHTTPRedirects(boolean followRedirects) {
        fFollowRedirects = followRedirects;
    } // setFollowHTTPRedirects(boolean)

    /**
     * Returns the value of the request property
     * associated with the given property name.
     *
     * @param key the name of the request property
     * @return the value of the request property or
     * <code>null</code> if this property has not
     * been set
     */
    public String getHTTPRequestProperty(String key) {
        return fHTTPRequestProperties.get(key);
    } // getHTTPRequestProperty(String):String

    /**
     * Returns an iterator for the request properties this
     * input source contains. Each object returned by the
     * iterator is an instance of <code>java.util.Map.Entry</code>
     * where each key and value are a pair of strings corresponding
     * to the name and value of a request property.
     *
     * @return an iterator for the request properties this
     * input source contains
     */
    public Iterator<Map.Entry<String, String>> getHTTPRequestProperties() {
        return fHTTPRequestProperties.entrySet().iterator();
    } // getHTTPRequestProperties():Iterator

    /**
     * Sets the value of the request property
     * associated with the given property name.
     *
     * @param key the name of the request property
     * @param value the value of the request property
     */
    public void setHTTPRequestProperty(String key, String value) {
        if (value != null) {
            fHTTPRequestProperties.put(key, value);
        }
        else {
            fHTTPRequestProperties.remove(key);
        }
    } // setHTTPRequestProperty(String,String)

} // class HTTPInputSource
