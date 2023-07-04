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

package sun.java2d.pipe.hw;

import java.awt.image.VolatileImage;

/**
 * Implementors of this interface provida a way to create a
 * {@code VolatileImage} whose destination surface is an
 * {@link AccelSurface} of specified type.
 *
 * @see AccelSurface
 */
public interface AccelGraphicsConfig extends BufferedContextProvider {
    /**
     * Returns a VolatileImage with specified width, height, transparency
     * and guaranteed accelerated surface type. If such image can not be created
     * (out of vram error, specific surface type is not supported) null
     * is returned.
     *
     * Note: if {@link AccelSurface#TEXTURE} type is requested, rendering
     * to the image will be denied by throwing
     * {@code UnsupportedOperationException }
     * from {@link java.awt.image.VolatileImage#getGraphics} and
     * {@link java.awt.image.VolatileImage#createGraphics}
     *
     * @param width the width of the returned {@code VolatileImage}
     * @param height the height of the returned {@code VolatileImage}
     * @param transparency the specified transparency mode
     * @param type requested accelerated surface type as specified by constants
     * in AccelSurface interface
     * @return a {@code VolatileImage} backed up by requested accelerated
     * surface type or null
     * @throws IllegalArgumentException if the transparency is not a valid value
     * @see AccelSurface#TEXTURE
     * @see AccelSurface#RT_PLAIN
     * @see AccelSurface#RT_TEXTURE
     */
    VolatileImage createCompatibleVolatileImage(int width, int height,
                                                       int transparency,
                                                       int type);
    /**
     * Returns object representing capabilities of the context associated
     * with this {@code AccelGraphicsConfig}.
     *
     * @return ContextCapabilities object representing caps
     * @see ContextCapabilities
     */
    ContextCapabilities getContextCapabilities();
}
