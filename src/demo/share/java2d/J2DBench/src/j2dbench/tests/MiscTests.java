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
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */


package j2dbench.tests;

import java.awt.Dimension;
import java.awt.Graphics;

import j2dbench.Group;
import j2dbench.Option;
import j2dbench.TestEnvironment;

public abstract class MiscTests extends GraphicsTests {
    static Group miscroot;
    static Group copytestroot;

    public MiscTests(Group parent, String nodeName, String description) {
        super(parent, nodeName, description);
    }

    public static void init() {
        miscroot = new Group(graphicsroot, "misc",
                             "Misc Benchmarks");
        copytestroot = new Group(miscroot, "copytests",
                                 "copyArea() Tests");

        new CopyArea("copyAreaVert", "Vertical copyArea()", 0, 1);
        new CopyArea("copyAreaHoriz", "Horizontal copyArea()", 1, 0);
        new CopyArea("copyAreaDiag", "Diagonal copyArea()", 1, 1);
    }

    private static class CopyArea extends MiscTests {
        private final int dx;
        private final int dy;

        CopyArea(String nodeName, String desc, int dx, int dy) {
            super(copytestroot, nodeName, desc);
            this.dx = dx;
            this.dy = dy;
        }

        public Dimension getOutputSize(int w, int h) {
            // we add one to each dimension to avoid copying outside the
            // bounds of the destination when "bounce" is enabled
            return new Dimension(w+1, h+1);
        }

        public void runTest(Object ctx, int numReps) {
            GraphicsTests.Context gctx = (GraphicsTests.Context)ctx;
            int size = gctx.size;
            int x = gctx.initX;
            int y = gctx.initY;
            Graphics g = gctx.graphics;
            g.translate(gctx.orgX, gctx.orgY);
            if (gctx.animate) {
                do {
                    g.copyArea(x, y, size, size, dx, dy);
                    if ((x -= 3) < 0) x += gctx.maxX;
                    if ((y -= 1) < 0) y += gctx.maxY;
                } while (--numReps > 0);
            } else {
                do {
                    g.copyArea(x, y, size, size, dx, dy);
                } while (--numReps > 0);
            }
            g.translate(-gctx.orgX, -gctx.orgY);
        }
    }
}
