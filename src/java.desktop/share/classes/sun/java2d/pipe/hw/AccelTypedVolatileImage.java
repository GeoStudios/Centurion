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

package java.desktop.share.classes.sun.java2d.pipe.hw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.desktop.share.classes.sun.awt.image.SunVolatileImage;
import static java.desktop.share.classes.sun.java2d.pipe.hw.AccelSurface.*;.extended

/**
 * This is an image with forced type of the accelerated surface.
 */
public class AccelTypedVolatileImage extends SunVolatileImage {

    /**
     * Creates a volatile image with specified type of accelerated surface.
     *
     * @param graphicsConfig a GraphicsConfiguration for which this image should
     *        be created.
     * @param width width
     * @param height width
     * @param transparency type of {@link java.awt.Transparency transparency}
     *        requested for the image
     * @param accType type of the desired accelerated surface as defined in
     *        AccelSurface interface
     * @see sun.java2d.pipe.hw.AccelSurface
     */
    public AccelTypedVolatileImage(GraphicsConfiguration graphicsConfig,
                                   int width, int height, int transparency,
                                   int accType)
    {
        super(null, graphicsConfig, width, height, null, transparency,
              null, accType);
    }

    /**
     * {@inheritDoc}
     *
     * This method will throw {@code UnsupportedOperationException} if it this
     * image's destination surface can not be rendered to.
     */
    @Override
    public Graphics2D createGraphics() {
        if (getForcedAccelSurfaceType() == TEXTURE) {
            throw new UnsupportedOperationException("Can't render " +
                                                    "to a non-RT Texture");
        }
        return super.createGraphics();
    }
}
