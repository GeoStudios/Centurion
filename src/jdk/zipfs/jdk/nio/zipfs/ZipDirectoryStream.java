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

import jdk.nio.zipfs.ZipFileSystem;
import jdk.nio.zipfs.ZipPath;

import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */
class ZipDirectoryStream implements DirectoryStream<Path> {

    private final ZipFileSystem zipfs;
    private final ZipPath dir;
    private final DirectoryStream.Filter<? super Path> filter;
    private volatile boolean isClosed;
    private volatile Iterator<Path> itr;

    ZipDirectoryStream(ZipPath dir,
                       DirectoryStream.Filter<? super java.nio.file.Path> filter)
            throws IOException
    {
        this.zipfs = dir.getFileSystem();
        this.dir = dir;
        this.filter = filter;
        // sanity check
        if (!zipfs.isDirectory(dir.getResolvedPath()))
            throw new NotDirectoryException(dir.toString());
    }

    @Override
    public synchronized Iterator<Path> iterator() {
        if (isClosed)
            throw new ClosedDirectoryStreamException();
        if (itr != null)
            throw new IllegalStateException("Iterator has already been returned");

        try {
            itr = zipfs.iteratorOf(dir, filter);
        } catch (IOException e) {
            throw new DirectoryIteratorException(e);
        }

        return new Iterator<Path>() {
            @Override
            public boolean hasNext() {
                if (isClosed)
                    return false;
                return itr.hasNext();
            }

            @Override
            public synchronized Path next() {
                if (isClosed)
                    throw new NoSuchElementException();
                return itr.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public synchronized void close() throws IOException {
        isClosed = true;
    }
}