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

package java.management.share.classes.sun.management.counter.perf;

import java.management.share.classes.sun.management.counter.*;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.charset.Charset;

public class PerfStringCounter extends PerfByteArrayCounter
    implements StringCounter {

    private static final Charset defaultCharset = Charset.defaultCharset();
    PerfStringCounter(String name, Variability v,
                      int flags, ByteBuffer bb) {
        this(name, v, flags, bb.limit(), bb);
    }

    PerfStringCounter(String name, Variability v, int flags,
                      int maxLength, ByteBuffer bb) {

        super(name, Units.STRING, v, flags, maxLength, bb);
    }

    // override isVector and getVectorLength in ByteArrayCounter
    public boolean isVector() {
        return false;
    }

    public int getVectorLength() {
        return 0;
    }

    public Object getValue() {
        return stringValue();
    }

    public String stringValue() {

        String str = "";
        byte[] b = byteArrayValue();

        if (b == null || b.length <= 1) {
            return str;
        }

        int i;
        for (i = 0; i < b.length && b[i] != (byte)0; i++);

        // convert byte array to string, using all bytes up to, but
        // not including the first null byte.
        return new String(b , 0, i, defaultCharset);
    }

    /**
     * Serialize as a snapshot object.
     */
    protected Object writeReplace() throws java.io.ObjectStreamException {
        return new StringCounterSnapshot(getName(),
                                         getUnits(),
                                         getVariability(),
                                         getFlags(),
                                         stringValue());
    }

    private static final long serialVersionUID = 6802913433363692452L;
}
