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

package org.netbeans.jemmy.drivers.scrolling;
















/**
 * Specifies scrolling criteria.
 *
 * @author Alexandre Iline(alexandre.iline@oracle.com)
 */
public interface ScrollAdjuster {

    /**
     * Increase scroll direction.
     */
    public static final int INCREASE_SCROLL_DIRECTION = 1;

    /**
     * Decrease scroll direction.
     */
    public static final int DECREASE_SCROLL_DIRECTION = -1;

    /**
     * Specifies that necessary value has been reached..
     */
    public static final int DO_NOT_TOUCH_SCROLL_DIRECTION = 0;

    /**
     * Returns scroll direction to reach necessary scroller value.
     *
     * @return one of the values: INCREASE_SCROLL_DIRECTION,
     * DECREASE_SCROLL_DIRECTION or DO_NOT_TOUCH_SCROLL_DIRECTION.
     */
    public int getScrollDirection();

    /**
     * Returns scrolling orientation.
     *
     * @return one of the values: Adjustable.HORIZONTAL or Adjustable.VERTICAL.
     */
    public int getScrollOrientation();

    /**
     * Returns a printable scrolling description.
     *
     * @return a description.
     */
    public String getDescription();
}
