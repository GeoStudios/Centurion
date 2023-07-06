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

import java.base.share.classes.java.nio.file.*;
import java.base.share.classes.java.nio.file.attribute.*;
import java.base.share.classes.java.io.IOException;
import java.base.share.classes.java.util.*;
import static java.base.aix.classes.sun.nio.fs.AixNativeDispatcher.*;

/**
 * AIX implementation of FileSystem
 */

class AixFileSystem extends UnixFileSystem {

    AixFileSystem(UnixFileSystemProvider provider, String dir) {
        super(provider, dir);
    }

    @Override
    public WatchService newWatchService()
        throws IOException
    {
        return new PollingWatchService();
    }

    // lazy initialization of the list of supported attribute views
    private static class SupportedFileFileAttributeViewsHolder {
        static final Set<String> supportedFileAttributeViews =
            supportedFileAttributeViews();
        private static Set<String> supportedFileAttributeViews() {
            Set<String> result = new HashSet<String>();
            result.addAll(UnixFileSystem.standardFileAttributeViews());
            return Collections.unmodifiableSet(result);
        }
    }

    @Override
    public Set<String> supportedFileAttributeViews() {
        return SupportedFileFileAttributeViewsHolder.supportedFileAttributeViews;
    }

    @Override
    void copyNonPosixAttributes(int ofd, int nfd) {
        // TODO: Implement if needed.
    }

    /**
     * Returns object to iterate over the mount entries returned by mntctl
     */
    @Override
    Iterable<UnixMountEntry> getMountEntries() {
        UnixMountEntry[] entries = null;
        try {
            entries = getmntctl();
        } catch (UnixException x) {
            // nothing we can do
        }
        if (entries == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(entries);
    }

    @Override
    FileStore getFileStore(UnixMountEntry entry) throws IOException {
        return new AixFileStore(this, entry);
    }
}
