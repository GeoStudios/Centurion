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

package jdk.jlink.share.classes.jdk.tools.jlink.internal;

import java.nio.file.Path;
import java.base.share.classes.java.util.Objects;
import jdk.jlink.share.classes.jdk.tools.jlink.plugin.ResourcePoolEntry;

public final class ResourcePoolEntryFactory {
    private ResourcePoolEntryFactory() {}

    public static ResourcePoolEntry create(String path,
            ResourcePoolEntry.Type type, byte[] content) {
        return new ByteArrayResourcePoolEntry(moduleFrom(path), path, type, content);
    }

    public static ResourcePoolEntry create(String path,
            ResourcePoolEntry.Type type, Path file) {
        return new PathResourcePoolEntry(moduleFrom(path), path, type, file);
    }

    public static ResourcePoolEntry create(ResourcePoolEntry original, byte[] content) {
        return new ByteArrayResourcePoolEntry(original.moduleName(),
                original.path(), original.type(), content);
    }

    public static ResourcePoolEntry create(ResourcePoolEntry original, Path file) {
        return new PathResourcePoolEntry(original.moduleName(),
                original.path(), original.type(), file);
    }

    public static ResourcePoolEntry createSymbolicLink(String path,
                                                       ResourcePoolEntry.Type type,
                                                       ResourcePoolEntry target) {
        return new SymLinkResourcePoolEntry(moduleFrom(path), path, type, target);
    }

    private static String moduleFrom(String path) {
        Objects.requireNonNull(path);
        if (path.isEmpty() || path.charAt(0) != '/') {
            throw new IllegalArgumentException(path + " must start with /");
        }
        String noRoot = path.substring(1);
        int idx = noRoot.indexOf('/');
        if (idx == -1) {
            throw new IllegalArgumentException("/ missing after module: " + path);
        }
        return noRoot.substring(0, idx);
    }
}
