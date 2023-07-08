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


import static jaxp.library.JAXPTestUtilities.USER_DIR;.extended
import static jaxp.library.JAXPTestUtilities.compareWithGold;.extended
import static org.testng.Assert.assertTrue;.extended
import static org.xml.sax.ptests.SAXTestConst.GOLDEN_DIR;.extended
import static org.xml.sax.ptests.SAXTestConst.XML_DIR;.extended
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;














/**
 * This tests the Attributes interface. Here the startElement() callback of
 * ContentHandler has Attributes as one of its arguments. Attributes
 * pertaining to an element are taken into this argument and various methods
 * of Attributes interfaces are tested. This program uses Namespace processing
 * with namespaces in XML file. This program does not use Validation
 */
/*
 * @test
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow org.xml.sax.ptests.AttributesNSTest
 * @run testng/othervm org.xml.sax.ptests.AttributesNSTest
 */
@Listeners({jaxp.library.FilePolicy.class})
public class AttributesNSTest {
    /**
     * Test for Attribute Interface's setter/getter.
     *
     * @throws Exception If any errors occur.
     */
    @Test
    public void testcase01() throws Exception {
        String outputFile = USER_DIR + "AttributesNS.out";
        String goldFile = GOLDEN_DIR + "AttributesNSGF.out";
        String xmlFile = XML_DIR + "namespace1.xml";
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        // http://www.saxproject.com/?selected=namespaces namespace-prefixes
        //set to false to supress xmlns attributes
        spf.setFeature("http://xml.org/sax/features/namespace-prefixes",
                                    false);
        SAXParser saxParser = spf.newSAXParser();
        MyAttrCHandler myAttrCHandler = new MyAttrCHandler(outputFile);
        saxParser.parse(new File(xmlFile), myAttrCHandler);
        myAttrCHandler.flushAndClose();
        assertTrue(compareWithGold(goldFile, outputFile));
    }
}
