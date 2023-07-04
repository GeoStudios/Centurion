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

public class FormatData_hu extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "janu\u00e1r", // january
                    "febru\u00e1r", // february
                    "m\u00e1rcius", // march
                    "\u00e1prilis", // april
                    "m\u00e1jus", // may
                    "j\u00fanius", // june
                    "j\u00falius", // july
                    "augusztus", // august
                    "szeptember", // september
                    "okt\u00f3ber", // october
                    "november", // november
                    "december", // december
                    "" // month 13 if applicable
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "janu\u00e1r",
                    "febru\u00e1r",
                    "m\u00e1rcius",
                    "\u00e1prilis",
                    "m\u00e1jus",
                    "j\u00fanius",
                    "j\u00falius",
                    "augusztus",
                    "szeptember",
                    "okt\u00f3ber",
                    "november",
                    "december",
                    "",
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jan.", // abb january
                    "febr.", // abb february
                    "m\u00e1rc.", // abb march
                    "\u00e1pr.", // abb april
                    "m\u00e1j.", // abb may
                    "j\u00fan.", // abb june
                    "j\u00fal.", // abb july
                    "aug.", // abb august
                    "szept.", // abb september
                    "okt.", // abb october
                    "nov.", // abb november
                    "dec.", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "jan.",
                    "febr.",
                    "m\u00e1rc.",
                    "\u00e1pr.",
                    "m\u00e1j.",
                    "j\u00fan.",
                    "j\u00fal.",
                    "aug.",
                    "szept.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "J",
                    "F",
                    "M",
                    "\u00c1",
                    "M",
                    "J",
                    "J",
                    "A",
                    "Sz",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "J",
                    "F",
                    "M",
                    "\u00c1",
                    "M",
                    "J",
                    "J",
                    "A",
                    "Sz",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "vas\u00e1rnap", // Sunday
                    "h\u00e9tf\u0151", // Monday
                    "kedd", // Tuesday
                    "szerda", // Wednesday
                    "cs\u00fct\u00f6rt\u00f6k", // Thursday
                    "p\u00e9ntek", // Friday
                    "szombat" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "vas\u00e1rnap",
                    "h\u00e9tf\u0151",
                    "kedd",
                    "szerda",
                    "cs\u00fct\u00f6rt\u00f6k",
                    "p\u00e9ntek",
                    "szombat",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "V", // abb Sunday
                    "H", // abb Monday
                    "K", // abb Tuesday
                    "Sze", // abb Wednesday
                    "Cs", // abb Thursday
                    "P", // abb Friday
                    "Szo" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "V",
                    "H",
                    "K",
                    "Sze",
                    "Cs",
                    "P",
                    "Szo",
                }
            },
            { "DayNarrows",
                new String[] {
                    "V",
                    "H",
                    "K",
                    "Sz",
                    "Cs",
                    "P",
                    "Sz",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "V",
                    "H",
                    "K",
                    "Sz",
                    "Cs",
                    "P",
                    "Sz",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "DE", // am marker
                    "DU" // pm marker
                }
            },
            { "Eras",
                new String[] { // era strings
                    "i.e.",
                    "i.u."
                }
            },
            { "short.Eras",
                new String[] {
                    "i. e.",
                    "i. sz.",
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal separator
                    "\u00a0", // group (thousands) separator
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
                    "H:mm:ss z", // full time pattern
                    "H:mm:ss z", // long time pattern
                    "H:mm:ss", // medium time pattern
                    "H:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "yyyy. MMMM d.", // full date pattern
                    "yyyy. MMMM d.", // long date pattern
                    "yyyy.MM.dd.", // medium date pattern
                    "yyyy.MM.dd.", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GanjkHmsSEDFwWxhKzZ" },
            { "buddhist.Eras",
                new String[] {
                    "BC",
                    "BK",
                }
            },
            { "buddhist.short.Eras",
                new String[] {
                    "BC",
                    "BK",
                }
            },
        };
    }
}
