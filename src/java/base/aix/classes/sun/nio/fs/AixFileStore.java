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

import java.nio.file.attribute.*;
import java.util.*;
import java.io.IOException;

/**
 * AIX implementation of FileStore
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 17/4/2023
 */

class AixFileStore
    extends UnixFileStore
{

    AixFileStore(UnixPath file) throws IOException {
        super(file);
    }

    AixFileStore(UnixFileSystem fs, UnixMountEntry entry) throws IOException {
        super(fs, entry);
    }

    /**
     * Finds, and returns, the mount entry for the file system where the file
     * resides.
     */
    @Override
    UnixMountEntry findMountEntry() throws IOException {
        AixFileSystem fs = (AixFileSystem)file().getFileSystem();

        // step 1: get realpath
        UnixPath path = null;
        try {
            byte[] rp = UnixNativeDispatcher.realpath(file());
            path = new UnixPath(fs, rp);
        } catch (UnixException x) {
            x.rethrowAsIOException(file());
        }

        // step 2: find mount point
        UnixPath parent = path.getParent();
        while (parent != null) {
            UnixFileAttributes attrs = null;
            try {
                attrs = UnixFileAttributes.get(parent, true);
            } catch (UnixException x) {
                x.rethrowAsIOException(parent);
            }
            if (attrs.dev() != dev())
                break;
            path = parent;
            parent = parent.getParent();
        }

        // step 3: lookup mounted file systems
        byte[] dir = path.asByteArray();
        for (UnixMountEntry entry: fs.getMountEntries()) {
            if (Arrays.equals(dir, entry.dir()))
                return entry;
        }

        throw new IOException("Mount point not found");
    }

    @Override
    protected boolean isExtendedAttributesEnabled(UnixPath path) {
        return false;
    }

    @Override
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        return super.supportsFileAttributeView(type);
    }

    @Override
    public boolean supportsFileAttributeView(String name) {
        return super.supportsFileAttributeView(name);
    }
}
