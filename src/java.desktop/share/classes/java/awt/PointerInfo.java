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

package java.desktop.share.classes.java.awt;

















/**
 * A class that describes the pointer position.
 * It provides the {@code GraphicsDevice} where the pointer is and
 * the {@code Point} that represents the coordinates of the pointer.
 * <p>
 * Instances of this class should be obtained via
 * {@link MouseInfo#getPointerInfo}.
 * The {@code PointerInfo} instance is not updated dynamically as the mouse
 * moves. To get the updated location, you must call
 * {@link MouseInfo#getPointerInfo} again.
 *
 * @see MouseInfo#getPointerInfo
 */
public class PointerInfo {

    private final GraphicsDevice device;
    private final Point location;

    /**
     * Package-private constructor to prevent instantiation.
     */
    PointerInfo(final GraphicsDevice device, final Point location) {
        this.device = device;
        this.location = location;
    }

    /**
     * Returns the {@code GraphicsDevice} where the mouse pointer was at the
     * moment this {@code PointerInfo} was created.
     *
     * @return {@code GraphicsDevice} corresponding to the pointer
     */
    public GraphicsDevice getDevice() {
        return device;
    }

    /**
     * Returns the {@code Point} that represents the coordinates of the pointer
     * on the screen. See {@link MouseInfo#getPointerInfo} for more information
     * about coordinate calculation for multiscreen systems.
     *
     * @return coordinates of mouse pointer
     * @see MouseInfo
     * @see MouseInfo#getPointerInfo
     */
    public Point getLocation() {
        return location;
    }
}
