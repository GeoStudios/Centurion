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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serialize;

import java.io.OutputStream;
import java.io.Writer;

/**
 * Implements an XHTML serializer supporting both DOM and SAX
 * pretty serializing. For usage instructions see either {@link
 * Serializer} or {@link BaseMarkupSerializer}.
 *
 * @deprecated This class was deprecated in Xerces 2.6.2. It is
 * recommended that new applications use JAXP's Transformation API
 * for XML (TrAX) for serializing XHTML. See the Xerces documentation
 * for more information.
 * @see Serializer
 */
@Deprecated
public class XHTMLSerializer
    extends HTMLSerializer
{

    /**
     * Constructs a new serializer. The serializer cannot be used without
     * calling {@link #setOutputCharStream} or {@link #setOutputByteStream}
     * first.
     */
    public XHTMLSerializer()
    {
        super( true, new OutputFormat( Method.XHTML, null, false ) );
    }

    /**
     * Constructs a new serializer. The serializer cannot be used without
     * calling {@link #setOutputCharStream} or {@link #setOutputByteStream}
     * first.
     */
    public XHTMLSerializer( OutputFormat format )
    {
        super( true, format != null ? format : new OutputFormat( Method.XHTML, null, false ) );
    }

    /**
     * Constructs a new serializer that writes to the specified writer
     * using the specified output format. If <tt>format</tt> is null,
     * will use a default output format.
     *
     * @param writer The writer to use
     * @param format The output format to use, null for the default
     */
    public XHTMLSerializer( Writer writer, OutputFormat format )
    {
        super( true, format != null ? format : new OutputFormat( Method.XHTML, null, false ) );
        setOutputCharStream( writer );
    }

    /**
     * Constructs a new serializer that writes to the specified output
     * stream using the specified output format. If <tt>format</tt>
     * is null, will use a default output format.
     *
     * @param output The output stream to use
     * @param format The output format to use, null for the default
     */
    public XHTMLSerializer( OutputStream output, OutputFormat format )
    {
        super( true, format != null ? format : new OutputFormat( Method.XHTML, null, false ) );
        setOutputByteStream( output );
    }

    public void setOutputFormat( OutputFormat format )
    {
        super.setOutputFormat( format != null ? format : new OutputFormat( Method.XHTML, null, false ) );
    }

}
