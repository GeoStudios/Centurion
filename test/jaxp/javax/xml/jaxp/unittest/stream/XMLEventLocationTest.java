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


import javax.xml.stream.Location;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.XMLEvent;
import org.testng.Assert;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;














/*
 * @test
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow stream.XMLEventLocationTest
 * @run testng/othervm stream.XMLEventLocationTest
 * @summary Test XMLEvent Location.
 */
@Listeners({jaxp.library.BasePolicy.class})
public class XMLEventLocationTest {

    @Test
    public void testNonNullLocation() {
        XMLEventFactory factory = XMLEventFactory.newInstance();
        XMLEvent event = factory.createComment("some comment");
        Assert.assertNotNull(event.getLocation());
    }

    @Test
    public void testSetLocation() {
        XMLEventFactory factory = XMLEventFactory.newInstance();
        Location loc = new MyLocation();
        factory.setLocation(loc);
        XMLEvent event = factory.createComment("some comment");
        Assert.assertEquals(event.getLocation().getLineNumber(), 15);
    }

    class MyLocation implements Location {
        public MyLocation() {
        }

        public int getCharacterOffset() {
            return 5;
        }

        public int getColumnNumber() {
            return 10;
        }

        public int getLineNumber() {
            return 15;
        }

        public String getPublicId() {
            return "-//My//DTD Public Id//EN";
        }

        public String getSystemId() {
            return "http://example.org/system/id";
        }
    }
}
