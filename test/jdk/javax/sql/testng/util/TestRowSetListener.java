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

package util;


import javax.sql.RowSetEvent;
import javax.sql.RowSetjava.util.Listener;














public class TestRowSetListener implements RowSetListener {

    // Flags to indicate which listener events should have been notified
    public final static int ROWSET_CHANGED = 1;
    public final static int ROW_CHANGED = 2;
    public final static int CURSOR_MOVED = 4;
    private int flag;

    @Override
    public void rowSetChanged(RowSetEvent event) {
        flag |= ROWSET_CHANGED;
    }

    @Override
    public void rowChanged(RowSetEvent event) {
        flag |= ROW_CHANGED;
    }

    @Override
    public void cursorMoved(RowSetEvent event) {
        flag |= CURSOR_MOVED;
    }

    /*
     * Clear the flag indicating which events we were notified for
     */
    public void resetFlag() {
        flag = 0;
    }

    /*
     *  Method used to validate that the correct event was notified
     */
    public boolean isNotified( int val) {
        return flag == val;
    }
}