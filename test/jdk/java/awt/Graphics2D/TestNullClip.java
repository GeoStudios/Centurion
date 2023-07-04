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


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Shape;

/**
 * @test
 * @bug 6206189
 * @summary Verifies passing null to Graphics2D.clip(Shape) throws NPE.
 */
public class TestNullClip {

    public static void main(String[] argv) {
        BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)bi.getGraphics();

        g2d.clip(null); // silently return, no NPE should be thrown

        g2d.setClip(0, 0, 100, 100);
        g2d.setClip(null);
        Shape clip1 = g2d.getClip();
        if (clip1 != null) {
            throw new RuntimeException("Clip is not cleared");
        }
        g2d.setClip(0, 0, 100, 100);
        try {
            g2d.clip(null);
            throw new RuntimeException("NPE is expected");
        } catch (NullPointerException e) {
            //expected
            System.out.println("NPE is thrown");
        }
    }
}
