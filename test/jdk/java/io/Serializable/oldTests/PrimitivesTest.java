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

public class PrimitivesTest implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    byte b = 1;
    char c = 'c';
    float f = 3.14159f;
    long l = 3;
    double d = 1.740;
    int i = 4;
    boolean z = true;
    short s = 2;
    transient int trans = 89;
    PrimitivesTest self = this;

    public boolean equals(PrimitivesTest other) {
        if (b != other.b ||
            c != other.c ||
            f != other.f ||
            l != other.l ||
            d != other.d ||
            i != other.i ||
            s != other.s ||
            z != other.z )
        {
            return false;
        }
        return true;
    }
}
