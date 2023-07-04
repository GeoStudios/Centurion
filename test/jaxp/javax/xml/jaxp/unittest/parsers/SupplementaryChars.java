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

package parsers;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @test
 * @bug 8072081
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow parsers.SupplementaryChars
 * @run testng/othervm parsers.SupplementaryChars
 * @summary verifies that supplementary characters are supported as character
 * data in xml 1.0, and also names in xml 1.1.
 *
 * Joe Wang (huizhe.wang@oracle.com)
 */

@Listeners({jaxp.library.BasePolicy.class})
public class SupplementaryChars {

    @Test(dataProvider = "supported")
    public void test(String xml) throws Exception {
        ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        getParser().parse(stream, new DefaultHandler());
        stream.close();
    }

    @Test(dataProvider = "unsupported", expectedExceptions = SAXParseException.class)
    public void testInvalid(String xml) throws Exception {
        ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        getParser().parse(stream, new DefaultHandler());
        stream.close();
    }

    @DataProvider(name = "supported")
    public Object[][] supported() {

        return new Object[][] {
            {"<?xml version=\"1.0\"?><tag>\uD840\uDC0B</tag>"},
            {"<?xml version=\"1.0\"?><!-- \uD840\uDC0B --><tag/>"},
            {"<?xml version=\"1.1\"?><tag\uD840\uDC0B>in tag name</tag\uD840\uDC0B>"},
            {"<?xml version=\"1.1\"?><tag attr\uD840\uDC0B=\"in attribute\">in attribute name</tag>"},
            {"<?xml version=\"1.1\"?><tag>\uD840\uDC0B</tag>"},
            {"<?xml version=\"1.1\"?><!-- \uD840\uDC0B --><dontCare/>"}
        };
    }

    @DataProvider(name = "unsupported")
    public Object[][] unsupported() {
        return new Object[][] {
            {"<?xml version=\"1.0\"?><tag\uD840\uDC0B>in tag name</tag\uD840\uDC0B>"},
            {"<?xml version=\"1.0\"?><tag attr\uD840\uDC0B=\"in attribute\">in attribute name</tag>"}
        };
    }

    private SAXParser getParser() {
        SAXParser parser = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return parser;
    }
}
