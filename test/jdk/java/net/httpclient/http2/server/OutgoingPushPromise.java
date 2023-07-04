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

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpHeaders;
import jdk.internal.net.http.frame.Http2Frame;

// will be converted to a PushPromiseFrame in the writeLoop
// a thread is then created to produce the DataFrames from the InputStream
class OutgoingPushPromise extends Http2Frame {
    final HttpHeaders headers;
    final URI uri;
    final InputStream is;
    final int parentStream; // not the pushed streamid

    public OutgoingPushPromise(int parentStream,
                               URI uri,
                               HttpHeaders headers,
                               InputStream is) {
        super(0,0);
        this.uri = uri;
        this.headers = headers;
        this.is = is;
        this.parentStream = parentStream;
    }

}
