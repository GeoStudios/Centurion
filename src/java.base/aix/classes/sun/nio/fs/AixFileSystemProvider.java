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

package java.base.aix.classes.sun.nio.fs;

import java.base.share.classes.java.io.IOException;

/**
 * AIX implementation of FileSystemProvider
 */

class AixFileSystemProvider extends UnixFileSystemProvider {
    public AixFileSystemProvider() {
        super();
    }

    @Override
    AixFileSystem newFileSystem(String dir) {
        return new AixFileSystem(this, dir);
    }

    /**
     * @see sun.nio.fs.UnixFileSystemProvider#getFileStore(sun.nio.fs.UnixPath)
     */
    @Override
    AixFileStore getFileStore(UnixPath path) throws IOException {
        return new AixFileStore(path);
    }
}