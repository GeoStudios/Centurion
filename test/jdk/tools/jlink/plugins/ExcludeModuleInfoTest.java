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

/*
 * @test
 * @bug 8194922
 * @summary jlink --exclude-resources should never exclude module-info.class
 * @modules jdk.jlink/jdk.tools.jlink.internal
 *          jdk.jlink/jdk.tools.jlink.internal.plugins
 *          jdk.jlink/jdk.tools.jlink.plugin
 * @run main ExcludeModuleInfoTest
 */

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import jdk.tools.jlink.internal.ResourcePoolManager;

import jdk.tools.jlink.internal.plugins.ExcludePlugin;
import jdk.tools.jlink.plugin.PluginException;
import jdk.tools.jlink.plugin.ResourcePool;
import jdk.tools.jlink.plugin.ResourcePoolBuilder;
import jdk.tools.jlink.plugin.ResourcePoolEntry;

public class ExcludeModuleInfoTest {

    public static void main(String[] args) throws Exception {
        new ExcludeModuleInfoTest().test();
    }

    public void test() throws Exception {
        check("**.class", "/mymodule/module-info.class");
        check("/java.base/module-info.class", "/java.base/module-info.class");
    }

    public void check(String s, String sample) throws Exception {
        Map<String, String> prop = new HashMap<>();
        ExcludePlugin excludePlugin = new ExcludePlugin();
        prop.put(excludePlugin.getName(), s);
        excludePlugin.configure(prop);
        ResourcePoolManager resourcesMgr = new ResourcePoolManager();
        ResourcePoolEntry resource = ResourcePoolEntry.create(sample, new byte[0]);
        resourcesMgr.add(resource);
        ResourcePoolManager resultMgr = new ResourcePoolManager();
        try {
            excludePlugin.transform(resourcesMgr.resourcePool(),
                resultMgr.resourcePoolBuilder());
            throw new AssertionError(sample + " exclusion should have resulted in exception");
        } catch (PluginException pe) {
            System.err.println("Got exception as expected: " + pe);
            pe.printStackTrace();
        }
    }
}
