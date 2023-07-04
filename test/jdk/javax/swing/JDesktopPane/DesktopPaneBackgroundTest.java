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
* @bug 7012008
* @summary Verify JDesktopPane Background Color for WLAF
* @requires (os.family == "windows")
* @run main DesktopPaneBackgroundTest
 */
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DesktopPaneBackgroundTest {

    private static Color defaultBackgroudColor;

    public static void main(String[] args) throws Exception {
        defaultBackgroudColor = (Color) Toolkit.getDefaultToolkit()
                .getDesktopProperty("win.mdi.backgroundColor");

        String[] lookAndFeel = new String[]{
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
            "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"};

        for (String laf : lookAndFeel) {
            UIManager.setLookAndFeel(laf);

            SwingUtilities.invokeAndWait(() -> {
                JDesktopPane desktopPane = new JDesktopPane();

                Color background = desktopPane.getBackground();
                if (!background.equals(defaultBackgroudColor)) {
                    throw new RuntimeException("Invalid JDesktopPane "
                            + "Background Color for WLAF");
                }
            });
        }
    }
}
