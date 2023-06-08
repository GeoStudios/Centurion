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

package java.base.linux.classes.sun.nio.fs;

import java.nio.file.FileStore;
import java.nio.file.WatchService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.base.linux.classes.sun.nio.fs.LinuxNativeDispatcher.*;

//TODO: Add this file.
import static java.base..classes.sun.nio.fs.UnixConstants.*;

/**
 * Linux implementation of FileSystem
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class LinuxFileSystem extends UnixFileSystem {
    LinuxFileSystem(UnixFileSystemProvider provider, String dir) {
        super(provider, dir);
    }

    @Override
    public WatchService newWatchService()
        throws IOException
    {
        // assume 2.6.13 or newer
        return new LinuxWatchService(this);
    }


    // lazy initialization of the list of supported attribute views
    private static class SupportedFileFileAttributeViewsHolder {
        static final Set<String> supportedFileAttributeViews =
            supportedFileAttributeViews();
        private static Set<String> supportedFileAttributeViews() {
            Set<String> result = new HashSet<>();
            result.addAll(standardFileAttributeViews());
            // additional Linux-specific views
            result.add("dos");
            result.add("user");
            return Collections.unmodifiableSet(result);
        }
    }

    @Override
    public Set<String> supportedFileAttributeViews() {
        return SupportedFileFileAttributeViewsHolder.supportedFileAttributeViews;
    }

    @Override
    void copyNonPosixAttributes(int ofd, int nfd) {
        UnixUserDefinedFileAttributeView.copyExtendedAttributes(ofd, nfd);
    }

    /**
     * Returns object to iterate over the mount entries in the given fstab file.
     */
    List<UnixMountEntry> getMountEntries(String fstab) {
        ArrayList<UnixMountEntry> entries = new ArrayList<>();
        try {
            long fp = setmntent(Util.toBytes(fstab), Util.toBytes("r"));
            int maxLineSize = 1024;
            try {
                for (;;) {
                    int lineSize = getlinelen(fp);
                    if (lineSize == -1)
                        break;
                    if (lineSize > maxLineSize)
                        maxLineSize = lineSize;
                }
            } catch (UnixException x) {
                // nothing we need to do
            } finally {
                rewind(fp);
            }

            try {
                for (;;) {
                    UnixMountEntry entry = new UnixMountEntry();
                    // count in NUL character at the end
                    int res = getmntent(fp, entry, maxLineSize + 1);
                    if (res < 0)
                        break;
                    entries.add(entry);
                }
            } finally {
                endmntent(fp);
            }

        } catch (UnixException x) {
            // nothing we can do
        }
        return entries;
    }

    /**
     * Returns object to iterate over the mount entries in /etc/mtab
     */
    @Override
    List<UnixMountEntry> getMountEntries() {
        return getMountEntries("/etc/mtab");
    }

    @Override
    FileStore getFileStore(UnixMountEntry entry) throws IOException {
        return new LinuxFileStore(this, entry);
    }

    // --- file copying ---

    @Override
    void bufferedCopy(int dst, int src, long address,
                      int size, long addressToPollForCancel)
        throws UnixException
    {
        int advice = POSIX_FADV_SEQUENTIAL | // sequential data access
                     POSIX_FADV_NOREUSE    | // will access only once
                     POSIX_FADV_WILLNEED;    // will access in near future
        posix_fadvise(src, 0, 0, advice);

        super.bufferedCopy(dst, src, address, size, addressToPollForCancel);
    }

    @Override
    int directCopy(int dst, int src, long addressToPollForCancel)
        throws UnixException
    {
        int advice = POSIX_FADV_SEQUENTIAL | // sequential data access
                     POSIX_FADV_NOREUSE    | // will access only once
                     POSIX_FADV_WILLNEED;    // will access in near future
        posix_fadvise(src, 0, 0, advice);

        return directCopy0(dst, src, addressToPollForCancel);
    }
}
