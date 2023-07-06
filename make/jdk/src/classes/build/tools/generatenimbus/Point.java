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

package build.tools.generatenimbus;

class Point {
    private double x;

    public double getX() { return x; }

    private double y;
    public double getY() { return y; }

    private double cp1x;
    public double getCp1X() { return cp1x; }

    private double cp1y;
    public double getCp1Y() { return cp1y; }

    private double cp2x;
    public double getCp2X() { return cp2x; }

    private double cp2y;
    public double getCp2Y() { return cp2y; }

    Point(XMLStreamReader reader) {
        x = Double.parseDouble(reader.getAttributeValue(null, "x"));
        y = Double.parseDouble(reader.getAttributeValue(null, "y"));
        cp1x = Double.parseDouble(reader.getAttributeValue(null, "cp1x"));
        cp1y = Double.parseDouble(reader.getAttributeValue(null, "cp1y"));
        cp2x = Double.parseDouble(reader.getAttributeValue(null, "cp2x"));
        cp2y = Double.parseDouble(reader.getAttributeValue(null, "cp2y"));
    }

    public boolean isP1Sharp() {
        return cp1x == x && cp1y == y;
    }

    public boolean isP2Sharp() {
        return cp2x == x && cp2y == y;
    }
}