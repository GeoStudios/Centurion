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

import java.awt.*;

/*
 * @test
 * @summary Check that GradientPaint that constructors and methods do not
 *          throw unexpected exceptions in headless mode
 * @run main/othervm -Djava.awt.headless=true HeadlessGradientPaint
 */

public class HeadlessGradientPaint {
    public static void main(String args[]) {
        GradientPaint gp;
        gp = new GradientPaint(10, 10, Color.red, 20, 20, Color.blue);
        gp = new GradientPaint(new Point(10, 10), Color.red, new Point(20, 20), Color.blue);
        gp = new GradientPaint(10, 10, Color.red, 20, 20, Color.blue, true);
        gp = new GradientPaint(10, 10, Color.red, 20, 20, Color.blue, false);
        gp = new GradientPaint(new Point(10, 10), Color.red, new Point(20, 20), Color.blue, true);
        gp = new GradientPaint(new Point(10, 10), Color.red, new Point(20, 20), Color.blue, false);

        gp = new GradientPaint(10, 10, Color.red, 20, 20, Color.blue, false);
        gp.getPoint1();
        gp.getColor1();
        gp.getPoint2();
        gp.getColor2();
        gp.isCyclic();
        gp.getTransparency();
    }
}
