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

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_ca extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "de gener",
                    "de febrer",
                    "de mar\u00e7",
                    "d\u2019abril",
                    "de maig",
                    "de juny",
                    "de juliol",
                    "d\u2019agost",
                    "de setembre",
                    "d\u2019octubre",
                    "de novembre",
                    "de desembre",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "G",
                    "F",
                    "M",
                    "A",
                    "M",
                    "J",
                    "G",
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "gener", // january
                    "febrer", // february
                    "mar\u00e7", // march
                    "abril", // april
                    "maig", // may
                    "juny", // june
                    "juliol", // july
                    "agost", // august
                    "setembre", // september
                    "octubre", // october
                    "novembre", // november
                    "desembre", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "de gen.",
                    "de febr.",
                    "de mar\u00e7",
                    "d\u2019abr.",
                    "de maig",
                    "de juny",
                    "de jul.",
                    "d\u2019ag.",
                    "de set.",
                    "d\u2019oct.",
                    "de nov.",
                    "de des.",
                    "",
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "gen.", // abb january
                    "feb.", // abb february
                    "mar\u00e7", // abb march
                    "abr.", // abb april
                    "maig", // abb may
                    "juny", // abb june
                    "jul.", // abb july
                    "ag.", // abb august
                    "set.", // abb september
                    "oct.", // abb october
                    "nov.", // abb november
                    "des.", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "g",
                    "f",
                    "m",
                    "a",
                    "m",
                    "j",
                    "j",
                    "a",
                    "s",
                    "o",
                    "n",
                    "d",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "diumenge", // Sunday
                    "dilluns", // Monday
                    "dimarts", // Tuesday
                    "dimecres", // Wednesday
                    "dijous", // Thursday
                    "divendres", // Friday
                    "dissabte" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "Diumenge",
                    "Dilluns",
                    "Dimarts",
                    "Dimecres",
                    "Dijous",
                    "Divendres",
                    "Dissabte",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "dg.", // abb Sunday
                    "dl.", // abb Monday
                    "dt.", // abb Tuesday
                    "dc.", // abb Wednesday
                    "dj.", // abb Thursday
                    "dv.", // abb Friday
                    "ds." // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "dg",
                    "dl",
                    "dt",
                    "dc",
                    "dj",
                    "dv",
                    "ds",
                }
            },
            { "DayNarrows",
                new String[] {
                    "G",
                    "L",  // Note: contributed item in CDLR
                    "T",
                    "C",
                    "J",
                    "V",
                    "S",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "g",
                    "l",
                    "t",
                    "c",
                    "j",
                    "v",
                    "s",
                }
            },
            { "short.Eras",
                new String[] {
                    "aC",
                    "dC",
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
                    "EEEE, d' / 'MMMM' / 'yyyy", // full date pattern
                    "d' / 'MMMM' / 'yyyy", // long date pattern
                    "dd/MM/yyyy", // medium date pattern
                    "dd/MM/yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GuMtkHmsSEDFwWahKzZ" },
        };
    }
}
