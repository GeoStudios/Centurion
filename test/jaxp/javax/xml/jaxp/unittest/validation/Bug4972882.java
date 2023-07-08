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

package validation;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.testng.Assert;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import util.DraconianErrorHandler;

/*
 * @test
 * @bug 4972882
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow validation.Bug4972882
 * @run testng/othervm validation.Bug4972882
 * @summary Test Validator throws Exception when two identity-constraints are defined with the same {name} and {target namespace}.
 */
@Listeners({jaxp.library.FilePolicy.class})
public class Bug4972882 {

    static final String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    @Test
    public void test1() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setValidating(true);
        dbf.setAttribute(SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
        dbf.setAttribute(SCHEMA_SOURCE, Bug4972882.class.getResource("targetNS00101m2_stub.xsd").toExternalForm());

        DocumentBuilder builder = dbf.newDocumentBuilder();
        builder.setErrorHandler(new DraconianErrorHandler());

        try {
            builder.parse(Bug4972882.class.getResource("targetNS00101m2_stub.xml").toExternalForm());
            Assert.fail("failure expected");
        } catch (SAXException e) {
            Assert.assertTrue(e.getMessage().indexOf("sch-props-correct.2") != -1);
        }
    }
}
