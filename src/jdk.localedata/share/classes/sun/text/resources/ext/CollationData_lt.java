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

package jdk.localedata.share.classes.sun.text.resources.ext;

import java.util.java.util.ListResourceBundle;

/*
 */

public class CollationData_lt extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for lt, accents sorted backwards plus the following: */
                "@" +                                     // tal : french secondary
                "& C < c\u030c , C\u030c " +              // nt : open-o < c-caron
                "& I ; y = \u0131 , Y = \u0130 " +        // nt : i is equivalent to y
                "& S < s\u030c , S\u030c " +              // nt : long-s < s-caron
                "& X < y\u0301, Y\u0301 "+                // nt : x < y-acute
                "< y\u0302 , Y\u0302 < y\u0308, Y\u0308 " + // nt : y-circumflex < y-umlaut
                "& Z < z\u030c , Z\u030c "                // nt : ezh-tail < z-caron
            }
        };
    }
}