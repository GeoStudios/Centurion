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


import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import validation.BaseTest;














/**
 * @author Peter McCracken, IBM
 * @version $Id$
 */
public class RootSimpleTypeDefinitionTest extends BaseTest {

    private QName typeString;
    private QName typeNonNegInt;

    private final static String INVALID_TYPE_ERROR = "cvc-type.3.1.3";
    private final static String MININCLUSIVE_DERIVATION_ERROR = "cvc-minInclusive-valid";

    protected String getXMLDocument() {
        return "simpleType.xml";
    }

    protected String getSchemaFile() {
        return "base.xsd";
    }

    protected String[] getRelevantErrorIDs() {
        return new String[] { INVALID_TYPE_ERROR, MININCLUSIVE_DERIVATION_ERROR };
    }

    @BeforeClass
    protected void setUp() throws Exception {
        super.setUp();
    }

    @AfterClass
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public RootSimpleTypeDefinitionTest(String name) {
        super(name);
        // This is a roundabout way of making sure that we're not using an
        // interned string (so that == doesn't work)
        String ns = "x" + XMLConstants.W3C_XML_SCHEMA_NS_URI;
        ns = ns.substring(1);
        typeString = new QName(ns, "string", "xsd");
        typeNonNegInt = new QName(ns, "nonNegativeInteger", "xsd");
    }

    @Test
    public void testSettingSimpleType() throws Exception {
        try {
            reset();
            fValidator.setProperty(ROOT_TYPE, typeString);
        } catch (SAXException e1) {
            fail("Problem setting property: " + e1.getMessage());
        }

        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        assertValidity(ItemPSVI.VALIDITY_VALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode
                .getValidationAttempted());
        assertElementNull(fRootNode.getElementDeclaration());
        assertTypeName("string", fRootNode.getTypeDefinition().getName());
    }

    @Test
    public void testSettingInvalidSimpleType() throws Exception {
        try {
            reset();
            fValidator.setProperty(ROOT_TYPE, typeNonNegInt);
        } catch (SAXException e1) {
            fail("Problem setting property: " + e1.getMessage());
        }

        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        assertError(INVALID_TYPE_ERROR);
        assertError(MININCLUSIVE_DERIVATION_ERROR);
        assertValidity(ItemPSVI.VALIDITY_INVALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode
                .getValidationAttempted());
        assertElementNull(fRootNode.getElementDeclaration());
        assertTypeName("nonNegativeInteger", fRootNode.getTypeDefinition().getName());
    }
}
