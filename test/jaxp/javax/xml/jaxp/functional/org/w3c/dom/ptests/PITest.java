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
package org.w3c.dom.ptests;

import static org.testng.Assert.assertEquals;
import static org.w3c.dom.ptests.DOMTestUtil.createDOMWithNS;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.ProcessingInstruction;

/*
 * @test
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow org.w3c.dom.ptests.PITest
 * @run testng/othervm org.w3c.dom.ptests.PITest
 * @summary Test for the methods of Processing Instruction
 */
@Listeners({jaxp.library.FilePolicy.class})
public class PITest {
    /*
     * Test getData, setData and getTarget methods
     */
    @Test
    public void test() throws Exception {
        Document document = createDOMWithNS("PITest01.xml");
        ProcessingInstruction pi = document.createProcessingInstruction("PI", "processing");
        assertEquals(pi.getData(), "processing");
        assertEquals(pi.getTarget(), "PI");

        pi.setData("newProcessing");
        assertEquals(pi.getData(), "newProcessing");
    }

}
