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

import org.testng.annotations.Test;

import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.dcmd.CommandExecutor;
import jdk.test.lib.dcmd.JMXExecutor;

/*
 * @test
 * @summary Test of diagnostic command VM.system_properties
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 *          java.compiler
 *          java.management
 *          jdk.internal.jvmstat/sun.jvmstat.monitor
 * @run testng SystemPropertiesTest
 */
public class SystemPropertiesTest {
    private final static String PROPERTY_NAME  = "SystemPropertiesTestPropertyName";
    private final static String PROPERTY_VALUE = "SystemPropertiesTestPropertyValue";

    public void run(CommandExecutor executor) {
        System.setProperty(PROPERTY_NAME, PROPERTY_VALUE);

        OutputAnalyzer output = executor.execute("VM.system_properties");
        output.shouldContain(PROPERTY_NAME + "=" + PROPERTY_VALUE);
    }

    @Test
    public void jmx() {
        run(new JMXExecutor());
    }
}
