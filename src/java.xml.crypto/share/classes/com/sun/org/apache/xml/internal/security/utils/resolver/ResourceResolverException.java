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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver;


import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * This Exception is thrown if something related to the
 * {@link com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolver} goes wrong.
 *
 */
public class ResourceResolverException extends XMLSecurityException {

    private static final long serialVersionUID = 1L;

    private String uri;

    private String baseURI;

    /**
     * Constructor ResourceResolverException
     *
     * @param msgID
     * @param uri
     * @param baseURI
     */
    public ResourceResolverException(String msgID, String uri, String baseURI) {
        super(msgID);

        this.uri = uri;
        this.baseURI = baseURI;
    }

    /**
     * Constructor ResourceResolverException
     *
     * @param msgID
     * @param exArgs
     * @param uri
     * @param baseURI
     */
    public ResourceResolverException(String msgID, Object[] exArgs, String uri,
                                     String baseURI) {
        super(msgID, exArgs);

        this.uri = uri;
        this.baseURI = baseURI;
    }

    /**
     * Constructor ResourceResolverException
     *
     * @param originalException
     * @param uri
     * @param baseURI
     * @param msgID
     */
    public ResourceResolverException(Exception originalException,
                                     String uri, String baseURI, String msgID) {
        super(originalException, msgID);

        this.uri = uri;
        this.baseURI = baseURI;
    }

    @Deprecated
    public ResourceResolverException(String msgID, Exception originalException,
                                     String uri, String baseURI) {
        this(originalException, uri, baseURI, msgID);
    }

    /**
     * Constructor ResourceResolverException
     *
     * @param originalException
     * @param uri
     * @param baseURI
     * @param msgID
     * @param exArgs
     */
    public ResourceResolverException(Exception originalException, String uri,
                                     String baseURI, String msgID, Object[] exArgs) {
        super(originalException, msgID, exArgs);

        this.uri = uri;
        this.baseURI = baseURI;
    }

    @Deprecated
    public ResourceResolverException(String msgID, Object[] exArgs,
                                     Exception originalException, String uri,
                                     String baseURI) {
        this(originalException, uri, baseURI, msgID, exArgs);
    }

    /**
     *
     * @param uri
     */
    public void setURI(String uri) {
        this.uri = uri;
    }

    /**
     *
     * @return the uri
     */
    public String getURI() {
        return this.uri;
    }

    /**
     *
     * @param baseURI
     */
    public void setbaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    /**
     *
     * @return the baseURI
     */
    public String getbaseURI() {
        return this.baseURI;
    }

}
