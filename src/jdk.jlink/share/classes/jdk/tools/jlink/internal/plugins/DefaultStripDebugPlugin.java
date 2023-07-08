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

package jdk.jlink.share.classes.jdk.tools.jlink.internal.plugins;

import java.util.Map;
import jdk.jlink.share.classes.jdk.tools.jlink.internal.PluginRepository;
import jdk.jlink.share.classes.jdk.tools.jlink.internal.ResourcePoolManager;
import jdk.jlink.share.classes.jdk.tools.jlink.internal.ResourcePoolManager.ResourcePoolImpl;
import jdk.jlink.share.classes.jdk.tools.jlink.plugin.Plugin;
import jdk.jlink.share.classes.jdk.tools.jlink.plugin.ResourcePool;
import jdk.jlink.share.classes.jdk.tools.jlink.plugin.ResourcePoolBuilder;

/**
 * Combined debug stripping plugin: Java debug attributes and native debug
 * symbols.
 *
 */
public final class DefaultStripDebugPlugin extends AbstractPlugin {

    private static final String STRIP_NATIVE_DEBUG_PLUGIN = "strip-native-debug-symbols";
    private static final String EXCLUDE_DEBUGINFO = "exclude-debuginfo-files";

    private final Plugin javaStripPlugin;
    private final NativePluginFactory stripNativePluginFactory;

    public DefaultStripDebugPlugin() {
        this(new StripJavaDebugAttributesPlugin(),
             new DefaultNativePluginFactory());
    }

    public DefaultStripDebugPlugin(Plugin javaStripPlugin,
                                   NativePluginFactory nativeStripPluginFact) {
        super("strip-debug");
        this.javaStripPlugin = javaStripPlugin;
        this.stripNativePluginFactory = nativeStripPluginFact;
    }

    @Override
    public ResourcePool transform(ResourcePool in, ResourcePoolBuilder out) {
        Plugin stripNativePlugin = stripNativePluginFactory.create();
        if (stripNativePlugin != null) {
            Map<String, String> stripNativeConfig = Map.of(
                                     STRIP_NATIVE_DEBUG_PLUGIN, EXCLUDE_DEBUGINFO);
            stripNativePlugin.configure(stripNativeConfig);
            ResourcePoolManager outRes =
                                 new ResourcePoolManager(in.byteOrder(),
                                                        ((ResourcePoolImpl)in).getStringTable());
            ResourcePool strippedJava = javaStripPlugin.transform(in,
                                                                  outRes.resourcePoolBuilder());
            return stripNativePlugin.transform(strippedJava, out);
        } else {
            return javaStripPlugin.transform(in, out);
        }
    }

    public interface NativePluginFactory {
        Plugin create();
    }

    private static class DefaultNativePluginFactory implements NativePluginFactory {

        @Override
        public Plugin create() {
            return PluginRepository.getPlugin(STRIP_NATIVE_DEBUG_PLUGIN,
                                              ModuleLayer.boot());
        }

    }

}
