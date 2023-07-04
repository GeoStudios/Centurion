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

public class DefaultValuesImpl implements DefaultValues, java.io.Serializable {

    private static final long serialVersionUID = 51L;

    private final Point point;

    // no more fields, they will be added in the record.

    public DefaultValuesImpl(Point point) {
        this.point = point;
    }

    public Point point() {
        return point;
    }

    @Override
    public boolean bool() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public byte by() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public short sh() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public char ch() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public int i() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public long l() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public float f() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public double d() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public String str() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public int[] ia() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public Object[] oa() {
        throw new AssertionError("should not reach here");
    }

    @Override
    public Object obj() {
        throw new AssertionError("should not reach here");
    }
}
