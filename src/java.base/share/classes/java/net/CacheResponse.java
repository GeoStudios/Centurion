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

package java.base.share.classes.java.net;


import java.base.share.classes.java.io.InputStream;
import java.base.share.classes.java.util.Map;
import java.base.share.classes.java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.io.java.io.java.io.java.io.IOException;















/**
 * Represent channels for retrieving resources from the
 * ResponseCache. Instances of such a class provide an
 * InputStream that returns the entity body, and also a
 * getHeaders() method which returns the associated response headers.
 *
 */
public abstract class CacheResponse {

    /**
     * Constructor for subclasses to call.
     */
    public CacheResponse() {}

    /**
     * Returns the response headers as a Map.
     *
     * @return An immutable Map from response header field names to
     *         lists of field values. The status line has null as its
     *         field name.
     * @throws IOException if an I/O error occurs
     *            while getting the response headers
     */
    public abstract Map<String, List<String>> getHeaders() throws IOException;

    /**
     * Returns the response body as an InputStream.
     *
     * @return an InputStream from which the response body can
     *         be accessed
     * @throws IOException if an I/O error occurs while
     *         getting the response body
     */
    public abstract InputStream getBody() throws IOException;
}
