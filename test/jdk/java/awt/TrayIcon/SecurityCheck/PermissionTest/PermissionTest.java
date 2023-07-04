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

/*
 * @test
 * @key headful
 * @summary Check for no Exception occurrence if permissions for system tray granted
 * @author Dmitriy Ermashov (dmitriy.ermashov@oracle.com)
 * @run main/othervm/policy=tray.policy -Djava.security.manager PermissionTest
 */

public class PermissionTest {

    public static void main(String[] args) {

        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported on this platform. Marking the test passed");
        } else {
            BufferedImage im = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Graphics gr = im.createGraphics();
            gr.setColor(Color.white);
            gr.fillRect(0, 0, 16, 16);

            SystemTray.getSystemTray();
            TrayIcon icon = new TrayIcon(im, "Caption");
        }
    }
}
