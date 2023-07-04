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

package validation.jdk8036951;


import com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.SchemaFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import validation.BaseTest;

/**
 * @author Peter McCracken, IBM
 * @version $Id$
 */
public class RootTypeDefinitionTest extends BaseTest {

    private QName unknownType;
    private QName typeX;
    private QName typeY;
    private QName typeZ;
    private QName typeOtherNamespace;

    private final static String UNKNOWN_TYPE_ERROR = "cvc-type.1";

    private final static String INVALID_DERIVATION_ERROR = "cvc-elt.4.3";

    @BeforeClass
    protected void setUp() throws Exception {
        super.setUp();
    }

    @AfterClass
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    protected String getXMLDocument() {
        return "base.xml";
    }

    protected String getSchemaFile() {
        return "base.xsd";
    }

    protected String[] getRelevantErrorIDs() {
        return new String[] { UNKNOWN_TYPE_ERROR, INVALID_DERIVATION_ERROR };
    }

    public RootTypeDefinitionTest(String name) {
        super(name);
        unknownType = new QName("W");
        typeX = new QName("X");
        typeY = new QName("Y");
        typeZ = new QName("Z");
        typeOtherNamespace = new QName("xslt.unittests", "W", "unit");
    }


    /**
     * XERCESJ-1141 root-type-definition property not read by XMLSchemaValidator during reset()
     */
    @Test
    public void testUsingDocumentBuilderFactory() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(ROOT_TYPE, typeX);
        dbf.setAttribute(DOCUMENT_CLASS_NAME,"com.sun.org.apache.xerces.internal.dom.PSVIDocumentImpl");
        dbf.setNamespaceAware(true);
        dbf.setValidating(false);

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        dbf.setSchema(sf.newSchema(fSchemaURL));

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(fDocumentURL.toExternalForm());
        ElementPSVI rootNode = (ElementPSVI) document.getDocumentElement();

        assertValidity(ItemPSVI.VALIDITY_VALID, rootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, rootNode
                .getValidationAttempted());
        assertElementNull(rootNode.getElementDeclaration());
        assertTypeName("X", rootNode.getTypeDefinition().getName());
    }

    private void checkDefault() {
        assertValidity(ItemPSVI.VALIDITY_VALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode
                .getValidationAttempted());
        assertElementName("A", fRootNode.getElementDeclaration().getName());
        assertTypeName("X", fRootNode.getTypeDefinition().getName());
    }
}
