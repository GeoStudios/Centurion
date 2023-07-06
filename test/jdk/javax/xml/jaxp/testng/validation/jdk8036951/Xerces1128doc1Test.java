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


import com.sun.org.apache.xerces.internal.dom.PSVIElementNSImpl;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import validation.BaseTest;














public class Xerces1128doc1Test extends BaseTest {

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
        return "xerces1128_1.xml";
    }

    protected String getSchemaFile() {
        return "xerces1128.xsd";
    }

    protected String[] getRelevantErrorIDs() {
        return new String[] { UNKNOWN_TYPE_ERROR, INVALID_DERIVATION_ERROR };
    }

    public Xerces1128doc1Test(String name) {
        super(name);
    }


    /**
     * XERCESJ-1128 values for {validation attempted} property in PSVI
     */
    @Test
    public void testDocument1() {
        try {
            reset();
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        // default value of the feature is false
        checkResult();
    }

    private void checkResult() {
        PSVIElementNSImpl child = super.getChild(1);
        assertValidity(ItemPSVI.VALIDITY_NOTKNOWN, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_NONE, child
                .getValidationAttempted());
        assertElementNull(child.getElementDeclaration());
        assertTypeName("anyType", child.getTypeDefinition().getName());
        assertTypeNamespace("http://www.w3.org/2001/XMLSchema",
                child.getTypeDefinition().getNamespace());

        child = super.getChild(2);
        assertValidity(ItemPSVI.VALIDITY_VALID, child.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, child
                .getValidationAttempted());
        assertElementNull(child.getElementDeclaration());
        assertTypeName("X", child.getTypeDefinition().getName());
        assertTypeNamespaceNull(child.getTypeDefinition().getNamespace());
    }
}
