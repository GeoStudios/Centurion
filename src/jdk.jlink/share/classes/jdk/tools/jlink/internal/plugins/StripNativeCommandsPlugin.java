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

import jdk.jlink.share.classes.jdk.tools.jlink.plugin.ResourcePool;
import jdk.jlink.share.classes.jdk.tools.jlink.plugin.ResourcePoolBuilder;
import jdk.jlink.share.classes.jdk.tools.jlink.plugin.ResourcePoolEntry;

/**
 *
 * Strip Native Commands plugin
 */
public final class StripNativeCommandsPlugin extends AbstractPlugin {

    public StripNativeCommandsPlugin() {
        super("strip-native-commands");
    }

    @Override
    public Category getType() {
        return Category.FILTER;
    }

    @Override
    public ResourcePool transform(ResourcePool in, ResourcePoolBuilder out) {
        in.transformAndCopy((file) -> {
            return file.type() == ResourcePoolEntry.Type.NATIVE_CMD ? null : file;
        }, out);

        return out.build();
    }
}