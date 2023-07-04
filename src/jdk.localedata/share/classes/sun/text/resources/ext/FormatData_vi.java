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

package sun.text.resources.ext;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_vi extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "th\u00e1ng m\u1ed9t", // january
                    "th\u00e1ng hai", // february
                    "th\u00e1ng ba", // march
                    "th\u00e1ng t\u01b0", // april
                    "th\u00e1ng n\u0103m", // may
                    "th\u00e1ng s\u00e1u", // june
                    "th\u00e1ng b\u1ea3y", // july
                    "th\u00e1ng t\u00e1m", // august
                    "th\u00e1ng ch\u00edn", // september
                    "th\u00e1ng m\u01b0\u1eddi", // october
                    "th\u00e1ng m\u01b0\u1eddi m\u1ed9t", // november
                    "th\u00e1ng m\u01b0\u1eddi hai", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "thg 1", // abb january
                    "thg 2", // abb february
                    "thg 3", // abb march
                    "thg 4", // abb april
                    "thg 5", // abb may
                    "thg 6", // abb june
                    "thg 7", // abb july
                    "thg 8", // abb august
                    "thg 9", // abb september
                    "thg 10", // abb october
                    "thg 11", // abb november
                    "thg 12", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "1",
                    "2",
                    "3",
                    "4",
                    "5",
                    "6",
                    "7",
                    "8",
                    "9",
                    "10",
                    "11",
                    "12",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "Ch\u1ee7 nh\u1eadt", // Sunday
                    "Th\u1ee9 hai", // Monday
                    "Th\u1ee9 ba",  // Tuesday
                    "Th\u1ee9 t\u01b0", // Wednesday
                    "Th\u1ee9 n\u0103m", // Thursday
                    "Th\u1ee9 s\u00e1u", // Friday
                    "Th\u1ee9 b\u1ea3y" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "CN", // abb Sunday
                    "Th 2", // abb Monday
                    "Th 3", // abb Tuesday
                    "Th 4", // abb Wednesday
                    "Th 5", // abb Thursday
                    "Th 6", // abb Friday
                    "Th 7" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "CN",
                    "T2",
                    "T3",
                    "T4",
                    "T5",
                    "T6",
                    "T7",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "CN",
                    "T2",
                    "T3",
                    "T4",
                    "T5",
                    "T6",
                    "T7",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "SA", // am marker
                    "CH" // pm marker
                }
            },
            { "Eras",
                new String[] { // era strings
                    "tr. CN",
                    "sau CN"
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal separator
                    ".", // group (thousands) separator
                    ";", // list separator
                    "%", // percent sign
                    "0", // native 0 digit
                    "#", // pattern digit
                    "-", // minus sign
                    "E", // exponential
                    "\u2030", // per mille
                    "\u221e", // infinity
                    "\ufffd" // NaN
                }
            },
            { "TimePatterns",
                new String[] {
                    "HH:mm:ss z", // full time pattern
                    "HH:mm:ss z", // long time pattern
                    "HH:mm:ss", // medium time pattern
                    "HH:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, 'ng\u00E0y' dd MMMM 'n\u0103m' yyyy", // full date pattern
                    "'Ng\u00E0y' dd 'th\u00E1ng' M 'n\u0103m' yyyy", // long date pattern
                    "dd-MM-yyyy", // medium date pattern
                    "dd/MM/yyyy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{0} {1}" // date-time pattern
                }
            },
        };
    }
}
