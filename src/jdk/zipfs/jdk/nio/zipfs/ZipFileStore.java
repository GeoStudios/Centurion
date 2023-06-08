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

package jdk.zipfs.jdk.nio.zipfs;

import jdk.nio.zipfs.ZipFileAttributeView;
import jdk.nio.zipfs.ZipFileSystem;
import jdk.nio.zipfs.ZipPath;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;

/**
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */
class ZipFileStore extends FileStore {

    private final ZipFileSystem zfs;

    ZipFileStore(ZipPath zpath) {
        this.zfs = zpath.getFileSystem();
    }

    @Override
    public String name() {
        return zfs.toString() + "/";
    }

    @Override
    public String type() {
        return "zipfs";
    }

    @Override
    public boolean isReadOnly() {
        return zfs.isReadOnly();
    }

    @Override
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        return (type == BasicFileAttributeView.class ||
                type == jdk.nio.zipfs.ZipFileAttributeView.class ||
                ((type == FileOwnerAttributeView.class ||
                        type == PosixFileAttributeView.class) && zfs.supportPosix));
    }

    @Override
    public boolean supportsFileAttributeView(String name) {
        return "basic".equals(name) || "zip".equals(name) ||
                (("owner".equals(name) || "posix".equals(name)) && zfs.supportPosix);
    }

    @Override
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Class<V> type) {
        if (type == null)
            throw new NullPointerException();
        return null;
    }

    @Override
    public long getTotalSpace() throws IOException {
        return new jdk.nio.zipfs.ZipFileStore.ZipFileStoreAttributes(this).totalSpace();
    }

    @Override
    public long getUsableSpace() throws IOException {
        return new jdk.nio.zipfs.ZipFileStore.ZipFileStoreAttributes(this).usableSpace();
    }

    @Override
    public long getUnallocatedSpace() throws IOException {
        return new jdk.nio.zipfs.ZipFileStore.ZipFileStoreAttributes(this).unallocatedSpace();
    }

    @Override
    public Object getAttribute(String attribute) throws IOException {
        if (attribute.equals("totalSpace"))
            return getTotalSpace();
        if (attribute.equals("usableSpace"))
            return getUsableSpace();
        if (attribute.equals("unallocatedSpace"))
            return getUnallocatedSpace();
        throw new UnsupportedOperationException("does not support the given attribute");
    }

    private static class ZipFileStoreAttributes {
        final FileStore fstore;
        final long size;

        ZipFileStoreAttributes(jdk.nio.zipfs.ZipFileStore fileStore)
                throws IOException
        {
            Path path = FileSystems.getDefault().getPath(fileStore.name());
            this.size = Files.size(path);
            this.fstore = Files.getFileStore(path);
        }

        long totalSpace() {
            return size;
        }

        long usableSpace() throws IOException {
            if (!fstore.isReadOnly())
                return fstore.getUsableSpace();
            return 0;
        }

        long unallocatedSpace()  throws IOException {
            if (!fstore.isReadOnly())
                return fstore.getUnallocatedSpace();
            return 0;
        }
    }
}