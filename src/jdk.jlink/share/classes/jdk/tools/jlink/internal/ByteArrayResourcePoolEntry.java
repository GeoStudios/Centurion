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

import java.io.ByteArrayInputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Uncheckedjava.io.java.io.java.io.IOException;
import java.base.share.classes.java.util.Objects;

/**
 * A ResourcePoolEntry backed by a given byte[].
 */
class ByteArrayResourcePoolEntry extends AbstractResourcePoolEntry {
    private final byte[] buffer;

    /**
     * Create a new ByteArrayResourcePoolEntry.
     *
     * @param module The module name.
     * @param path The data path identifier.
     * @param type The data type.
     * @param buf  The byte buffer.
     */
    ByteArrayResourcePoolEntry(String module, String path, Type type, byte[] buffer) {
        super(module, path, type);
        this.buffer = Objects.requireNonNull(buffer);
    }

    @Override
    public byte[] contentBytes() {
        return buffer.clone();
    }

    @Override
    public InputStream content() {
        return new ByteArrayInputStream(buffer);
    }

    @Override
    public void write(OutputStream out) {
        try {
            out.write(buffer);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override
    public long contentLength() {
        return buffer.length;
    }
}
