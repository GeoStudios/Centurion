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

package java.base.share.classes.sun.security.provider;

import java.io.*;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A pool of {@code InputStream}s opened from distinct files. Only a single
 * instance is ever opened from the same file. This is used to read special
 * infinite files like {@code /dev/random} where the current file pointer is not
 * relevant, so multiple readers can share the same file descriptor and
 * consequently the same {@code InputStream}.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
class FileInputStreamPool {

    /**
     * a pool of: StreamRef -> UnclosableInputStream -> FileInputStream(s)
     */
    private static final ConcurrentMap<File, StreamRef> pool =
        new ConcurrentHashMap<>();

    /**
     * a reference queue of cleared StreamRef(s)
     */
    private static final ReferenceQueue<UnclosableInputStream> refQueue =
        new ReferenceQueue<>();

    /**
     * This method opens an underlying {@link java.io.FileInputStream} for a
     * given {@code file} and returns a wrapper over it. The wrapper is shared
     * among multiple readers of the same {@code file} and ignores
     * {@link java.io.InputStream#close()} requests. The underlying stream is
     * closed when all references to the wrapper are relinquished.
     *
     * @param file the file to be opened for reading.
     * @return a shared {@link java.io.InputStream} instance opened from given
     * file.
     * @throws FileNotFoundException if the file does not exist, is a directory
     *                               rather than a regular file, or for some
     *                               other reason cannot be opened for  reading.
     * @throws SecurityException     if a security manager exists and its
     *                               <code>checkRead</code> method denies read
     *                               access to the file.
     */
    static InputStream getInputStream(File file) throws IOException {

        // expunge any cleared references
        StreamRef oldRref;
        while ((oldRref = (StreamRef) refQueue.poll()) != null) {
            pool.remove(oldRref.file, oldRref);
        }

        // canonicalize the path
        // (this also checks the read permission on the file if SecurityManager
        // is present, so no checking is needed later when we just return the
        // already opened stream)
        File cfile = file.getCanonicalFile();

        // check if it exists in pool
        oldRref = pool.get(cfile);
        UnclosableInputStream oldStream = (oldRref == null)
            ? null
            : oldRref.get();
        StreamRef newRef = null;
        UnclosableInputStream newStream = null;

        // retry loop
        while (true) {
            if (oldStream != null) {
                // close our optimistically opened stream 1st (if we opened it)
                if (newStream != null) {
                    try {
                        newStream.getWrappedStream().close();
                    } catch (IOException ignore) {
                        // can't do anything here
                    }
                }
                // return it
                return oldStream;
            } else {
                // we need to open new stream optimistically (if not already)
                if (newStream == null) {
                    newStream = new UnclosableInputStream(
                        new FileInputStream(cfile));
                    newRef = new StreamRef(cfile, newStream, refQueue);
                }
                // either try to install newRef or replace oldRef with newRef
                if (oldRref == null) {
                    oldRref = pool.putIfAbsent(cfile, newRef);
                } else {
                    oldRref = pool.replace(cfile, oldRref, newRef)
                        ? null
                        : pool.get(cfile);
                }
                if (oldRref == null) {
                    // success
                    return newStream;
                } else {
                    // lost race
                    oldStream = oldRref.get();
                    // another loop
                }
            }
        }
    }

    private static class StreamRef extends WeakReference<UnclosableInputStream> {
        final File file;

        StreamRef(File file,
                  UnclosableInputStream stream,
                  ReferenceQueue<UnclosableInputStream> refQueue) {
            super(stream, refQueue);
            this.file = file;
        }
    }

    private static final class UnclosableInputStream extends FilterInputStream {
        UnclosableInputStream(InputStream in) {
            super(in);
        }

        @Override
        public void close() throws IOException {
            // Ignore close attempts since underlying InputStream is shared.
        }

        InputStream getWrappedStream() {
            return in;
        }
    }
}
