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

package stream.XMLStreamReaderTest;

import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import org.testng.Assert;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow stream.XMLStreamReaderTest.IssueTracker35
 * @run testng/othervm stream.XMLStreamReaderTest.IssueTracker35
 * @summary Test StAX parse xsd document including external DTD.
 */
@Listeners({jaxp.library.FilePolicy.class})
public class IssueTracker35 {

    @Test
    public void testSkippingExternalDTD() throws Exception {
        XMLInputFactory xif = XMLInputFactory.newInstance();
        try(
                InputStream is= getClass().getResourceAsStream("XMLSchema.xsd");
        ) {
                XMLStreamReader reader = xif.createXMLStreamReader(getClass().getResource("XMLSchema.xsd").getFile(), is);
                int e;
                while ((e = reader.next()) == XMLStreamConstants.COMMENT);

                Assert.assertEquals(e, XMLStreamConstants.DTD, "should be DTD");
                reader.nextTag();
                Assert.assertEquals(reader.getLocalName(), "schema", "next tag should be schema");
        }
    }
}