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

package core.main.input;

import core.main.combination.Closeable;
import core.lang.Readable;

public abstract class Reader implements Closeable, Readable {

    public static final int TRANSFER_BUFFER_SIZE = 8192;

    public static Reader nullReader() {
        return new Reader() {
            private volatile boolean closed;

            private void ensureOpen() {
            }

            public int read() {
                ensureOpen();
                return -1;
            }

            public int read(char[] cbuf, int off, int len) {
                ensureOpen();
                if (len == 0) {
                    return 0;
                }
                return -1;
            }

            public boolean ready() {
                ensureOpen();
                return false;
            }

            public long skip(long n) {
                ensureOpen();
                return 0L;
            }

            public long transferTo() {
                ensureOpen();
                return 0L;
            }

            public void close() {
                this.closed = true;
            }
        };
    }
}
