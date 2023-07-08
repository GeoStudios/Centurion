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

package jdk.httpserver.share.classes.sun.net.httpserver;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.*;

/**
 * a class which allows the caller to read up to a defined
 * number of bytes off an underlying stream
 * close() does not close the underlying stream
 */

class FixedLengthInputStream extends LeftOverInputStream {
    private long remaining;

    FixedLengthInputStream (ExchangeImpl t, InputStream src, long len) {
        super (t, src);
        this.remaining = len;
    }

    protected int readImpl (byte[]b, int off, int len) throws IOException {

        eof = (remaining == 0L);
        if (eof) {
            return -1;
        }
        if (len > remaining) {
            len = (int)remaining;
        }
        int n = in.read(b, off, len);
        if (n > -1) {
            remaining -= n;
            if (remaining == 0) {
                t.getServerImpl().requestCompleted (t.getConnection());
            }
        }
        if (n < 0 && !eof)
            throw new IOException("connection closed before all data received");
        return n;
    }

    public int available () throws IOException {
        if (eof) {
            return 0;
        }
        int n = in.available();
        return n < remaining? n: (int)remaining;
    }

    public boolean markSupported () {return false;}

    public void mark (int l) {
    }

    public void reset () throws IOException {
        throw new IOException ("mark/reset not supported");
    }
}
