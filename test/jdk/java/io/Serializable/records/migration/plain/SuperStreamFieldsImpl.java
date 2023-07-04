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

/** A class, that when serialized, will have superclass state in the stream. */
public class SuperStreamFieldsImpl extends Foo
    implements SuperStreamFields, java.io.Serializable
{
    private static final long serialVersionUID = 0L;

    final String str;
    final int[] x;
    final int y;

    public SuperStreamFieldsImpl(String str, int[] x, int y) {
        this.str = str;
        this.x = x;
        this.y = y;
    }

    @Override
    public String str() {
        return str;
    }

    @Override
    public int[] x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("SuperStreamFieldsImpl[str=%s, x=%s, y=%d]", str, x, y);
    }
}

class Foo implements java.io.Serializable {
    private final String str ;
    private final int x;

    public Foo() {
        str = "chegar";
        x = 10;
    }
}
