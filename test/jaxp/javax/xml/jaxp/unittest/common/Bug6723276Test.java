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

package common;

import java.net.URL;
import java.net.URLClassLoader;
import javax.xml.parsers.SAXParserFactory;
import org.testng.Assert;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 6723276
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow common.Bug6723276Test
 * @run testng/othervm common.Bug6723276Test
 * @summary Test JAXP class can be loaded by bootstrap classloader.
 */
@Listeners({jaxp.library.BasePolicy.class})
public class Bug6723276Test {
    private static final String ERR_MSG = "org.apache.xerces.jaxp.SAXParserFactoryImpl not found";

    @Test
    public void testWithDefaultContextClassLoader() {
        try {
            SAXParserFactory.newInstance();
        } catch (Exception e) {
            if (e.getMessage().contains(ERR_MSG)) {
                Assert.fail(e.getMessage());
            }
        }
    }

    @Test
    public void testWithGivenURLContextClassLoader() {
        try {
            System.out.println(Thread.currentThread().getContextClassLoader());
            System.out.println(ClassLoader.getSystemClassLoader().getParent());
            Thread.currentThread().setContextClassLoader(new URLClassLoader(new URL[0], ClassLoader.getSystemClassLoader().getParent()));
            SAXParserFactory.newInstance();
        } catch (Exception e) {
            if (e.getMessage().contains(ERR_MSG)) {
                Assert.fail(e.getMessage());
            }
        }
    }

}
