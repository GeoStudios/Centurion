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

package validation.jdk8037819;


import com.sun.org.apache.xerces.internal.dom.PSVIElementNSImpl;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import validation.BaseTest;














public class UnparsedEntityCheckingTest extends BaseTest {
    public static final String UNDECLARED_ENTITY = "UndeclaredEntity";

    protected String getXMLDocument() {
        return "unparsedEntity.xml";
    }

    protected String getSchemaFile() {
        return "base.xsd";
    }

    protected String[] getRelevantErrorIDs() {
        return new String[] { UNDECLARED_ENTITY };
    }

    public UnparsedEntityCheckingTest(String name) {
        super(name);
    }

    @BeforeClass
    protected void setUp() throws Exception {
        super.setUp();
    }

    @AfterClass
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testDefaultValid() {
        try {
            reset();
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testSetFalseValid() {
        try {
            reset();
            fValidator.setFeature(UNPARSED_ENTITY_CHECKING, false);
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testSetTrueValid() {
        try {
            reset();
            fValidator.setFeature(UNPARSED_ENTITY_CHECKING, true);
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testDefaultInvalid() {
        try {
            reset();
            ((PSVIElementNSImpl) fRootNode).setAttributeNS(null,
                    "unparsedEntityAttr", "invalid");
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkInvalid();
    }

    @Test
    public void testSetFalseInvalid() {
        try {
            reset();
            ((PSVIElementNSImpl) fRootNode).setAttributeNS(null,
                    "unparsedEntityAttr", "invalid");
            fValidator.setFeature(UNPARSED_ENTITY_CHECKING, false);
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testSetTrueInvalid() {
        try {
            reset();
            ((PSVIElementNSImpl) fRootNode).setAttributeNS(null,
                    "unparsedEntityAttr", "invalid");
            fValidator.setFeature(UNPARSED_ENTITY_CHECKING, true);
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkInvalid();
    }

    private void checkDefault() {
        assertNoError(UNDECLARED_ENTITY);
        assertValidity(ItemPSVI.VALIDITY_VALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode
                .getValidationAttempted());
        assertElementName("A", fRootNode.getElementDeclaration().getName());
        assertTypeName("X", fRootNode.getTypeDefinition().getName());
    }

    private void checkInvalid() {
        assertError(UNDECLARED_ENTITY);
        assertValidity(ItemPSVI.VALIDITY_INVALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode
                .getValidationAttempted());
        assertElementName("A", fRootNode.getElementDeclaration().getName());
        assertTypeName("X", fRootNode.getTypeDefinition().getName());
    }
}
