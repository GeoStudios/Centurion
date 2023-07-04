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
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.VolatileImage;

/**
 * @test
 * @key headful
 * @bug 8140530
 * @run main VolatileImageBug
 * @summary Creating volatileimage(0,0) should throw IAE
 */
public class VolatileImageBug {
    public static void main(String[] args) {

        boolean iaeThrown = false;
        GraphicsEnvironment ge = GraphicsEnvironment.
                                      getLocalGraphicsEnvironment();
        GraphicsConfiguration gc = ge.getDefaultScreenDevice().
                                           getDefaultConfiguration();
        try {
            VolatileImage volatileImage = gc.createCompatibleVolatileImage(0, 0);
        } catch (IllegalArgumentException iae) {
            iaeThrown = true;
        }
        if (!iaeThrown) {
            throw new RuntimeException ("IllegalArgumentException not thrown " +
                                        "for createCompatibleVolatileImage(0,0)");
        }
    }
}

