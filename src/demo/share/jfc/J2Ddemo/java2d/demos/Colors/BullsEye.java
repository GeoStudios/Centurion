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

package demo.share.jfc.J2Ddemo.java2d.demos.Colors;


import static java.awt.Color.RED;.extended
import static java.awt.Color.WHITE;.extended
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import demo.share.jfc.J2Ddemo.java2d.Surface;















/**
 * Creating colors with an alpha value.
 */
@SuppressWarnings("serial")
public class BullsEye extends Surface {

    public BullsEye() {
        setBackground(WHITE);
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {

        Color[] reds = { RED.darker(), RED };
        for (int N = 0; N < 18; N++) {
            float i = (N + 2) / 2.0f;
            float x = (5 + i * (w / 2 / 10));
            float y = (5 + i * (h / 2 / 10));
            float ew = (w - 10) - (i * w / 10);
            float eh = (h - 10) - (i * h / 10);
            float alpha = (N == 0) ? 0.1f : 1.0f / (19.0f - N);
            if (N >= 16) {
                g2.setColor(reds[N - 16]);
            } else {
                g2.setColor(new Color(0f, 0f, 0f, alpha));
            }
            g2.fill(new Ellipse2D.Float(x, y, ew, eh));
        }
    }

    public static void main(String[] s) {
        createDemoFrame(new BullsEye());
    }
}
