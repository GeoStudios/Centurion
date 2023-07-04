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

import java.util.Arrays;
import java.util.Collection;

class T8071432 {

    static class Point {

        private double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double distance(Point p) {
            return Math.sqrt((this.x - p.x) * (this.x - p.x) +
                             (this.y - p.y) * (this.y - p.y));
        }

        public double distance() {
            return Math.sqrt(this.x * this.x + this.y * this.y);
        }

        public String toString() {
            return "(" + x + ":" + y + ")";
        }
    }

    public static void main(String[] args) {
        Collection<Point> c = Arrays.asList(new Point(1.0, 0.1));
        System.out.println("------- 1 ---------------");
        System.out.println(c.stream().reduce(0.0,
                                            (s, p) -> s += p.distance(), (d1, d2) -> 0));
    }
}
