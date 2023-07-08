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

package java.base.share.classes.sun.nio.ch;


import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.io.java.io.java.io.java.io.IOException;















/**
 * A Future representing the result of an I/O operation that has already
 * completed.
 */

final class CompletedFuture<V> implements Future<V> {
    private final V result;
    private final Throwable exc;

    private CompletedFuture(V result, Throwable exc) {
        this.result = result;
        this.exc = exc;
    }

    static <V> CompletedFuture<V> withResult(V result) {
        return new CompletedFuture<V>(result, null);
    }

    static <V> CompletedFuture<V> withFailure(Throwable exc) {
        // exception must be IOException or SecurityException
        if (!(exc instanceof IOException) && !(exc instanceof SecurityException))
            exc = new IOException(exc);
        return new CompletedFuture<V>(null, exc);
    }

    static <V> CompletedFuture<V> withResult(V result, Throwable exc) {
        if (exc == null) {
            return withResult(result);
        } else {
            return withFailure(exc);
        }
    }

    @Override
    public V get() throws ExecutionException {
        if (exc != null)
            throw new ExecutionException(exc);
        return result;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        if (unit == null)
            throw new NullPointerException();
        if (exc != null)
            throw new ExecutionException(exc);
        return result;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }
}
