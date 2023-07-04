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

import java.awt.image.IndexColorModel;

/*
 * @test
 * @summary Check that IndexColorModel constructor and methods
 *          do not throw unexpected exceptions in headless mode
 * @run main/othervm -Djava.awt.headless=true HeadlessIndexColorModel
 */

public class HeadlessIndexColorModel {
    public static void main(String args[]) {
        IndexColorModel cm =
                new IndexColorModel(8, 1, new byte[]{(byte) 128}, new byte[]{(byte) 128}, new byte[]{(byte) 128});
        cm.getTransparency();
        cm.getComponentSize();
        cm.isAlphaPremultiplied();
        cm.hasAlpha();
        cm.isAlphaPremultiplied();
        cm.getTransferType();
        cm.getPixelSize();
        cm.getComponentSize();
        cm.getNumComponents();
        cm.getNumColorComponents();
        cm.getRed(20);
        cm.getGreen(20);
        cm.getBlue(20);
        cm.getAlpha(20);
        cm.getRGB(20);
        cm.isAlphaPremultiplied();
    }
}
