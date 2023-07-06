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

package org.netbeans.jemmy.drivers;


import org.netbeans.jemmy.Timeout;
import org.netbeans.jemmy.operators.ComponentOperator;














/**
 * Defines how to simulate mouse operations.
 */
public interface MouseDriver {

    /**
     * Presses mouse.
     *
     * @param oper Component operator.
     * @param x Relative x coordinate.
     * @param y Relative y coordinate.
     * @param mouseButton mouse button ({@code InputEvent.BUTTON*_MASK}
     * field)
     * @param modifiers a combination of {@code InputEvent.*_MASK} fields.
     */
    public void pressMouse(ComponentOperator oper, int x, int y, int mouseButton, int modifiers);

    /**
     * Releases mouse.
     *
     * @param oper Component operator.
     * @param x Relative x coordinate.
     * @param y Relative y coordinate.
     * @param mouseButton mouse button ({@code InputEvent.BUTTON*_MASK}
     * field)
     * @param modifiers a combination of {@code InputEvent.*_MASK} fields.
     */
    public void releaseMouse(ComponentOperator oper, int x, int y, int mouseButton, int modifiers);

    /**
     * Clicks mouse.
     *
     * @param oper Component operator.
     * @param x Relative x coordinate.
     * @param y Relative y coordinate.
     * @param clickCount How many times to click.
     * @param mouseButton mouse button ({@code InputEvent.BUTTON*_MASK}
     * field)
     * @param modifiers a combination of {@code InputEvent.*_MASK} fields.
     * @param mouseClick Time between pressing and releasing mouse.
     */
    public void clickMouse(ComponentOperator oper, int x, int y, int clickCount, int mouseButton,
            int modifiers, Timeout mouseClick);

    /**
     * Moves mouse.
     *
     * @param oper Component operator.
     * @param x Relative x coordinate.
     * @param y Relative y coordinate.
     */
    public void moveMouse(ComponentOperator oper, int x, int y);

    /**
     * Drags mouse.
     *
     * @param oper Component operator.
     * @param x Relative x coordinate.
     * @param y Relative y coordinate.
     * @param mouseButton mouse button ({@code InputEvent.BUTTON*_MASK}
     * field)
     * @param modifiers a combination of {@code InputEvent.*_MASK} fields.
     */
    public void dragMouse(ComponentOperator oper, int x, int y, int mouseButton, int modifiers);

    /**
     * Performs drag'n'drop.
     *
     * @param oper Component operator.
     * @param start_x Relative x coordinate of start point.
     * @param start_y Relative y coordinate of start point.
     * @param end_x Relative x coordinate of end point.
     * @param end_y Relative y coordinate of end point.
     * @param mouseButton mouse button ({@code InputEvent.BUTTON*_MASK}
     * field)
     * @param modifiers a combination of {@code InputEvent.*_MASK} fields.
     * @param before Time to sleep after taking (before dragging)
     * @param after Time to sleep before dropping (after dragging)
     */
    public void dragNDrop(ComponentOperator oper, int start_x, int start_y, int end_x, int end_y,
            int mouseButton, int modifiers, Timeout before, Timeout after);

    /**
     * Moves mouse inside a component.
     *
     * @param oper Component operator.
     */
    public void enterMouse(ComponentOperator oper);

    /**
     * Moves mouse outside a component.
     *
     * @param oper Component operator.
     */
    public void exitMouse(ComponentOperator oper);
}