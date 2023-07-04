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

package stream.EventsTest;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.StartDocument;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow stream.EventsTest.Issue53Test
 * @run testng/othervm stream.EventsTest.Issue53Test
 * @summary Test encodingSet/standaloneSet returns correct result in case encoding/standalone is set when constructing StartDocument.
 */
@Listeners({jaxp.library.BasePolicy.class})
public class Issue53Test {

    @Test
    public void testEncodingSet() {
        XMLEventFactory f = XMLEventFactory.newInstance();

        try {
            StartDocument sd = f.createStartDocument("UTF-8");
            System.out.println("Encoding: " + sd.getCharacterEncodingScheme());
            System.out.println("Encoding set: " + sd.encodingSet());
            Assert.assertTrue(sd.encodingSet(), "encoding is set, should return true.");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void testStandaloneSet() {
        XMLEventFactory f = XMLEventFactory.newInstance();

        try {
            StartDocument sd = f.createStartDocument("UTF-8", "1.0", true);
            System.out.println(sd.isStandalone());
            System.out.println(sd.standaloneSet());
            Assert.assertTrue(sd.standaloneSet(), "standalone is set, should return true.");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

}
