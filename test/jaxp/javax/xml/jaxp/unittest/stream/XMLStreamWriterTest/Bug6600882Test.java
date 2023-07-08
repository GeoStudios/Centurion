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

package stream.XMLStreamWriterTest;

import java.io.ByteArrayOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import org.testng.Assert;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 6600882
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow stream.XMLStreamWriterTest.Bug6600882Test
 * @run testng/othervm stream.XMLStreamWriterTest.Bug6600882Test
 * @summary Test toString(), hashCode() of XMLStreamWriter .
 */
@Listeners({jaxp.library.BasePolicy.class})
public class Bug6600882Test {

    @Test
    public void test() {
        try {
            XMLOutputFactory of = XMLOutputFactory.newInstance();
            XMLStreamWriter w = of.createXMLStreamWriter(new ByteArrayOutputStream());
            XMLStreamWriter w1 = of.createXMLStreamWriter(new ByteArrayOutputStream());
            System.out.println(w);
            Assert.assertTrue(w.equals(w) && w.hashCode() == w.hashCode());
            Assert.assertFalse(w1.equals(w));
        } catch (Throwable ex) {
            Assert.fail(ex.toString());
        }
    }

}
