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




public class CollationData_lv extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for lv, accents sorted backwards plus the following: */

                "@" /* sort accents bkwd */
                + "& C < c\u030c , C\u030c "  // C < c-caron
                + "& G < g\u0327 , G\u0327 "  // G < g-cedilla
                + "& I < y, Y"                // tal : i < y
                + "& K < k\u0327 , K\u0327 "  // K < k-cedilla
                + "& L < l\u0327 , L\u0327 "  // L < l-cedilla
                + "& N < n\u0327 , N\u0327 "  // N < n-cedilla
                + "& S < s\u030c , S\u030c "  // S < s-caron
                + "& Z < z\u030c , Z\u030c "  // Z < z-caron
            }
        };
    }
}
