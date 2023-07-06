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

package java.xml.crypto.share.classes.org.jcp.xml.dsig.internal;

import java.io.ByteArrayOutputStream;
import javax.crypto.Mac;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Derived from Apache sources and changed to use Mac objects instead of
 * com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm objects.
 *
 *
 */
public class MacOutputStream extends ByteArrayOutputStream {
    private final Mac mac;

    public MacOutputStream(Mac mac) {
        this.mac = mac;
    }

    @Override
    public void write(int arg0) {
        super.write(arg0);
        mac.update((byte) arg0);
    }

    @Override
    public void write(byte[] arg0, int arg1, int arg2) {
        super.write(arg0, arg1, arg2);
        mac.update(arg0, arg1, arg2);
    }
}
