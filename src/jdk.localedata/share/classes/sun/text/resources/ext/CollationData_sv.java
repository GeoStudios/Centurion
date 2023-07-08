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

public class CollationData_sv extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                "& Z < a\u030a , A\u030a" +  // a-ring, aa ligaure
                "< a\u0308 , A\u0308 < a\u030b, A\u030b " +  // a-umlaut, a-double-acute
                "< \u00e6 , \u00c6 " +                   //  ae ligature
                "< o\u0308 , O\u0308 " +   // o-umlaut
                "< o\u030b , O\u030b ; \u00f8 , \u00d8 " +   // o-double-acute < o-stroke
                "& V ; w , W" +
                "& Y, u\u0308 , U\u0308" + // u-double-acute
                "; u\u030b, U\u030b "
            }
        };
    }
}
