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

/**
 * @test
 * @key headful
 * @bug 7092283
 * @author Alexander Scherbatiy
 * @summary Property Window.locationByPlatform is not cleared by calling
 *          setVisible(false)
 * @run main LocationByPlatformTest
 */
import java.awt.Window;

public class LocationByPlatformTest {

    public static void main(String[] args) {

        Window window = new Window(null);
        window.setSize(100, 100);
        window.setLocationByPlatform(true);

        if (!window.isLocationByPlatform()) {
            throw new RuntimeException("Location by platform is not set");
        }

        window.setLocation(10, 10);

        if (window.isLocationByPlatform()) {
            throw new RuntimeException("Location by platform is not cleared");
        }

        window.setLocationByPlatform(true);
        window.setBounds(20, 20, 50, 50);

        if (window.isLocationByPlatform()) {
            throw new RuntimeException("Location by platform is not cleared");
        }

        window.setLocationByPlatform(true);
        window.setVisible(false);

        if (window.isLocationByPlatform()) {
            throw new RuntimeException("Location by platform is not cleared");
        }

        window.setLocationByPlatform(true);
        window.setVisible(true);

        if (window.isLocationByPlatform()) {
            throw new RuntimeException("Location by platform is not cleared");
        }

        window.dispose();
    }
}
