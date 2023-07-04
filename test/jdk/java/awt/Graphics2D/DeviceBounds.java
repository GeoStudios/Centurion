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
import java.awt.image.BufferedImage;

/**
 * @test
 * @bug 8072682
 * @summary Graphics.getDeviceConfiguration().getBounds returns wrong width/height
 * @run main DeviceBounds
 */
public class DeviceBounds {
    public static void main(String[] args) {
        // NB: all images have the same type
        BufferedImage[] images = new BufferedImage[] {
                new BufferedImage(200, 200, BufferedImage.TYPE_3BYTE_BGR),
                new BufferedImage(400, 400, BufferedImage.TYPE_3BYTE_BGR),
                new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR)
        };
        int count = 0;
        for (BufferedImage i : images) {
            Graphics2D g = i.createGraphics();
            Rectangle bounds[] = new Rectangle[images.length];
            bounds[count] = g.getDeviceConfiguration().getBounds();
            System.out.println(bounds[count]);

            g.dispose();
            if (bounds[count].width != Integer.MAX_VALUE) {
                throw new RuntimeException("Wrong getBounds");
            }
            count++;
        }
    }
}
