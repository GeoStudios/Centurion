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

package xp1;

import java.io.OutputStream;
import java.io.Writer;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

public class XMLOutputFactoryImpl extends XMLOutputFactory {

    @Override
    public XMLStreamWriter createXMLStreamWriter(Writer stream) throws XMLStreamException {
        return null;
    }

    @Override
    public XMLStreamWriter createXMLStreamWriter(OutputStream stream) throws XMLStreamException {
        return null;
    }

    @Override
    public XMLStreamWriter createXMLStreamWriter(OutputStream stream, String encoding)
            throws XMLStreamException {
        return null;
    }

    @Override
    public XMLStreamWriter createXMLStreamWriter(Result result) throws XMLStreamException {
        return null;
    }

    @Override
    public XMLEventWriter createXMLEventWriter(Result result) throws XMLStreamException {
        return null;
    }

    @Override
    public XMLEventWriter createXMLEventWriter(OutputStream stream) throws XMLStreamException {
        return null;
    }

    @Override
    public XMLEventWriter createXMLEventWriter(OutputStream stream, String encoding)
            throws XMLStreamException {
        return null;
    }

    @Override
    public XMLEventWriter createXMLEventWriter(Writer stream) throws XMLStreamException {
        return null;
    }

    @Override
    public void setProperty(String name, Object value) throws IllegalArgumentException {

    }

    @Override
    public Object getProperty(String name) throws IllegalArgumentException {
        return null;
    }

    @Override
    public boolean isPropertySupported(String name) {
        return false;
    }

}
