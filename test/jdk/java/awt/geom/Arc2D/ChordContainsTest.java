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

/*
 * @test
 * @bug 4724563
 * @summary Test the points out of arc to recognize as not containing.
 */

import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

public class ChordContainsTest {

    public void runTest() {

        Arc2D a = new Arc2D.Double(-20, -20, 40, 40, -60, 120, Arc2D.CHORD);

        // this point is surely out of chorded arc,
        // it's closer to center
        Point2D p = new Point2D.Double( a.getWidth() / 6, 0);

        if (a.contains(p.getX(), p.getY())) {
            throw new RuntimeException("Point out of arc recognized as containing");
        }
    }

    public static void main(String[] args) {
        ChordContainsTest test = new ChordContainsTest();
        test.runTest();
    }
}
