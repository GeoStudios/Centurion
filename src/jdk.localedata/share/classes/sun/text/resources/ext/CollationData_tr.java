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
 */

package sun.text.resources.ext;

import java.util.ListResourceBundle;

public class CollationData_tr extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                "& A < a\u0308 , A\u0308 "   // a-umlaut sorts between a and b
                + "& C < c\u0327 , C\u0327 " // c-cedilla sorts between c and d
                + "& G < g\u0306 , G\u0306 " // g-breve sorts between g and h
                + "& H < \u0131 , I "  // dotless i, I
                + "& I < i , \u0130 "  // dotted i, I
                + "< \u0132 , \u0133 "       // ij ligature sorts between i and j
                + "& O < o\u0308 , O\u0308 " // o-umlaut sorts between o and p
                + "& S < s\u0327 , S\u0327 " // s-cedilla sorts between s and t
                + "& U < u\u0308 , U\u0308 " // u-umlaut sorts between u and v
            }
        };
    }
}
