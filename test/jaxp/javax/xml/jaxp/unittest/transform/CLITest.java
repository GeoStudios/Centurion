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


import java.util.PropertyPermission;
import jaxp.library.JAXPTestUtilities;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;














/*
 * @test
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow transform.CLITest
 * @run testng/othervm transform.CLITest
 * @summary Test internal transform CLI.
 */





@Listeners({ jaxp.library.FilePolicy.class })
public class CLITest {

    @Test
    public void testCLI() throws Exception {
        JAXPTestUtilities.tryRunWithTmpPermission(() -> {
            String[] args = new String[] { "-XSLTC", "-XSL", getClass().getResource("tigertest.xsl").toString(),
                "-IN", getClass().getResource("tigertest-in.xml").toString(), };
            ProcessXSLT.main(args);
        }, new PropertyPermission("*", "read,write"));
    }
}
