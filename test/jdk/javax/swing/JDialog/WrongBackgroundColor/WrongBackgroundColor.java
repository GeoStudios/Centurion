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

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * @test
 * @key headful
 * @bug 8033786
 * @summary JDialog should update background color of the native peer.
 * @author Sergey Bylokhov
 */
public final class WrongBackgroundColor {

    public static void main(final String[] args)
            throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            UIDefaults ui = UIManager.getDefaults();
            ui.put("control", new ColorUIResource(54, 54, 54));
            final JDialog dialog = new JDialog();
            final JFrame frame = new JFrame();
            frame.pack();
            dialog.pack();
            final Color dialogBackground = dialog.getBackground();
            final Color frameBackground = frame.getBackground();
            frame.dispose();
            dialog.dispose();
            if (!dialogBackground.equals(frameBackground)) {
                System.err.println("Expected:" + frameBackground);
                System.err.println("Actual:" + dialogBackground);
                throw new RuntimeException("Wrong background color");
            }
        });
    }
}
