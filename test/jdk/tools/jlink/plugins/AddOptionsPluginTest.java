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

import jdk.test.lib.process.*;

import tests.Helper;

/* @test
 * @bug 8232080
 * @summary Test the --add-options plugin
 * @library ../../lib
 * @library /test/lib
 * @modules java.base/jdk.internal.jimage
 *          jdk.jdeps/com.sun.tools.classfile
 *          jdk.jlink/jdk.tools.jlink.internal
 *          jdk.jlink/jdk.tools.jmod
 *          jdk.jlink/jdk.tools.jimage
 *          jdk.compiler
 * @build tests.*
 * @run main AddOptionsPluginTest
 */

public class AddOptionsPluginTest {

    private static final String PROP = "add.options.plugin.test";
    private static final String VALUE = "xyzzy";
    private static final String OPTS = "-D" + PROP + "=" + VALUE;

    public static void main(String[] args) throws Throwable {

        Helper helper = Helper.newHelper();
        if (helper == null) {
            System.err.println("Test not run");
            return;
        }

        var module = "addoptions";
        helper.generateDefaultJModule(module);
        var image = helper.generateDefaultImage(new String[] { "--add-options", OPTS },
                                                module)
            .assertSuccess();
        helper.checkImage(image, module, null, null);

        var launcher = image.resolve("bin/java"
                                     + (System.getProperty("os.name").startsWith("Windows")
                                        ? ".exe" : ""));
        var oa = ProcessTools.executeProcess(launcher.toString(),
                                             "-XshowSettings:properties", "--version");
        oa.stderrShouldMatch("^ +" + PROP + " = " + VALUE + "$");

    }

}
