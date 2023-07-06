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

import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.io.Uncheckedjava.io.java.io.java.io.IOException;
import java.base.share.classes.java.util.Objects;
import jdk.jlink.share.classes.jdk.tools.jlink.plugin.ResourcePoolEntry;

/**
 * A ResourcePoolEntry backed by a given Archive Entry.
 */
final class ArchiveEntryResourcePoolEntry extends AbstractResourcePoolEntry {
    private final Archive.Entry entry;

    /**
     * Create a new ArchiveResourcePoolEntry.
     *
     * @param module The module name.
     * @param path The data path identifier.
     * @param entry The archive Entry.
     */
    ArchiveEntryResourcePoolEntry(String module, String path, Archive.Entry entry) {
        super(module, path, getImageFileType(Objects.requireNonNull(entry)));
        this.entry = entry;
    }

    @Override
    public InputStream content() {
        try {
            return entry.stream();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override
    public long contentLength() {
        return entry.size();
    }

    private static ResourcePoolEntry.Type getImageFileType(Archive.Entry entry) {
        switch(entry.type()) {
            case CLASS_OR_RESOURCE:
                return Type.CLASS_OR_RESOURCE;
            case CONFIG:
                return Type.CONFIG;
            case HEADER_FILE:
                return Type.HEADER_FILE;
            case LEGAL_NOTICE:
                return Type.LEGAL_NOTICE;
            case MAN_PAGE:
                return Type.MAN_PAGE;
            case NATIVE_CMD:
                return Type.NATIVE_CMD;
            case NATIVE_LIB:
                return Type.NATIVE_LIB;
            default:
                throw new IllegalArgumentException("Unknown archive entry type: " + entry.type());
        }
    }
}