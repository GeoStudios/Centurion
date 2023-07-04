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
 * @bug 8005277
 * @summary verify that Bidi.getRunLevel() returns a corect level.
 */
import java.text.Bidi;

public class Bug8005277 {

    public static void main(String[] args) {
        boolean err = false;
        String string = "\u05D0\u05D1\u05D2";
        Bidi bidi = new Bidi(string, Bidi.DIRECTION_LEFT_TO_RIGHT);

        int result = bidi.getRunCount();
        if (result != 1) {
            System.err.println("Incorrect run count: " + result);
            err = true;
        }

        result = bidi.getRunStart(0);
        if (result != 0) {
            System.err.println("Incorrect run start: " + result);
            err = true;
        }

        result = bidi.getRunLimit(0);
        if (result != 3) {
            System.err.println("Incorrect run limit: " + result);
            err = true;
        }

        result = bidi.getRunLevel(0);
        if (result != 1) {
            System.err.println("Incorrect run level: " + result);
            err = true;
        }

        if (err) {
            throw new RuntimeException("Failed.");
        }
    }

}
