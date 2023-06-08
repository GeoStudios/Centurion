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

package java.base.share.classes.sun.nio.ch;

import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.*;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Base implementation of AsynchronousFileChannel.
 */

abstract class AsynchronousFileChannelImpl
    extends AsynchronousFileChannel
{
    // close support
    protected final ReadWriteLock closeLock = new ReentrantReadWriteLock();
    protected volatile boolean closed;

    // file descriptor
    protected final FileDescriptor fdObj;

    // indicates if open for reading/writing
    protected final boolean reading;
    protected final boolean writing;

    // associated Executor
    protected final ExecutorService executor;

    protected AsynchronousFileChannelImpl(FileDescriptor fdObj,
                                          boolean reading,
                                          boolean writing,
                                          ExecutorService executor)
    {
        this.fdObj = fdObj;
        this.reading = reading;
        this.writing = writing;
        this.executor = executor;
    }

    final ExecutorService executor() {
        return executor;
    }

    @Override
    public final boolean isOpen() {
        return !closed;
    }

    /**
     * Marks the beginning of an I/O operation.
     *
     * @throws  ClosedChannelException  If channel is closed
     */
    protected final void begin() throws IOException {
        closeLock.readLock().lock();
        if (closed)
            throw new ClosedChannelException();
    }

    /**
     * Marks the end of an I/O operation.
     */
    protected final void end() {
        closeLock.readLock().unlock();
    }

    /**
     * Marks end of I/O operation
     */
    protected final void end(boolean completed) throws IOException {
        end();
        if (!completed && !isOpen())
            throw new AsynchronousCloseException();
    }

    // -- file locking --

    abstract <A> Future<FileLock> implLock(long position,
                                           long size,
                                           boolean shared,
                                           A attachment,
                                           CompletionHandler<FileLock,? super A> handler);

    @Override
    public final Future<FileLock> lock(long position,
                                       long size,
                                       boolean shared)

    {
        return implLock(position, size, shared, null, null);
    }

    @Override
    public final <A> void lock(long position,
                               long size,
                               boolean shared,
                               A attachment,
                               CompletionHandler<FileLock,? super A> handler)
    {
        if (handler == null)
            throw new NullPointerException("'handler' is null");
        implLock(position, size, shared, attachment, handler);
    }

    private volatile FileLockTable fileLockTable;

    final void ensureFileLockTableInitialized() throws IOException {
        if (fileLockTable == null) {
            synchronized (this) {
                if (fileLockTable == null) {
                    fileLockTable = new FileLockTable(this, fdObj);
                }
            }
        }
    }

    final void invalidateAllLocks() throws IOException {
        if (fileLockTable != null) {
            for (FileLock fl: fileLockTable.removeAll()) {
                synchronized (fl) {
                    if (fl.isValid()) {
                        FileLockImpl fli = (FileLockImpl)fl;
                        implRelease(fli);
                        fli.invalidate();
                    }
                }
            }
        }
    }

    /**
     * Adds region to lock table
     */
    protected final FileLockImpl addToFileLockTable(long position, long size, boolean shared) {
        final FileLockImpl fli;
        try {
            // like begin() but returns null instead of exception
            closeLock.readLock().lock();
            if (closed)
                return null;

            try {
                ensureFileLockTableInitialized();
            } catch (IOException x) {
                // should not happen
                throw new AssertionError(x);
            }
            fli = new FileLockImpl(this, position, size, shared);
            // may throw OverlappedFileLockException
            fileLockTable.add(fli);
        } finally {
            end();
        }
        return fli;
    }

    protected final void removeFromFileLockTable(FileLockImpl fli) {
        fileLockTable.remove(fli);
    }

    /**
     * Releases the given file lock.
     */
    protected abstract void implRelease(FileLockImpl fli) throws IOException;

    /**
     * Invoked by FileLockImpl to release the given file lock and remove it
     * from the lock table.
     */
    final void release(FileLockImpl fli) throws IOException {
        try {
            begin();
            implRelease(fli);
            removeFromFileLockTable(fli);
        } finally {
            end();
        }
    }


    // -- reading and writing --

    abstract <A> Future<Integer> implRead(ByteBuffer dst,
                                         long position,
                                         A attachment,
                                         CompletionHandler<Integer,? super A> handler);

    @Override
    public final Future<Integer> read(ByteBuffer dst, long position) {
        return implRead(dst, position, null, null);
    }

    @Override
    public final <A> void read(ByteBuffer dst,
                               long position,
                               A attachment,
                               CompletionHandler<Integer,? super A> handler)
    {
        if (handler == null)
            throw new NullPointerException("'handler' is null");
        implRead(dst, position, attachment, handler);
    }

    abstract <A> Future<Integer> implWrite(ByteBuffer src,
                                           long position,
                                           A attachment,
                                           CompletionHandler<Integer,? super A> handler);


    @Override
    public final Future<Integer> write(ByteBuffer src, long position) {
        return implWrite(src, position, null, null);
    }

    @Override
    public final <A> void write(ByteBuffer src,
                                long position,
                                A attachment,
                                CompletionHandler<Integer,? super A> handler)
    {
        if (handler == null)
            throw new NullPointerException("'handler' is null");
        implWrite(src, position, attachment, handler);
    }
}
