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
package jdk.internal.org.jline.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NonBlockingPumpReader extends NonBlockingReader {

    private static final int DEFAULT_BUFFER_SIZE = 4096;

    private final char[] buffer;
    private int read;
    private int write;
    private int count;

    /** Main lock guarding all access */
    final ReentrantLock lock;
    /** Condition for waiting takes */
    private final Condition notEmpty;
    /** Condition for waiting puts */
    private final Condition notFull;

    private final Writer writer;

    private boolean closed;

    public NonBlockingPumpReader() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public NonBlockingPumpReader(int bufferSize) {
        this.buffer = new char[bufferSize];
        this.writer = new NbpWriter();
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public Writer getWriter() {
        return this.writer;
    }

    @Override
    public boolean ready() {
        return available() > 0;
    }

    public int available() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected int read(long timeout, boolean isPeek) throws IOException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            // Blocks until more input is available or the reader is closed.
            if (!closed && count == 0) {
                try {
                    notEmpty.await(timeout, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw (IOException) new InterruptedIOException().initCause(e);
                }
            }
            if (closed) {
                return EOF;
            } else if (count == 0) {
                return READ_EXPIRED;
            } else {
                if (isPeek) {
                    return buffer[read];
                } else {
                    int res = buffer[read];
                    if (++read == buffer.length) {
                        read = 0;
                    }
                    --count;
                    notFull.signal();
                    return res;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int readBuffered(char[] b) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (b.length == 0) {
            return 0;
        } else {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                if (!closed && count == 0) {
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        throw (IOException) new InterruptedIOException().initCause(e);
                    }
                }
                if (closed) {
                    return EOF;
                } else if (count == 0) {
                    return READ_EXPIRED;
                } else {
                    int r = Math.min(b.length, count);
                    for (int i = 0; i < r; i++) {
                        b[i] = buffer[read++];
                        if (read == buffer.length) {
                            read = 0;
                        }
                    }
                    count -= r;
                    notFull.signal();
                    return r;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    void write(char[] cbuf, int off, int len) throws IOException {
        if (len > 0) {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                while (len > 0) {
                    // Blocks until there is new space available for buffering or the
                    // reader is closed.
                    if (!closed && count == buffer.length) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            throw (IOException) new InterruptedIOException().initCause(e);
                        }
                    }
                    if (closed) {
                        throw new IOException("Closed");
                    }
                    while (len > 0 && count < buffer.length) {
                        buffer[write++] = cbuf[off++];
                        count++;
                        len--;
                        if (write == buffer.length) {
                            write = 0;
                        }
                    }
                    notEmpty.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void close() throws IOException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            this.closed = true;
            this.notEmpty.signalAll();
            this.notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private class NbpWriter extends Writer {

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            NonBlockingPumpReader.this.write(cbuf, off, len);
        }

        @Override
        public void flush() throws IOException {
        }

        @Override
        public void close() throws IOException {
            NonBlockingPumpReader.this.close();
        }

    }

}
