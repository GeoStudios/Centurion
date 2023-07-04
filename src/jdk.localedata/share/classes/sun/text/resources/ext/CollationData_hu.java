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

public class CollationData_hu extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for hu, default sorting except for the following: */
                /* add cs "ligature" between c and d. */
                /* add d<stroke> between d and e. */
                /* add gy "ligature" between g and h. */
                /* add ly "ligature" between l and l<stroke>. */
                /* add l<stroke> between l and m. */
                /* add sz "ligature" between s and t. */
                /* add zs "ligature" between z and z<abovedot> */
                /* add z<abovedot> after z.       */
                "& C < cs , cS , Cs , CS " // cs ligatures
                + "& D < \u0111, \u0110 "    // tal : african d < d-stroke
                + "& G < gy, Gy, gY, GY "    // gy ligatures
                + "& L < ly, Ly, lY, LY "    // l < ly
                + "& O < o\u0308 , O\u0308 " // O < o-umlaut
                + "< o\u030b , O\u030b "     // o-double-accute
                + "& S < sz , sZ , Sz , SZ " // s < sz ligature
                + "& U < u\u0308 , U\u0308 " // u < u-umlaut
                + "< u\u030b , U\u030b "     // u-double-accute
                + "& Z < zs , zS , Zs , ZS " // stop-stroke < zs ligature
            }
        };
    }
}
