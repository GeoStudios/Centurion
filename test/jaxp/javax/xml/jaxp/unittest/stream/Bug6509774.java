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

package stream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import org.testng.Assert;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 6509774
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow stream.Bug6509774
 * @run testng/othervm stream.Bug6509774
 * @summary Test Property javax.xml.stream.supportDTD, DTD events are now returned even if supportDTD=false.
 */
@Listeners({jaxp.library.FilePolicy.class})
public class Bug6509774 {

    @Test
    public void test0() {

        try {

            XMLInputFactory xif = XMLInputFactory.newInstance();

            xif.setProperty("javax.xml.stream.supportDTD", Boolean.TRUE);

            XMLStreamReader xsr = xif.createXMLStreamReader(

            getClass().getResource("sgml_Bug6509774.xml").toString(),

            getClass().getResourceAsStream("sgml_Bug6509774.xml"));

            Assert.assertTrue(xsr.getEventType() == XMLStreamConstants.START_DOCUMENT);

            int event = xsr.next();

            // Must be a DTD event since DTDs are supported

            Assert.assertTrue(event == XMLStreamConstants.DTD);

            while (xsr.hasNext()) {

                event = xsr.next();

            }

            Assert.assertTrue(event == XMLStreamConstants.END_DOCUMENT);

            xsr.close();

        }

        catch (Exception e) {

            Assert.fail(e.getMessage());

        }

    }

    @Test
    public void test1() {

        try {

            XMLInputFactory xif = XMLInputFactory.newInstance();

            xif.setProperty("javax.xml.stream.supportDTD", Boolean.FALSE);

            XMLStreamReader xsr = xif.createXMLStreamReader(

            getClass().getResource("sgml_Bug6509774.xml").toString(),

            getClass().getResourceAsStream("sgml_Bug6509774.xml"));

            Assert.assertTrue(xsr.getEventType() == XMLStreamConstants.START_DOCUMENT);

            int event = xsr.next();

            // Should not be a DTD event since they are ignored

            Assert.assertTrue(event == XMLStreamConstants.DTD);

            while (xsr.hasNext()) {

                event = xsr.next();

            }

            Assert.assertTrue(event == XMLStreamConstants.END_DOCUMENT);

            xsr.close();

        }

        catch (Exception e) {

            Assert.fail(e.getMessage());

        }

    }

    @Test
    public void test2() {

        try {

            XMLInputFactory xif = XMLInputFactory.newInstance();

            xif.setProperty("javax.xml.stream.supportDTD", Boolean.FALSE);

            XMLStreamReader xsr = xif.createXMLStreamReader(

            getClass().getResource("sgml-bad-systemId.xml").toString(),

            getClass().getResourceAsStream("sgml-bad-systemId.xml"));

            Assert.assertTrue(xsr.getEventType() == XMLStreamConstants.START_DOCUMENT);

            int event = xsr.next();

            // Should not be a DTD event since they are ignored

            Assert.assertTrue(event == XMLStreamConstants.DTD);

            while (xsr.hasNext()) {

                event = xsr.next();

            }

            Assert.assertTrue(event == XMLStreamConstants.END_DOCUMENT);

            xsr.close();

        }

        catch (Exception e) {

            // Bogus systemId in XML document should not result in exception

            Assert.fail(e.getMessage());

        }

    }

}