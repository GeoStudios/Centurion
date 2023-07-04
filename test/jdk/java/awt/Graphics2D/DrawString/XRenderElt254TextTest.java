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
import java.awt.MultipleGradientPaint.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

/**
 * @test
 * @key headful
 * @bug 8028722
 * @summary tests whether drawString with 254 characters causes the xrender
 *          pipeline to hang.
 * @author ceisserer
 */
public class XRenderElt254TextTest extends Frame implements Runnable {
    public volatile boolean success = false;

    public void run() {
        Image dstImg = getGraphicsConfiguration().createCompatibleVolatileImage(400, 400);
        Graphics2D g = (Graphics2D) dstImg.getGraphics();

        StringBuilder strBuilder = new StringBuilder(254);
        for (int c = 0; c < 254; c++) {
          strBuilder.append('a');
        }

        for (int i = 0; i < 100; i++) {
            g.drawString(strBuilder.toString(), 20, 20);
            Toolkit.getDefaultToolkit().sync();
        }
        success = true;
    }

    public static void main(String[] args) throws Exception {
        XRenderElt254TextTest test = new XRenderElt254TextTest();
        new Thread(test).start();

        for (int i = 0; i < 30; i++) {
            Thread.sleep(1000);

            if (test.success) {
            return; // Test finished successful
            }
        }

        throw new RuntimeException("Test Failed");
    }
}
