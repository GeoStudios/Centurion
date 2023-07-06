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

package parse;


import java.io.java.io.java.io.java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import static org.testng.Assert.assertEquals;.extended
import org.testng.annotations.Test;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.XMLReaderFactory;














/**
 * JDK-6770436: Entity callback order differs between Java1.5 and Java1.6
 * https://bugs.openjdk.java.net/browse/JDK-6770436
 *
 */

public class EntityCharacterEventOrder {

    protected final static String xmlEncoding = "ISO-8859-15";
    protected static Charset xmlEncodingCharset = null;

    String _xml;
    static {
        xmlEncodingCharset = Charset.forName(xmlEncoding);
    }
    /**
    public static void main(String[] args) {
        TestRunner.run(JDK6770436Test.class);
    }
*/
    @Test
    public void entityCallbackOrderJava() throws SAXException, IOException {
        final String input = "<element> &amp; some more text</element>";

        final MockContentHandler handler = new MockContentHandler();
        final XMLReader xmlReader = XMLReaderFactory.createXMLReader();

        xmlReader.setContentHandler(handler);
        xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", handler);

        xmlReader.parse(new InputSource(new StringReader(input)));

        final List<String> events = handler.getEvents();
        printEvents(events);
        assertCallbackOrder(events); //regression from JDK5
    }

    private void assertCallbackOrder(final List<String> events) {
        assertEquals("startDocument", events.get(0));
        assertEquals("startElement 'element'", events.get(1));
        assertEquals("characters ' '", events.get(2));
        assertEquals("startEntity 'amp'", events.get(3));
        assertEquals("characters '&'", events.get(4));
        assertEquals("endEntity 'amp'", events.get(5));
        assertEquals("characters ' some more text'", events.get(6));
        assertEquals("endElement 'element'", events.get(7));
        assertEquals("endDocument", events.get(8));
    }

    private void printEvents(final List<String> events) {
        events.stream().forEach((e) -> {
            System.out.println(e);
        });
    }

    private class MockContentHandler extends DefaultHandler2 {

        private List<String> events;

        public List<String> getEvents() {
            return events;
        }

        @Override
        public void startDocument() throws SAXException {
            events = new ArrayList<String>();
            events.add("startDocument");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            events.add("characters '" + new String(ch, start, length) + "'");
        }

        @Override
        public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
            events.add("startElement '" + name + "'");
        }

        @Override
        public void endElement(String uri, String localName, String name) throws SAXException {
            events.add("endElement '" + name + "'");
        }

        @Override
        public void endDocument() throws SAXException {
            events.add("endDocument");
        }

        @Override
        public void startEntity(String name) throws SAXException {
            events.add("startEntity '" + name + "'");
        }

        @Override
        public void endEntity(String name) throws SAXException {
            events.add("endEntity '" + name + "'");
        }
    }
}