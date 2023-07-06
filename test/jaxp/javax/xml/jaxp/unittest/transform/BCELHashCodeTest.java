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

package transform;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 8248348
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng transform.BCELHashCodeTest
 * @summary The addition of the hashCode() method to Instruction.java in BCEL 6.0
 * caused a regression. This test verifies that the issue has been fixed.
 */
public class BCELHashCodeTest {
    /**
     * Verifies the patch by attempting to create a transformer from the stylesheet.
     * The stylesheet contains an extra table-row with element-id="17921" that
     * causes the generated bytecode to exceed the 64Kbyte method size limit.
     * Splitting it into multiple methods requires mutating the instructions that
     * results in an Exception without the fix. The stylesheet would pass with
     * or without the fix if the extra table-row is removed.
     *
     * @throws TransformerConfigurationException if the test fails
     */
    @Test
    public void test() throws TransformerConfigurationException {
         StreamSource stylesheet = new StreamSource(this.getClass()
                 .getResourceAsStream("BCELHashCodeTest.xsl"));
         TransformerFactory tFactory =TransformerFactory.newInstance();
         Transformer transformer = tFactory.newTransformer(stylesheet);
    }
}
