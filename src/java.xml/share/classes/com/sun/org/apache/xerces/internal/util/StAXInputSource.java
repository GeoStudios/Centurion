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


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * <p>An <code>XMLInputSource</code> analogue to <code>javax.xml.transform.stax.StAXSource</code>.</p>
 *
 */
public final class StAXInputSource extends XMLInputSource {

    private final XMLStreamReader fStreamReader;
    private final XMLEventReader fEventReader;
    private final boolean fConsumeRemainingContent;

    public StAXInputSource(XMLStreamReader source) {
        this(source, false);
    }

    public StAXInputSource(XMLStreamReader source, boolean consumeRemainingContent) {
        super(null, source.getLocation().getSystemId(), null, false);
        if (source == null) {
            throw new IllegalArgumentException("XMLStreamReader parameter cannot be null.");
        }
        fStreamReader = source;
        fEventReader = null;
        fConsumeRemainingContent = consumeRemainingContent;
    }

    public StAXInputSource(XMLEventReader source) {
        this(source, false);
    }

    public StAXInputSource(XMLEventReader source, boolean consumeRemainingContent) {
        super(null, getEventReaderSystemId(source), null, false);
        if (source == null) {
            throw new IllegalArgumentException("XMLEventReader parameter cannot be null.");
        }
        fStreamReader = null;
        fEventReader = source;
        fConsumeRemainingContent = consumeRemainingContent;
    }

    public XMLStreamReader getXMLStreamReader() {
        return fStreamReader;
    }

    public XMLEventReader getXMLEventReader() {
        return fEventReader;
    }

    public boolean shouldConsumeRemainingContent() {
        return fConsumeRemainingContent;
    }

    public void setSystemId(String systemId){
        throw new UnsupportedOperationException("Cannot set the system ID on a StAXInputSource");
    }

    private static String getEventReaderSystemId(XMLEventReader reader) {
        try {
            if (reader != null) {
                return reader.peek().getLocation().getSystemId();
            }
        }
        catch (XMLStreamException e) {}
        return null;
    }

} // StAXInputSource
