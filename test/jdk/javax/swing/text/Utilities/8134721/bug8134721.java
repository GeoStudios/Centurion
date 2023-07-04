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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;

/**
 * @test
 * @bug 8134721
 * @author Alexandr Scherbatiy
 * @summary NPE in SwingUtilities2.drawChars after JDK-6302464
 */
public class bug8134721 {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(bug8134721::testNPE);
    }

    private static void testNPE() {

        Graphics g = null;
        try {
            String test = "\ttest\ttest2";
            BufferedImage buffImage = new BufferedImage(
                    100, 100, BufferedImage.TYPE_INT_RGB);
            g = buffImage.createGraphics();
            Segment segment = new Segment(test.toCharArray(), 0, test.length());
            Utilities.drawTabbedText(segment, 0, 0, g, null, 0);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }
}