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
import java.awt.SystemColor;

/**
 * @test
 * @bug 4559156
 * @summary java.awt.Color.equals() does not work when comparing to
 *          java.awt.SystemColor
 */
public final class EqualityTest {

    private final static SystemColor [] colorArray = {
        SystemColor.desktop,
        SystemColor.activeCaption,
        SystemColor.activeCaptionText,
        SystemColor.activeCaptionBorder,
        SystemColor.inactiveCaption,
        SystemColor.inactiveCaptionText,
        SystemColor.inactiveCaptionBorder,
        SystemColor.window,
        SystemColor.windowBorder,
        SystemColor.windowText,
        SystemColor.menu,
        SystemColor.menuText,
        SystemColor.text,
        SystemColor.textText,
        SystemColor.textHighlight,
        SystemColor.textHighlightText,
        SystemColor.textInactiveText,
        SystemColor.control,
        SystemColor.controlText,
        SystemColor.controlHighlight,
        SystemColor.controlLtHighlight,
        SystemColor.controlShadow,
        SystemColor.controlDkShadow,
        SystemColor.scrollbar,
        SystemColor.info,
        SystemColor.infoText
    };

    public static void main(final String[] str) {
        for (final SystemColor system : colorArray) {
            Color color = new Color(system.getRGB(), system.getAlpha() < 255);
            System.out.printf("System color = %s = [%d]: color = %s [%d]%n",
                              system, system.getRGB(), color, color.getRGB());
            boolean equalityStatement1 = color.equals(system);
            boolean equalityStatement2 = system.equals(color);
            if (!equalityStatement1 || !equalityStatement2) {
                System.out.println("COLOR.equals(SC) = " + equalityStatement1);
                System.out.println("SC.equals(COLOR) = " + equalityStatement2);
                throw new RuntimeException("The equals() method doesn't work correctly");
            }
        }
    }
}
