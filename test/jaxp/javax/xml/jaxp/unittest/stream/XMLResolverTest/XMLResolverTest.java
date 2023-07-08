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

package stream.XMLResolverTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.testng.Assert;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow stream.XMLResolverTest.XMLResolverTest
 * @run testng/othervm stream.XMLResolverTest.XMLResolverTest
 * @summary Test XMLResolver.
 */
@Listeners({jaxp.library.FilePolicy.class})
public class XMLResolverTest {

    @Test
    public void testXMLResolver() {
        try {
            XMLInputFactory xifactory = XMLInputFactory.newInstance();
            xifactory.setProperty(XMLInputFactory.RESOLVER, new MyStaxResolver());
            File file = new File(getClass().getResource("XMLResolverTest.xml").getFile());
            String systemId = file.toURI().toString();
            InputStream entityxml = new FileInputStream(file);
            XMLStreamReader streamReader = xifactory.createXMLStreamReader(systemId, entityxml);
            while (streamReader.hasNext()) {
                int eventType = streamReader.next();
                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    eventType = streamReader.next();
                    if (eventType == XMLStreamConstants.CHARACTERS) {
                        String text = streamReader.getText();
                        Assert.assertTrue(text.contains("replace2"));
                    }
                }
            }
        } catch (XMLStreamException ex) {

            if (ex.getNestedException() != null) {
                ex.getNestedException().printStackTrace();
            }
            // ex.printStackTrace() ;
        } catch (Exception io) {
            io.printStackTrace();
        }
    }

    class MyStaxResolver implements XMLResolver {

        public MyStaxResolver() {

        }

        public Object resolveEntity(String publicId, String systemId, String baseURI, String namespace) throws javax.xml.stream.XMLStreamException {

            Object object = null;
            try {
                object = new FileInputStream(getClass().getResource("replace2.txt").getFile());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return object;
        }

    }
}
