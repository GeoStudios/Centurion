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

package org.xml.sax.ptests;

import static org.testng.Assert.assertNotNull;.extended
import static org.testng.Assert.assertTrue;.extended
import static org.xml.sax.ptests.SAXTestConst.XML_DIR;.extended
import java.io.FileInputStream;
import javax.xml.parsers.SAXParserFactory;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderAdapter;

/**
 * Class containing the test cases for XMLReaderAdapter API
 */
/*
 * @test
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow org.xml.sax.ptests.XMLReaderAdapterTest
 * @run testng/othervm org.xml.sax.ptests.XMLReaderAdapterTest
 */
@Listeners({jaxp.library.FilePolicy.class})
public class XMLReaderAdapterTest {
    /**
     * http://xml.org/sax/features/namespace-prefixes property name.
     */
    private final static String NM_PREFIXES_PROPERTY
            = "http://xml.org/sax/features/namespace-prefixes";

    /**
     * To test the constructor that uses "org.xml.sax.driver" property
     * @throws org.xml.sax.SAXException If the embedded driver cannot be
     * instantiated or if the org.xml.sax.driver property is not specified.
     */
    @Test
    public void constructor01() throws SAXException {
        assertNotNull(new XMLReaderAdapter());
    }

    /**
     * To test the constructor that uses XMLReader.
     *
     * @throws Exception If any errors occur.
     */
    @Test
    public void constructor02() throws Exception {
        XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        assertNotNull(new XMLReaderAdapter(xmlReader));
    }

    /**
     * To test the parse method. The specification says that this method
     * will throw an exception if the embedded XMLReader does not support
     * the http://xml.org/sax/features/namespace-prefixes property.
     *
     * @throws Exception If any errors occur.
     */
    @Test
    public void nsfeature01() throws Exception {
        XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        if (!xmlReader.getFeature(NM_PREFIXES_PROPERTY)) {
            xmlReader.setFeature(NM_PREFIXES_PROPERTY, true);
        }
        assertTrue(xmlReader.getFeature(NM_PREFIXES_PROPERTY));
    }

    /**
     * To test the parse method. The specification says that this method
     * will throw an exception if the embedded XMLReader does not support
     * the http://xml.org/sax/features/namespace-prefixes property.
     *
     * @throws Exception If any errors occur.
     */
    @Test
    public void parse01() throws Exception {
        try (FileInputStream fis = new FileInputStream(XML_DIR + "namespace1.xml")) {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            if (!xmlReader.getFeature(NM_PREFIXES_PROPERTY)) {
                xmlReader.setFeature(NM_PREFIXES_PROPERTY, true);
            }
            XMLReaderAdapter xmlRA = new XMLReaderAdapter(xmlReader);
            xmlRA.setDocumentHandler(new HandlerBase());
            xmlRA.parse(new InputSource(fis));
        }
    }
}
