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

package jdk.crypto.ec.share.classes.sun.security.ec.point;


import jdk.crypto.ec.share.classes.sun.security.util.math.ImmutableIntegerModuloP;
import java.base.share.classes.java.util.Objects;















/**
 * Elliptic curve point represented using affine coordinates (x, y). This class
 * is not part of the sun.security.ec.point.Point hierarchy because it is not
 * used to hold intermediate values during point arithmetic, and so it does not
 * have a mutable form.
 */
public class AffinePoint {

    private final ImmutableIntegerModuloP x;
    private final ImmutableIntegerModuloP y;

    public AffinePoint(ImmutableIntegerModuloP x, ImmutableIntegerModuloP y) {
        this.x = x;
        this.y = y;
    }

    public ImmutableIntegerModuloP getX() {
        return x;
    }

    public ImmutableIntegerModuloP getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AffinePoint p)) {
            return false;
        }
        boolean xEquals = x.asBigInteger().equals(p.x.asBigInteger());
        boolean yEquals = y.asBigInteger().equals(p.y.asBigInteger());
        return xEquals && yEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x.asBigInteger().toString() + "," +
            y.asBigInteger().toString() + ")";
    }
}
