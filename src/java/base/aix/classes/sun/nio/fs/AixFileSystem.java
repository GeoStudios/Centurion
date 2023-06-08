/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.aix.classes.sun.nio.fs;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import java.util.*;
import static java.base.aix.classes.sun.nio.fs.AixNativeDispatcher.*;

/**
 * AIX implementation of FileSystem
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 17/4/2023
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