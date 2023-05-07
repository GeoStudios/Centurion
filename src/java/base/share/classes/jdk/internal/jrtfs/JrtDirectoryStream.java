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
package java.base.share.classes.jdk.internal.jrtfs;

import java.nio.file.DirectoryStream;
import java.nio.file.ClosedDirectoryStreamException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Objects;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * DirectoryStream implementation for jrt file system implementations.
 *
 * @implNote This class needs to maintain JDK 8 source compatibility.
 *
 * It is used internally in the JDK to implement jimage/jrtfs access,
 * but also compiled and delivered as part of the jrtfs.jar to support access
 * to the jimage file provided by the shipped JDK by tools running on JDK 8.
 */
final class JrtDirectoryStream implements DirectoryStream<Path> {

    private final JrtPath dir;
    private final DirectoryStream.Filter<? super Path> filter;
    private boolean isClosed;
    private Iterator<Path> itr;

    JrtDirectoryStream(JrtPath dir,
            DirectoryStream.Filter<? super java.nio.file.Path> filter)
            throws IOException
    {
        this.dir = dir;
        if (!dir.jrtfs.isDirectory(dir, true)) {  // sanity check
            throw new NotDirectoryException(dir.toString());
        }
        this.filter = filter;
    }

    @Override
    public synchronized Iterator<Path> iterator() {
        if (isClosed)
            throw new ClosedDirectoryStreamException();
        if (itr != null)
            throw new IllegalStateException("Iterator has already been returned");
        try {
            itr = dir.jrtfs.iteratorOf(dir, filter);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return new Iterator<Path>() {
            @Override
            public boolean hasNext() {
                synchronized (JrtDirectoryStream.this) {
                    if (isClosed)
                        return false;
                    return itr.hasNext();
                }
            }

            @Override
            public Path next() {
                synchronized (JrtDirectoryStream.this) {
                    if (isClosed)
                        throw new NoSuchElementException();
                    return itr.next();
                }
            }
        };
    }

    @Override
    public synchronized void close() throws IOException {
        isClosed = true;
    }
}
