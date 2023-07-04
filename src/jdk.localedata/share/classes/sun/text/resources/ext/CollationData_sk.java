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

public class CollationData_sk extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for sk, default sorting except for the following: */

                /* add d<stroke> between d and e. */
                /* add ch "ligature" between h and i */
                /* add l<stroke> between l and m. */
                /* add z<abovedot> after z.       */
                "& \u0361 ; \u0308 = \u030d "
                + "& A < a\u0308 , A\u0308 " // A < a-umlaut
                + "& C < c\u030c , C\u030c " // C < c-caron
                + "& D < \u0111, \u0110 "    // D < d-stroke
                + "& H < ch , cH , Ch , CH " // H < ch ligature
                + "& L < \u0142 , \u0141 "   // L < l-stroke
                + "& O < o\u0302 , O\u0302 " // oe < o-circumflex
                + "& R < r\u030c , R\u030c " // R < r-caron
                + "& S < s\u030c , S\u030c " // S < s-caron
                + "& Z < z\u030c , Z\u030c " // Z < z-caron
                + "< z\u0307 , Z\u0307 "     // z-dot-above
            }
        };
    }
}
