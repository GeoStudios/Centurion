/*
 * Copyright (c) 2023 GeoStudios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.core.java.io;

import java.core.jdk.internal.misc.Unsafe;

/**
 * A {@code BufferedInputStream} adds
 * functionality to another input stream-namely,
 * the ability to buffer the input and to
 * support the {@code mark} and {@code reset}
 * methods. When  the {@code BufferedInputStream}
 * is created, an internal buffer array is
 * created. As bytes  from the stream are read
 * or skipped, the internal buffer is refilled
 * as necessary  from the contained input stream,
 * many bytes at a time. The {@code mark}
 * operation  remembers a point in the input
 * stream and the {@code reset} operation
 * causes all the  bytes read since the most
 * recent {@code mark} operation to be
 * reread before new bytes are  taken from
 * the contained input stream.
 *
 * @author Logan Abernathy
 * @version Alpha CDK-1.0
 */

public class BufferedInputStream {

    private static int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * As this class is used early during bootstrap, it's motivated to use
     * Unsafe.compareAndSetObject instead of AtomicReferenceFieldUpdater
     * (or VarHandles) to reduce dependencies and improve startup time.
     */

    private static final Unsafe U = Unsafe.getUnsafe();
}