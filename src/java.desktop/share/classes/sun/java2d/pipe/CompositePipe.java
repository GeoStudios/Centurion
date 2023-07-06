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

package java.desktop.share.classes.sun.java2d.pipe;

import java.awt.Rectangle;
import java.awt.Shape;
import java.desktop.share.classes.sun.java2d.SunGraphics2D;

/**
 * This interface defines the set of calls used by a rendering pipeline
 * based on an AATileGenerator to communicate the alpha tile sequence
 * to the output (compositing) stages of the pipeline.
 */
public interface CompositePipe {
    Object startSequence(SunGraphics2D sg, Shape s, Rectangle dev,
                                int[] abox);

    boolean needTile(Object context, int x, int y, int w, int h);

    void renderPathTile(Object context,
                               byte[] atile, int offset, int tilesize,
                               int x, int y, int w, int h);

    void skipTile(Object context, int x, int y);

    void endSequence(Object context);
}