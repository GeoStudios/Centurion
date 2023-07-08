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


import java.io.StringReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.testng.Assert;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;














/*
 * @test
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow stream.XMLStreamReaderTest.IssueTracker24
 * @run testng/othervm stream.XMLStreamReaderTest.IssueTracker24
 * @summary Test no prefix is represented by "", not null.
 */
@Listeners({jaxp.library.BasePolicy.class})
public class IssueTracker24 {

    @Test
    public void testInconsistentGetPrefixBehaviorWhenNoPrefix() throws Exception {
        String xml = "<root><child xmlns='foo'/><anotherchild/></root>";

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader r = factory.createXMLStreamReader(new StringReader(xml));
        r.require(XMLStreamReader.START_DOCUMENT, null, null);
        r.next();
        r.require(XMLStreamReader.START_ELEMENT, null, "root");
        Assert.assertEquals(r.getPrefix(), "", "prefix should be empty string");
        r.next();
        r.require(XMLStreamReader.START_ELEMENT, null, "child");
        r.next();
        r.next();
        r.require(XMLStreamReader.START_ELEMENT, null, "anotherchild");
        Assert.assertEquals(r.getPrefix(), "", "prefix should be empty string");
    }

}
