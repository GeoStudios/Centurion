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
import org.xml.sax.SAXException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import validation.BaseTest;














public class IdentityConstraintCheckingTest extends BaseTest {
    // These values are unstable, since they're not actually error keys, but
    // simply
    // the first part of the error message.
    public static final String DUPLICATE_UNIQUE = "cvc-identity-constraint.4.1";

    public static final String DUPLICATE_KEY = "cvc-identity-constraint.4.2.2";

    public static final String INVALID_KEYREF = "cvc-identity-constraint.4.3";

    protected String getXMLDocument() {
        return "idc.xml";
    }

    protected String getSchemaFile() {
        return "idc.xsd";
    }

    protected String[] getRelevantErrorIDs() {
        return new String[] { DUPLICATE_UNIQUE, DUPLICATE_KEY, INVALID_KEYREF };
    }

    public IdentityConstraintCheckingTest(String name) {
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
    public void testDefault() {
        try {
            reset();
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testSetFalse() {
        try {
            reset();
            fValidator.setFeature(IDC_CHECKING, false);
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkValidResult();
    }

    @Test
    public void testSetTrue() {
        try {
            reset();
            fValidator.setFeature(IDC_CHECKING, true);
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    private void checkDefault() {
        assertError(DUPLICATE_UNIQUE);
        assertError(DUPLICATE_KEY);
        assertError(INVALID_KEYREF);

        assertValidity(ItemPSVI.VALIDITY_INVALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode
                .getValidationAttempted());
        assertElementName("itemList", fRootNode.getElementDeclaration()
                .getName());
        assertTypeName("itemListType", fRootNode.getTypeDefinition().getName());

        // this one is valid because it's the first one to define the unique
        // value
        PSVIElementNSImpl child = super.getChild(1);
        assertValidity(ItemPSVI.VALIDITY_VALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementName("item", child.getElementDeclaration().getName());
        assertTypeName("itemType", child.getTypeDefinition().getName());

        // invalid because it repeats the unique value
        child = super.getChild(2);
        assertValidity(ItemPSVI.VALIDITY_INVALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementName("item", child.getElementDeclaration().getName());
        assertTypeName("itemType", child.getTypeDefinition().getName());

        // invalid because it repeats the key
        child = super.getChild(3);
        assertValidity(ItemPSVI.VALIDITY_INVALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementName("item", child.getElementDeclaration().getName());
        assertTypeName("itemType", child.getTypeDefinition().getName());

        // valid because key references aren't figured out until the validation
        // root
        child = super.getChild(4);
        assertValidity(ItemPSVI.VALIDITY_VALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementName("itemRef", child.getElementDeclaration().getName());
        assertTypeName("string", child.getTypeDefinition().getName());
    }

    private void checkValidResult() {
        assertNoError(DUPLICATE_UNIQUE);
        assertNoError(DUPLICATE_KEY);
        assertNoError(INVALID_KEYREF);

        assertValidity(ItemPSVI.VALIDITY_VALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode
                .getValidationAttempted());
        assertElementName("itemList", fRootNode.getElementDeclaration()
                .getName());
        assertTypeName("itemListType", fRootNode.getTypeDefinition().getName());

        PSVIElementNSImpl child = super.getChild(1);
        assertValidity(ItemPSVI.VALIDITY_VALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementName("item", child.getElementDeclaration().getName());
        assertTypeName("itemType", child.getTypeDefinition().getName());

        child = super.getChild(2);
        assertValidity(ItemPSVI.VALIDITY_VALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementName("item", child.getElementDeclaration().getName());
        assertTypeName("itemType", child.getTypeDefinition().getName());

        child = super.getChild(3);
        assertValidity(ItemPSVI.VALIDITY_VALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementName("item", child.getElementDeclaration().getName());
        assertTypeName("itemType", child.getTypeDefinition().getName());

        child = super.getChild(4);
        assertValidity(ItemPSVI.VALIDITY_VALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementName("itemRef", child.getElementDeclaration().getName());
        assertTypeName("string", child.getTypeDefinition().getName());
    }
}
