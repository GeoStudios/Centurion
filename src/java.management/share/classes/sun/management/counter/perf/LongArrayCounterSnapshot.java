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















/**
 * A snapshot of the perf counter for serialization.
 */
class LongArrayCounterSnapshot extends AbstractCounter
       implements LongArrayCounter {

    long[] value;

    // package private
    LongArrayCounterSnapshot(String name, Units u, Variability v, int flags,
                             int vectorLength, long[] value) {
        super(name, u, v, flags, vectorLength);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public long[] longArrayValue() {
        return value;
    }

    public long longAt(int index) {
        return value[index];
    }

    private static final long serialVersionUID = 3585870271405924292L;
}
