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

/* @test
 * @bug      4108737
 * @summary  java.util.Date doesn't fail if current TimeZone is changed
 */

import java.util.TimeZone;
import java.util.Date;

public class TZ {

    public static void main(String args[]) {
        TimeZone tz = TimeZone.getDefault();
        try {
            testMain();
        } finally {
            TimeZone.setDefault(tz);
        }
    }

    static void testMain() {
        String expectedResult = "Sat Feb 01 00:00:00 PST 1997";

        // load the java.util.Date class in the GMT timezone
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        new Date(); // load the class (to run static initializers)

        // use the class in different timezone
        TimeZone.setDefault(TimeZone.getTimeZone("PST"));
        @SuppressWarnings("deprecation")
        Date date = new Date(97, 1, 1);
        if (!date.toString().equals(expectedResult)) {
            throw new RuntimeException("Regression bug id #4108737 - Date fails if default time zone changed");
        }
    }
}
