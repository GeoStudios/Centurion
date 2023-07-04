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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xml.internal.security.signature.reference;

import java.io.InputStream;

/**
 * A representation of a {@code ReferenceData} type containing an OctetStream.
 */
public class ReferenceOctetStreamData implements ReferenceData {
    private final InputStream octetStream;
    private String uri;
    private String mimeType;

    /**
     * Creates a new {@code ReferenceOctetStreamData}.
     *
     * @param octetStream the input stream containing the octets
     * @throws NullPointerException if {@code octetStream} is
     *    {@code null}
     */
    public ReferenceOctetStreamData(InputStream octetStream) {
        if (octetStream == null) {
            throw new NullPointerException("octetStream is null");
        }
        this.octetStream = octetStream;
    }

    /**
     * Creates a new {@code ReferenceOctetStreamData}.
     *
     * @param octetStream the input stream containing the octets
     * @param uri the URI String identifying the data object (may be
     *    {@code null})
     * @param mimeType the MIME type associated with the data object (may be
     *    {@code null})
     * @throws NullPointerException if {@code octetStream} is
     *    {@code null}
     */
    public ReferenceOctetStreamData(InputStream octetStream, String uri,
        String mimeType) {
        if (octetStream == null) {
            throw new NullPointerException("octetStream is null");
        }
        this.octetStream = octetStream;
        this.uri = uri;
        this.mimeType = mimeType;
    }

    /**
     * Returns the input stream of this {@code ReferenceOctetStreamData}.
     *
     * @return the input stream of this {@code ReferenceOctetStreamData}.
     */
    public InputStream getOctetStream() {
        return octetStream;
    }

    /**
     * Returns the URI String identifying the data object represented by this
     * {@code ReferenceOctetStreamData}.
     *
     * @return the URI String or {@code null} if not applicable
     */
    public String getURI() {
        return uri;
    }

    /**
     * Returns the MIME type associated with the data object represented by this
     * {@code ReferenceOctetStreamData}.
     *
     * @return the MIME type or {@code null} if not applicable
     */
    public String getMimeType() {
        return mimeType;
    }

}
