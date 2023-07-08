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

package test.astro;


import javax.xml.parsers.SAXParserFactory;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.XMLReader;














/*
 * @test
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow test.astro.SAX201Test
 * @run testng/othervm test.astro.SAX201Test
 * @summary verify SAX 2.0.1 allows to use null in setters
 */
@Listeners({jaxp.library.BasePolicy.class})
public class SAX201Test {
    @Test
    public void test() throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        XMLReader reader = spf.newSAXParser().getXMLReader();
        reader.setErrorHandler(null); // SAX 2.0.1 allows
        reader.setContentHandler(null); // SAX 2.0.1 allows
        reader.setEntityResolver(null); // SAX 2.0.1 allows
        reader.setDTDHandler(null); // SAX 2.0.1 allows
    }
}
