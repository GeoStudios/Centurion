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
 * @bug 8210058
 * @summary Algorithmic Italic font leans opposite angle in Printing
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class RotatedItalicsTest {
    public static void main(String[] args) throws Exception {
        File fontFile = new File(System.getProperty("test.src", "."), "A.ttf");
        Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        Font font = baseFont.deriveFont(Font.ITALIC, 120);

        BufferedImage image = new BufferedImage(100, 100,
                                                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();
        g.rotate(Math.PI / 2);
        g.setFont(font);
        g.drawString("A", 10, -10);
        g.dispose();

        if (image.getRGB(50, 76) != Color.white.getRGB()) {
            throw new RuntimeException("Wrong glyph rendering");
        }
    }
}
