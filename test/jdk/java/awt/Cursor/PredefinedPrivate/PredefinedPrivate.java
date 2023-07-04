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
 * @test
 * @bug 6656586
 * @summary Test that Cursor.predefined array is not used in
Cursor.getPredefinedCursor() method
 * @author Artem Ananiev
 * @run main PredefinedPrivate
 */

import java.awt.*;

public class PredefinedPrivate {
    public static void main(String args[]) {
        new MyCursor();
        if (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR) instanceof MyCursor) {
            throw new RuntimeException("Test FAILED: getPredefinedCursor() returned modified cursor");
        }
    }
}

class MyCursor extends Cursor {
    public MyCursor() {
        super(DEFAULT_CURSOR);
        Cursor.predefined[DEFAULT_CURSOR] = this;
    }
}
