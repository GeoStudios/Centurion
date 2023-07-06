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

package java.desktop.share.classes.javax.swing.plaf;

import java.awt.Color;
import java.beans.ConstructorProperties;

/**
 * A subclass of Color that implements UIResource.  UI
 * classes that create colors should use this class.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @see javax.swing.plaf.UIResource
 *
 */
@SuppressWarnings("serial") // Same-version serialization only
public class ColorUIResource extends Color implements UIResource
{
    /**
     * Constructs a {@code ColorUIResource}.
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     */
    @ConstructorProperties({"red", "green", "blue"})
    public ColorUIResource(int r, int g, int b) {
        super(r, g, b);
    }

    /**
     * Constructs a {@code ColorUIResource}.
     * @param rgb the combined RGB components
     */
    public ColorUIResource(int rgb) {
        super(rgb);
    }

    /**
     * Constructs a {@code ColorUIResource}.
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     */
    public ColorUIResource(float r, float g, float b) {
        super(r, g, b);
    }

    /**
     * Constructs a {@code ColorUIResource}.
     * @param c the color
     */
    public ColorUIResource(Color c) {
        super(c.getRGB(), (c.getRGB() & 0xFF000000) != 0xFF000000);
    }
}